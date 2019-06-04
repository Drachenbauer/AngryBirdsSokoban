package main.com.zetcode;

import java.util.ArrayList;
import java.util.Collections;

/*
 * "#" = wall
 * "." = nest
 * "$" = egg
 * "@" = bird
 * "*" = egg in nest
 * "+" = bird in nest
 * " " = empty field
 * 
 * In this version of sokoban you control a bird from the
 * "Angry Birds" - flock and have to push eggs into nests.
 */

public class Levels
{
    public ArrayList<String> levels = new ArrayList<>();
    
    public Levels()
    {
        Collections.addAll(levels, "  ###\n"
                                 + "  #.#\n"
                                 + "###$###\n"
                                 + "#.$@$.#\n"
                                 + "###$###\n"
                                 + "  #.#\n"
                                 + "  ###\n" ,
                                 
                                   "      ###\n"
                                 + "      #.#\n"
                                 + "    ### ###\n"
                                 + "    #.$ $.#\n"
                                 + "  ##### #####\n"
                                 + "  #.#*# #*#.#\n"
                                 + "###$###$###$###\n"
                                 + "#.    $@$    .#\n"
                                 + "###$###$###$###\n"
                                 + "  #.#*# #*#.#\n"
                                 + "  ##### #####\n"
                                 + "    #.$ $.#\n"
                                 + "    ### ###\n"
                                 + "      #.#\n"
                                 + "      ###\n" ,
                                 
                                   "   #######\n"
                                 + "####     #\n"
                                 + "#   .### #\n"
                                 + "# # #    ##\n"
                                 + "# # $ $#. #\n"
                                 + "# #  *  # #\n"
                                 + "# .#$ $ # #\n"
                                 + "##    # # ###\n"
                                 + " # ###.   @ #\n"
                                 + " #     ##   #\n"
                                 + " ############\n" ,
                                 
                                   "    #####\n"
                                 + "    #   #\n"
                                 + "    #$  #\n"
                                 + "  ###  $##\n"
                                 + "  #  $ $ #\n"
                                 + "### # ## #   ######\n"
                                 + "#   # ## #####  ..#\n"
                                 + "# $  $          ..#\n"
                                 + "##### ### #@##  ..#\n"
                                 + "    #     #########\n"
                                 + "    #######\n" ,
                                 
                                   "      ###\n"
                                 + "     ## ##\n"
                                 + "    ##   ##\n"
                                 + "   ##     ##\n"
                                 + "  ##  $ $  ##\n"
                                 + " ##  .   .  ##\n"
                                 + "##           ##\n"
                                 + "#  $   +   $  #\n"
                                 + "##           ##\n"
                                 + " #   .   .   #\n"
                                 + " ##   ...   ##\n"
                                 + "  #         #\n"
                                 + "  ##  $ $  ##\n"
                                 + "   #       #\n"
                                 + "   ##  $  ##\n"
                                 + "    #     #\n"
                                 + "    ## $ ##\n"
                                 + "     #   #\n"
                                 + "     ## ##\n"
                                 + "      ###\n" ,
                                 
                                   "################\n"
                                 + "#...#  ##  #...#\n"
                                 + "#... $ ## $ ...#\n"
                                 + "#... $ ## $ ...#\n"
                                 + "##   $ ## $   ##\n"
                                 + "# $$$$    $$$$ #\n"
                                 + "#      $ $     #\n"
                                 + "##### $ $  #####\n"
                                 + "#####  $@$ #####\n"
                                 + "#     $ $      #\n"
                                 + "# $$$$    $$$$ #\n"
                                 + "##   $ ## $   ##\n"
                                 + "#... $ ## $ ...#\n"
                                 + "#... $ ## $ ...#\n"
                                 + "#...#  ##  #...#\n"
                                 + "################\n" ,
                                 
                                   "####### #### #######\n"
                                 + "#     # #..# #     #\n"
                                 + "# $$  ###..###  $$ #\n"
                                 + "#  $  #......#  $  #\n"
                                 + "# $$  #......#  $$ #\n"
                                 + "#  ######..######  #\n"
                                 + "# $#    #..#    #$ #\n"
                                 + "#  # $$      $$ #  #\n"
                                 + "# $#  ##    ##  #$ #\n"
                                 + "#      $ ## $      #\n"
                                 + "#  #  ## @  ##  #  #\n"
                                 + "####################\n" ,
                                 
                                   "        ######\n"
                                 + "        #    ####\n"
                                 + "        # ##  $ #\n"
                                 + "        #  $    #\n"
                                 + "######### #### ###\n"
                                 + "#...   #   $   $ #\n"
                                 + "#...     $   $  @#\n"
                                 + "#...     $   $   #\n"
                                 + "#...   #   $   $ #\n"
                                 + "######### #### ###\n"
                                 + "        #  $    #\n"
                                 + "        # ##  $ #\n"
                                 + "        #    ####\n"
                                 + "        ######\n" );
    }
} 
