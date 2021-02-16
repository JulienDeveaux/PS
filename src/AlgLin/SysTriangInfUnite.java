package AlgLin;

public class SysTriangInfUnite extends SysTriangInf {
	public SysTriangInfUnite(Matrice m, Vecteur v) throws Exception{
		super(m, v);
	}

	public static void main(String[] args) throws Exception {
		double[] composants = new double[3];
		composants[0] = 4;
		composants[1] = 5;
		Vecteur vecteur = new Vecteur(composants);
		double mat[][]= {{1, 0},{3, 1}};				//1x + 0y = 4
		Matrice matrice = new Matrice(mat);				//3x + 1y = 5
		SysLin sys = new SysTriangInfUnite(matrice, vecteur);
		System.out.println(sys.resolution());
	}
}