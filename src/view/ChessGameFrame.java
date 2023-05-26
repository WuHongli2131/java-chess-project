package view;

import controller.GameController;
import model.Chessboard;
import model.Constant;
import model.PlayerColor;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;

    private final int WIDTH;
    private final int HEIGTH;

    private final int ONE_CHESS_SIZE;
    private GameController controller;

    private ChessboardComponent chessboardComponent;
    public static JLabel turnlabel= new JLabel();
    public static JLabel colorlabel=new JLabel();
    public ChessGameFrame(int width, int height) {
        setTitle("JungleChess"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        addChessboard();
        addHelloButton();
        addsingleButton();
        addLoadButton();
        addStorageButton();
    }

    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }


    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent.setLocation(HEIGTH / 5, HEIGTH / 10);
        add(chessboardComponent);
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {//有bug
        turnlabel = new JLabel("Jungle Chess Turn:"+ (this.controller.turn/2+1));
        turnlabel.setLocation(HEIGTH, HEIGTH / 10);
        turnlabel.setSize(400, 60);
        turnlabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(turnlabel);
    }




       private void addPlayerLabel() {
        colorlabel = new JLabel();
        if(this.controller.turn%2==1) {
            colorlabel = new JLabel("Red Turn" );
        }else{
            colorlabel = new JLabel("Blue Turn" );
        }
            colorlabel.setLocation(HEIGTH, HEIGTH / 10 - 60);
            colorlabel.setSize(200, 60);
            colorlabel.setFont(new Font("Rockwell", Font.BOLD, 20));
            add(colorlabel);
    }
    private void addBackGroundLabel() {
        JLabel jl=new JLabel(" ");
        URL url =ChessGameFrame.class.getResource("img.jpg");
        Icon icon =new ImageIcon(url);
        jl.setIcon(icon);
        jl.setHorizontalAlignment(SwingConstants.CENTER);
        jl.setOpaque(true);
        this.add(jl);
        setSize(1100, 810);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }



    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */
    private void addsingleButton() {
        JButton button = new JButton("Restart");
        button.addActionListener((e) -> {
          //开启单人游戏的方法
            controller.restartGame();
                });
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void adddoubleButton() {
        JButton button = new JButton("muti");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));//开启单人游戏的方法
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }


    private void addHelloButton() {
        JButton button = new JButton("Music");
        final AudioInputStream[] audioInputStream = new AudioInputStream[1];
        button.addActionListener((e) -> {
            try {
                File audioFile = new File("C:\\Users\\explorer\\Documents\\GitHub\\java-chess-project\\src\\file.wav");
                audioInputStream[0] = AudioSystem.getAudioInputStream(audioFile);
                AudioFormat format = audioInputStream[0].getFormat();

                // Create data line for playback
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip clip = (Clip) AudioSystem.getLine(info);

                // Open audio stream and start playback
                clip.open(audioInputStream[0]);
                clip.start();
            }catch (IOException ex) {
                ex.printStackTrace();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            } catch (UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            }});
        button.setLocation(HEIGTH, HEIGTH / 10 + 480);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

    }

    public void setGameController(GameController gameController) {
        this.controller=gameController;
        this.addPlayerLabel();
        this.addLabel();
    }

    private void addStorageButton(){
        JButton button = new JButton("Save");
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String name = JOptionPane.showInputDialog(this,"Input txtFilename here");
            System.out.println(name);
            controller.readGameToFile(name);
        });
    }
   private void addLoadButton() {//这个类是用来读档的
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
       button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            System.out.println(path);
            controller.loadGameFromFile(path);
        });
    }

}
