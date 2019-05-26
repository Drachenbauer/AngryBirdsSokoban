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
public class BirdDialog extends JDialog
{
	private JPanel contentPane = new JPanel();
	private JLabel text = new JLabel("Choose your bird-character to play as!", SwingConstants.CENTER);
    @SuppressWarnings("rawtypes")
	public JComboBox birdList;
    public JButton okButton = new JButton("OK");
    
    ImageIcon[] images;
    
    String[] birds = {"Red", "Chuck", "The Blues",
    		                "Bomb", "Mathilda", "Terence",
    		                "Silver", "Bubbles", "Hal",
    		                "Stella", "Ice Bird"};
    
    private int selectedIndex;
    public String selected;
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BirdDialog(String title, boolean modal, String selected)
	{
        super();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setModal(modal);
        setTitle(title);
        
        images = new ImageIcon[birds.length];
        Integer[] intArray = new Integer[birds.length];
        
        for (int i = 0; i < birds.length; i++)
        {
            intArray[i] = new Integer(i);
            
            if (birds[i].equals("The Blues"))
            {
            	images[i] = new ImageIcon("src/main/resources/birds/The_Blues/bird_down.png");
            }
            else if (birds[i].equals("Ice Bird"))
            {
            	images[i] = new ImageIcon("src/main/resources/birds/Ice_Bird/bird_down.png");
            }
            else
            {
                images[i] = new ImageIcon("src/main/resources/birds/" + birds[i] + "/bird_down.png");
            }
            
            if (birds[i].equals(selected))
            {
                selectedIndex = i;
            }
        }
        
        birdList = new JComboBox(intArray);
        ComboBoxRenderer renderer= new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(120, 32));
        birdList.setRenderer(renderer);
        
        contentPane.setLayout(null);
        
        contentPane.add(text);
        text.setLocation(16, 16);
        text.setSize(224, 32);
        
        contentPane.add(birdList);
        birdList.setLocation(16, 80);
        birdList.setSize(224, 40);
        birdList.setMaximumRowCount(10);
        birdList.setSelectedIndex(selectedIndex);
        
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
	    	selected = birds[selectedIndex];
		}
		
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			int selectedIndex = ((Integer)value).intValue();
			
			String bird = birds[selectedIndex];
			
			if (isSelected)
			{
	            setBackground(list.getSelectionBackground());
	            setForeground(list.getSelectionForeground());
	            selected = birds[selectedIndex];
	        }
			else
			{
	            setBackground(list.getBackground());
	            setForeground(list.getForeground());
	        }
			
			ImageIcon icon = images[selectedIndex];
	        
	        setIcon(icon);
	        setText(bird);
	        setFont(list.getFont());
	        
			return this;
		}
	}
}
