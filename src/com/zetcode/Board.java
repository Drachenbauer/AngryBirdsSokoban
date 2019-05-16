package com.zetcode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import com.zetcode.game_tiles.Egg;
import com.zetcode.game_tiles.Movable;
import com.zetcode.game_tiles.Nest;
import com.zetcode.game_tiles.Player;
import com.zetcode.game_tiles.Tile;
import com.zetcode.game_tiles.Wall;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener
{
    private final int OFFSET = 32;
    public static final int SPACE = 32;
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;
    
    private ArrayList<Wall> walls;
    private ArrayList<Nest> nests;
    private ArrayList<Egg> eggs;
    private Player bird;
    
    private final int WIDTH = 1088; // fits up to 32 level-tiles + 2 x OFFSET
    private final int HIGHT = 704;  // fits up to 20 level-tiles + 2 x OFFSET
    
    private int finishedEggs;
    private int nOfEggs;
        
    private int moves = 0;
    
    private boolean isCompleted = false;
    private boolean isMoving = false;
    
    private LevelDialog level_dialog = new LevelDialog("Choose Level", true);
    private Levels levels = new Levels();
    public String level = levels.levels.get(0);
    public int level_number = 0;
    
    private WallDesignDialog wall_design_dialog = new WallDesignDialog("Choose Wall-Design", true);
    
    private Timer timer = new Timer();
    
    public Board()
    {
    	setPreferredSize(new Dimension(WIDTH, HIGHT));
    	setFocusable(true);
    	
        addKeyListener(new TAdapter());
        level_dialog.okButton.addActionListener(this);
        wall_design_dialog.okButton.addActionListener(this);
        
        initWorld();
        isCompleted();
    }
    
    private void initWorld()
    {    
        walls = new ArrayList<>();
        nests = new ArrayList<>();
        eggs = new ArrayList<>();
        
        Wall wall;
        Nest nest;
        Egg egg;
        
        int x = OFFSET;
        int y = OFFSET;
                
        for (int i = 0; i < level.length(); i++)
        {
            char item = level.charAt(i);
            
            switch (item)
            {
                case '\n':
                y += SPACE;
                
                x = OFFSET;
                break;
                
                case '#':
                wall = new Wall(x, y);
                walls.add(wall);
                x += SPACE;
                break;
                
                case '.':
                nest = new Nest(x, y);
                nests.add(nest);
                x += SPACE;
                break;
                
                case '$':
                egg = new Egg(x, y);
                eggs.add(egg);
                x += SPACE;
                break;
                
                case '@':
                bird = new Player(x, y);
                x += SPACE;
                break;
                
                case '*':
                nest = new Nest(x, y);
                nests.add(nest);
                egg = new Egg(x, y);
                eggs.add(egg);
                x += SPACE;
                break;
                
                case '+':
                nest = new Nest(x, y);
                nests.add(nest);
                bird = new Player(x, y);
                x += SPACE;
                break;
                
                case ' ':
                x += SPACE;
                break;
                
                default:
                break;
            }
        }
    }
    
    private void buildWorld(Graphics g)
    {
    	g.setColor(new Color(127, 127, 0));
        g.fillRect(0, 0, WIDTH, HIGHT);
        
        g.setColor(new Color(0, 191, 0));
        g.fillRect(OFFSET, OFFSET, WIDTH - OFFSET * 2, HIGHT - OFFSET * 2);
        
        ArrayList<Tile> world = new ArrayList<>();
        
        world.addAll(walls);
        world.addAll(nests);
        world.addAll(eggs);
        world.add(bird);
        
        for (Tile tile : world)
        {
            g.drawImage(tile.image, tile.x, tile.y, this);    
        }
        
        g.setColor(new Color(255, 255, 0));
        g.drawString("" + finishedEggs + " of " + nOfEggs + " eggs solved", 304, 16);
        g.drawString("" + moves + " moves used", 816, 16);
        
        if (isCompleted)
        {    
            g.drawString("Completed", 16, 16);
        }
        else
        {
        	g.drawString("Level " + level_number, 16, 16);
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        buildWorld(g);
    }
    
    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
        	if (isMoving)
            {
                return;
            }
        	
            int key = e.getKeyCode();
            
            switch (key)
            {    
                case KeyEvent.VK_LEFT:
                	
                if (isCompleted)
                {
                    return;
                }
                
                if (checkWallCollision(bird, LEFT_COLLISION))
                {
                	bird.setImage(bird.iicon_bird_left);
                	repaint();
                    return;
                }
                
                if (checkEggCollision(LEFT_COLLISION))
                {
                	bird.setImage(bird.iicon_bird_left);
                	repaint();
                    return;
                }
                
                isMoving = true;
                
                for (Egg egg : eggs)
                {
                	egg.savePos();
                	
                    if (bird.isLeftCollision(egg))
                    {
                        egg.setImage(egg.iicon_egg_1);
                        
                        egg.move(-SPACE / 4, 0);
                        repaint();
                        
                        timer.schedule(new TimerTask()
            	        {
        			        @Override
        			        public void run()
        			        {
        				        egg.move(-SPACE / 4, 0);
        		                repaint();
        		                
        		    	        timer.schedule(new TimerTask()
        		    	        {
        					        @Override
        					        public void run()
        					        {
        						        egg.move(-SPACE / 4, 0);
        				                repaint();
        				                
        				    	        timer.schedule(new TimerTask()
        				    	        {
        							        @Override
        							        public void run()
        							        {
        								        egg.move(-SPACE / 4, 0);
        						                repaint();
        						                
        						                isCompleted();
        						            }
        				    	        }
        				    	        , 100);
        					        }
        		    	        }
        		    	        , 100);
        			        }
            	        }
                        , 100);
                    }
                }
                
                bird.savePos();

                bird.setImage(bird.iicon_bird_hop_left);
                bird.move(-SPACE / 4, 0);
                repaint();
                
        		timer.schedule(new TimerTask()
        		{
    				@Override
    				public void run()
    				{
    					bird.setImage(bird.iicon_bird_left);
    					bird.move(-SPACE / 4, 0);
    		            repaint();
    		            
    		    		timer.schedule(new TimerTask()
    		    		{
    						@Override
    						public void run()
    						{
    							bird.setImage(bird.iicon_bird_hop_left);
    							bird.move(-SPACE / 4, 0);
    				            repaint();
    				            
    				    		timer.schedule(new TimerTask()
    				    		{
    								@Override
    								public void run()
    								{
    									bird.setImage(bird.iicon_bird_left);
    									bird.move(-SPACE / 4, 0);
    						            repaint();
    						            
    						            timer.schedule(new TimerTask()
    	    				    		{
    	    								@Override
    	    								public void run()
    	    								{
    	    						            moves++;
    	    						            repaint();
    	    						            isMoving = false;
    	    								}
    	    				    	    }
    						            , 100);
    								}
    				    	    }
    				    		, 100);
    						}
    		    	    }
    		    		, 100);
    				}
        	    }
        		, 100);
                
                break;
                
                case KeyEvent.VK_RIGHT:
                	
                if (isCompleted)
                {
                    return;
                }
                
                if (checkWallCollision(bird, RIGHT_COLLISION))
                {
                	bird.setImage(bird.iicon_bird_right);
                	repaint();
                    return;
                }
                
                if (checkEggCollision(RIGHT_COLLISION))
                {
                	bird.setImage(bird.iicon_bird_right);
                	repaint();
                    return;
                }
                
                isMoving = true;
                
                for (Egg egg : eggs)
                {
                	egg.savePos();
                	
                    if (bird.isRightCollision(egg))
                    {
                        egg.setImage(egg.iicon_egg_1);
                        
                        egg.move(SPACE / 4, 0);
                        repaint();
                        
                		timer.schedule(new TimerTask()
                		{
            				@Override
            				public void run()
            				{
            					egg.move(SPACE / 4, 0);
            		            repaint();
            		            
            		    		timer.schedule(new TimerTask()
            		    		{
            						@Override
            						public void run()
            						{
            							egg.move(SPACE / 4, 0);
            				            repaint();
            				            
            				    		timer.schedule(new TimerTask()
            				    		{
            								@Override
            								public void run()
            								{
            									egg.move(SPACE / 4, 0);
            						            repaint();
            						            
            						            isCompleted();
            								}
            				    	    }
            				    		, 100);
            						}
            		    	    }
            		    		, 100);
            				}
                	    }
                		, 100);
                    }
                }
                
                bird.savePos();

                bird.setImage(bird.iicon_bird_hop_right);
                bird.move(SPACE / 4, 0);
                repaint();
                
        		timer.schedule(new TimerTask()
        		{
    				@Override
    				public void run()
    				{
    					bird.setImage(bird.iicon_bird_right);
    					bird.move(SPACE / 4, 0);
    		            repaint();
    		            
    		    		timer.schedule(new TimerTask()
    		    		{
    						@Override
    						public void run()
    						{
    							bird.setImage(bird.iicon_bird_hop_right);
    							bird.move(SPACE / 4, 0);
    				            repaint();
    				            
    				    		timer.schedule(new TimerTask()
    				    		{
    								@Override
    								public void run()
    								{
    									bird.setImage(bird.iicon_bird_right);
    									bird.move(SPACE / 4, 0);
    						            repaint();
    						            
    						            timer.schedule(new TimerTask()
    	    				    		{
    	    								@Override
    	    								public void run()
    	    								{
    	    						            moves++;
    	    						            repaint();
    	    						            isMoving = false;
    	    								}
    	    				    	    }
    						            , 100);
    								}
    				    	    }
    				    		, 100);
    						}
    		    	    }
    		    		, 100);
    				}
        	    }
        		, 100);
                
                break;
                
                case KeyEvent.VK_UP:
                	
                if (isCompleted)
                {
                    return;
                }
                
                if (checkWallCollision(bird, TOP_COLLISION))
                {
                	bird.setImage(bird.iicon_bird_up);
                	repaint();
                    return;
                }
                
                if (checkEggCollision(TOP_COLLISION))
                {
                	bird.setImage(bird.iicon_bird_up);
                	repaint();
                    return;
                }
                
                isMoving = true;
                
                for (Egg egg : eggs)
                {
                	egg.savePos();
                	
                    if (bird.isTopCollision(egg))
                    {
                        egg.setImage(egg.iicon_egg_1);
                        
                        egg.move(0, -SPACE / 4);
                        repaint();
                        
                		timer.schedule(new TimerTask()
                		{
            				@Override
            				public void run()
            				{
            					egg.move(0, -SPACE / 4);
            		            repaint();
            		            
            		    		timer.schedule(new TimerTask()
            		    		{
            						@Override
            						public void run()
            						{
            							egg.move(0, -SPACE / 4);
            				            repaint();
            				            
            				    		timer.schedule(new TimerTask()
            				    		{
            								@Override
            								public void run()
            								{
            									egg.move(0, -SPACE / 4);
            									repaint();
            									
            									isCompleted();
            								}
            				    	    }
            				    		, 100);
            						}
            		    	    }
            		    		, 100);
            				}
                	    }
                		, 100);
                    }
                }
                
                bird.savePos();

                bird.setImage(bird.iicon_bird_hop_up);
                bird.move(0, -SPACE / 4);
                repaint();
                
        		timer.schedule(new TimerTask()
        		{
    				@Override
    				public void run()
    				{
    					bird.setImage(bird.iicon_bird_up);
    					bird.move(0, -SPACE / 4);
    		            repaint();
    		            
    		    		timer.schedule(new TimerTask()
    		    		{
    						@Override
    						public void run()
    						{
    							bird.setImage(bird.iicon_bird_hop_up);
    							bird.move(0, -SPACE / 4);
    				            repaint();
    				            
    				    		timer.schedule(new TimerTask()
    				    		{
    								@Override
    								public void run()
    								{
    									bird.setImage(bird.iicon_bird_up);
    									bird.move(0, -SPACE / 4);
    						            repaint();
    						            
    						            timer.schedule(new TimerTask()
    	    				    		{
    	    								@Override
    	    								public void run()
    	    								{
    	    						            moves++;
    	    						            repaint();
    	    						            isMoving = false;
    	    								}
    	    				    	    }
    						            , 100);
    								}
    				    	    }
    				    		, 100);
    						}
    		    	    }
    		    		, 100);
    				}
        	    }
        		, 100);
                
                break;
                
                case KeyEvent.VK_DOWN:
                	
                if (isCompleted)
                {
                    return;
                }
                
                if (checkWallCollision(bird, BOTTOM_COLLISION))
                {
                	bird.setImage(bird.iicon_bird_down);
                	repaint();
                    return;
                }
                
                if (checkEggCollision(BOTTOM_COLLISION))
                {
                	bird.setImage(bird.iicon_bird_down);
                	repaint();
                    return;
                }
                
                isMoving = true;
                
                for (Egg egg : eggs)
                {
                	egg.savePos();
                	
                    if (bird.isBottomCollision(egg))
                    {
                    	egg.setImage(egg.iicon_egg_1);
                    	
                    	egg.move(0, SPACE / 4);
                        repaint();
                        
                		timer.schedule(new TimerTask()
                		{
            				@Override
            				public void run()
            				{
            					egg.move(0, SPACE / 4);
            		            repaint();
            		            
            		    		timer.schedule(new TimerTask()
            		    		{
            						@Override
            						public void run()
            						{
            							egg.move(0, SPACE / 4);
            				            repaint();
            				            
            				    		timer.schedule(new TimerTask()
            				    		{
            								@Override
            								public void run()
            								{
            									egg.move(0, SPACE / 4);
            						            repaint();
            						            
            						            isCompleted();
            								}
            				    	    }
            				    		, 100);
            						}
            		    	    }
            		    		, 100);
            				}
                	    }
                		, 100);
                    }
                }
                
                bird.savePos();

                bird.setImage(bird.iicon_bird_hop_down);
                bird.move(0, SPACE / 4);
                repaint();
                
        		timer.schedule(new TimerTask()
        		{
    				@Override
    				public void run()
    				{
    					bird.setImage(bird.iicon_bird_down);
    					bird.move(0, SPACE / 4);
    		            repaint();
    		            
    		    		timer.schedule(new TimerTask()
    		    		{
    						@Override
    						public void run()
    						{
    							bird.setImage(bird.iicon_bird_hop_down);
    							bird.move(0, SPACE / 4);
    				            repaint();
    				            
    				    		timer.schedule(new TimerTask()
    				    		{
    								@Override
    								public void run()
    								{
    									bird.setImage(bird.iicon_bird_down);
    									bird.move(0, SPACE / 4);
    						            repaint();
    						            
    						            timer.schedule(new TimerTask()
    	    				    		{
    	    								@Override
    	    								public void run()
    	    								{
    	    						            moves++;
    	    						            repaint();
    	    						            isMoving = false;
    	    								}
    	    				    	    }
    						            , 100);
    								}
    				    	    }
    				    		, 100);
    						}
    		    	    }
    		    		, 100);
    				}
        	    }
        		, 100);
                
                break;
                
                case KeyEvent.VK_BACK_SPACE:
                
                if (isCompleted)
                {
                    return;
                }
                
                if (moves > 0)
                {
                	bird.undoMove();
                	
                	for (Egg egg : eggs)
                	{
                		egg.setImage(egg.iicon_egg_1);
                		egg.undoMove();
                	}
                	
                	moves--;
                	isCompleted();
                }
                
                break;
                
                case KeyEvent.VK_R:
                
                restartLevel();
                
                break;
                
                case KeyEvent.VK_L:
                
                level_dialog.setVisible(true);
                
                break;
                
                case KeyEvent.VK_W:
                
                wall_design_dialog.setVisible(true);
                
                break;
                
                default:
                break;
            }
            
            repaint();
        }
    }
    
    public void actionPerformed(ActionEvent evt)
    {
    	if (evt.getSource()==level_dialog.okButton)
        {
    	    changeLevel((int)level_dialog.levelList.getSelectedItem());
    	    level_dialog.setVisible(false);
        }
    	else if (evt.getSource()==wall_design_dialog.okButton)
    	{
    		for (Wall wall : walls)
    		{
    			wall.changeWall((String)wall_design_dialog.wallList.getSelectedItem());
    		}
    		repaint();
    		wall_design_dialog.setVisible(false);
    	}
    }
    
    public void changeLevel(int number)
    {
    	level_number = number;
    	level = levels.levels.get(level_number);
        restartLevel();
    }
    
    private boolean checkWallCollision(Movable movable, int type)
    {
        switch (type)
        {
            case LEFT_COLLISION:
            
            for (Wall wall: walls)
            {
                if (movable.isLeftCollision(wall))
                {    
                    return true;
                }
            }
            
            return false;
            
            case RIGHT_COLLISION:
            
            for (Wall wall: walls)
            {
                if (movable.isRightCollision(wall))
                {
                    return true;
                }
            }
            
            return false;
            
            case TOP_COLLISION:
            
            for (Wall wall: walls)
            {
                if (movable.isTopCollision(wall))
                {
                    return true;
                }
            }
            
            return false;
            
            case BOTTOM_COLLISION:
            
            for (Wall wall: walls)
            {
                if (movable.isBottomCollision(wall))
                {    
                    return true;
                }
            }
            
            return false;
            
            default:
            
            break;
        }
        
        return false;
    }
    
    private boolean checkEggCollision(int type)
    {
        switch (type)
        {    
            case LEFT_COLLISION:
            
            for (Egg egg1 : eggs)
            {
                if (bird.isLeftCollision(egg1))
                {
                    for (Egg egg2 : eggs)
                    {
                        if (!egg1.equals(egg2))
                        {    
                            if (egg1.isLeftCollision(egg2))
                            {
                                return true;
                            }
                        }
                        
                        if (checkWallCollision(egg1, LEFT_COLLISION))
                        {
                            return true;
                        }
                    }
                }
            }
            
            return false;
            
            case RIGHT_COLLISION:
            
            for (Egg egg1 : eggs)
            {
                if (bird.isRightCollision(egg1))
                {
                    for (Egg egg2 : eggs) 
                    {
                        if (!egg1.equals(egg2))
                        {
                            if (egg1.isRightCollision(egg2))
                            {
                                return true;
                            }
                        }
                        
                        if (checkWallCollision(egg1, RIGHT_COLLISION))
                        {
                            return true;
                        }
                    }
                }
            }
            
            return false;
            
            case TOP_COLLISION:
                
            for (Egg egg1 : eggs)
            {
                if (bird.isTopCollision(egg1))
                {
                    for (Egg egg2 : eggs)
                    {
                        if (!egg1.equals(egg2))
                        {
                            if (egg1.isTopCollision(egg2))
                            {
                                return true;
                            }
                        }
                        
                        if (checkWallCollision(egg1, TOP_COLLISION))
                        {
                            return true;
                        }
                    }
                }
            }

            return false;
                
            case BOTTOM_COLLISION:
                
            for (Egg egg1 : eggs)
            {
                if (bird.isBottomCollision(egg1))
                {
                    for (Egg egg2 : eggs)
                    {
                        if (!egg1.equals(egg2)) 
                        {
                            if (egg1.isBottomCollision(egg2))
                            {
                                return true;
                            }
                        }
                            
                        if (checkWallCollision(egg1,BOTTOM_COLLISION))
                        {
                            return true;
                        }
                    }
                }
            }
            
            return false;
            
            default:
            
            break;
        }
        
        return false;
    }
    
    private void isCompleted()
    {
        nOfEggs = eggs.size();
        finishedEggs = 0;
        
        for (Egg egg : eggs)
        {
            for (Nest nest: nests)
            {
                if (egg.x == nest.x && egg.y == nest.y)
                {
                    finishedEggs++;
                    egg.setImage(egg.iicon_egg_2);
                }
            }
        }
        
        if (finishedEggs == nOfEggs)
        {
        	if (level_number + 1 < levels.levels.size())
        	{
        		isMoving = true;
        		
        		timer.schedule(new TimerTask()
        		{
    				@Override
    				public void run()
    				{
    					changeLevel(level_number + 1);
    					isMoving = false;
    				}
        	    }
        		, 2000);
        	}
        	else
        	{
                isCompleted = true;
        	}
        }
        repaint();
    }
    
    private void restartLevel()
    {
    	walls.clear();
        nests.clear();
        eggs.clear();
        
        moves = 0;
        
        initWorld();
        isCompleted();
        
        if (isCompleted)
        {
            isCompleted = false;
        }
    }
}
