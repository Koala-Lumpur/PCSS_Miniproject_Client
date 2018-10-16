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
	
	//Constructor
	Client(String name){
		threadName = name;
	}
	
	//Method for starting the Client
	public static void startClient() {
		//Entering player name
		while(isBlank(playerName)) {
			System.out.print("Enter player name: ");
			playerName = input.nextLine();
			if(isBlank(playerName)) {
				System.out.println("Invalid name");
			}
		}
		
		//Joining the server and sending the player name
		try {
			socket = new Socket("localhost", 8000);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			Client clientThread = new Client("clientThread");
			clientThread.start();
			out.writeBytes(playerName+"\n");
			while(true) {}
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
				System.out.println(in.readLine());
			}
		} catch (IOException e) {
			System.out.println("ERROR");
		}
	}
}
