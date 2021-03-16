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
        if(this.nbLigne() != this.nbColonne()) {
            throw new IrregularSysLinException("Matrice non carrée");
        }
        return transpose(cofactorMatrice(this)).produit(1/determinant(this));
    }

    private double determinant(Matrice mat){
        double determinant = 0;
        Matrice temp;
        if(mat.nbLigne() == 1) {
            determinant = mat.getCoef(0, 0);
            return determinant;
        }
        if(mat.nbLigne() == 2) {
            determinant = (mat.getCoef(0, 0) * mat.getCoef(1, 1) - (mat.getCoef(0, 1) * mat.getCoef(1, 0)));
            return determinant;
        }
        for(int i = 0; i < mat.nbLigne(); i++) {
            temp = new Matrice(mat.nbLigne() - 1, mat.nbColonne() - 1);

            for(int j = 1; j < mat.nbLigne(); j++) {
                for(int k = 0; k < mat.nbColonne(); k++) {
                    if(k < i) {
                        temp.remplacecoef(j - 1, k, mat.getCoef(j, k));
                    } else if(k > i) {
                        temp.remplacecoef(j - 1, k - 1, mat.getCoef(j, k));
                    }
                }
            }
            determinant += mat.getCoef(0, i) * Math.pow(-1, (double) i) * determinant(temp);
        }
        return determinant;
    }

    public double cofactor(int ii, int jj, Matrice mat){
        return Math.pow(-1,ii+jj)*(determinant(mat.sousMatrice(ii,jj)));
    }

    public Matrice cofactorMatrice(Matrice mat){
        double[][] comatrix = new double[ mat.nbLigne() ][ mat.nbColonne() ];
        for(int i = 0; i < mat.nbLigne(); i++ ){
            for(int j = 0; j < mat.nbColonne() - 1; j++ ){
                comatrix[i][j] = mat.cofactor(i,j, mat);
            }
        }
        return new Matrice(comatrix);
    }

    public Matrice sousMatrice(int ii, int jj){
        Matrice sousMat = new Matrice(this.nbColonne() - 1, this.nbLigne() - 1);
        for(int i = 0; i < this.nbColonne(); i++ ){
            for(int j = 0; j < this.nbLigne(); j++ ){
                if(i<ii && j<jj) {
                    sousMat.remplacecoef(i, j, this.getCoef(i, j));
                } else if(i>ii && j<jj) {
                    sousMat.remplacecoef(i - 1, j, this.getCoef(i, j));
                } else if(i<ii && j>jj) {
                    sousMat.remplacecoef(i, j - 1, this.getCoef(i, j));
                } else if(i>ii && j>jj) {
                    sousMat.remplacecoef(i - 1, j - 1, this.getCoef(i, j));
                }
            }
        }
        return sousMat;
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


    public double norme_1(){
        double res = 0.0;
        for(int i = 0; i < this.nbLigne(); i++){
            for(int j = 0; j < this.nbColonne(); j++){
                res += Math.abs(this.getCoef(i, j));
            }
        }
        return res;
    }

    public double norme_inf(){
        double res = 0.0;
        for(int i = 0; i < this.nbLigne(); i ++) {
            for(int j = 0; j < nbColonne(); j ++){
                res = Math.max(res, Math.abs(this.getCoef(i,j)));
            }
        }
        return res;
    }

    public void calculAvecUneFonction(FonctionGenerale fonc) throws IrregularSysLinException {
        fonc.evaluationDeLaFonction();
    }

    public void conditionnement(){
   //norme de A * norme de l'inverse de A
    }

    public static void main(String[] args) throws Exception {
        double mat[][]= {{2,3},{0,6}};
        Matrice a = new Matrice(mat);
        System.out.println(a);
        System.out.println(a.inverse());
    }
}
