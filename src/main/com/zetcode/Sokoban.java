package main.com.zetcode;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.ini4j.Wini;

@SuppressWarnings("serial")
public class Sokoban extends JFrame
{
    ImageIcon iicon_nest_icon = new ImageIcon("src/main/resources/nest_icon.png");
    
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
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter()
        {
            @SuppressWarnings("unused")
            @Override
            public void windowClosing(WindowEvent event)
            {
                try
                {
                    File newFile = new File("D:\\Mods\\AngryBirdsSokoban-Data.ini");
                    newFile.createNewFile();
                    Wini ini = new Wini(new File("D:\\Mods\\AngryBirdsSokoban-Data.ini"));
                    
                    ini.put("Levels", "Actual", board.level_number);
                    
                    int i = 0;
                    
                    for (String level: board.levels.levels)
                    {
                        ini.put("Levels", "Level "  + i + " solved", board.isLevelSolved[i]);
                        i++;
                    }
                    
                    ini.put("Design", "Bird", board.selected_bird);
                    ini.put("Design", "Walls", board.selected_wall);
                    
                    ini.store();
                }
                catch(Exception e)
                {
                    System.err.println(e.getMessage());
                }
                
                dispose();
                System.exit(0);
            }
        }
        );
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
