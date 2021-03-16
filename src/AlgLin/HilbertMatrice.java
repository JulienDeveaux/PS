package AlgLin;

import java.util.Scanner;

public class HilbertMatrice {
	private final Matrice hilbertMat;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ordre de la matrice Hilbert : ");
		int ordre = scanner.nextInt();
		HilbertMatrice matrice = new HilbertMatrice(ordre);
		System.out.println(matrice);

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
