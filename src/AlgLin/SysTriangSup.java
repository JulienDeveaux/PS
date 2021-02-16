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
		double mat[][]= {{2,1},{3,2}};
		Matrice matrice = new Matrice(mat);								//2x + 1y = 4
		SysLin sys = new SysTriangSup(matrice, vecteur);				//3x + 2y = 5
		sys.resolution();
	}

	public Vecteur resolution() throws IrregularSysLinException {
		return null;
	}
}