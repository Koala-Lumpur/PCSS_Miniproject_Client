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


	public static void newTurn() {
		turnNumber++;
		attack();
		target();
	}

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
		for (int i = 0; i < 4; i++) {
			System.out.println(Player.player.get(i).getPlayerName());
		}
		String playerInput = in.nextLine();
		try {
			switch(playerInput) {
			case "1":
				if(!playerIsDead(0)) {
				Client.out.writeInt(0);
				Client.out.writeInt(damageDealt);
				System.out.println("You targeted " + Player.player.get(0).getPlayerName() + "!");
				}
				break;
			case "2":
				Client.out.writeInt(1);
				Client.out.writeInt(damageDealt);
				System.out.println("You targeted " + Player.player.get(1).getPlayerName() + "!");
				break;
			case "3":
				Client.out.writeInt(2);
				Client.out.writeInt(damageDealt);
				System.out.println("You targeted " + Player.player.get(2).getPlayerName() + "!");
				break;
			case "4":
				Client.out.writeInt(3);
				Client.out.writeInt(damageDealt);
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
	
	public static boolean playerIsDead(int i) {
		if(Player.player.get(i).getCurrentHealth() <= 0) {
			System.out.println(Player.player.get(i).getPlayerName() + " is dead.");
			target();
			return true;
		} else {
			return false;
		}
		
	}

	public static int mageAttack1() {

		return -2;

	}

	public static int mageAttack2() {

		return -ThreadLocalRandom.current().nextInt(1, 3 +1);

	}

	public static int mageAttack3() {

		return -4;

	}

	public static int mageHeal4() {

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



}








