package socket;

import java.io.Serializable;

public class Restaurant implements Serializable{
	
	private String nom;
	private String numTel;
	private Point2D position;
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNumTel() {
		return numTel;
	}
	
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	
	public Point2D getPosition() {
		return position;
	}
	public void setPosition (Point2D p) {
		this.position = p;
	}
	
	public String toString() {
		return "Restaurant:[" + nom + "], Tel:[" + numTel +"], Position:[" + position + "].";
	}
	public Restaurant (String nom, String numTel, Point2D position ) {
		this.nom = nom;
		this.numTel = numTel;
		this.position = position;
	}
	

}
