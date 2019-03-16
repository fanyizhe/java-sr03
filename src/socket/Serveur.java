package socket;

import static java.lang.Math.floor;

import java.io.*;
import java.net.*;
public class Serveur {

	public static void main(String[] args) {
		System.out.println("Serveur....");
		
		try(
				ServerSocket conn = new ServerSocket(10082);
				Socket comm = conn.accept();
				DataInputStream in = new DataInputStream(comm.getInputStream());
				DataOutputStream out = new DataOutputStream(comm.getOutputStream());
				

				
) {
			while (true) {
				byte[] b = new byte[1024];
				if (in.read(b) != -1) {
					System.out.println("Le Client a dit:" + new String(b));
					out.writeUTF("Bonjour Client");
					
				}
			
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
