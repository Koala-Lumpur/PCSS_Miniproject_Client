import java.util.Random;
import java.util.Scanner;

public final class CharacterClass {
	static Scanner scan = new Scanner(System.in);
	static Random rand = new Random();
	static Dice die = new Dice();
	public static String playerName;
	public static int playerhp;
	public static int maxhp;
	public static int playermeleedmg;
	public static int playerrangedmg;
	public static int playerfireball;
	public static String charclass;
	public static boolean fighting = false; // globals for player stats & enemy stats

	//printing out a players stats
	static void printStats() {
		if (charclass.equals("mage")) {
			System.out.println(Client.playerName + "\nhp: " + playerhp + "\ndamage: " + playermeleedmg
					+ "\n");
		} else {
			System.out.println(Client.playerName + "\nhp: " + playerhp + "\ndamage: " + playermeleedmg + "\n");
		}
	}

	//printing out the enemy stats
	private static void printEnemyStats() {
		System.out.println("Enemy " + "\nhp: " + "\ndmg: " + "\n");
	}

	//In the below classes both melee damage and ranged damage has been assigned variables.
	//These variables did not end up being used
	//MaxHP was a remnant of the initial idea that players would roll their starting health (using the dice class)
	
	//building the warrior class
	public static void buildWarrior() {
		charclass = "warrior";
		maxhp = 18;
		playerhp = 18;
		playermeleedmg = 4;
	}

	//building the ranger class
	public static void buildRanger() {
		charclass = "ranger";
		maxhp = 14;
		playerhp = 14;
		playermeleedmg = 2;
		playerrangedmg = 4;
	}

	//building the assassin class
	public static void buildAssassin() {
		charclass = "assassin";
		maxhp = 14;
		playerhp = 14;
		playermeleedmg = 4;
	}

	//building the mage class
	public static void buildMage() {
		charclass = "mage";
		maxhp = 12;
		playerhp = 12;
		playermeleedmg = 2; //if the mage chooses the melee attack, they will regain 6 mana
		playerfireball = 6; //fireball should cost 8 mana for each use
	}

}
