package com.zetcode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
public class Board extends JPanel
{
    private final int OFFSET = 32;
    private final int SPACE = 32;
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;
    
    private ArrayList<Wall> walls;
    private ArrayList<Nest> nests;
    private ArrayList<Egg> eggs;
    private Player bird;
    
    public final int WIDTH = 1088;
    public final int HIGHT = 704;
    
    private int finishedEggs;
    private int nOfEggs;
        
    private int steps = 0;
    
    private boolean isCompleted = false;
    private boolean isMoving = false;
    
    private String level = Levels.LEVEL_0;
    private String level_num = "Level 0";
    
    private Timer timer = new Timer();
    
    public Board()
    {
        initBoard();
    }
    
    private void initBoard()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setSize(new Dimension(WIDTH, HIGHT));
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
        
        g.setColor(new Color(0, 255, 0));
        g.fillRect(OFFSET, OFFSET, WIDTH - OFFSET * 2, HIGHT - OFFSET * 2);
        
        ArrayList<Tile> world = new ArrayList<>();
        
        world.addAll(walls);
        world.addAll(nests);
        world.addAll(eggs);
        world.add(bird);
        
        for (Tile tile : world)
        {
            g.drawImage(tile.getImage(), tile.x(), tile.y(), this);    
        }
        
        g.setColor(new Color(255, 255, 0));
        g.drawString("" + finishedEggs + " of " + nOfEggs + " eggs solved", 304, 16);
        g.drawString("" + steps + " steps moved", 816, 16);
        
        if (isCompleted)
        {    
            g.drawString("Completed", 16, 16);
        }
        else
        {
        	g.drawString(level_num, 16, 16);
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
                	bird.turnLeft();
                	repaint();
                    return;
                }
                
                if (checkEggCollision(LEFT_COLLISION))
                {
                	bird.turnLeft();
                	repaint();
                    return;
                }
                
                isMoving = true;
                bird.savePos();
                bird.move(-SPACE / 4, 0);
                bird.hopLeft();
                repaint();
                
        		timer.schedule(new TimerTask()
        		{
    				@Override
    				public void run()
    				{
    					bird.move(-SPACE / 4, 0);
    		            bird.turnLeft();
    		            repaint();
    		            
    		    		timer.schedule(new TimerTask()
    		    		{
    						@Override
    						public void run()
    						{
    							bird.move(-SPACE / 4, 0);
    				            bird.hopLeft();
    				            repaint();
    				            
    				    		timer.schedule(new TimerTask()
    				    		{
    								@Override
    								public void run()
    								{
    									bird.move(-SPACE / 4, 0);
    						            bird.turnLeft();
    						            steps += 1;
    						            isMoving = false;
    						            repaint();
    								}
    				    	    }, 100);
    						}
    		    	    }, 100);
    				}
        	    }, 100);
                
                break;
                
                case KeyEvent.VK_RIGHT:
                	
                if (isCompleted)
                {
                    return;
                }
                
                if (checkWallCollision(bird, RIGHT_COLLISION))
                {
                	bird.turnRight();
                	repaint();
                    return;
                }
                
                if (checkEggCollision(RIGHT_COLLISION))
                {
                	bird.turnRight();
                	repaint();
                    return;
                }
                
                isMoving = true;
                bird.savePos();
                bird.move(SPACE / 4, 0);
                bird.hopRight();
                repaint();
                
        		timer.schedule(new TimerTask()
        		{
    				@Override
    				public void run()
    				{
    					bird.move(SPACE / 4, 0);
    		            bird.turnRight();
    		            repaint();
    		            
    		    		timer.schedule(new TimerTask()
    		    		{
    						@Override
    						public void run()
    						{
    							bird.move(SPACE / 4, 0);
    				            bird.hopRight();
    				            repaint();
    				            
    				    		timer.schedule(new TimerTask()
    				    		{
    								@Override
    								public void run()
    								{
    									bird.move(SPACE / 4, 0);
    						            bird.turnRight();
    						            steps += 1;
    						            isMoving = false;
    						            repaint();
    								}
    				    	    }, 100);
    						}
    		    	    }, 100);
    				}
        	    }, 100);
                
