/*
 *	Name: Loren Davies CSC-111-001
 *	Class: player
 *	Description: This class works with the shooting of the word at each other
*/

import java.util.ArrayList;
import java.util.List;

public class player
{
	// Class constants

	// Class variables
	private List<String> ammo;

	public player()
	{
		// Local constants

		// Local variables
		ammo = new ArrayList<String>();

		/********************* Start constructor *********************/

    }

    public void loadAmmo(String word)
    {
		// Local constants

		// Local variables


		/********************* Start loadAmmo *********************/

		// Add the inputed word to the list
		ammo.add(word);
	}

	public String shoot()
	{
		// Local constants

		// Local variables
		String shot;											// Shot towards the other worrd

		/********************* Start shoot *********************/

		// Shot equals the ammo size minus one
		shot = ammo.get(ammo.size() - 1);

		// Takes the word off the list
		ammo.remove(ammo.size() - 1);
		return shot;
    }
}