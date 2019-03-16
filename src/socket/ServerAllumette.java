package socket;

import static java.lang.Math.floor;

import java.net.*;
import java.io.*;
public class ServerAllumette {

	
	public static int jeu_ordi (int nb_allum, int prise_max)
	{
		int prise = 0;
		int s = 0;
		float t = 0;
		s = prise_max + 1;
		t = ((float) (nb_allum - s)) / (prise_max + 1);
		while (t != floor(t))
		{
			s--;
			t = ((float) (nb_allum-s)) / (prise_max + 1);
		}
		prise = s - 1;
		if (prise == 0)
		prise = 1;
		return (prise);
	}
	
	public static String afficher_allu(int n)
	{
		String tmp="";
		int i;
		tmp += "\n";
		for (i=0; i<n ;i++)
			tmp += "  o";
		tmp += "\n";
		for (i=0; i<n; i++)
			tmp += "  |";
		tmp += "\n";
		for (i=0; i<n; i++)
			tmp += "  |";
		tmp += "\n";
		return tmp;
	}

	public static void main(String[] args) {
		System.out.println("Serveur....");
		
		try(
				ServerSocket conn = new ServerSocket(10080);
				Socket comm = conn.accept();
				DataInputStream in = new DataInputStream(comm.getInputStream());
				DataOutputStream out = new DataOutputStream(comm.getOutputStream());
					
) {
			//TODO end the socket
			while(true) {
				int nb_max_d=0; /*nbre d'allumettes maxi au d¨¦part*/
				int nb_allu_max=0; /*nbre d'allumettes maxi que l'on peut tirer au maxi*/
				int qui=0; /*qui joue? 0=Nous --- 1=PC*/
				int prise=0; /*nbre d'allumettes prises par le joueur*/
				int nb_allu_rest=0; /*nbre d'allumettes restantes*/
				System.out.println("connexion r¨¦ussite ¡­");
				
					do{
						out.writeUTF("Nombre d'allumettes dispos¨¦es entre les deux joueurs (entre 10 et 60) :");
						out.flush();
						nb_max_d= Integer.parseInt(in.readUTF());
						}
					while((nb_max_d<10) || (nb_max_d>60));
					do
					{
						out.writeUTF("Nombre maximal d'allumettes que l'on peut retirer : ");
						//TODO check if input is int or not 
						nb_allu_max = Integer.parseInt(in.readUTF());
						if (nb_allu_max >= nb_max_d)
							out.writeUTF("Erreur !");
							out.flush();
						}
					while ((nb_allu_max >= nb_max_d)||(nb_allu_max == 0));
					/* On r¨¦p¨¨te la demande de prise tant que le nombre donn¨¦ n'est pas correct */
					do
					{
						out.writeUTF("Quel joueur commence? 0--> Joueur ; 1--> Ordinateur : ");
						qui=Integer.parseInt(in.readUTF());

						if ((qui != 0) && (qui != 1))
							out.writeUTF("\nErreur");
							out.flush();
						}
					while ((qui != 0) && (qui != 1));
					nb_allu_rest = nb_max_d;
					
					do
					{
						out.writeUTF("Nombre d'allumettes restantes :"+nb_allu_rest);
						out.writeUTF(afficher_allu(nb_allu_rest));
						if (qui==0)
						{
							do
							{
								out.writeUTF("Combien d'allumettes souhaitez-vous piocher ? ");
								prise=Integer.parseInt(in.readUTF());
								if ((prise > nb_allu_rest) || (prise > nb_allu_max))
								{
									out.writeUTF("Erreur !\n");
								}
							}
							while ((prise > nb_allu_rest) || (prise > nb_allu_max));
							/* On r¨¦p¨¨te la demande de prise tant que le nombre donn¨¦ n'est pas correct */
						}
						else
						{
							prise = jeu_ordi (nb_allu_rest , nb_allu_max);
							out.writeUTF("Prise de l'ordi :"+prise);
						}
						qui=(qui+1)%2;

						nb_allu_rest= nb_allu_rest - prise;
						}
						while (nb_allu_rest >0);


						if (qui == 0) /* Cest ¨¤ nous de jouer */
							out.writeUTF("\nVous avez gagn¨¦!\n");
						else
							out.writeUTF("\nVous avez perdu!\n");
					//TODO it restart automatically
					System.out.println ("Test end");
			}
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
