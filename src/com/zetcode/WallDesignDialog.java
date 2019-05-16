package com.zetcode;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class WallDesignDialog extends JDialog
{
	private JLabel text;
    @SuppressWarnings("rawtypes")
	public JComboBox wallList;
    public JButton okButton;
    
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public WallDesignDialog(String title, boolean modal)
	{
        super();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setModal(modal);
        
        setTitle(title);
        setPreferredSize(new Dimension(320, 160));
        setResizable(false);
        
    	text = new JLabel("Choose your Wall-Design!", SwingConstants.CENTER);
    	wallList = new JComboBox();
        okButton = new JButton("OK");

        setLayout(new GridLayout(3, 1, 32, 32));
        
        int i = 0;
        
        wallList.addItem("Bricks");
        wallList.addItem("Bricks-Frame");
        wallList.addItem("Ice");
        wallList.addItem("Ice-Frame");
        wallList.addItem("Stone");
        wallList.addItem("Stone-Frame");
        wallList.addItem("Wood");
        wallList.addItem("Wood-Frame");
        
        add(text);
        add(wallList);
        add(okButton);
        pack();     
    }
}
