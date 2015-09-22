/*
 *	Name: Loren Davies CSC-111-001
 *	Class: game
 *	Description: This class contains all the funtions pertaining to the user's input
*/

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;

public class game
{
	// Class constants

	// Class variables
	private player user;							// The player, user
    private player comp;							// The player, computer
    private ArrayList<Character> letters;			// ArrayList containing the letters
    private String compWord;						// The computer's word
    private int difficulty;							// The game difficulty

	public game()
	{
		// Constructor constancts

		// Constructor varables
		user = new player();						// create a new player called user
		comp = new player();						// create a new player called computer
		letters = new ArrayList<Character>();		// ArrayList containing the letters

		/********************* Start constructor *********************/

	}

	public void genLetters()
	{
		// Local constants

		// Local variables
		Random rand = new Random();										// New random
		String alphabet = "abcdefghijklmnopqrstuvwxyzetaoinshrdlcum";   // Letters to be used. Some are repeated to increase frequency
																		// chances of them getting picked

		/********************* Start genLetters *********************/

		// Clear the letters
		letters.clear();

		// Grab 10 random letters from the String alaphabet
		for (int i = 0; i < 12; i++)
			letters.add(alphabet.charAt(rand.nextInt(alphabet.length())));

		// If letters doesn't contain any vowels, gen letters again
		if (!letters.contains('a') && !letters.contains('e') && !letters.contains('i') && !letters.contains('o') && !letters.contains('u'))
            genLetters();

	}

	public String formatLetters()
	{
		// Local constants

		// Local variables
		String formatedLetters = "";									// The varaible to hold the fomated string

		/********************* Start formatLetters *********************/

		// While i is less than the size of the letters
		for(int i = 0; i < letters.size(); i++)
			// If it's not the last letter, put a comma
			if(i < letters.size() - 1)
				formatedLetters += letters.get(i) + ", ";
			// Else it's the last letter, put a comma
			else
				formatedLetters += letters.get(i);

		// Return the formated String
		return formatedLetters;
	}

	public void start(int diff) throws IOException, ClassNotFoundException
	{
		// Local constants

		// Local variables
		String userWord;												// The word the user inputs
		boolean valid = false;

		/********************* Start start *********************/

		// Set the class wide difficulty
		difficulty = diff;

		// Generate the letters the user can use from
		genLetters();

		// Input the user's word, also call the formatLetters function
		userWord = JOptionPane.showInputDialog("Your letters are " + formatLetters() + "\nEnter a word using those letters: ");

		while(verifyWord(userWord) == false)
		{
			// Notify the user that it isn't valid
			JOptionPane.showMessageDialog (null, "You can't make that word with those letters!");

			// Input the user's word, also call the formatLetters function
			userWord = JOptionPane.showInputDialog("Your letters are " + formatLetters() + "\nEnter a word using those letters: ");
		}

		// Send the word to loadAmmo
		user.loadAmmo(userWord);

		// Call the function to generate the computer's word
		compWord = compWord();

		// Send the computer's word to loadAmmo
		comp.loadAmmo(compWord());
	}

	private Boolean verifyWord(String word)
	{
		// Local constants

		// Local variables
		ArrayList<Character> tempLetters = new ArrayList<Character>(letters);				// Copy the ArrayList with the letters to a temp ArrayList

		/********************* Start verifyWord *********************/

		// For every character
	    for (Character c : word.toCharArray())
	    {
			// If the char matches one of the chars provided to the user
	    	if (tempLetters.contains(c))
	    		// Remove it
	        	tempLetters.remove(c);
	        // The word isn't valid
	        else
	            return false;
	    }

		// The word is valid
	    return true;
    }

  	public String compWord() throws IOException, ClassNotFoundException
  	{
		// Local constants
		final int MAX_COUNT = 8;

		// Local variables
		BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"));		// Create a new BufferedReader, looking for dictionary.txt
		List<String> lines = new ArrayList<String>();										// New ArrayList to keep track of the lines
		String line;																		// Current line
		Random rand = new Random();															// New random object
		String word = null;																	// The computer's word

		/********************* Start compWord *********************/

		// Start reading the txt file
		line = reader.readLine();

		// WHILE the line isn't null
		while(line != null)
		{
			// Add the line to lines list
		    lines.add(line);

		    // Go to the next line
		    line = reader.readLine();
		}

		// Switch the difficulty
		switch(difficulty)
		{
			// Easy difficulty
			case 1:
				// WHILE the word length is bigger than 4
				do
				{
					// Set the computers word to a random word in the list
					word = lines.get(rand.nextInt(lines.size()));
				}
				while (word.length() > 4);
			break;

			// Medium difficulty
			case 2:
				// WHILE the word length is greater than 6
				do
				{
					// Set the computers word to a random word in the list
					word = lines.get(rand.nextInt(lines.size()));
				}
				while (word.length() > 6);
			break;

			// Hard difficulty
			case 3:
				// WHILE the word length is greater than 8
				do
				{
					// Set the computers word to a random word in the list
					word = lines.get(rand.nextInt(lines.size()));
				}
				while (word.length() > 8);
			break;
		}

		// Close the file reader
		reader.close();

		// Return the computer's word
		return word;
	}

	public String determine()
	{
		// Local constants

		// Local variables
		String shootUser = user.shoot();						// New shoot object for user
		String shootComp = comp.shoot();						// New shoot object for computer

		int userPts = 0;										// User points
		int compPts = 0;										// Computer points

		/********************* Start determine *********************/

		// For every char in the user's word
		for(int i = 0; i < shootUser.length(); i++)
			// Check to see if it matches the comp, then add points
			userPts += (int) shootUser.charAt(i);

		// For every char in the comp's word
		for(int i = 0; i < shootComp.length(); i++)
			// Check to see if it matches the comp, then add points
			compPts += (int) shootComp.charAt(i);

		// If user has higher points
		if (userPts > compPts)
		    return "The computer's word was " + compWord + "\nYou win the battle!";
		// Else if computer has higher points
		else if (compPts > userPts)
		    return "The computer's word was " + compWord + "\nThe computer wins the battle!";
		// Else it is a tie
		else
			return "The computer's word was " + compWord + "\nIt's a tie!";
	}
}