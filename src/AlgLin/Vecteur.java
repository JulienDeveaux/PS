package AlgLin;

import java.io.*;
import java.util.*;

public class Vecteur {
	private int taille;
	private Matrice composants;

	Vecteur(int taille) {
		this.taille = taille;
		this.composants = new Matrice(taille, 1);
	}

	Vecteur(double coeff[]) {
		this.taille = coeff.length;
		double[][] t;
		for(int i = 0; i < coeff.length; i++) {
			t[i][0] = coeff[i];
		}
		this.composants = new Matrice(t);
	}

	Vecteur(String fichier) {
		try {
			Scanner sc = new Scanner(new File(fichier));
			this.taille = sc.nextInt();
			double[][] tab;
			for(int i = 0; i < this.taille; i++) {
				tab[i][0] = sc.nextDouble();
			}
			this.composants = new Matrice(t);
			sc.close();
		}
		catch(FileNotFoundException e) {
			Syste.out.println("Fichier absent");
		}
	}
}