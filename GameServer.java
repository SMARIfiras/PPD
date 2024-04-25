package projet1;
import java.io.*;
import java.net.*;
import java.util.*;
public class GameServer {
	public static void main(String[] args) throws IOException {
ServerSocket serverSocket = new ServerSocket(33333);		

Socket socket = serverSocket.accept();
Scanner fromClient = new Scanner (socket.getInputStream());
Scanner fromCONSOLE = new Scanner (System.in);

PrintWriter fromServer = new PrintWriter(socket.getOutputStream(),true);

String input,output;

while(true) {
	input = fromClient.nextLine();
	System.out.println("Client :  " + input);
	if(input.equals("exit")) {
		break;
	}
	System.out.print("Server :  ");
	output = fromCONSOLE.nextLine();
	if(fromCONSOLE.equals("exit")) {
		break;
	}
	fromServer.println(output);
	
}
socket.close();
	}

}
