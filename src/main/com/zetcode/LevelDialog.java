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
public class LevelDialog extends JDialog
{
    private JPanel contentPane = new JPanel();
    private JLabel text = new JLabel("Choose your level!", SwingConstants.CENTER);
    @SuppressWarnings("rawtypes")
    public JComboBox levelList;
    public JButton okButton = new JButton("OK");
    
    private Levels levels = new Levels();
    
    private ImageIcon[] images;
    private int selectedIndex;
    private Integer[] intArray;
    
    public int level_number;
    public boolean[] isLevelSolved = new boolean[levels.levels.size()];
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public LevelDialog(int level_number, boolean[] isLevelSolved)
    {
        super();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setModal(true);
        setTitle("Choose Level");
        
        this.level_number = level_number;
        this.isLevelSolved = isLevelSolved;
        
        setSolvedGraphics();
        
        levelList= new JComboBox(intArray);
        ComboBoxRenderer renderer= new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(120, 32));
        levelList.setRenderer(renderer);
        
        contentPane.setLayout(null);
        
        contentPane.add(text);
        text.setLocation(16, 16);
        text.setSize(224, 32);
        
        contentPane.add(levelList);
        levelList.setLocation(16, 80);
        levelList.setSize(224, 40);
        levelList.setMaximumRowCount(10);
        levelList.setSelectedIndex(selectedIndex);
        
        contentPane.add(okButton);
        okButton.setLocation(16, 144);
        okButton.setSize(224, 32);
        
        add(contentPane);
        contentPane.setPreferredSize(new Dimension(256, 192));
        
        setResizable(false);
        pack();
    }
    
    @SuppressWarnings("unused")
    public void setSolvedGraphics()
    {
        images = new ImageIcon[levels.levels.size()];
        intArray = new Integer[levels.levels.size()];
        int i = 0;
        
        for (String level : levels.levels)
        {
            intArray[i] = i;
            
            if (isLevelSolved[i])
            {
                images[i] = new ImageIcon("src/main/resources/nest_icon_solved.png");
            }
            else
            {
                images[i] = new ImageIcon("src/main/resources/nest_icon.png");
            }
            
            if (intArray[i] == level_number)
            {
                selectedIndex = i;
            }
            
            i++;
        }
    }
    
    @SuppressWarnings({ "rawtypes" })
    class ComboBoxRenderer extends JLabel implements ListCellRenderer
    {
        public ComboBoxRenderer()
        {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
            level_number = selectedIndex;
        }
        
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            int selectedIndex = ((Integer)value).intValue();
            
            if (isSelected)
            {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
                level_number = selectedIndex;
            }
            else
            {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            
            setIcon(images[selectedIndex]);
            setText("Level " + selectedIndex);
            setFont(list.getFont());
            
            return this;
        }
    }
}
