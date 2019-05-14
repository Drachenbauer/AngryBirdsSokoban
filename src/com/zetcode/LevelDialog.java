package com.zetcode;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LevelDialog extends JDialog implements ActionListener
{
	private JLabel text;
    @SuppressWarnings("rawtypes")
	public JComboBox levelList;
    public JButton okButton;
    private Levels levels;
    
    public ArrayList<Integer> list_levels = new ArrayList<>();
    
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public LevelDialog(String title, boolean modal)
	{
        super();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setModal(modal);
        
        setTitle(title);
        setPreferredSize(new Dimension(320, 160));
        setResizable(false);
        
    	text = new JLabel("Choose your level!");
    	levelList = new JComboBox();
        okButton = new JButton("OK");
        levels = new Levels();

        setLayout(new GridLayout(3, 1, 32, 32));
        
        int i = 0;
        
        for (String level: levels.levels)
        {
        	list_levels.add (i);
        	i++;
        }
        
        for (int level : list_levels)
        {
        	levelList.addItem(level);
        }
        
        add(text);
        add(levelList);
        add(okButton);
        pack();     
    }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
	}
}
