package com.zetcode;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Sokoban extends JFrame
{
	ImageIcon iicon_nest_icon = new ImageIcon("src/resources/nest_icon.png");
	
    public Sokoban()
    {
        initUI();
    }

    private void initUI()
    {
        Board board = new Board();
        add(board);

        setTitle("Sokoban");
        setIconImage(iicon_nest_icon.getImage());
        
        setResizable(false);
        pack();
        
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
