package FusionProjet1.Fusion3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Noeud {
	protected Stagiaire stagiaire;
	protected int filsGauche;
	protected int filsDroit;
	protected int doublon;

	public static final int FILS_NUL = -1;
	public static final int TAILLE_FD_FG_DB = 3 * 4;

	public static final int TAILLE_TOTAL_OCTETS = Stagiaire.TAILLE_MAX_OCTET_STAGIAIRE + TAILLE_FD_FG_DB;

	public Noeud(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
		this.filsGauche = FILS_NUL;
		this.filsDroit = FILS_NUL;
		this.doublon = FILS_NUL;
	}

	public Noeud(Stagiaire stagiaire, int filsGauche, int filsDroit, int doublon) {

		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
		this.doublon = doublon;
	}

	public Noeud() {
		// TODO Auto-generated constructor stub
	}

	public int getDoublon() {
		return doublon;
	}

	public void setDoublon(int doublon) {
		this.doublon = doublon;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public int getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public int getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}

	@Override
	public String toString() {
		return "Noeud [stagiaire=" + stagiaire + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit
				+ ", doublon=" + doublon + "]";
	}

	public void ecrireStagiaire(Stagiaire stagiaire, RandomAccessFile raf) {
		try {
			// raf = new RandomAccessFile("src/main/ressources/monFichier.bin", "rw");

			raf.seek(raf.length()); // positionne le curseur à la fin

			// Ecrit les differents variables de l'objet Stagiaire avec writeChars et
			// writeInt
			raf.writeChars(stagiaire.getNomLong());
			raf.writeChars(stagiaire.getPrenomLong());
			raf.writeChars(stagiaire.getLocalisationLong());
			raf.writeChars(stagiaire.getFormationLong());
			raf.writeInt(stagiaire.getAnneeEntree());
			raf.writeInt(Noeud.FILS_NUL);
			raf.writeInt(Noeud.FILS_NUL);
			raf.writeInt(Noeud.FILS_NUL);

//			raf.close(); // ferme le fichier

		} catch (IOException e) {
			System.out.println("Probleme");
			e.printStackTrace();
		}
	}

	public Noeud lireStagiaire(RandomAccessFile raf) {

		String nom = "";
		String prenom = "";
		String localisation = "";
		String formation = "";
		int anneeEntree = 0;
		int filsGauche = -1;
		int filsDroit = -1;
		int doublon = -1;

		try {

			// lit les variables de l'objet Stagiaire

			for (int i = 0; i < Stagiaire.TAILLE_MAX_NOM; i++) {
				nom += raf.readChar();
			}

			for (int i = 0; i < Stagiaire.TAILLE_MAX_PRENOM; i++) {
				prenom += raf.readChar();
			}

			for (int i = 0; i < Stagiaire.TAILLE_MAX_LOCALISATION; i++) {
				localisation += raf.readChar();
			}

			for (int i = 0; i < Stagiaire.TAILLE_MAX_FORMATION; i++) {
				formation += raf.readChar();
			}
			anneeEntree = raf.readInt();
			filsGauche = raf.readInt();
			filsDroit = raf.readInt();
			doublon = raf.readInt();

//			raf.close(); // ferme le fichier

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Crée un nouvel objet Stagiaire en utilisant les variables lues à partir du
		// fichier binaire
		Stagiaire stagiaire = new Stagiaire(nom.trim(), prenom.trim(), localisation.trim(), formation.trim(),
				anneeEntree);

		// ces variables sont utilisées pour créer un nouvel objet Noeud en utilisant
		// l'objet Stagiaire et les valeurs pour les filsGauche, filsDroit et
		// doublon et retourne l'objet Noeud
		return new Noeud(stagiaire, filsGauche, filsDroit, doublon);
	}

	public void ajouterStagiaire(Stagiaire stagiaireAjout, RandomAccessFile raf) throws IOException {

		int comparaison = this.stagiaire.compareTo(stagiaireAjout); // compare le nom de stagiaire
																	// actuel avec le nom du
																	// stagiaire à ajouter

		if (comparaison > 0) { // si le nom du stagiaire actuel est superieur au nom de stagiaire à ajouter, le
			// noeu doit etre ajouté à gauche

			if (this.filsGauche == FILS_NUL) {
				raf.seek(raf.getFilePointer() - 12); // deplace le curseur 12 octets vers
														// l'arriere pour
														// se positionner sur la partie fils gauche du noeud

				raf.writeInt((int) (raf.length() / TAILLE_TOTAL_OCTETS)); // calcule l'index du nouveau noeud à
																			// ajouter et ecrit cet index dans le
																			// fichier
				raf.seek(raf.length()); // le curseur à la fin du fichier
				ecrireStagiaire(stagiaireAjout, raf); // écrit le nouveau nœud dans le fichier

			} else {
				raf.seek(this.filsGauche * TAILLE_TOTAL_OCTETS); // déplace le pointeur à la position du fils
																	// gauche, la variable this.filsGauche est l'indice
																	// du fils gauche du nœud actuel dans
																	// l'arbre binaire. En multipliant cet indice par la
																	// taille totale du noeud
																	// (TAILLE_TOTAL_OCTETS), on obtient la position de
																	// début de la structure de données dans le fichier.
																	// La méthode seek() permet ensuite de déplacer le
																	// pointeur de RandomAccessFile à cette position.

				Noeud noeudFilsGauche = lireStagiaire(raf); // lit le nœud fils gauche en utilisant la méthode
				noeudFilsGauche.ajouterStagiaire(stagiaireAjout, raf); // appelle récursivement la
																		// méthode sur le nœud fils
																		// gauche pour continuer la
																		// recherche de
																		// l'emplacement d'insertion
																		// pour le nouveau nœud dans
																		// le sous-arbre gauche.
			}
		} else if (comparaison < 0) {
			if (this.filsDroit == FILS_NUL) {
				raf.seek(raf.getFilePointer() - 8); // deplace le curseur 8 octets vers
													// l'arriere pour
													// se positionner sur la partie fils droit du noeud

				raf.writeInt((int) (raf.length() / TAILLE_TOTAL_OCTETS)); // calcule l'index du nouveau noeud à
																			// ajouter et ecrit cet index dans le
				raf.seek(raf.length()); // fichier
				ecrireStagiaire(stagiaireAjout, raf); // écrit le nouveau nœud dans le fichier

			} else {
				raf.seek(this.filsDroit * TAILLE_TOTAL_OCTETS); // déplace le pointeur à la position du fils
																// droit
				Noeud noeudFilsDroit = lireStagiaire(raf); // lit le nœud fils droit en utilisant la méthode
				noeudFilsDroit.ajouterStagiaire(stagiaireAjout, raf); // appelle récursivement la
																		// méthode sur le nœud fils
																		// droit pour continuer la
																		// recherche de
																		// l'emplacement d'insertion
																		// pour le nouveau nœud dans
																		// le sous-arbre droit.
			}
		} else if (comparaison == 0) {
			if (this.doublon == FILS_NUL) {
				raf.seek(raf.getFilePointer() - 4); // deplace le curseur 4 octets vers
													// l'arriere pour
													// se positionner sur la partie doublon du noeud

				raf.writeInt((int) (raf.length() / TAILLE_TOTAL_OCTETS)); // calcule l'index du nouveau noeud à
																			// ajouter et ecrit cet index dans le
				raf.seek(raf.length()); // fichier
				ecrireStagiaire(stagiaireAjout, raf); // écrit le nouveau doublon dans le fichier

			} else {
				raf.seek(this.doublon * TAILLE_TOTAL_OCTETS); // déplace le pointeur à la position du fils
																// droit
				Noeud noeudDoublon = lireStagiaire(raf); // lit le nœud fils droit en utilisant la méthode
				noeudDoublon.ajouterStagiaire(stagiaireAjout, raf); // appelle récursivement la
																	// méthode sur le doublon pour
																	// continuer la
																	// recherche de
																	// l'emplacement d'insertion
																	// pour le nouveau nœud dans
																	// le sous-arbre droit.
			}
		}

	}

	public void affichageInfixeNoeud(RandomAccessFile raf, ArrayList<Stagiaire> listeStagiaires) throws IOException {
		if (this.filsGauche != FILS_NUL) { // si filsGauche est different de -1
			raf.seek(this.filsGauche * TAILLE_TOTAL_OCTETS); // deplace le curseur à la valeur du filsGauche
			Noeud noeudCourant = lireStagiaire(raf); // lit le filsGauche
			noeudCourant.affichageInfixeNoeud(raf, listeStagiaires); // appel la methode recursive affichageInfixeNoeud
																		// dans l'arbre binaire de recherche jusqu'à ce
																		// qu'il n'y ait plus de fils gauche et à la fin
																		// de l'appel, listeStagiaire contiendra tous
																		// les Stagiaire de l'arbre triés en ordre
																		// croissant
		}
		// System.out.println(this.stagiaire);
		listeStagiaires.add(this.stagiaire); // ajoute le Stagiaire du noeud courant à la liste Stagiaire à la fin de la
												// listeStagiaires
		if (this.doublon != FILS_NUL) {
			raf.seek(this.doublon * TAILLE_TOTAL_OCTETS);
			Noeud noeudCourant = lireStagiaire(raf);
			noeudCourant.affichageInfixeNoeud(raf, listeStagiaires);
		}
		if (this.filsDroit != FILS_NUL) {
			raf.seek(this.filsDroit * TAILLE_TOTAL_OCTETS);
			Noeud noeudCourant = lireStagiaire(raf);
			noeudCourant.affichageInfixeNoeud(raf, listeStagiaires);
		}
	}

	public void rechercherStagiaire(RandomAccessFile raf, Stagiaire nomRecherche, List<Stagiaire> resultat)
			throws IOException {
		int comparaison = this.stagiaire.getNom().compareToIgnoreCase(nomRecherche.getNom().trim());

		if (comparaison == 0) {
			resultat.add(this.stagiaire);
			if (this.doublon != FILS_NUL) {
				raf.seek(this.doublon * TAILLE_TOTAL_OCTETS);
				Noeud doublon = lireStagiaire(raf);
				doublon.rechercherStagiaire(raf, nomRecherche, resultat);
			}
		} else {
			if (comparaison > 0) {
				if (this.filsGauche != FILS_NUL) {
					raf.seek(this.filsGauche * TAILLE_TOTAL_OCTETS);
					Noeud gauche = lireStagiaire(raf);
					gauche.rechercherStagiaire(raf, nomRecherche, resultat);
				} else {
					System.out.println("n'existe pas");
				}

			} else if (comparaison < 0) {
				if (this.filsDroit != FILS_NUL) {
					raf.seek(this.filsDroit * TAILLE_TOTAL_OCTETS);
					Noeud droite = lireStagiaire(raf);
					droite.rechercherStagiaire(raf, nomRecherche, resultat);
				} else {
					System.out.println("n'existe pas");
				}
			}

		}
	}

	// Cette methode est utilisée pour chercher le noeud à supprimer dans l'arbre,
	// et pour appeler la methode supprimerNoeud lorsqu'il est trouvé
	public Noeud supprimer(RandomAccessFile raf, String nomSupprime, int indexParent) {
		try {
			System.out.println("supprimer début");
			int position = (int) (raf.getFilePointer()); // Récupère la position actuelle du pointeur du fichier
			if (raf.length() == 0) { // Si le fichier est vide, il n'y a rien à supprimer
				return null;
			} else {
				Noeud noeudCourant = lireStagiaire(raf); // Lit le nœud suivant à partir de la position actuelle
															// du pointeur

				raf.seek(position); // Replace le curseur à la position du nœud précédent
				if (nomSupprime.trim().equals(noeudCourant.getStagiaire().getNom().trim())) { // si le nom du nœud à
																								// supprimer est égal au
																								// nom
																								// contenu dans le nœud
																								// actuel
					System.out.println("suppression trouvée");
					// return noeudCourant;
					return noeudCourant.supprimerNoeud(noeudCourant, raf, indexParent); // Appelle la
					// méthode supprimerNoeud pour
					// supprimer le nœud
				} else if (noeudCourant.getStagiaire().getNom().trim().compareTo(nomSupprime) > 0) { // si le nom du
																										// nœud actuel
					// est superieur au nom du
					// noeud à supprimer qui
					// signifie que le nœud à
					// supprimer se trouve dans
					// le sous-arbre gauche du
					// nœud actuel.
					System.out.println("cherche à gauche");
					if (noeudCourant.filsGauche != -1) { // si le noeud courant à un filsGauche
						raf.seek(noeudCourant.filsGauche * Noeud.TAILLE_TOTAL_OCTETS); // On se positionne au début du
																						// nœud
																						// du
																						// fils gauche.
						return supprimer(raf, nomSupprime, position); // Appelle récursivement la méthode supprimer pour
																		// chercher le nœud à supprimer dans le
																		// sous-arbre gauche
					} else {
						return null;
					}
				} else if (noeudCourant.getStagiaire().getNom().trim().compareTo(nomSupprime) < 0) {
					System.out.println("cherche à droite");
					if (noeudCourant.filsDroit != -1) {
						raf.seek(noeudCourant.filsDroit * Noeud.TAILLE_TOTAL_OCTETS);
						return supprimer(raf, nomSupprime, position);
					} else {
						return null;
					}
				} else if ((noeudCourant.getStagiaire().getNom().trim().compareTo(nomSupprime) == 0)) {
					System.out.println("c'est un doublon");
					if (noeudCourant.doublon != -1) {
						raf.seek(noeudCourant.doublon * Noeud.TAILLE_TOTAL_OCTETS);
						return supprimer(raf, nomSupprime, position);
					} else {
						return null;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Cette methode est utilisé lorsque le noeud à supprimer a été trouvé et qu'il
	// a soit 0 ou 1 enfant.Elle va modifier le parent du noeud à supprimer
	// pour qu'il pointe vers l'enfant restant (s'il y en a un), ou vers null sinon.
	// Ensuite, elle va libérer l'espace occupé par le noeud supprimé en écrivant -1
	// à la place du filsGauche et filsDroit
	public Noeud supprimerNoeud(Noeud noeudSupp, RandomAccessFile raf, int indexParent) throws IOException {

		try {
			if (noeudSupp.getFilsGauche() == FILS_NUL && noeudSupp.getFilsDroit() == FILS_NUL) { // si le noeudSupp a ni
																									// FG ni FD donc
				// c'est une feuille
				raf.seek(indexParent); // deplace le curseur à la position du parent du noeud supprimé
				Noeud noeudParent = lireStagiaire(raf); // lit le noeud parent

				// compare le nom du parent avec le nom de stagiaire qu'on veut supprimer pour
				// determiner si le noeudSupp etait le FG ou FD de son parent. Si le nom du
				// parent est superieur au nomSuppr, ça veut dire que le nomSupp est un FG
				if (noeudParent.getStagiaire().getNom().trim()
						.compareTo(noeudSupp.getStagiaire().getNom().trim()) > 0) {
					raf.seek(raf.getFilePointer() - 12); // deplace le curseur à la position du fils gauche
					raf.writeInt(-1); // écrit -1 à cette position pour indiquer que le fils gauche n'existe plus.
				} else if (noeudParent.getStagiaire().getNom().trim()
						.compareTo(noeudSupp.getStagiaire().getNom().trim()) < 0) {
					raf.seek(raf.getFilePointer() - 8); // deplace le curseur à la position du fils droit
					raf.writeInt(-1); // écrit -1 à cette position pour indiquer que le fils droit n'existe plus.
				}
//					else {
//						raf.seek(raf.getFilePointer() - 4); // deplace le curseur à la position du doublon
//						raf.writeInt(-1); // écrit -1 à cette position pour indiquer que le fils gauche n'existe plus.
//					}
			} else if (noeudSupp.getFilsGauche() == FILS_NUL || noeudSupp.getFilsDroit() == FILS_NUL) { // Si le noeud à
																										// supprimer a
																										// seulement un
																										// fils (droit
																										// ou gauche),
																										// on entre dans
																										// cette
																										// condition.
				if (noeudSupp.filsGauche != FILS_NUL) { // Si le noeud à supprimer a un fils gauche, on entre dans cette
														// condition.
					raf.seek(indexParent); // deplace le curseur à la position du parent
					Noeud parent = lireStagiaire(raf); // lit le noeud parent

					// compare les noms du parent et du noeud à supprimer pour déterminer si le fils
					// à supprimer est le fils gauche ou le fils droit du parent.
					if (parent.getStagiaire().getNom().trim()
							.compareToIgnoreCase(noeudSupp.getStagiaire().getNom().trim()) > 0) { // si le nom du
																									// stagiaire parent
																									// est superieur au
																									// nom du stagiaire
																									// qu'on veut
																									// supprimer,c'est
																									// le fils gauche du
																									// parent
						raf.seek(raf.getFilePointer() - 12); // deplace le curseur au debut du fils gauche
						raf.writeInt(noeudSupp.filsGauche); // écrit l'index du fils gauche du noeudSupp permet de
															// mettre à jour la référence du nœud parent vers son
															// nouveau fils gauche.

					} else if (parent.getStagiaire().getNom().trim()
							.compareToIgnoreCase(noeudSupp.getStagiaire().getNom().trim()) < 0) {
						raf.seek(raf.getFilePointer() - 8);
						raf.writeInt(noeudSupp.filsGauche);
					}

				} else { // si le noeud à supprimer à un fils droit, on entre dans cette condition
					raf.seek(indexParent);
					Noeud parent = lireStagiaire(raf);
					if (parent.getStagiaire().getNom().trim()
							.compareToIgnoreCase(noeudSupp.getStagiaire().getNom().trim()) > 0) {
						raf.seek(raf.getFilePointer() - 12);
						raf.writeInt(noeudSupp.filsDroit);
					} else if (parent.getStagiaire().getNom().trim()
							.compareToIgnoreCase(noeudSupp.getStagiaire().getNom().trim()) < 0) {
						raf.seek(raf.getFilePointer() - 8);
						raf.writeInt(noeudSupp.filsDroit);
					}
				}
			} else if (noeudSupp.getFilsGauche() != FILS_NUL && noeudSupp.getFilsDroit() != FILS_NUL) { // Si le nœud à
																										// supprimer a
																										// deux enfants

				int position = (int) raf.getFilePointer(); // on sauvegarde l'index actuel
				raf.seek(noeudSupp.filsGauche * Noeud.TAILLE_TOTAL_OCTETS); // On se positionne au début du nœud du
																			// fils gauche.
				Noeud noeudSupprimerFilsGauche = lireStagiaire(raf); // on lit le noeud gauche et stocke dans une
																		// variable
				Noeud descendant = dernierDescendant(raf, noeudSupprimerFilsGauche); // on trouve le dernier descandant
																						// du noeud
																						// fils gauche en appelant la
																						// methode
																						// dernierDescendant et on le
																						// stocke dans la variable
																						// descendant

				// ces deux lignes affichent respectivement le dernier descendant trouvé et la
				// position de l'index sauvegardé.
				System.out.println(descendant);
				System.out.println(position);
				// noeudSupp.stagiaire = descendant.stagiaire; // on remplace le stagiaire à
				// supprimer par le stagiaire du dernier
				// descendant
				raf.seek(position); // on revient à la position sauvegardée precedemment

				// ces lignes écrivent les informations du stagiaire du dernier descendant
				// trouvé à la place du stagiaire à supprimer, en utilisant les méthodes
				// writeChars et writeInt de l'objet raf pour écrire chaque attribut du
				// stagiaire
				raf.writeChars(descendant.getStagiaire().getNomLong());
				raf.writeChars(descendant.getStagiaire().getPrenomLong());
				raf.writeChars(descendant.getStagiaire().getLocalisationLong());
				raf.writeChars(descendant.getStagiaire().getFormationLong());
				raf.writeInt(descendant.getStagiaire().getAnneeEntree());
				raf.seek(raf.getFilePointer() + 8); // on ecrit le doublon s'il a un doublon
				raf.writeInt(descendant.doublon);
				raf.seek(position);
				Noeud ecrit = lireStagiaire(raf); // on lit le nœud qui vient d'être écrit et on le stocke dans la
													// variable ecrit.
				System.out.println(ecrit);
				raf.seek(noeudSupp.filsGauche * Noeud.TAILLE_TOTAL_OCTETS); // on se positionne de nouveau au début du
																			// nœud du fils gauche.

				supprimer(raf, descendant.getStagiaire().getNom(), position); // on appelle la méthode supprimer pour
																				// supprimer le nom du stagiaire du
																				// dernier descendant et la position
																				// sauvegardée précédemment.

			}
//				else if (noeudSupp.getDoublon() != FILS_NUL) {
//					int position = (int) raf.getFilePointer();
//					raf.seek(noeudSupp.doublon * Noeud.TAILLE_TOTAL_OCTETS);
//					Noeud noeudDoublon = lireStagiaire(raf);
//				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// On doit trouver le dernier descendant pour déterminer l'élément à échanger
	// avec l'élément à supprimer. C'est à dire que si le nœud à supprimer a deux
	// enfants, on doit le remplacer par le nœud le plus à droite du sous-arbre
	// gauche de l'élément à supprimer qu'on appelle dernier descendant car il est
	// le
	// descendant le plus à droite de l'élément à supprimer.
	public Noeud dernierDescendant(RandomAccessFile raf, Noeud noeudSupp) {
		try {
			if (noeudSupp.filsDroit == -1) { // si le noeudSupp n'a pas de filsDroit, la méthode renvoie le nœud
												// lui-même
				return noeudSupp;
			} else {
				raf.seek(noeudSupp.filsDroit * Noeud.TAILLE_TOTAL_OCTETS); // Si le nœud a un fils droit, on deplace le
																			// curseur à la position du fils droit
				Noeud noeudFilsDroit = lireStagiaire(raf);
				return dernierDescendant(raf, noeudFilsDroit); // appel recursif de la methode dernierDescendant jusqu'à
																// dernier noeud descendant, c'est-à-dire le noeud qui
																// n'a
																// pas de fils droit. Le dernier descendant sera ensuite
			} // utilisé pour remplacer le noeud à supprimer dans la
				// méthode de suppression.
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
