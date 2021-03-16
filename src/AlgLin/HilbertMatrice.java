package AlgLin;

import java.util.Scanner;

public class HilbertMatrice {
	private final Matrice hilbertMat;

	public static void main(String[] args) throws IrregularSysLinException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ordre de la matrice Hilbert : ");
		int ordre = scanner.nextInt();
		HilbertMatrice matrice = new HilbertMatrice(ordre);
		System.out.println("Matrice de hilbert générée : \n" + matrice);
		System.out.println("Matrice de hilbert inversée : \n" + matrice.hilbertMat.inverse());
		System.out.println("Vérification en multipliant les deux matrices obtenues : \n" + (Matrice.produit(matrice.hilbertMat, matrice.hilbertMat.inverse())));
		System.out.println("conditionnement par norme 1 de la matrice inversée : \n" + matrice.hilbertMat.inverse().conditionnement(new LaFonctionUtilisee(0)));
		System.out.println("conditionnement par norme inférieure de la matrice inversée : \n" + matrice.hilbertMat.inverse().conditionnement(new LaFonctionUtilisee(1)));
	}

	public HilbertMatrice(int taille) {
		hilbertMat = new Matrice(new double[taille][taille]);
		for(int i = 0; i < taille; i++) {
			for(int j = 0; j < taille; j++) {
				hilbertMat.remplacecoef(i, j, 1.0 / (i + j + 1));
			}
		}
	}

	@Override
	public String toString() {
		return hilbertMat.toString();
	}
}
