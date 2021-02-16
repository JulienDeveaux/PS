package AlgLin;

import java.io.*;
import java.util.*;

public class SysTriangSup extends SysLin{
	SysTriangSup(Matrice m, Vecteur secondMembre) throws Exception {
		super(m, secondMembre);
	}

	// Cette classe décrit un système linéaire triangulaire supérieur.
	public static void main(String[] args) throws Exception{
		double composants[] = new double[3];
		composants[0] = 4;
		composants[1] = 5;
		Vecteur vecteur = new Vecteur(composants);
		double mat[][]= {{2,1},{0,2}};
		Matrice matrice = new Matrice(mat);								//2x + 1y = 4
		SysLin sys = new SysTriangSup(matrice, vecteur);				//0x + 2y = 5
		System.out.println(sys.resolution());
	}

	public Vecteur resolution() throws IrregularSysLinException {Vecteur resultat = new Vecteur(getMatriceSystem().nbLigne());

		double somme, coefficient;

		for (int i = matriceSystem.nbLigne()-1; i >= 0; i--)
		{
			somme = 0;
			for (int j = matriceSystem.nbLigne()-1; j > i; j--)
				somme += getMatriceSystem().getCoef(i, j) * resultat.getCoef(j);
			coefficient = (secondMembre.getCoef(i) - somme) / matriceSystem.getCoef(i, i);
			resultat.remplacecoef(i, coefficient);
		}
		return resultat;
	}
}