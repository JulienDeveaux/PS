package AlgLin;

import java.io.*;
import java.util.*;

public class Vecteur {

	public int coefficient[];
	private int taille;
	private Matrice composants;

	public int[] getCoefficient() {
		return coefficient;
	}
	public int getTaille(){
		return this.taille;
	}

	Vecteur(int taille) {
		this.taille = taille;
		this.composants = new Matrice(taille, 0);
	}

	Vecteur(double coeff[]) {
		this.taille = coeff.length;
		double[][] t = new double[this.taille][0];
		for(int i = 0; i < coeff.length; i++) {
			t[i][0] = coeff[i];
		}
		this.composants = new Matrice(t);
	}


	Vecteur(String fichier) {
		try {
			Scanner sc = new Scanner(new File(fichier));
			this.taille = sc.nextInt();
			double[][] tab = new double[this.taille][0];
			for(int i = 0; i < this.taille; i++) {
				tab[i][0] = sc.nextDouble();
			}
			this.composants = new Matrice(tab);
			sc.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Fichier absent");
		}
	}

	public String toString() {
		int taille = this.taille;
		double coeffs[] = new double[taille];
		for(int i = 0; i < taille; i++) {
			coeffs[i] = this.composants.getCoef(i, 0);
		}
		String res = "";
		for(int i = 0; i < taille; i++) {
			res += " " + composants.getCoef(i, 0);
		}
		res += "\n";
		return res;
	}

	public static void main (String[]args){
		Vecteur v = new Vecteur(3);

	}

}