                break;
                
                case KeyEvent.VK_UP:
                	
                if (isCompleted)
                {
                    return;
                }
                
                if (checkWallCollision(bird, TOP_COLLISION))
                {
                	bird.turnUp();
                	repaint();
                    return;
                }
                
                if (checkEggCollision(TOP_COLLISION))
                {
                	bird.turnUp();
                	repaint();
                    return;
                }
                
                isMoving = true;
                bird.savePos();
                bird.move(0, -SPACE / 4);
                bird.hopUp();
                repaint();
                
        		timer.schedule(new TimerTask()
        		{
    				@Override
    				public void run()
    				{
    					bird.move(0, -SPACE / 4);
    		            bird.turnUp();
    		            repaint();
    		            
    		    		timer.schedule(new TimerTask()
    		    		{
    						@Override
    						public void run()
    						{
    							bird.move(0, -SPACE / 4);
    				            bird.hopUp();
    				            repaint();
    				            
    				    		timer.schedule(new TimerTask()
    				    		{
    								@Override
    								public void run()
    								{
    									bird.move(0, -SPACE / 4);
    						            bird.turnUp();
    						            steps += 1;
    						            isMoving = false;
    						            repaint();
    								}
    				    	    }, 100);
    						}
    		    	    }, 100);
    				}
        	    }, 100);
                
                break;
                
                case KeyEvent.VK_DOWN:
                	
                if (isCompleted)
                {
                    return;
                }
                
                if (checkWallCollision(bird, BOTTOM_COLLISION))
                {
                	bird.turnDown();
                	repaint();
                    return;
                }
                
                if (checkEggCollision(BOTTOM_COLLISION))
                {
                	bird.turnDown();
                	repaint();
                    return;
                }
                
                isMoving = true;
                bird.savePos();
                bird.move(0, SPACE / 4);
                bird.hopDown();
                repaint();
                
        		timer.schedule(new TimerTask()
        		{
    				@Override
    				public void run()
    				{
    					bird.move(0, SPACE / 4);
    		            bird.turnDown();
    		            repaint();
    		            
    		    		timer.schedule(new TimerTask()
    		    		{
    						@Override
    						public void run()
    						{
    							bird.move(0, SPACE / 4);
    				            bird.hopDown();
    				            repaint();
    				            
    				    		timer.schedule(new TimerTask()
    				    		{
    								@Override
    								public void run()
    								{
    									bird.move(0, SPACE / 4);
    						            bird.turnDown();
    						            steps += 1;
    						            isMoving = false;
    						            repaint();
    								}
    				    	    }, 100);
    						}
    		    	    }, 100);
    				}
        	    }, 100);
                
                break;
                
                
                
                case KeyEvent.VK_BACK_SPACE:
                
                if (bird.isMoved)
                {
                	bird.undoStep();
                	
                	for (Egg egg : eggs)
                	{
                		if (egg.isMoved)
                		{
                			egg.undoStep();
                			isCompleted();
                			egg.isMoved = false;
                		}
                	}
                	
                	bird.isMoved = false;
                }
                
                break;
                
                case KeyEvent.VK_R:
                
                restartLevel();
                
                break;
                
                case KeyEvent.VK_0:
                
                level = Levels.LEVEL_0;
                level_num = "Level 0";
                
                restartLevel();
                
                break;
                
                case KeyEvent.VK_1:
                
                level = Levels.LEVEL_1;
                level_num = "Level 1";
                
                restartLevel();
                
                break;
                
                case KeyEvent.VK_2:
                
                level = Levels.LEVEL_2;
                level_num = "Level 2";
                
                restartLevel();
                
                break;
                
                case KeyEvent.VK_3:
                
                level = Levels.LEVEL_3;
                level_num = "Level 3";
                
                restartLevel();
                
                break;
                
                case KeyEvent.VK_4:
                
                level = Levels.LEVEL_4;
                level_num = "Level 4";
                
                restartLevel();
                
                break;
                
                case KeyEvent.VK_B:
                
