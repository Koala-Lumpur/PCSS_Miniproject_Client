import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {
	//The player names are stored in an array list
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

	//Gives the user the option to make commands such as help or ready
	public void handleCommand(String s) {

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
			
			//Remnant of the point where characters had significant stats such as maxHP, melee damage, ranged damage, etc)

		case "stats":
			CharacterClass.printStats();
			break;
			
			//This should print the players on the 2 teams (Similarly to what happens at the start of each round) 
			
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
