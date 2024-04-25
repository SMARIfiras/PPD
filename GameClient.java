package projet;

import java.io.*;
import java.net.*;
import java.util.*;
public class GameClient {
	public static void main(String[] args) {
		
		
		try {
			Scanner fromConsole =  new Scanner(System.in);
			Socket socket = new Socket("localhost",33333);
			Scanner fromServer =  new Scanner(socket.getInputStream());
			PrintWriter fromClient = new PrintWriter(socket.getOutputStream(),true);

String input,output;

while(true) {
	
	System.out.print("Client :  ");	
	input = fromConsole.nextLine();
	
	fromClient.println(input);
	if(input.equals("exit")) {
		break;
		}
	output = fromServer.nextLine();
	if(input.equals("exit")) {
		break;
	}
	System.out.print("Server :  ");
    System.out.println(output);
    
}
		socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		
	}

}
