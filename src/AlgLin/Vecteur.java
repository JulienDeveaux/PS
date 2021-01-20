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
		this.composants = new Matrice(taille, 1);
	}

	Vecteur(double coeff[]) {
		this.taille = coeff.length;
		double[][] t = new double[this.taille][1];
		for(int i = 0; i < coeff.length; i++) {
			t[i][0] = coeff[i];
		}
		this.composants = new Matrice(t);
	}


	Vecteur(String fichier) {
		try {
			Scanner sc = new Scanner(new File(fichier));
			this.taille = sc.nextInt();
			double[][] tab = new double[this.taille][1];
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
			res += composants.getCoef(i, 0) + " ";
		}
		return res;
	}

	public double getCoef(int pos) {
		return this.composants.getCoef(pos, 0);
	}

	public void remplacecoef(int pos, double coef) {
		this.composants.remplacecoef(pos, 0, coef);
	}

	public static double produitScalaire(Vecteur v1, Vecteur v2) {
		double res = 0;
		int taille;

		if(v1.taille < v2.taille) {
			taille = v1.taille;
		} else {
			taille = v2.taille;
		}

		for(int i = 0; i < taille; i++) {
			res += v1.getCoef(i) * v2.getCoef(i);
		}
		return res;
	}

	public static void main (String[]args){
		Vecteur v = new Vecteur(3);
		double c1[] = new double[3];
		c1[0] = 1;
		c1[1] = 2;
		c1[2] = 3;
		Vecteur v1 = new Vecteur(c1);
		double c2[] = new double[3];
		c2[0] = 4;
		c2[1] = 5;
		c2[2] = 6;
		Vecteur v2 = new Vecteur(c2);
		System.out.println(v1);
		System.out.println(v1.getCoef(1));
		v1.remplacecoef(1, 5.5);
		System.out.println(v1);
		System.out.println(v2);
		System.out.println(produitScalaire(v1, v2));
	}

}