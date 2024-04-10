package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Tetris extends JFrame {
    private JLabel statusbar;
    private boolean hasLost;
    private String name;

    public Tetris(String name) {
        this.name = name;
        initUI();
    }

    private void initUI() {
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);
        var board = new Board(this, this.name);
        System.out.println(this.name);
        add(board);
        board.start();

        setTitle("Tetris");
        setSize(450, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }


    JLabel getStatusBar() {
        return statusbar;
    }

    public boolean hasLost() {
        return hasLost;
    }
}
