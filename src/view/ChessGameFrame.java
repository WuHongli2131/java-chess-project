package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

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
    public ChessGameFrame(int width, int height) {
        setTitle("JungleChess"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        ImageIcon img=new ImageIcon("C:\\Users\\explorer\\Desktop\\新建文件夹\\R-C(3).jpg");
        JLabel label = new JLabel(img);
        label.setBounds(0, 0, width, height);


        addChessboard();
        addLabel();
        addHelloButton();
        addsingleButton();
        addLoadButton();
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
    private void addLabel() {
        JLabel statusLabel = new JLabel("Jungle Chess");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
    }
    private void addBackGroundLabel() {
        Icon icon=new ImageIcon("view/img.jpg");

        JLabel statusLabel = new JLabel("1",icon,JLabel.CENTER);
        statusLabel.setLocation(0, 0);
        statusLabel.setSize(200, 60);
        add(statusLabel);
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
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    public void setGameController(GameController gameController) {
        this.controller=gameController;
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
