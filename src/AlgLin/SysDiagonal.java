package AlgLin;

import java.io.*;
import java.util.*;

public class SysDiagonal extends SysLin {
	public SysDiagonal(Matrice m, Vecteur v) throws Exception {
		super(m, v);
	}

	// Cette classe décrit un système linéaire diagonal
	public static void main(String[] args) throws Exception{
		double composants[] = new double[3];
		composants[0] = 4;
		composants[1] = 5;
		Vecteur vecteur = new Vecteur(composants);
		double mat[][]= {{2,0},{0,3}};
		Matrice matrice = new Matrice(mat);								//2x + 0y = 4
		SysLin sys = new SysDiagonal(matrice, vecteur);					//0x + 3y = 5
		System.out.println(sys.resolution());
	}

	public Vecteur resolution() throws IrregularSysLinException {
		Matrice matrice = getMatriceSystem();
		Vecteur secondMembre = getsecondMembre();
		int ligne = matrice.nbLigne();
		int colonne = matrice.nbColonne();
		String echo = "";
		System.out.println("Système à résoudre : ");
		for(int i = 0; i < ligne; i++){
			for(int j = 0; j < colonne; j++){
				if(j == 0 && i == 0)
				{
					echo += matrice.getCoef(i, j) + "x";
				}
				else if(j == 0)
				{
					echo += " = " + secondMembre.getCoef(i - 1);
					echo += "\n";
					echo += matrice.getCoef(i, j) + "x";
				}
				else
				{
					echo += " " + matrice.getCoef(i, j) + "y";
				}
			}
		}
		echo += " = " + secondMembre.getCoef(colonne - 1);
		System.out.println(echo);

		Vecteur res = new Vecteur(matrice.nbLigne());

		for(int i = 0; i < matrice.nbLigne(); i++) {
			res.remplacecoef(i, secondMembre.getCoef(i) / matrice.getCoef(i, i));
		}
		return res;
	}
}