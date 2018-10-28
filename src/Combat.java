import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Combat {
	private static Scanner in = new Scanner(System.in);
	Random r1 = new Random();
	Random r2 = new Random();
	Random r3 = new Random();
	int lower = 2;
	int low = 3;
	int high = 5;
	int higher = 7;
	int rand1 = r1.nextInt(high-lower) + lower; //2-5
	int rand2 = r2.nextInt(high-low) + low; // 3-5
	int rand3 = r3.nextInt(higher-low) + low; // 3-7
	static int turnNumber = 0;
	boolean actionTaken = false;
	static int damageDealt;

	//The new turn method is intended to be called every time all 4 players have taken their turn. 
	//The turn number integer was intended to be used for a "cooldown" system, wherein certain attacks were not usable for several rounds.
	public static void newTurn() {
		turnNumber++;
		attack();
		target();
		readServerCalculation();
	}

	//Only two attacks were implemeted here. The invalid choice is a remnant of when only one attack was implemented but has been 
	//left for testing purposes
	public static void attack() {
		System.out.println("Please choose an attack (1) \n");
		String playerInput = in.nextLine();
		switch(playerInput) {
		case "1":
			//System.out.println("You have joined team 1.");
			damageDealt = mageAttack1();
			System.out.println(damageDealt);
			break;
		case "2":
			damageDealt = mageAttack2();
			System.out.println(damageDealt);
		default:
			System.out.println("Invalid choice, plaese select 1. \n");
			attack();
			break;
		}

	}

	public static void target() {
		System.out.println("Select a target (1-4): ");
		for (int i = 0; i < 4; i++) {
			System.out.println(Player.player.get(i).getPlayerName());
		}
		String playerInput = in.nextLine();
		try {
			switch(playerInput) {
			case "1":
				if(!playerIsDead(0)) {
					Client.out.writeInt(0);
					System.out.println("Index sent ");
					Client.out.writeInt(damageDealt);
					System.out.println("damageDealt sent ");
					Client.out.writeInt(Client.index);
					Client.out.writeBoolean(true);
					System.out.println("You targeted " + Player.player.get(0).getPlayerName() + "!");
				}
				break;
			case "2":
				Client.out.writeInt(1);
				System.out.println("Index sent ");
				Client.out.writeInt(damageDealt);
				System.out.println("damageDealt sent ");
				Client.out.writeInt(Client.index);
				Client.out.writeBoolean(true);
				System.out.println("You targeted " + Player.player.get(1).getPlayerName() + "!");
				break;
			case "3":
				Client.out.writeInt(2);
				System.out.println("Index sent ");
				Client.out.writeInt(damageDealt);
				System.out.println("damageDealt sent ");
				Client.out.writeInt(Client.index);
				Client.out.writeBoolean(true);
				System.out.println("You targeted " + Player.player.get(2).getPlayerName() + "!");
				break;
			case "4":
				Client.out.writeInt(3);
				System.out.println("Index sent ");
				Client.out.writeInt(damageDealt);
				System.out.println("damageDealt sent ");
				Client.out.writeInt(Client.index);
				Client.out.writeBoolean(true);
				System.out.println("You targeted " + Player.player.get(3).getPlayerName() + "!");
				break;
			default:
				System.out.println("Invalid input.");
				target();
				break;
			}
		}catch(IOException e){
			System.out.println("Target can't be sent to server");
		}
	}

	//Checks if the player is dead and prints out a message if the player is dead, but does not yet make that player 'untargetable'
	public static boolean playerIsDead(int i) {
		if(Player.player.get(i).getCurrentHealth() <= 0) {
			System.out.println(Player.player.get(i).getPlayerName() + " is dead.");
			target();
			return true;
		} else {
			return false;
		}

	}

	public static void readServerCalculation() {
		try {
			for(int i = 0; i < 4; i++) {
				System.out.println(Client.in.readLine());
				//int index = (Client.in.readInt());
				//System.out.println("Index is: " + index);
				//Player.player.get(index).setCurrentHealth(Client.in.readInt());
				//System.out.println("New health is: " + Player.player.get(index).getCurrentHealth());
			}
		} catch (IOException e) {}
	}

	// Implementation for 16 different attacks (4 attacks for every class)
	// Mage attack 4 returns a positive integer as it is supposed to be a healing spell (Not implemented)
	// Some attacks returned fixed integers, and some return a random; this is because the damage output of the attack was supposed to be within
	//a defined range

	public static int mageAttack1() {

		return -2;

	}

	public static int mageAttack2() {

		return -ThreadLocalRandom.current().nextInt(1, 3 +1);

	}

	public static int mageAttack3() {

		return -4;

	}


	public static int mageAttack4() {

		return 5;

	}

	public static int warriorAttack1() {

		return ThreadLocalRandom.current().nextInt(2, 5 +1);

	}

	public static int warriorAttack2() {

		return ThreadLocalRandom.current().nextInt(1, 7 +1);

	}

	public static int warriorAttack3() {

		return ThreadLocalRandom.current().nextInt(1, 6 +1);

	}

	public static int warriorAttack4() {

		return -3;

	}
	public static int RangerAttack1() {

		return -4;

	}

	public static int RangerAttack2() {

		return ThreadLocalRandom.current().nextInt(2, 5 +1);

	}

	public static int RangerAttack3() {

		return ThreadLocalRandom.current().nextInt(3, 7 +1);

	}

	public static int RangerAttack4() {

		return -2;

	}

	public static int AssassinAttack1() {

		return -3;

	}

	public static int AssassinAttack2() {

		return ThreadLocalRandom.current().nextInt(2, 5 +1);

	}

	public static int AssassinAttack3() {

		return -7;

	}

	public static int AssassinAttack4() {

		return -4;

	}

	//For later implementation here is the list of attack names and descriptions in a print to screen format:
	//While these did not ultimately end up with being implemented they indicate the intent of some of the decisions made in this class
	//


	//static void possibleAttacks() {
	//if (CharacterClass.charclass.equals("mage")) {
	//	System.out.println("\tWhich of the following spells would you like to cast?");

	//	System.out.println("\t1. Acid Splash: "
	//			+ "You throw a vial of acid at your opponent, damaging them 2 hp!");
	//	System.out.println("\t2. Magic Missile: "
	//			+ "You fling darts of pure magic into the air, damaging your opponent 1-3 hp!");
	//	System.out.println("\t3. Lightning Bolt: "
	//			+ "You conjure forth a bolt of lightning, damaging your opponent 4 hp! Cooldown: 2 turns");
	//	System.out.println("\t4. Cure Wounds: "
	//			+ "You summon gusts of healing mists, restoring 5 hp to you and your ally! Cooldown 3 turns");

	//	} else if (CharacterClass.charclass.equals("warrior")){
	// System.out.println("\tWhich of the following attacks would you like to use?");
	// System.out.println("\t1. Shield Bash: "
	//			+ "You bash your mighty shield into your opponent, damaging them 2-5 hp!");
	//	System.out.println("\t2. Cleave: "
	//			+ "You swing your mighty axe through the air in front of you, damaging your opponent 1-7 hp! Cooldown 2 turns" );
	//	System.out.println("\t3. Backhand: "
	//			+ "With a sudden jerk, you backhand an opponent who had gotten a little too close, damaging them 1-6 hp!");
	//	System.out.println("\t4. Kidneyshot: "
	//			+ "You gleefully punch your enemy right where it hurts, dealing 3hp in damage!");

	//  } else if(CharacterClass.charclass.equals("ranger")) {
	//	System.out.println("\tWhich of the following attacks would you like to use?");
	//	System.out.println("\t1. Rapid Shot: "
	//			+ "You fire two arrows at your enemy in rapid succesion, damaging them 4 hp!");
	//	System.out.println("\t2. Kill Command: "
	//			+ "You send forth your animal companion to visciously atatck you enemy, damaging them 2-5 hp!" );
	//	System.out.println("\t3. Fire Arrow: "
	//			+ "You fire a flaming arrow at your opponent, damaging them 3-7 hp! Cooldown: 3 turns");
	//	System.out.println("\t4. Multishot: "
	//			+ "You fire two tilting arrows, damaging BOTH of your opponents 2 hp!");

	// } else {
	//	System.out.println();
	//	System.out.println("\tWhich of the following attacks would you like to use?");
	//	System.out.println("\t1. Backstab: "
	//			+ "You leap out of the shadows and stab your opponent in the back, dealing 3hp in damage!");
	//	System.out.println("\t2. Garrote: "
	//			+ "You wrap a sharp metal wire around your enemy's throat damaging them 2-5 hp! Cooldown: 1 turn");
	//	System.out.println("\t3. Death from above: "
	//			+ "You jump on top of your enemy unleashing a flurry of attacks, dealing 7 hp in damage! Cooldown: 5 turns");
	//	System.out.println("\t4. Hamstring: "
	//			+ "You cut your opponent's hamstring, dealing 4 damage! Cooldown 2 turns");
	//}






}