                for(Wall wall : walls)
                {
                    wall.changeWallBrick();
                }
                
                break;
                
                case KeyEvent.VK_I:
                
                for(Wall wall : walls)
                {
                    wall.changeWallIce();
                }
                
                break;
                
                case KeyEvent.VK_S:
                
                for (Wall wall : walls)
                {
                    wall.changeWallStone();
                }
                
                break;
                
                case KeyEvent.VK_W:
                
                for (Wall wall : walls)
                {
                    wall.changeWallWood();
                }
                
                break;
                
                default:
                break;
            }
            
            repaint();
        }
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
            
            for (Egg egg : eggs)
            {
                if (bird.isLeftCollision(egg))
                {
                    for (Egg item : eggs)
                    {
                        if (!egg.equals(item))
                        {    
                            if (egg.isLeftCollision(item))
                            {
                                return true;
                            }
                        }
                        
                        if (checkWallCollision(egg, LEFT_COLLISION))
                        {
                            return true;
                        }
                    }
                    
                    egg.setImage1();
                    egg.savePos();
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
        									isCompleted();
        						            repaint();
        								}
        				    	    }, 100);
        						}
        		    	    }, 100);
        				}
            	    }, 100);
                }
            }
            
            return false;
            
            case RIGHT_COLLISION:
            
            for (Egg egg : eggs)
            {
                if (bird.isRightCollision(egg))
                {
                    for (Egg item : eggs) 
                    {
                        if (!egg.equals(item))
                        {
                            if (egg.isRightCollision(item))
                            {
                                return true;
                            }
                        }
                        
                        if (checkWallCollision(egg, RIGHT_COLLISION))
                        {
                            return true;
                        }
                    }
                    
                    egg.setImage1();
                    egg.savePos();
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
        									isCompleted();
        						            repaint();
        								}
        				    	    }, 100);
        						}
        		    	    }, 100);
        				}
            	    }, 100);
                }
            }
            
            return false;
            
            case TOP_COLLISION:
                
            for (Egg egg : eggs)
            {
                if (bird.isTopCollision(egg))
                {
                    for (Egg item : eggs)
                    {
                        if (!egg.equals(item))
                        {
                            if (egg.isTopCollision(item))
                            {
                                return true;
                            }
                        }
                        
                        if (checkWallCollision(egg, TOP_COLLISION))
                        {
                            return true;
                        }
                    }
                    
                    egg.setImage1();
                    egg.savePos();
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
        									isCompleted();
        						            repaint();
        								}
        				    	    }, 100);
        						}
        		    	    }, 100);
        				}
            	    }, 100);
                }
            }

            return false;
                
            case BOTTOM_COLLISION:
                
            for (Egg egg : eggs)
            {
                if (bird.isBottomCollision(egg))
                {
                    for (Egg item : eggs)
                    {
                        if (!egg.equals(item)) 
                        {
                            if (egg.isBottomCollision(item))
                            {
                                return true;
                            }
                        }
                            
                        if (checkWallCollision(egg,BOTTOM_COLLISION))
                        {
                            return true;
                        }
                    }
                    
                    egg.setImage1();
                    egg.savePos();
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
        									isCompleted();
        						            repaint();
        								}
        				    	    }, 100);
        						}
        		    	    }, 100);
        				}
            	    }, 100);
                }
            }
            
            break;
            
            default:
            break;
        }
        
        return false;
    }
    
    public void isCompleted()
    {
        nOfEggs = eggs.size();
        finishedEggs = 0;
        
        for (Egg egg : eggs)
        {
            for (Nest nest: nests)
            {
                if (egg.x() == nest.x() && egg.y() == nest.y())
                {
                    finishedEggs += 1;
                    egg.setImage2();
                }
            }
        }
        
        if (finishedEggs == nOfEggs)
        {
            isCompleted = true;
            repaint();
        }
    }
    
    private void restartLevel()
    {
        nests.clear();
        eggs.clear();
        walls.clear();
        
        steps = 0;
        
        initWorld();
        isCompleted();
        
        if (isCompleted)
        {
            isCompleted = false;
        }
    }
}
