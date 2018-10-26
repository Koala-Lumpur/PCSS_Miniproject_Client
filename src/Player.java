import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {

	public static ArrayList<Player> player = new ArrayList<Player>();
	private String playerName;
	private String playerClass;
	private int maxHealth;
	private int currentHealth;
	private int minDamage;
	private int maxDamage;
	private Random random = new Random();
	private Scanner inputScanner = new Scanner(System.in);
	private String playerTeam;
	static boolean ready;

	public Player(String playerName, String playerClass, int maxHealth, String playerTeam) {
		this.playerName = playerName;
		this.playerClass = playerClass;
		this.maxHealth = maxHealth;
		currentHealth = maxHealth;
		this.playerTeam = playerTeam;
	}

	public Player(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerClass() {
		return playerClass;
	}

	public void setPlayerClass(String playerClass) {
		this.playerClass = playerClass;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public String getPlayerTeam() {
		return playerTeam;
	}

	public void setPlayerTeam(String playerTeam) {
		this.playerTeam = playerTeam;
	}


	public enum Choice {
		WARRIOR, RANGER, MAGE, ASSASSIN;
	}

	//Gives the player a choice between 4 classes with 4 different inputs
	public Choice getChoice() {
		char firstLetter = 'B';
		System.out.print("What class would you like to play " + playerName + "? Warrior, Ranger, Mage or Assassin?");
		String playerInput = inputScanner.nextLine();
		playerInput = playerInput.toUpperCase();
		try {
			firstLetter = playerInput.charAt(0);
		} catch (StringIndexOutOfBoundsException e) {}
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
		System.out.println("\nInvalid choice, please try again.");
		return getChoice();
	}
	
	//Gives the player the option to join a team
	public void chooseTeam() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Please select a team (1/2): ");
		String playerInput = inputScanner.nextLine();
		switch(playerInput) {
		case "1":
			//System.out.println("You have joined team 1.");
			setPlayerTeam("Team 1");
			break;
		case "2":
			//System.out.println("You have joined team 2.");
			setPlayerTeam("Team 2");
			break;
		default:
			System.out.println("Invalid choice, plaese select 1 or 2. \n");
			chooseTeam();
			break;
		}
	}
	
	//Gives the user a command option such as help or ready
	public void handleCommand(String s) {
		//String playerInput = inputScanner.nextLine();

		switch(s) {
		case "help":
			System.out.println("help - list of commands \nstats - show character stats"
					+ "\nready - ready up for starting the game \nshowteam - show joined team");
			break;
			//This doesn't work as intended (doesn't send to the server)	
		case "switch":
			Client.teamChosen = !Client.teamChosen;
			break;

		case "disconnect":
			try {
				Client.out.close();
				Client.in.close();
				Client.socket.close();
			} catch (IOException e1) {}
			break;

		case "stats":
			CharacterClass.printStats();
			break;
			//THIS SHOULD ALSO DISPLAY TEAM MEMBERS
		case "showteam":
			System.out.println(playerTeam);
			break;

		case "ready":
			ready = true;
			try {
				Client.out.writeInt(Client.index);
				Client.out.writeBoolean(ready);
			} catch (IOException e) {}
			break;
		default:
			System.out.println("Invalid command, type help for a list of commands. \n");
		}
	}
}
/*public void readyCheck() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Please state when you are ready to enter combat ");
		String ready = inputScanner.nextLine();
		switch(ready) {
		case "Ready":
			team1 = true;
			break;
		case "Not ready":
			team2 = false;
			break;
		default:
			System.out.println("Invalid choice, please try again \n");
			chooseTeam();
			break;
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