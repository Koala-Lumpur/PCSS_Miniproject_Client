import java.util.Random;
import java.util.Scanner;

public final class CharacterClass {
	static Scanner scan = new Scanner(System.in);
	static Random rand = new Random();
	static Dice die = new Dice();
	public static String playerName;
	public static int playerhp;
	public static int maxhp;
	public static int maxmana;
	public static int mana;
	public static int playermeleedmg;
	public static int Strength;
	public static int Dexterity;
	public static int Intelligence;
	public static int Constitution;
	public static String charclass;
	public static boolean fighting = false; // globals for player stats & enemy stats

	static void printStats() {
		if (charclass.equals("mage")) {
			System.out.println(Client.playerName + "\nhp: " + playerhp + "\nmana: " + mana + "\ndamage: " + playermeleedmg
					 + "\n");
		} else {
			System.out.println(Client.playerName + "\nhp: " + playerhp + "\ndamage: " + playermeleedmg + "\n");
		}
	}

	private static void printEnemyStats() {
		System.out.println("Enemy " + "\nhp: " + "\ndmg: " + "\n");
	}

	static void buildWarrior() {
		charclass = "warrior";
		maxhp = 20;
		playerhp = 20;
		playermeleedmg = 4;
		Strength = 10;
		Dexterity = 6;
		Intelligence = 5;
		Constitution = 10;
	}

	static void buildRanger() {
		charclass = "ranger";
		maxhp = 14;
		playerhp = 14;
		playermeleedmg = 6;
		Strength = 6;
		Dexterity = 10;
		Intelligence = 6;
		Constitution = 8;
	}

	static void buildAssassin() {
		charclass = "assassin";
		maxhp = 14;
		playerhp = 14;
		playermeleedmg = 6;
		Strength = 8;
		Dexterity = 10;
		Intelligence = 6;
		Constitution = 8;
	}

	static void buildMage() {
		charclass = "mage";
		maxhp = 10;
		playerhp = 10;
		mana = 20;
		maxmana = 20;
		playermeleedmg = 2;
		Strength = 5;
		Dexterity = 5;
		Intelligence = 10;
		Constitution = 10;
	}

}
