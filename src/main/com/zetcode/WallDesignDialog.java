package main.com.zetcode;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class WallDesignDialog extends JDialog
{
	private JPanel contentPane = new JPanel();
	private JLabel text = new JLabel("Choose your Wall-Design!", SwingConstants.CENTER);
    @SuppressWarnings("rawtypes")
	public JComboBox wallList;
    public JButton okButton = new JButton("OK");
    
    ImageIcon[] images;
    
    String[] wallDesigns = {"Bricks", "Bricks-Frame",
    		                "Ice", "Ice-Frame",
    		                "Stone", "Stone-Frame",
    		                "Wood", "Wood-Frame"};
    
    private int selectedIndex;
    public String selected;
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public WallDesignDialog(String title, boolean modal, String selected)
	{
        super();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setModal(modal);
        setTitle(title);
        
        images = new ImageIcon[wallDesigns.length];
        Integer[] intArray = new Integer[wallDesigns.length];
        
        for (int i = 0; i < wallDesigns.length; i++)
        {
            intArray[i] = new Integer(i);
            images[i] = new ImageIcon("src/main/resources/" + wallDesigns[i] + ".png");
            
            if (wallDesigns[i].equals(selected))
            {
                selectedIndex = i;
            }
        }
        
        wallList = new JComboBox(intArray);
        ComboBoxRenderer renderer= new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(120, 32));
        wallList.setRenderer(renderer);
        
        contentPane.setLayout(null);
        
        contentPane.add(text);
        text.setLocation(16, 16);
        text.setSize(224, 32);
        
        contentPane.add(wallList);
        wallList.setLocation(16, 80);
        wallList.setSize(224, 40);
        wallList.setSelectedIndex(selectedIndex);
        
        contentPane.add(okButton);
        okButton.setLocation(16, 144);
        okButton.setSize(224, 32);
        
        add(contentPane);
        contentPane.setPreferredSize(new Dimension(256, 192));
        
        setResizable(false);
        pack();
    }
	
	@SuppressWarnings({ "rawtypes" })
	class ComboBoxRenderer extends JLabel implements ListCellRenderer
	{
		public ComboBoxRenderer()
		{
			setOpaque(true);
	    	setHorizontalAlignment(CENTER);
	    	setVerticalAlignment(CENTER);
	    	selected = wallDesigns[selectedIndex];
		}
		
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			int selectedIndex = ((Integer)value).intValue();
			
			String wallDesign = wallDesigns[selectedIndex];
			
			if (isSelected)
			{
	            setBackground(list.getSelectionBackground());
	            setForeground(list.getSelectionForeground());
	            selected = wallDesigns[selectedIndex];
	        }
			else
			{
	            setBackground(list.getBackground());
	            setForeground(list.getForeground());
	        }
			
			ImageIcon icon = images[selectedIndex];
	        
	        setIcon(icon);
	        setText(wallDesign);
	        setFont(list.getFont());
	        
			return this;
		}
	}
}

