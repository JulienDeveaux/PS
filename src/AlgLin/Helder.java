package AlgLin;

public class Helder extends SysLin {

    public Helder(Matrice m, Vecteur v) throws  IrregularSysLinException  {
        super(m, v);
    }

    public void factorLDR() throws IrregularSysLinException  {
        SysTriangInfUnite L = new SysTriangInfUnite(matriceSystem, secondMembre);
        SysDiagonal D = new SysDiagonal(matriceSystem, secondMembre);
        SysTriangSupUnite R = new SysTriangSupUnite(matriceSystem, secondMembre);

        Matrice.produit(matriceSystem, L.matriceSystem);
        Matrice.produit(matriceSystem, D.matriceSystem);
        Matrice.produit(matriceSystem, R.matriceSystem);
    }

    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        factorLDR();
        SysTriangInfUnite L = new SysTriangInfUnite(matriceSystem, secondMembre);
        SysDiagonal D = new SysDiagonal(matriceSystem, L.resolution());
        SysTriangSupUnite R = new SysTriangSupUnite(matriceSystem, D.resolution());

        return R.resolution();
    }

    public Vecteur resolutionPartielle() throws IrregularSysLinException {
        SysTriangInfUnite L = new SysTriangInfUnite(matriceSystem, secondMembre);
        SysDiagonal D = new SysDiagonal(matriceSystem, L.resolution());
        SysTriangSupUnite R = new SysTriangSupUnite(matriceSystem, D.resolution());

        return R.resolution();
    }

    public void setSecondMembre(Vecteur SecondMembre) {
        this.secondMembre = SecondMembre;
    }

    public static void main(String[] args) {
        Matrice mat1 = new Matrice("src/resources/mat1.txt");
        Matrice mat2 = new Matrice("src/resources/mat1.txt");
        Vecteur vec1 = new Vecteur("src/resources/vec1.txt");

        System.out.println("Matrice 1 : \n" + mat1);
        System.out.println("Matrice 2 : \n" + mat2);
        System.out.println("Vecteur : \n" + vec1);
    }
}