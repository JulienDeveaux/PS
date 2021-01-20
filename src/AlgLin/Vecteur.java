#include <iostream>
#include "scalaire.h"
#include "vecteur.h"

using namespace std;

Vecteur::Vecteur(int n, Scalaire s) {
	taille = n;
	valeur = new Scalaire[n];
	for (int i = 0; i < taille; i++) {
		valeur[i] = s;
	}
}

void Vecteur::free() {
	delete valeur;
}

void Vecteur::copy(const Vecteur &v) {
	taille = v.taille;
	valeur = new Scalaire[taille];
	for (int i = 0; i < taille; i++) {
		valeur[i] = v.valeur[i];
	}
}

Vecteur::Vecteur(const Vecteur& v) {
	copy(v);
}

Vecteur::~Vecteur() {
	free();
}

Vecteur& Vecteur::operator=(const Vecteur& v) {
	if(this != &v) {
		free();
		copy(v);
	}
	return *this;
}

bool Vecteur::operator==(const Vecteur &v)const {
	if(v.taille == taille) {
		for (int i = 0; i < taille; i++) {
			if(v.valeur[i] != valeur[i]) {
				return false;
			}
		}
		return true;
	}
	return false;
}