/*
 *	Name: Loren Davies CSC-111-001
 *	Class: wordWar.java
 *	Description: Application class. This class calls all the functions needed to run the game
 *
 *  Pseudo code:
 *	BEGIN wordWar
 *		Welcome screen
 *		WHILE continue equals 1
 *			Get the difficulty the user wants
 *			WHILE their input insn't valid
 *				Get the difficulty the user wants
 *			END WHILE
 *			Call the method to input the words
 *			Call the method that removes the like letters and scores
 *			Prompt the user to see if they want to continue
 *			IF the user chooses not to cont = 0
 *				cont = 0
 *			END IF
 *		END WHILE
 *	END wordWar
 *
 *  Program description: This game allows you to go to war with the computer! Your weapons are words
 *  For example, with the letters you are given you create the word cat. However, the computer chooses the word
 *  kitten. There is no C in kitten, and there is no A, in kitten. However, there is a T in kitten. Since both
 *  words conatin the letter T, they both cancel. The computer is left with Kiten, and the user is left with Ca.
 *  Since the computer has more letters left over, the computer wins.
 *
 *	game.java
 *  Description: This class contains all the funtions pertaining to the user's input
 *
 *  player.java
 *	Description: This class works with the shooting of the word at each other
 */

import java.util.Scanner;
import javax.swing.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class WordWar
{
	// Class constants

	// Class variables

	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		// Local contants

		// Local variables
		game war = new game();									// Create a new instance of the game
		int cont = 1;											// Variable to continue
		String diff;											// Difficulty String
		int difficulty;											// Difficulty parsed

		/********************* Start main *********************/

		// Welcome screen
		JOptionPane.showMessageDialog (null, "--- Word War ---\nThe object of the game is have more letters left over than the computer.\nIf the letters in each word match, they both cancel.\nThe player with the most letters left over wins!");

		// WHILE continue equals
		do
		{
			// Get the difficulty the user wants
			diff = JOptionPane.showInputDialog("Insert difficulty\n1. Easy (Computer uses a word with 4 or less characters)\n2. Medium(Computer uses a word with 6 or less characters)\n3. Hard(Computer uses a word with 8 or less characters)");
			difficulty = Integer.parseInt(diff);

			// While their input insn't valid
			while(difficulty != 1 && difficulty != 2 && difficulty != 3)
			{
				// Get the difficulty the user wants
				diff = JOptionPane.showInputDialog("Insert difficulty\n1. Easy\n2. Medium\n3. Hard");
				difficulty = Integer.parseInt(diff);
			}

			// Call the method to input the words
			war.start(difficulty);

			// Call the method that removes the like letters and scores
			JOptionPane.showMessageDialog (null, war.determine());

			// Prompt the user to see if they want to continue
			if (JOptionPane.showInputDialog("Would you like to play again? (Y/N) : ").equalsIgnoreCase("N"))
				// If the user chooses not to cont = 0
            	cont = 0;
		}
		while (cont == 1);
	}
}