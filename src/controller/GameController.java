package controller;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractSet;
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
            ChessGameFrame.colorlabel.setText("Blue Turn");
        }else{
           ChessGameFrame.colorlabel.setText("Red Turn");
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
            List<String> loading = Files.readAllLines(Path.of(path));
            for (String s : loading) {
                System.out.println(s);
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
    public void readGameToFile(String name){
        Cell[][] save= model.getGrid();


    }
}
