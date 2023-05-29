package controller;

import java.awt.*;
import java.io.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.List;

import listener.GameListener;
import model.*;
import view.CellComponent;
import view.ChessComponent;
import view.ChessGameFrame;
import view.ChessboardComponent;

import javax.imageio.IIOException;
import javax.swing.*;

/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and onPlayerClickChessPiece()]
 */
public class GameController implements GameListener {


    private Chessboard model;
    private ChessboardComponent view;
    private PlayerColor currentPlayer;

    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;
    public static JLabel turnlabel= new JLabel();
    public int turn;

    public GameController(ChessboardComponent view, Chessboard model) {
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;
        this.turn=0;

        view.registerController(this);
        initialize();
        view.initiateChessComponent(model);
        view.repaint();
    }

    private void initialize() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {

            }
        }
    }

    // after a valid move swap the player
    private void swapColor() {
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;
        this.turn+=1;
       ChessGameFrame.turnlabel.setText("Jungle Chess Turn:"+ (this.turn/2+1));
        if(this.turn%2==1) {
            ChessGameFrame.colorlabel.setText("Red Turn");
        }else{
           ChessGameFrame.colorlabel.setText("Blue Turn");
        }
        ChessGameFrame.colorlabel.repaint();
        ChessGameFrame.turnlabel.repaint();
    }


    private boolean win() {
        // TODO: Check the board if there is a winner
        int red = 0;
        int blue = 0;
        Cell[][] cells = model.getGrid();
        for (int i = 0; i < cells.length; i++) {
            Cell[] cellH = cells[i];
            for (Cell cell : cellH) {
                if (null == cell.getPiece())
                    continue;
                if (cell.getPiece().getOwner().getColor().equals(Color.RED)) {
                    red++;
                }
                if (cell.getPiece().getOwner().getColor().equals(Color.BLUE)) {
                    blue++;
                }
            }
        }
        if (red == 0 || blue == 0)
            return true;
        Cell blueHole = cells[0][3];
        Cell redHole = cells[8][3];
        if (null != redHole.getPiece()) {
            if (redHole.getPiece().getOwner().getColor().equals(Color.BLUE)) {
                return true;
            }
        }
        if (null != blueHole.getPiece()) {
            if (blueHole.getPiece().getOwner().getColor().equals(Color.RED)) {
                return true;
            }
        }
        return false;
    }

    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        if (selectedPoint != null && model.isValidMove(selectedPoint, point)) {
            model.moveChessPiece(selectedPoint, point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            selectedPoint = null;
            view.repaint();
            // TODO: if the chess enter Dens or Traps and so on
            if (win()) {
                if(currentPlayer.getColor().equals(Color.BLUE)){
                    JOptionPane.showMessageDialog(view,"蓝方获胜","提示",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(view,"红方获胜","提示",JOptionPane.INFORMATION_MESSAGE);
                }
                restartGame();
            }
            swapColor();
        }
    }

    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, ChessComponent component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        } else {
            if (null == model.getChessPiece(point)) {
                model.moveChessPiece(selectedPoint, point);
            } else {
                // TODO: Implement capture function
                model.captureChessPiece(selectedPoint, point);
                view.removeChessComponentAtGrid(point);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                selectedPoint = null;
                view.repaint();
            }
            if (win()) {
                if(currentPlayer.getColor().equals(Color.BLUE)){
                    JOptionPane.showMessageDialog(view,"蓝方获胜","提示",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(view,"红方获胜","提示",JOptionPane.INFORMATION_MESSAGE);
                }
                restartGame();
            }
            swapColor();
        }

    }

    public void restartGame() {
        this.turn=0;
        ChessGameFrame.turnlabel.setText("Jungle Chess Turn:"+ (this.turn/2+1));
        if(this.turn%2==1) {
            ChessGameFrame.colorlabel.setText("Red Turn");
        }else{
            ChessGameFrame.colorlabel.setText("Blue Turn");
        }
        this.currentPlayer=PlayerColor.BLUE;
        ChessGameFrame.colorlabel.repaint();
        ChessGameFrame.turnlabel.repaint();
        model.removeAllPieces();
        model.initPieces();
        selectedPoint = null;
        view.removeAllPieces();
        view.initiateChessComponent(model);
        view.repaint();
    }

    public void loadGameFromFile(String path) {
        try {
            String fileName=path;
            File file=new File(fileName);
            String extension=getFileExtension(file);

            if(!extension.equals("txt")){
                throw new RuntimeException("101");
            }
            System.out.println("user dir:"+System.getProperty("user.dir"));
            List<String> loading = Files.readAllLines(Paths.get("C:\\Users\\86158\\Desktop\\java-chess-project\\"+fileName));
            if(loading.size()==9){
                throw new RuntimeException("104");
            }
            if(loading.size()!=10){
                throw new RuntimeException("102");
            }
            for(int i=0;i<9;i++) {
                if (loading.get(i).length() != 7) {
                    throw new RuntimeException("102");
                }
            }

            int j;
//            List<String> ab=new ArrayList<>();
//            ab.add("0");
//            ab.add("1");
//            ab.add("2");
//            ab.add("3");
//            ab.add("4");
//            ab.add("5");
//            ab.add("6");
//            ab.add("7");
//            ab.add("8");
//            ab.add("a");
//            ab.add("b");
//            ab.add("c");
//            ab.add("d");
//            ab.add("e");
//            ab.add("f");
//            ab.add("g");
//            ab.add("h");
//            for (String s : loading) {
//                for(j=0;j<7;j++){
//                    String b=s.substring(j);
//                   if(!ab.contains(b)){
//                       throw new RuntimeException("103");
//                   }
//                    }
//                }


            for (String s : loading) {
                for(j=0;j<7;j++){
                    if(s.contains("9")||s.contains("i")||s.contains("j")||s.contains("k")||
                            s.contains("l")||s.contains("m")||s.contains("n")||s.contains("o")||
                            s.contains("p")||s.contains("q")||s.contains("r")||s.contains("s")||
                            s.contains("t")||s.contains("u")||s.contains("v")||s.contains("w")||
                            s.contains("x")||s.contains("y")||s.contains("z")){
                        throw new RuntimeException("103");
                    }
                }
            }
            String a=loading.get(9);
            int b=Integer.parseInt(a);
            this.turn =b-2;
            model.removeAllPieces();
            model.intiatePieces(loading);
            swapColor();
            swapColor();
            view.removeAllPieces();
            view.initiateChessComponent(model);
            view.repaint();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String getFileExtension(File file) {
        String extension = null;
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");

        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extension = fileName.substring(dotIndex + 1);
        }

        return extension;
    }
    public void readGameToFile(String filename){
        try {
            int t=turn;
            Cell[][] save = model.getGrid();
            File file = new File("C:\\path\\to\\temp\\"+filename);
            FileWriter fileWriter=new FileWriter(filename,true);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    if (save[i][j].getPiece() != null) {
                        if (save[i][j].getPiece().getOwner() == PlayerColor.BLUE) {
                            switch (save[i][j].getPiece().getRank()) {
                                case 1:fileWriter.write("1");
                                    break;
                                case 2:fileWriter.write("2");
                                    break;
                                case 3:fileWriter.write("3");
                                    break;
                                case 4:fileWriter.write("4");
                                    break;
                                case 5:fileWriter.write("5");
                                    break;
                                case 6:fileWriter.write("6");
                                    break;
                                case 7:fileWriter.write("7");
                                    break;
                                case 8:fileWriter.write("8");
                                    break;
                            }

                        } else if (save[i][j].getPiece().getOwner() == PlayerColor.RED) {
                            switch (save[i][j].getPiece().getRank()) {
                                case 1:fileWriter.write("a");
                                    break;
                                case 2:fileWriter.write("b");
                                    break;
                                case 3:fileWriter.write("c");
                                    break;
                                case 4:fileWriter.write("d");
                                    break;
                                case 5:fileWriter.write("e");
                                    break;
                                case 6:fileWriter.write("f");
                                    break;
                                case 7:fileWriter.write("g");
                                    break;
                                case 8:fileWriter.write("h");
                                    break;
                            }
                        }
                    }else{
                        fileWriter.write("0");
                    }
                }
                fileWriter.write("\n");
            }
            fileWriter.write(String.format("%d",t));
            fileWriter.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
