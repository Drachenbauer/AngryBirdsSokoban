package com.zetcode;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Sokoban extends JFrame
{
    public Sokoban()
    {
        initUI();
    }

    private void initUI()
    {
        Board board = new Board();
        add(board);

        setTitle("Sokoban");
        
        getContentPane().setPreferredSize(new Dimension(board.WIDTH, board.HIGHT));
        setResizable(false);
        pack(); //max level size : 32 x 20 tiles
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            Sokoban game = new Sokoban();
            game.setVisible(true);
        }
        );
    }
}
