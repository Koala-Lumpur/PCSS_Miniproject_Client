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
	static boolean clientGameStarted;
	static boolean playerInfoReceived;

	//Constructor
	Client(String name){
		threadName = name;
	}

	//Method for starting the Client
	public static void startClient() {
		
		//System.out.println(Combat.mageAttack2());
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
			//Pairing Character Class to the Enums
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
			//System.out.println("ClientNoIndex read.");
			out.writeBytes(playerName+"\n");
			out.writeBytes(playerChoice+"\n");
			out.writeInt(CharacterClass.playerhp);
			
			while(true) {
				if(!teamChosen) {
					player.chooseTeam();
					teamChosen = !teamChosen;
					out.writeInt(index);
					out.writeBytes(player.getPlayerTeam()+"\n");
				} else if (!Player.ready) {
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
		startGame();
	}

	//reads from the server
	public static void startGame() {
		try {
			while(true) {
				if(index == 0) {
					index = in.readInt();
				}
				if(!clientGameStarted) {
					String fromServer = in.readLine();
					System.out.println("\n" + fromServer);

					if(fromServer.equals("Game is starting in... 1")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {}
						clientGameStarted = true;
						System.out.println("\nThe game has started.");
					}
				}
				if(clientGameStarted) {
					if(!playerInfoReceived) {
						for(int i = 0; i < 4; i++) {
							String otherPlayerName = in.readLine();
							String otherPlayerClass = in.readLine();
							String otherPlayerTeam = in.readLine();
							int otherMaxHealth = in.readInt();
							Player.player.add(new Player(otherPlayerName, otherPlayerClass, 
									otherMaxHealth, otherPlayerTeam));
							System.out.println(Player.player.get(i).getPlayerName());
							System.out.println(Player.player.get(i).getPlayerClass());
							System.out.println(Player.player.get(i).getMaxHealth());
							System.out.println(Player.player.get(i).getPlayerTeam());
						}
						playerInfoReceived = true;
					}
					Combat.newTurn();
				}
			}
		} catch (IOException e) {
			System.out.println("Disconnected from the server.");
		}
	}
}
