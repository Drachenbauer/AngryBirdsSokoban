package com.zetcode;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LevelDialog extends JDialog
{
	private JLabel text;
    @SuppressWarnings("rawtypes")
	public JComboBox levelList;
    public JButton okButton;
    private Levels levels;
    
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public LevelDialog(String title, boolean modal)
	{
        super();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setModal(modal);
        
        setTitle(title);
        setPreferredSize(new Dimension(320, 160));
        setResizable(false);
        
    	text = new JLabel("Choose your level!", SwingConstants.CENTER);
    	levelList = new JComboBox();
        okButton = new JButton("OK");
        levels = new Levels();

        setLayout(new GridLayout(3, 1, 32, 32));
        
        int i = 0;
        
        for (String level: levels.levels)
        {
        	levelList.addItem(i);
        	i++;
        }
        
        add(text);
        add(levelList);
        add(okButton);
        pack();     
    }
}
