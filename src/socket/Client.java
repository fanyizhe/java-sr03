package socket;

import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) {
		System.out.println("Client....");
		
		try(
				Socket comm = new Socket("127.0.0.1",10082);
				DataInputStream in = new DataInputStream(comm.getInputStream());
				DataOutputStream out = new DataOutputStream(comm.getOutputStream());
				
				BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in))

				
) {
			System.out.println("connexion r¨¦ussite ¡­");
			while(true) {
				
				String keyboardInputString = keyboardIn.readLine();
				if (keyboardInputString.equals("end")) {
					break;
				}
				out.writeUTF(keyboardInputString);
				out.flush();			
				byte[] b = new byte[1024];
				if (in.read(b) != -1) {
					System.out.println("Le Server a dit:" + new String(b));					
				}
				
			}


			
		} catch(ConnectException e) {
			System.out.println("Server doesn't run");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
