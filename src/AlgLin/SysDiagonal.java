package AlgLin;

import java.io.*;
import java.util.*;

public class SysDiagonal extends SysLin {

	public SysDiagonal(Matrice m, Vecteur v) throws Exception {
		super(m, v);
	}

	// Cette classe décrit un système linéaire diagonal
	public static void main(String[] args) throws Exception{
		double composants[] = new double[2];
		composants[0] = 4;
		composants[1] = 5;
		Vecteur vecteur = new Vecteur(composants);
		double mat[][]= {{2,0},{0,3}};
		Matrice matrice = new Matrice(mat);								//2x + 0y = 4
		SysLin sys = new SysDiagonal(matrice, vecteur);					//0x + 3y = 5

		Vecteur resolution = sys.resolution();
		Matrice resolu = new Matrice(resolution.getTaille(), 1);
		for(int i = 0; i < resolu.nbLigne(); i++){
			resolu.remplacecoef(i, 0, resolution.getCoef(i));
		}
		Matrice v = new Matrice(vecteur.getTaille(), 1);
		for(int i = 0; i < vecteur.getTaille(); i++){
			v.remplacecoef(i, 0, vecteur.getCoef(i));
			v.remplacecoef(i, 0, - v.getCoef(i, 0));
		}

		Matrice.verif_produit(matrice, resolu);
		Matrice resultat = Matrice.produit(matrice,resolu);
		Matrice.verif_addition(resultat, v);
		Matrice resulatFinal = Matrice.addition(resultat, v);
		Vecteur norme = new Vecteur(resulatFinal.nbLigne());
		for(int i = 0; i < resulatFinal.nbLigne(); i++){
			norme.remplacecoef(i, resulatFinal.getCoef(i,0));
		}
		double normeFinal =	Vecteur.normeL1(norme);
		if(normeFinal <= 0.0 || normeFinal > Matrice.EPSILON){
			System.out.println("La norme du vecteur est null ou plus exactement très petite");
		}
		double normeFinalL2 =	Vecteur.normeL2(norme);
		if(normeFinalL2 <= 0.0 || normeFinalL2 > Matrice.EPSILON){
			System.out.println("La norme du vecteur est null ou plus exactement très petite");
		}
		double normeFinalLi =	Vecteur.normeInfini(norme);
		if(normeFinalLi <= 0.0 || normeFinalLi > Matrice.EPSILON){
			System.out.println("La norme du vecteur est null ou plus exactement très petite");
		}
	}

	public Vecteur resolution() throws IrregularSysLinException {
		Matrice matrice = getMatriceSystem();

		Vecteur res = new Vecteur(matrice.nbLigne());

		for(int i = 0; i < matrice.nbLigne(); i++) {
			res.remplacecoef(i, secondMembre.getCoef(i) / matrice.getCoef(i, i));
		}
		return res;
	}
}