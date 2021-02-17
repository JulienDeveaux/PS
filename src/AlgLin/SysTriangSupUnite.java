package AlgLin;

public class SysTriangSupUnite extends SysTriangSup {
	public static SysTriangSupUnite make(Matrice m, Vecteur v) throws Exception {
		int  j = 0;
		boolean b = true;
		for(int i = 0; i < m.nbLigne(); i++) {
			if(m.getCoef(i, j) != 1) {
				b = false;
			}
			j++;
		}
		if(b) {
			SysTriangSupUnite sys = new SysTriangSupUnite(m, v);
			return sys;
		}
		return null;
	}

	private SysTriangSupUnite(Matrice m, Vecteur v) throws Exception {
		super(m, v);
	}

	public static void main(String[] args) throws Exception {
		double[] composants = new double[3];
		composants[0] = 4;
		composants[1] = 5;
		Vecteur vecteur = new Vecteur(composants);
		double mat[][]= {{1, 3},{0, 1}};				//1x + 3y = 4
		Matrice matrice = new Matrice(mat);				//0x + 1y = 5
		SysLin sys = make(matrice, vecteur);
		System.out.println(sys.resolution());
	}
}