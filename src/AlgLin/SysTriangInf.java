package AlgLin;

import java.io.*;
import java.util.*;

public class SysTriangInf extends SysLin {
	public SysTriangInf(Matrice m, Vecteur v) throws Exception {
		super(m, v);
	}

	// Cette classe décrit un système linéaire triangulaire inférieur
	public static void main(String[] args) throws Exception  {
		double composants[] = new double[3];
		composants[0] = 4;
		composants[1] = 5;
		composants[2] = 3;
		Vecteur vecteur = new Vecteur(composants);
		double mat[][]= {{2, 0, 0},{3, 2, 0},{4, 2, 1}};
		Matrice matrice = new Matrice(mat);								//2x + 0y = 4
		SysLin sys = new SysTriangInf(matrice, vecteur);				//3x + 2y = 5
		System.out.println(sys.resolution());
	}

	public Vecteur resolution() throws IrregularSysLinException {
		Vecteur resultat = new Vecteur(matriceSystem.nbLigne());

		double sum, coeff;

		for(int i=0;i<matriceSystem.nbLigne();i++) {
			sum=0;
			for(int j=0; j<i;j++) {
				sum+=matriceSystem.getCoef(i,j)*resultat.getCoef(j);
			}
			coeff=(secondMembre.getCoef(i)-sum)/matriceSystem.getCoef(i, i);
			resultat.remplacecoef(i, coeff);
		}
		return resultat;
	}
}