# Java-Sokoban-Game
Java Sokoban game source code

http://zetcode.com/tutorials/javagamestutorial/sokoban/

https://github.com/Drachenbauer/AngryBirdsSokoban

![Sokoban game screenshot](sokoban_game.png)

This is now an Angry Birds style version.

I turned the player-dot into a bird from the Angry Birds flock.  
I turned the baggage into eggs.  
I turned the storage area into nests.  
And I added six wall-styles similar to the piggie-construct-materials in the official Angry Birds Games.


code-changes:

-four direction-sprites and hopping-animation for the bird (player)

-many levels to choose from by dialog

-solving a level starts the next one

-eight wall-styles to choose from

-added progress-strings to show number of solved eggs and as a step-counter

-added an undo-steps-function


Controls:

arrow-keys:  
Move the bird.  
Move against eggs to push them.  
You can´t push more than one egg with one move.  
If you try to push two or more eggs in a row, your bird doesn´t move.

backspace-key:  
Undo moves.  
You can undo all moves you made so far in the level.

R-key:  
Restart the actual level.

number-keys (0-9):  
Choose your level to solve.

B-key:  
Change wall-style to brick-wall.

I-key:  
Change wall-style to ice-cubes.

S-key:  
Change wall-style to stone-blocks.

W-key:  
Change wall-style to wood-planks.

Press a key for the wall-styles again to switch to the "empty-frame"-version of this style and back to "solid".