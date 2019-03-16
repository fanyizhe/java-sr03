package socket;

import java.net.*;
import java.io.*;
public class ServeurRestaurant {
	
	public static Restaurant[] sortDistanceRestaurant (Restaurant[] resto, int numResto, Point2D p) {
		Restaurant[] result = new Restaurant[3];
		for (int i=0;i<3;i++) {
			for(int j=i+1;j<numResto;j++) {
				if (Point2D.calculerDistance(resto[i].getPosition(), p)
					> Point2D.calculerDistance(resto[j].getPosition(), p) ){
						Restaurant tmp = resto[i];
						resto[i] = resto[j];
						resto[j] = tmp;
				}
			}
			result[i] = resto[i];

		}

		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("Serveur....");
		try (
				
				ServerSocket conn = new ServerSocket(10084);
				Socket comm = conn.accept();
				
	){
			ObjectInputStream in = new ObjectInputStream(comm.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(comm.getOutputStream());
			
			while(true) {
				// Initialiser les restaurants
				int numResto = 5;
				Restaurant[] resto = new Restaurant[numResto];
				for(int i=0;i<numResto;i++) {
					resto[i] = new Restaurant("nom"+i,"123"+i,new Point2D(i,i));
				}
				//String position = null;
				Point2D position;
				while (true) {
					//position = in.readUTF();
					position = (Point2D)in.readObject();
					if (position != null) {
						
						//String[] inPosition = position.split(",",-1);
						Restaurant[] resultResto = new Restaurant[3];
						resultResto = sortDistanceRestaurant(resto,numResto,position);
						
						out.writeObject(resultResto[0]);
						out.writeObject(resultResto[1]);
						out.writeObject(resultResto[2]);
						
					}
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
