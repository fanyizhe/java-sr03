package socket;

import java.net.*;
import java.io.*;

public class ClientAllumette {

	private static String command ="";

	public static void main(String[] args) {
		System.out.println("Client....");
		
		try(
				Socket comm = new Socket("127.0.0.1",10080);
				DataInputStream in = new DataInputStream(comm.getInputStream());
				DataOutputStream out = new DataOutputStream(comm.getOutputStream());
				
				BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in))

				
) {
			System.out.println("connexion r¨¦ussite ¡­");
			
			Thread tRead = new Thread(()->{
				while (!command.equalsIgnoreCase("exit")) {
					try {
						System.out.println(in.readUTF());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} );
			tRead.start();
			while(true) {
				command = keyboardIn.readLine();
				if (command.equals("end")) {
					break;
				}
				out.writeUTF(command);
				out.flush();			
			
			}
			tRead.stop();
			comm.close();
			
		} catch(ConnectException e) {
			System.out.println("Server doesn't run");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
