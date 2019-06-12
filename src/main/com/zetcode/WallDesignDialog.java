package main.com.zetcode;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

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
    private JLabel text = new JLabel("Choose your wall-design!", SwingConstants.CENTER);
    @SuppressWarnings("rawtypes")
    public JComboBox wallList;
    public JButton okButton = new JButton("OK");
    
    private ImageIcon[] images;
    private String[] wallDesigns = {"Bricks", "Bricks-Frame",
                                    "Ice", "Ice-Frame",
                                    "Stone", "Stone-Frame",
                                    "Wood", "Wood-Frame"};
    
    private int selectedIndex;
    public String selected;
    
    @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
    public WallDesignDialog(String selected)
    {
        super();
        setModal(true);
        setTitle("Choose Wall-Design");
        
        images = new ImageIcon[wallDesigns.length];
        Integer[] intArray = new Integer[wallDesigns.length];
        int i = 0;
        
        for (String wall: wallDesigns)
        {
            intArray[i] = new Integer(i);
            images[i] = new ImageIcon("src/main/resources/" + wallDesigns[i] + ".png");
            
            if (wallDesigns[i].equals(selected))
            {
                selectedIndex = i;
            }
            
            i++;
        }
        
        wallList = new JComboBox(intArray);
        ComboBoxRenderer renderer= new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(120, 32));
        wallList.setRenderer(renderer);
        
        contentPane.setLayout(null);
        
        contentPane.add(text);
        text.setLocation(16, 16);
        text.setSize(288, 32);
        text.setFont(new Font("Courier New", Font.BOLD, 16));
        
        contentPane.add(wallList);
        wallList.setLocation(16, 80);
        wallList.setSize(288, 40);
        wallList.setFont(new Font("Courier New", Font.BOLD, 16));
        wallList.setSelectedIndex(selectedIndex);
        
        contentPane.add(okButton);
        okButton.setLocation(16, 144);
        okButton.setSize(288, 32);
        okButton.setFont(new Font("Courier New", Font.BOLD, 16));
        
        add(contentPane);
        contentPane.setPreferredSize(new Dimension(320, 192));
        
        setResizable(false);
        pack();
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
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
            
            setIcon(images[selectedIndex]);
            setText(wallDesigns[selectedIndex]);
            setFont(list.getFont());
            
            return this;
        }
    }
}

