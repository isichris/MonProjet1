package FusionProjet1.Fusion3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * La classe Noeud est utilisée pour représenter les noeuds dans un arbre
 * binaire de recherche où chaque noeud contient un objet Stagiaire ainsi que
 * les indices de ses fils gauche et droit et de son doublon. Les objets de
 * cette classe contiennent également des constantes pour représenter des
 * valeurs nulles ou des tailles en octets.
 */

public class Noeud {

	/**
	 * L'objet Stagiaire stocké dans le noeud. L'indice du fils gauche du noeud.
	 * L'indice du fils droit du noeud. L'indice du doublon du noeud.
	 */
	protected Stagiaire stagiaire;
	protected int filsGauche;
	protected int filsDroit;
	protected int doublon;

	/**
	 * Valeur représentant l'absence de fils gauche ou droit.
	 */
	public static final int FILS_NUL = -1;

	/**
	 * Taille en octets des champs filsGauche, filsDroit et doublon.
	 */
	public static final int TAILLE_FD_FG_DB = 3 * 4;

	/**
	 * Taille totale en octets d'un objet Noeud.
	 */
	public static final int TAILLE_TOTAL_OCTETS = Stagiaire.TAILLE_MAX_OCTET_STAGIAIRE + TAILLE_FD_FG_DB;

	/**
	 * Constructeur prenant un objet Stagiaire en paramètre et initialisant les
	 * champs filsGauche et filsDroit et doublon à FILS_NUL
	 * 
	 * @param stagiaire l'objet Stagiaire à stocker dans le noeud.
	 */
	public Noeud(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
		this.filsGauche = FILS_NUL;
		this.filsDroit = FILS_NUL;
		this.doublon = FILS_NUL;
	}

	/**
	 * Constructeur prenant un objet Stagiaire et les indices de ses fils gauche,
	 * droit et doublon éventuel.
	 * 
	 * @param stagiaire  l'objet Stagiaire à stocker dans le noeud.
	 * @param filsGauche l'indice du fils gauche du noeud.
	 * @param filsDroit  l'indice du fils droit du noeud.
	 * @param doublon    l'indice du doublon éventuel du noeud.
	 */
	public Noeud(Stagiaire stagiaire, int filsGauche, int filsDroit, int doublon) {

		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
		this.doublon = doublon;
	}

	/**
	 * Constructeur par défaut.
	 */
	public Noeud() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Les getters et les setters pour les attributs
	 */
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

	/**
	 * Renvoie une représentation sous forme de chaîne de caractères de l'objet
	 * Noeud.
	 * 
	 * @return une chaîne de caractères représentant l'objet Noeud.
	 */
	@Override
	public String toString() {
		return "Noeud [stagiaire=" + stagiaire + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit
				+ ", doublon=" + doublon + "]";
	}

	/**
	 * Écrit un objet Stagiaire dans un fichier RandomAccessFile à la position
	 * courante du curseur.
	 *
	 * @param stagiaire l'objet Stagiaire à écrire
	 * @param raf       le fichier RandomAccessFile dans lequel écrire l'objet
	 *                  Stagiaire
	 */
	public void ecrireStagiaire(Stagiaire stagiaire, RandomAccessFile raf) {
		try {

			// Place le curseur à la fin du fichier pour ajouter l'objet Stagiaire à la fin
			// du fichier
			raf.seek(raf.length());

			// Écrit les propriétés de l'objet Stagiaire dans le fichier
			raf.writeChars(stagiaire.getNomLong());
			raf.writeChars(stagiaire.getPrenomLong());
			raf.writeChars(stagiaire.getLocalisationLong());
			raf.writeChars(stagiaire.getFormationLong());
			raf.writeInt(stagiaire.getAnneeEntree());
			raf.writeInt(Noeud.FILS_NUL);
			raf.writeInt(Noeud.FILS_NUL);
			raf.writeInt(Noeud.FILS_NUL);

		} catch (IOException e) {
			System.out.println("Probleme");
			e.printStackTrace();
		}
	}

