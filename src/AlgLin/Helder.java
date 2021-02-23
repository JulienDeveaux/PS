package AlgLin;

public class Helder extends SysLin
{

    public Helder(Matrice m, Vecteur v) throws  IrregularSysLinException  {
        super(m, v);
    }

    public void factorLDR() throws IrregularSysLinException  {

        SysTriangInfUnite l = new SysTriangInfUnite( matriceSystem, secondMembre );
        SysDiagonal d = new SysDiagonal( matriceSystem, secondMembre );
        SysTriangSupUnite r = new SysTriangSupUnite( matriceSystem, secondMembre );

        Matrice.produit(matriceSystem, l.matriceSystem);
        Matrice.produit(matriceSystem, d.matriceSystem);
        Matrice.produit(matriceSystem, r.matriceSystem);
    }

    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        factorLDR();
        SysTriangInfUnite l = new SysTriangInfUnite(matriceSystem, secondMembre);
        SysDiagonal  d = new SysDiagonal(matriceSystem, l.resolution());
        SysTriangSupUnite r = new SysTriangSupUnite(matriceSystem, d.resolution());

        return r.resolution();
    }

    public Vecteur resolutionPartielle() throws IrregularSysLinException {
        SysTriangInfUnite l = new SysTriangInfUnite(matriceSystem, secondMembre);
        SysDiagonal d = new SysDiagonal(matriceSystem, l.resolution());
        SysTriangSupUnite r = new SysTriangSupUnite(matriceSystem, d.resolution());

        return r.resolution();
    }

    public void setSecondMembre(Vecteur newSecondMembre) {
        secondMembre = newSecondMembre;
    }

    public static void main(String[] args) {
        Matrice m1 = new Matrice("resources/matrice1.txt");
        Matrice m2 = new Matrice("resources/matrice1.txt");
        Vecteur v1 = new Vecteur("resources/vecteur1.txt");

        System.out.println(m1);
        System.out.println(m2);
        System.out.println(v1);
    }
}