//package AlgLin;

import java.io.*;
import java.util.*;

public abstract class SysLin {
	private int ordre;							//nb de lignes/colonnes de la matrice ou second membre
	protected Matrice matriceSystem;	//matrice du syst_me
	protected Vecteur secondMembre;		//second membre du système

	SysLin(Matrice m, Vecteur secondMembre) throws Exception {
		if(m.nbLigne() == m.nbColonne()) {	//m est carrée et même taille secondM
			this.ordre = m.nbLigne();
			this.secondMembre = secondMembre;
			this.matriceSystem = m;
		} else {
			throw new Exception("Matrice non carrée ou mauvais second membre");
		}
	}

	public int getOrdre() {
		return ordre;
	}

	public Matrice getMatriceSystem() {
		return matriceSystem;
	}

	public Vecteur getsecondMembre() {
		return secondMembre;
	}

	public abstract Vecteur resolution() throws IrregularSysLinException;
}