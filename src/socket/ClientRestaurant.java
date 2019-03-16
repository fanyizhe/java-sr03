package socket;

import java.net.*;
import java.io.*;
public class ClientRestaurant {
	private static String command ="";

	public static void main(String[] args) {
		System.out.println("Client....");
		
		try(
				
				Socket comm = new Socket("127.0.0.1",10084);
				
					
) {
			ObjectOutputStream out = new ObjectOutputStream(comm.getOutputStream());

			ObjectInputStream in = new ObjectInputStream(comm.getInputStream());
			BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("connexion r¨¦ussite ¡­");
			
			Thread tRead = new Thread(()->{
				int indice = 1;
				while (!command.equalsIgnoreCase("exit")) {
					try {
						Restaurant res = (Restaurant)in.readObject();
						if (res != null) {
							System.out.println("Le "+indice+"er le plus proche restaurant:");
							System.out.println(res.toString());
							System.out.println("\n");
							indice ++;
						}
						if (indice == 4) indice = 1;
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			} );
			tRead.start();
			
			while(true) {
				System.out.println("\nPrint your Position X :");
				command = keyboardIn.readLine();
				String x = command;
				System.out.println("\nPrint your Position Y :");
				command = keyboardIn.readLine();
				String y = command;
				//String outputPoint = x+","+y;
				Point2D outputPoint = new Point2D(Float.parseFloat(x),Float.parseFloat(y));

				out.writeObject(outputPoint);
				out.flush();			
			
			}

			
		} catch(ConnectException e) {
			System.out.println("Server doesn't run");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
