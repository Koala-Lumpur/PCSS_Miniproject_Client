	import java.util.Random;
	import java.util.Scanner;
	import java.util.concurrent.ThreadLocalRandom;

	public class Combat {
		private static Scanner in;
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
		static int turnNumber;
		boolean actionTaken = false;
		
		
		public static void newTurn() {
			turnNumber++;
			attack();
		}
		
		public static void attack() {
			System.out.println("Please choose an attack (1) \n");
			String playerInput = in.nextLine();
			switch(playerInput) {
			case "1":
				//System.out.println("You have joined team 1.");
				mageAttack1();
				break;
			default:
				System.out.println("Invalid choice, plaese select 1. \n");
				attack();
				break;
			}
			
		}
		
		
		public static int mageAttack1() {
			
			return 2;
			
		}
		
        public static int mageAttack2() {
        
			return ThreadLocalRandom.current().nextInt(1, 3 +1);
			
		}
        
        public static int mageAttack3() {
			
        	return 4;
			
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
			
			return 2;
			
		}
        public static int RangerAttack1() {
			
			return 2;
			
		}
        
        public static int RangerAttack2() {
			
			return 2;
			
		}

        public static int RangerAttack3() {
			
			return 2;
			
		}
        
        public static int RangerAttack4() {
			
			return 2;
			
		}
        
        public static int AssassinAttack1() {
			
			return 2;
			
		}
        
        public static int AssassinAttack2() {
			
			return 2;
			
		}

        public static int AssassinAttack3() {
			
			return 2;
			
		}
        
        public static int AssassinAttack4() {
			
			return 2;
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
