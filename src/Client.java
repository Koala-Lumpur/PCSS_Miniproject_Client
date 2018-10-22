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
	
	//Constructor
	Client(String name){
		threadName = name;
	}
	
	//Method for starting the Client
	public static void startClient() {
		System.out.println("Welcome to our game.");
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
			out.writeBytes(playerName+"\n");
			out.writeBytes(playerChoice+"\n");
			while(true) {
				if(!teamChosen) {
					player.chooseTeam();
					String playerTeam = player.team();
					teamChosen = !teamChosen;
					out.writeBytes(playerTeam+"\n");
				} else {
					player.handleCommand(input.nextLine());
				}
			}
		}catch(IOException ex) {
			System.out.println("Unable to connect to server.");
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
				System.out.println("");
				System.out.println(in.readLine());
			}
		} catch (IOException e) {
			System.out.println("ERROR");
		}
	}
}
