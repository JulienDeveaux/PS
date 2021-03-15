package AlgLin;

import java.io.*;
import java.util.*;

public class Matrice {
    protected static final double EPSILON = 0.0000010;

    /** Définir ici les attributs de la classe **/
    protected double coefficient[][];

    /** Définir ici les constructeur de la classe **/
    Matrice (int nbligne, int nbcolonne){
        this.coefficient = new double[nbligne][nbcolonne];
    }

    Matrice(double[][] tableau){
        coefficient = tableau;
    }

    Matrice(String fichier){
        try {
            Scanner sc = new Scanner(new File(fichier));
            int ligne = sc.nextInt();
            int colonne = sc.nextInt();
            this.coefficient = new double[ligne][colonne];
            for(int i=0; i<ligne;i++)
                for(int j=0; j< colonne; j++)
                    this.coefficient[i][j]=sc.nextDouble();
            sc.close();

        }
        catch(FileNotFoundException e) {
            System.out.println("Fichier absent");
        }
    }
    /** Definir ici les autres methodes */

    public void recopie(Matrice arecopier){
        int ligne, colonne;
        ligne = arecopier.nbLigne(); colonne = arecopier.nbColonne();
        this.coefficient = new double[ligne][colonne];
        for(int i=0; i<ligne; i++)
            for (int j=0;j<colonne;j++)
                this.coefficient[i][j]= arecopier.coefficient[i][j];
    }

    public int nbLigne(){
        return this.coefficient.length;
    }

    public int nbColonne(){
        return this.coefficient[0].length;
    }

    public double getCoef(int ligne, int colonne){
        return this.coefficient[ligne][colonne];
    }

    public void remplacecoef(int ligne, int colonne, double value){
        this.coefficient[ligne][colonne]=value;
    }

    public String toString(){
        int ligne = this.nbLigne();
        int colonne = this.nbColonne();
        String matr = "";
        for(int i = 0; i<ligne;i++){
            for(int j =0; j< colonne;j++){
                if(j == 0)
                {
                    matr += this.getCoef(i, j);
                }
                else{
                    matr += " " + this.getCoef(i, j);
                }
            }
            matr += "\n";
        }
        return matr;
    }

    public Matrice produit(double scalaire){
        int ligne = this.nbLigne();
        int colonne = this.nbColonne();
        for(int i=0; i<ligne;i++)
            for(int j=0; j< colonne; j++)
                this.coefficient[i][j]*=scalaire;
        return this;
    }

    static Matrice addition(Matrice a, Matrice b){
        int ligne = a.nbLigne();
        int colonne = a.nbColonne();
        Matrice mat = new Matrice(ligne, colonne);
        for(int i=0; i<ligne;i++)
            for(int j=0; j< colonne; j++)
                mat.coefficient[i][j]=a.coefficient[i][j] + b.coefficient[i][j];
        return mat;
    }

    static Matrice verif_addition(Matrice a, Matrice b) throws IrregularSysLinException {
        if((a.nbLigne() == b.nbLigne()) && (a.nbColonne() == b.nbColonne()))
        {
            int ligne = a.nbLigne();
            int colonne = a.nbColonne();
            Matrice mat = new Matrice(ligne, colonne);
            for(int i=0; i<ligne;i++)
                for(int j=0; j< colonne; j++)
                    mat.coefficient[i][j]=a.coefficient[i][j] + b.coefficient[i][j];
            return mat;
        }
        else {
            throw new IrregularSysLinException ("Les deux matrices n'ont pas les mêmes dimensions !!!");
        }
    }

    static Matrice produit(Matrice a, Matrice b){
        int ligne, colonne;
        ligne = a.nbLigne();
        colonne = b.nbColonne();
        Matrice mat = new Matrice(ligne, colonne);
        for(int i=0; i<ligne;i++)
            for(int j=0; j< colonne; j++)
            {
                mat.coefficient[i][j]=0;
                for(int k=0; k <a.nbColonne();k++)
                    mat.coefficient[i][j] += a.coefficient[i][k] * b.coefficient[k][j];
            }
        return mat;
    }

    static Matrice verif_produit(Matrice a, Matrice b) throws IrregularSysLinException {
        int ligne = 0;
        int colonne = 0;
        if(a.nbColonne()==b.nbLigne())
        {
            ligne = a.nbLigne();
            colonne = b.nbColonne();
        }
        else{
            throw new IrregularSysLinException ("Dimensions des matrices à multiplier incorrectes");
        }

        Matrice mat = new Matrice(ligne, colonne);
        for(int i=0; i<ligne;i++)
            for(int j=0; j< colonne; j++)
            {
                mat.coefficient[i][j]=0;
                for(int k=0; k <a.nbColonne();k++)
                    mat.coefficient[i][j] += a.coefficient[i][k] * b.coefficient[k][j];
            }
        return mat;
    }

    public Matrice inverse() throws IrregularSysLinException{
        int ligne = 0;
        int colonne = 0;

        if(this.nbColonne()==this.nbLigne())
        {
            ligne = this.nbLigne();
            colonne = this.nbColonne();
        }
        else{
            throw new IrregularSysLinException ("La matrice n'est pas carré");
        }
        Matrice m = this;
        if(determinant(m, ligne)==0){
            System.out.println(determinant(m,ligne));
            throw new IrregularSysLinException("La matrice n'est pas inversible");
        }


        Matrice inverse;
        inverse = transpose(cofacteur(m, ligne,colonne,ligne));
        inverse.produit( (1/determinant(m,ligne)));


        return inverse;
    }

    private int determinant(Matrice mat , int n ){
        int determinant = 0;
        int signe = 1;
        Matrice temp = new Matrice(nbLigne(),nbColonne());
        for(int i = 0; i < n; i++){
            determinant += signe * temp.coefficient[0][i] * determinant(temp,n - 1);
            signe = -signe;
        }
        return determinant;
    }

    private Matrice cofacteur(Matrice mat, int p , int q ,int n ){
        int i = 0, j = 0;
        Matrice temp = new Matrice(nbLigne(),nbColonne());

        for(int ligne = 0; ligne < n; ligne++){
            for(int colonne = 0; colonne < n ; colonne++){
                if(ligne != p && colonne != q){
                    temp.coefficient[i][j++] = mat.coefficient[ligne][colonne];
                    if( j == i -1){
                        j = 0;
                        i++;
                    }
                }
            }
        }
        return temp;
    }

    private Matrice transpose(Matrice mat) {
        Matrice tmat = new Matrice(mat.nbLigne(), mat.nbColonne());
        for (int i = 0; i < mat.nbLigne(); i++) {
            for (int j = 0; j < mat.nbColonne(); j++) {
                tmat.coefficient[i][j] = tmat.coefficient[j][i];
            }
        }
        return tmat;
    }



    public static void main(String[] args) throws Exception {
        double mat[][]= {{2,3},{0,4}};
        Matrice a = new Matrice(mat);
        a.inverse();
        System.out.println(a);

    }
}
