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
		Vecteur resultat = new Vecteur(getMatriceSystem().nbLigne());
		double somme, coefficient;
		for (int i = matriceSystem.nbLigne()-1; i >= 0; i--) {
			somme = 0;
			for (int j = matriceSystem.nbLigne()-1; j > i; j--){
				somme += getMatriceSystem().getCoef(i, j) * resultat.getCoef(j);
			}
			coefficient = (secondMembre.getCoef(i) - somme) / matriceSystem.getCoef(i, i);
			resultat.remplacecoef(i, coefficient);
		}
		return resultat;
	}
}