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

	static void printStats() {
		if (charclass.equals("mage")) {
			System.out.println(Client.playerName + "\nhp: " + playerhp + "\ndamage: " + playermeleedmg
					+ "\n");
		} else {
			System.out.println(Client.playerName + "\nhp: " + playerhp + "\ndamage: " + playermeleedmg + "\n");
		}
	}

	private static void printEnemyStats() {
		System.out.println("Enemy " + "\nhp: " + "\ndmg: " + "\n");
	}

	public static void buildWarrior() {
		charclass = "warrior";
		maxhp = 18;
		playerhp = 18;
		playermeleedmg = 4;
	}

	public static void buildRanger() {
		charclass = "ranger";
		maxhp = 14;
		playerhp = 14;
		playermeleedmg = 2;
		playerrangedmg = 4;
	}

	public static void buildAssassin() {
		charclass = "assassin";
		maxhp = 14;
		playerhp = 14;
		playermeleedmg = 4;
	}

	public static void buildMage() {
		charclass = "mage";
		maxhp = 12;
		playerhp = 12;
		playermeleedmg = 2; //if the mage chooses the melee attack, they will regain 6 mana
		playerfireball = 6; //fireball should cost 8 mana for each use
	}

}