	/**
	 * Lit un objet Noeud depuis un fichier RandomAccessFile à la position courante
	 * du curseur.
	 *
	 * @param raf le fichier RandomAccessFile à partir duquel lire l'objet Noeud
	 * @return l'objet Noeud lu depuis le fichier
	 */
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

			// Lit les propriétés de l'objet Stagiaire depuis le fichier
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

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Crée un objet Stagiaire à partir des données lues depuis le fichier
		Stagiaire stagiaire = new Stagiaire(nom.trim(), prenom.trim(), localisation.trim(), formation.trim(),
				anneeEntree);

		// Crée un objet Noeud contenant l'objet Stagiaire et les références aux fils et
		// doublon
		return new Noeud(stagiaire, filsGauche, filsDroit, doublon);
	}

	/**
	 * Ajoute un stagiaire dans l'arbre binaire de recherche en écriture dans le
	 * fichier binaire.
	 * 
	 * @param stagiaireAjout le stagiaire à ajouter
	 * 
	 * @param raf            le fichier binaire à écrire
	 * 
	 * @throws IOException si une erreur d'entrée/sortie se produit lors de
	 *                     l'écriture dans le fichier binaire
	 */
	public void ajouterStagiaire(Stagiaire stagiaireAjout, RandomAccessFile raf) throws IOException {

		// comparaison de la valeur du stagiaire avec le noeud actuel
		int comparaison = this.stagiaire.compareTo(stagiaireAjout);

		if (comparaison > 0) {

			// le stagiaire à ajouter doit être inséré dans le fils gauche
			if (this.filsGauche == FILS_NUL) {
				raf.seek(raf.getFilePointer() - 12); // déplacement avant l'écriture du nouveau noeud

				raf.writeInt((int) (raf.length() / TAILLE_TOTAL_OCTETS)); // écriture de l'indice du nouveau noeud dans
																			// le fichier
				raf.seek(raf.length()); // déplacement à la fin du fichier pour écrire le nouveau noeud
				ecrireStagiaire(stagiaireAjout, raf); // écriture du stagiaire dans le fichier

			} else {
				raf.seek(this.filsGauche * TAILLE_TOTAL_OCTETS); // déplacement vers le noeud fils gauche dans le
																	// fichier

				Noeud noeudFilsGauche = lireStagiaire(raf); // lecture du noeud fils gauche
				noeudFilsGauche.ajouterStagiaire(stagiaireAjout, raf); // // appel récursif pour ajouter le stagiaire
																		// dans le fils gauche
			}
		} else if (comparaison < 0) {

			// le stagiaire à ajouter doit être inséré dans le fils droit
			if (this.filsDroit == FILS_NUL) {
				raf.seek(raf.getFilePointer() - 8); // déplacement avant l'écriture du nouveau noeud

				raf.writeInt((int) (raf.length() / TAILLE_TOTAL_OCTETS)); // écriture de l'indice du nouveau noeud dans
																			// le fichier
				raf.seek(raf.length()); // déplacement à la fin du fichier pour écrire le nouveau noeud
				ecrireStagiaire(stagiaireAjout, raf); // écriture du stagiaire dans le fichier

			} else {
				raf.seek(this.filsDroit * TAILLE_TOTAL_OCTETS); // déplacement vers le noeud fils droit dans le fichier
				Noeud noeudFilsDroit = lireStagiaire(raf); // lecture du noeud fils droit
				noeudFilsDroit.ajouterStagiaire(stagiaireAjout, raf); // appel récursif pour ajouter le stagiaire dans
																		// le fils droit
			}
		}

		// Si le stagiaire à ajouter est égal au stagiaire courant, on ajoute le
		// stagiaire comme doublon.
		else if (comparaison == 0) {
			if (this.doublon == FILS_NUL) {
				raf.seek(raf.getFilePointer() - 4);

				raf.writeInt((int) (raf.length() / TAILLE_TOTAL_OCTETS));
				raf.seek(raf.length());
				ecrireStagiaire(stagiaireAjout, raf);

			} else {
				raf.seek(this.doublon * TAILLE_TOTAL_OCTETS);
				Noeud noeudDoublon = lireStagiaire(raf);
				noeudDoublon.ajouterStagiaire(stagiaireAjout, raf);
			}
		}

	}

	/**
	 * Parcours l'arbre binaire de recherche en mode infixe et ajoute les Stagiaires
	 * de chaque noeud dans une liste.
	 * 
	 * @param raf             le fichier RandomAccessFile dans lequel est stocké
	 *                        l'arbre binaire de recherche
	 * 
	 * @param listeStagiaires la liste de Stagiaire à remplir avec les Stagiaires de
	 *                        chaque noeud de l'arbre binaire de recherche
	 * 
	 * @throws IOException si une erreur survient lors de la lecture du fichier
	 *                     RandomAccessFile
	 */
	public void affichageInfixeNoeud(RandomAccessFile raf, ArrayList<Stagiaire> listeStagiaires) throws IOException {
		if (this.filsGauche != FILS_NUL) { // si filsGauche est different de -1
			raf.seek(this.filsGauche * TAILLE_TOTAL_OCTETS); // deplace le curseur à la valeur du filsGauche
			Noeud noeudCourant = lireStagiaire(raf); // lit le filsGauche
			noeudCourant.affichageInfixeNoeud(raf, listeStagiaires);
		}
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

	/**
	 * Recherche un stagiaire par nom dans l'arbre à partir du noeud courant. Ajoute
	 * tous les Stagiaires ayant un nom égal à nomRecherche dans la liste resultat.
	 *
	 * @param raf          le fichier à parcourir
	 * @param nomRecherche le Stagiaire à rechercher
	 * @param resultat     la liste des Stagiaires correspondant à la recherche
	 * @throws IOException si une erreur d'entrée/sortie se produit lors de la
	 *                     lecture du fichier
	 */
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

	/**
	 * Cette méthode est utilisée pour chercher le nœud à supprimer dans l'arbre et
	 * appeler la méthode supprimerNoeud lorsqu'il est trouvé.
	 * 
	 * @param raf         le fichier à partir duquel le nœud est lu et supprimé
	 * @param nomSupprime le nom du nœud à supprimer
	 * @param indexParent l'index du parent du nœud courant
	 * @return le nœud supprimé, null s'il n'a pas été trouvé
	 */
	public Noeud supprimer(RandomAccessFile raf, String nomSupprime, int indexParent) {
		try {
//			System.out.println("supprimer début");
			int position = (int) (raf.getFilePointer());
			if (raf.length() == 0) {
				return null;
			} else {
				Noeud noeudCourant = lireStagiaire(raf);

				raf.seek(position);

				// Si le nom du stagiaire courant correspond au nom à supprimer, appelle la
				// méthode supprimerNoeud pour supprimer ce nœud. Sinon, si le nom du stagiaire
				// courant est supérieur au nom à supprimer, recherche le nœud à supprimer dans
				// le sous-arbre gauche. Si le sous-arbre gauche est vide, renvoie null.

				if (nomSupprime.trim().equals(noeudCourant.getStagiaire().getNom().trim())) {
					System.out.println("suppression trouvée");
					// return noeudCourant;
					return noeudCourant.supprimerNoeud(noeudCourant, raf, indexParent);

				} else if (noeudCourant.getStagiaire().getNom().trim().compareTo(nomSupprime) > 0) {

//					System.out.println("cherche à gauche");
					if (noeudCourant.filsGauche != -1) {
						raf.seek(noeudCourant.filsGauche * Noeud.TAILLE_TOTAL_OCTETS);
						return supprimer(raf, nomSupprime, position);
					} else {
						return null;
					}
				}

				// Si le nom du stagiaire dans le nœud courant est plus petit que le nom à
				// supprimer, on cherche dans le sous-arbre droit. Si le sous-arbre droit n'est
				// pas vide, on déplace le pointeur de fichier vers le nœud correspondant et on
				// appelle la méthode récursivement. Sinon, le nœud à supprimer n'a pas été
				// trouvé et on retourne null.

				else if (noeudCourant.getStagiaire().getNom().trim().compareTo(nomSupprime) < 0) {
//					System.out.println("cherche à droite");
					if (noeudCourant.filsDroit != -1) {
						raf.seek(noeudCourant.filsDroit * Noeud.TAILLE_TOTAL_OCTETS);
						return supprimer(raf, nomSupprime, position);
					} else {
						return null;
					}
				}

				// Si le nom du nœud courant est égal au nom du nœud à supprimer, cette partie
				// de la méthode est utilisée pour supprimer un doublon dans l'arbre. Si le nœud
				// courant a un doublon, on se positionne sur le doublon et on appelle
				// récursivement la méthode supprimer pour supprimer le doublon. Si le nœud
				// courant n'a pas de doublon, la méthode renvoie null.

				else if ((noeudCourant.getStagiaire().getNom().trim().compareTo(nomSupprime) == 0)) {
//					System.out.println("c'est un doublon");
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

	/**
	 * Cette méthode est appelée lorsqu'un noeud à supprimer a été trouvé et qu'il a
	 * soit 0 ou 1 enfant. Elle va modifier le parent du noeud à supprimer pour
	 * qu'il pointe vers l'enfant restant (s'il y en a un), ou vers null sinon.
	 * Ensuite, elle va libérer l'espace occupé par le noeud supprimé en écrivant -1
	 * à la place du filsGauche et filsDroit.
	 *
	 * @param noeudSupp   le noeud à supprimer
	 * @param raf         le fichier dans lequel le noeud est stocké
	 * @param indexParent l'index du parent du noeud à supprimer
	 * @return le noeud parent mis à jour
	 * @throws IOException si une erreur survient lors de l'écriture dans le fichier
	 */
	public Noeud supprimerNoeud(Noeud noeudSupp, RandomAccessFile raf, int indexParent) throws IOException {

		try {

			// si le noeudSupp a ni FG ni FD donc c'est une feuille
			if (noeudSupp.getFilsGauche() == FILS_NUL && noeudSupp.getFilsDroit() == FILS_NUL) {
				raf.seek(indexParent); // deplace le curseur à la position du parent du noeud supprimé
				Noeud noeudParent = lireStagiaire(raf); // lit le noeud parent

				// compare le nom du parent avec le nom de stagiaire qu'on veut supprimer pour
				// determiner si le noeudSupp est le FG ou FD de son parent. Si le nom du parent
				// est superieur au nomSuppr, ça veut dire que le nomSupp est un FG

				if (noeudParent.getStagiaire().getNom().trim()
						.compareTo(noeudSupp.getStagiaire().getNom().trim()) > 0) {
					raf.seek(raf.getFilePointer() - 12); // deplace le curseur à la position du fils gauche
					raf.writeInt(-1); // écrit -1 à cette position pour indiquer que le fils gauche n'existe plus.
				} else if (noeudParent.getStagiaire().getNom().trim()
						.compareTo(noeudSupp.getStagiaire().getNom().trim()) < 0) {
					raf.seek(raf.getFilePointer() - 8); // deplace le curseur à la position du fils droit
					raf.writeInt(-1); // écrit -1 à cette position pour indiquer que le fils droit n'existe plus.
				}
			}

			// Cette partie de code permet de supprimer un noeud de l'arbre binaire dans le
			// cas où il ne possède qu'un fils gauche ou droit. Si le noeud à supprimer a un
			// fils gauche, le noeud parent est lu, puis le nom du stagiaire parent est
			// comparé avec le nom du stagiaire du noeud à supprimer. Si le nom du stagiaire
			// parent est supérieur, alors cela signifie que le fils gauche du noeud parent
			// doit être remplacé par le fils gauche du noeud à supprimer. Sinon, c'est le
			// fils droit qui doit être remplacé. Un index vers le nouveau fils est écrit à
			// l'emplacement approprié. Si le noeud à supprimer a un fils droit, la même
			// procédure est suivie, mais avec le fils droit.

			else if (noeudSupp.getFilsGauche() == FILS_NUL || noeudSupp.getFilsDroit() == FILS_NUL) {
				if (noeudSupp.filsGauche != FILS_NUL) {
					raf.seek(indexParent); // deplace le curseur à la position du parent
					Noeud parent = lireStagiaire(raf); // lit le noeud parent

					// compare les noms du parent et du noeud à supprimer pour déterminer si le fils
					// à supprimer est le fils gauche ou le fils droit du parent.
					if (parent.getStagiaire().getNom().trim()
							.compareToIgnoreCase(noeudSupp.getStagiaire().getNom().trim()) > 0) {
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
			}
			// Si le nœud à supprimer a deux enfants, on le remplace par son dernier
			// descendant.
			else if (noeudSupp.getFilsGauche() != FILS_NUL && noeudSupp.getFilsDroit() != FILS_NUL) {

				int position = (int) raf.getFilePointer(); // on sauvegarde l'index actuel
				raf.seek(noeudSupp.filsGauche * Noeud.TAILLE_TOTAL_OCTETS); // Se positionne au début du nœud du fils
																			// gauche du nœud à supprimer
				Noeud noeudSupprimerFilsGauche = lireStagiaire(raf); // Lit le nœud du fils gauche et stocke dans une
																		// variable
				// Trouve le dernier descendant du nœud fils gauche en appelant la méthode
				// dernierDescendant et le stocke dans la variable descendant
				Noeud descendant = dernierDescendant(raf, noeudSupprimerFilsGauche);

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
				raf.seek(noeudSupp.filsGauche * Noeud.TAILLE_TOTAL_OCTETS); // Se positionne de nouveau au début du nœud
																			// du fils gauche du nœud à supprimer
				// Appelle la méthode supprimer pour supprimer le nom du stagiaire du dernier
				// descendant et la position sauvegardée précédemment
				supprimer(raf, descendant.getStagiaire().getNom(), position);

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

	/**
	 * Cette méthode prend en paramètres le raf et un nœud à supprimer
	 * et renvoie le dernier descendant de ce nœud. Si le nœud à supprimer n'a pas
	 * de fils droit, la méthode renvoie le nœud lui-même. Sinon, elle se déplace à
	 * la position du fils droit et appelle récursivement la méthode
	 * dernierDescendant jusqu'à ce qu'elle atteigne le dernier descendant,
	 * c'est-à-dire le nœud qui n'a pas de fils droit. Le dernier descendant sera
	 * ensuite utilisé pour remplacer le nœud à supprimer dans la méthode de
	 * suppression.
	 * 
	 * @param raf       le fichier dans lequel le noeud est stocké
	 * @param noeudSupp Le nœud à supprimer.
	 * @return Le dernier descendant du nœud à supprimer.
	 */
	public Noeud dernierDescendant(RandomAccessFile raf, Noeud noeudSupp) {
		try {
			if (noeudSupp.filsDroit == -1) { 
				return noeudSupp;
			} else {
				raf.seek(noeudSupp.filsDroit * Noeud.TAILLE_TOTAL_OCTETS);
				Noeud noeudFilsDroit = lireStagiaire(raf);
				return dernierDescendant(raf, noeudFilsDroit); 
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
