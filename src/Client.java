import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client implements Runnable {
	
	static String playerName;
	static Scanner input = new Scanner(System.in);
	static Socket socket;
	static DataInputStream in;
	static DataOutputStream out;
	public String threadName;
	public Thread t;
	static Player player;
	static boolean teamChosen = false;
	static int index;
	
	//Constructor
	Client(String name){
		threadName = name;
	}
	
	//Method for starting the Client
	public static void startClient() {
		System.out.println("Welcome to Dungeons & Mille PVP mode. For a list of commands, type \"help\".");
		//Entering player name
		while(isBlank(playerName)) {
			System.out.print("Please enter player name: ");
			playerName = input.nextLine();
			player = new Player(playerName);
			if(isBlank(playerName)) {
				System.out.println("Invalid name");
			}
			//Player.Choice playerChoice = player.getChoice();
			//System.out.println(playerName + " has chosen the " + playerChoice + " class.");
		}
		
		//Joining the server and sending the player name
		//Rasmus hamachi IP - 25.20.154.243
		try {
			socket = new Socket("localhost", 8000);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			Client clientThread = new Client("clientThread");
			clientThread.start();
			Player.Choice playerChoice = player.getChoice();
			System.out.println("You have chosen the " + playerChoice + " class.");
			//Pariring Character Class to the Enums
			switch(playerChoice) {
			case WARRIOR:
				CharacterClass.buildWarrior();
				//CharacterClass.printStats();
				break;
			case RANGER:
				CharacterClass.buildRanger();
				//CharacterClass.printStats();
				break;
			case MAGE:
				CharacterClass.buildMage();
				//CharacterClass.printStats();
				break;
			case ASSASSIN:
				CharacterClass.buildAssassin();
				//CharacterClass.printStats();
				break;
			}
			//index = in.readInt();
			System.out.println("ClientNoIndex read.");
			out.writeBytes(playerName+"\n");
			out.writeBytes(playerChoice+"\n");
			while(true) {
				if(!teamChosen) {
					player.chooseTeam();
					teamChosen = !teamChosen;
					out.writeInt(index);
					out.writeBytes(Player.playerTeam+"\n");
				} else {
					player.handleCommand(input.nextLine());
				}
			}
		}catch(IOException ex) {
			System.out.println("Unable to connect to server. "
					+ "Please make sure that the server is running.");
		}
	}

	//Boolean for checking if the name is blank or only contains spaces
	private static boolean isBlank(String s) {
		return (s == null) || (s.trim().length() == 0);
	}

	//Main method
	public static void main(String[] args) {
		startClient();
	}
	
	//Start method for the threads
	public void start() {
		if( t== null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
	
	//Run method for the threads
	public void run() {
		try {
			while(true) {
				if(index == 0) {
					index = in.readInt();
				}
				System.out.println("");
				System.out.println(in.readLine());
			}
		} catch (IOException e) {
			System.out.println("Disconnected from the server.");
		}
	}
}
