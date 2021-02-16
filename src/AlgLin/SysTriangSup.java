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

	public Vecteur resolution() throws IrregularSysLinException {
		Vecteur resultat = new Vecteur(getMatriceSystem().nbLigne());

		double sum, coeff;

		for(int i=0;i<getMatriceSystem().nbLigne();i++) {
			sum=0;
			for(int j=0; j<i;j++) {
				sum=sum+getMatriceSystem().getCoef(i,j)*resultat.getCoef(j);
			}
			coeff=(getsecondMembre().getCoef(i)-sum)/getMatriceSystem().getCoef(i, i);
			resultat.remplacecoef(i, coeff);
		}
		return resultat;
	}
}