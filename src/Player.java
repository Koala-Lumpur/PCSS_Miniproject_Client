import java.util.Random;
import java.util.Scanner;


public class Player {
	
	private String playerName;
	private String description;
	private int maxHitPoints;
	private int hitPoints;
	private int numPotions;
	private int minDamage;
	private int maxDamage;
	private Random random = new Random();
	private Scanner inputScanner;
	private boolean team1;

	public Player(String playerName, int maxHitPoints, int minDamage, int maxDamage, int numPoints) {
		this.playerName = playerName;
		this.maxHitPoints = maxHitPoints;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.numPotions = numPotions;
		this.hitPoints = maxHitPoints;
		inputScanner = new Scanner(System.in);
	}
	
	public Player(String playerName) {
		this.playerName = playerName;
		inputScanner = new Scanner(System.in);
	}
	
	public enum Choice {
		WARRIOR, RANGER, MAGE, ASSASSIN;
	}
	
	
	public Choice getChoice() {
		
		System.out.println("What class would you like to play? Warrior, Ranger, Mage or Assassin?\n");
		String playerInput = inputScanner.nextLine();
		playerInput = playerInput.toUpperCase();
		char firstLetter = playerInput.charAt(0);
		if (firstLetter == 'W' || firstLetter == 'R' || firstLetter == 'M' || firstLetter == 'A') {
			switch (firstLetter) {
			case 'W':
				return Choice.WARRIOR;
			case 'R':
				return Choice.RANGER;
			case 'M':
				return Choice.MAGE;
			case 'A':
				return Choice.ASSASSIN;
			}
		}
		// If the user has not entered a valid input try again.
		return getChoice();	
	}
	
	public void chooseTeam() {
		System.out.println("Please select a team (1/2): ");
		String playerInput = inputScanner.nextLine();
		switch(playerInput) {
		case "1":
			//System.out.println("You have joined team 1.");
			team1 = true;
			break;
		case "2":
			//System.out.println("You have joined team 2.");
			team1 = false;
			break;
		default:
			System.out.println("Invalid choice, plaese select 1 or 2. \n");
			chooseTeam();
			break;
		}
	}
	
	public void handleCommand(String s) {
		//String playerInput = inputScanner.nextLine();
		switch(s) {
		case "help":
			//Show available commands for the client
			
		case "switch":
			System.out.println("You switched teams.");
			team1 = !team1;
		}
	}
	
	public String team() {
		if(team1) {
			return "team 1";
		} else {
			return "team 2";
		}
	}
}


	/*public int attack() {
		return random.nextInt(maxDamage - minDamage + 1) + minDamage;
	}

	public void defend(Player player) {
		int attackStrength = player.attack();
		hitPoints = (hitPoints > attackStrength) ? hitPoints - attackStrength : 0;
		System.out.printf("  " + playerName + " is hit for %d HP of damage (%s)\n", attackStrength, getStatus());
		if (hitPoints == 0) {
			System.out.println("  " + playerName + " has been defeated");
		}
	}

	public void heal() {
		if (numPotions > 0) {
			hitPoints = Math.min(maxHitPoints, hitPoints + 10);
			System.out.printf("  %s drinks healing potion (%s, %d potions left)\n", name, getStatus(), --numPotions);
		} else {
			System.out.println("  You've exhausted your potion supply!");
		}
	}

	public boolean isAlive() {
		return hitPoints > 0;
	}

	public String getStatus() {
		return "Player HP: " + hitPoints;
	}

	@Override
	public String toString() {
		return playerName;
	}

	public String getDescription() {
		return description;
	}

	public static Player newInstance() {
		return new Player("moshi moshi", 40, 6, 20, 10);
	}
}
*/

	/*public static void main(String[] args) {
		String charclass;
        int num = 2;
        while(num > 1){
        System.out.println("Enter your Name: ");
        playerName = scan.nextLine();
        System.out.println("Choose your class: ");
        System.out.println("'w' for warrior");
        System.out.println("'a' for archer");
        System.out.println("'m' for mage");
        System.out.println("'s' for assassin");
        charclass = scan.nextLine();
        while(charclass.charAt(0) != 'w' && charclass.charAt(0) != 'a' && charclass.charAt(0) != 'm'){
            System.out.println("'w' for warrior");
            System.out.println("'a' for archer");
            System.out.println("'m' for mage");
            System.out.println("'s' for assassin");
            charclass = scan.nextLine();
        }
        if(charclass.charAt(0) == 'w'){
            buildWarrior();
        }
        if(charclass.charAt(0) == 'a'){
            buildArcher();
        }
        if(charclass.charAt(0) == 'm'){
            buildMage();
        }
        if(charclass.charAt(0)== 's') {
        	buildAssassin();
        }

	} 

}
*/