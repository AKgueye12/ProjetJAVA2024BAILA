
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Classe;
import entities.Etudiant;
import entities.Inscription;
import entities.ProfClasse;
import entities.Professeur;
import service.ClasseService;
import service.EtudiantService;
import service.InscriptionService;
import service.ProfesseurService;

public class AttacheView {
    public static void main(String[] args) throws Exception {
        int choix;
        Scanner sc = new Scanner(System.in);
        EtudiantService etudiantService = new EtudiantService();
        ClasseService classeService= new ClasseService();
        InscriptionService inscriptionService= new InscriptionService();
        ProfesseurService professeurService= new ProfesseurService();
        do{
            System.out.println("1-Faire une inscription ou une reinscription");
            System.out.println("2-Lister les étudiants inscrits");
            System.out.println("3-Ajouter une classe");
            System.out.println("4-Lister les classes");
            System.out.println("5-Ajouter des professeurs et affecter une classe");
            System.out.println("6-Lister les professeurs");
            System.out.println("7-Quitter");
            choix=sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                System.out.println("Entrer le matricule de l'etudiant");
                String matricule=sc.nextLine();
                Etudiant etudiant  =new Etudiant();
                etudiant= etudiantService.rechercheEtudiantParMatricule(matricule);
                if (etudiant==null) {
                    System.out.println("Etudiant n'existe pas.Veuillez l'inscrire");
                    System.out.println("Entrer le matricule de l'etudiant");
                    matricule=sc.nextLine();
                    System.out.println("Veuillez entrer le nom complet de l'etudiant");
                    String nomComplet=sc.nextLine();
                    System.out.println("Veuillez entrer le tuteur de l'etudiant");
                    String tuteur=sc.nextLine();
                    etudiant =new Etudiant();
                    etudiant.setMatricule(matricule);
                    etudiant.setNomComplet(nomComplet);
                    etudiant.setTuteur(tuteur);
                    etudiantService.ajouterEtudiant(etudiant);

                    
                }else{
                    System.out.println("La matricule existe deja.Reinscrivez l'etudiant");
                }
                Inscription inscription=new Inscription();
                System.out.println("Veuillez choisir l'année");
                String anneeScolaire=sc.nextLine();
                System.out.println("Les classes sont les suivantes");
                List <Classe> classes= classeService.ListerClasse();
                for(Classe cl:classes){
                    System.out.println(cl.getId()+""+cl.getNiveau()+""+cl.getFiliere());
                }
                int idClasse=sc.nextInt();
                Classe classe=new Classe();
                classe=classeService.findClasseById(idClasse);
                inscription.setClasse(classe);
                inscription.setAnneeScolaire(anneeScolaire);
                inscription.setEtudiant(etudiant);
                inscriptionService.faireInscription(inscription);
                break;

                    
                    
                case 2:
                System.out.println("Saisir l'année d'inscription");
                anneeScolaire=sc.nextLine();
                List<Inscription> inscriptions=inscriptionService.listerInscriptionParAnnee(anneeScolaire);
                for (Inscription inscript : inscriptions) {
                    System.out.println("Matricule: "+inscript.getEtudiant().getMatricule()+"\n Nom complet : "+inscript.getEtudiant().getNomComplet()+"\n Tuteur : "+inscript.getEtudiant().getNomComplet());
                    
                }
                System.out.println("***************************************************");
                System.out.println("Voulez vous filtrez cette liste par classe oui/non");
                String reponse=sc.nextLine();
                if(reponse.equalsIgnoreCase("oui")){
                    System.out.println("les classes sont les suivantes :");
                    classes= classeService.ListerClasse();
                    for(Classe cl: classes){
                        System.out.println(cl.getId()+"-"+cl.getNiveau()+""+ cl.getFiliere());
                    }
                    idClasse=sc.nextInt();

                    inscriptions= inscriptionService.listerInscriptionParAnnee(anneeScolaire,idClasse);
                    for (Inscription inscript : inscriptions) {
                        System.out.println("Matricule: "+inscript.getEtudiant().getMatricule()+"\n Nom complet : "+inscript.getEtudiant().getNomComplet()+"\n Tuteur : "+inscript.getEtudiant().getNomComplet());
                        
                    }

                }

                break;

                case 3:
                System.out.println("Entrez le niveau de la classe(L1, L2, L3)");
                String niveau=sc.nextLine();
                System.out.println("Entrez la filière de la classe");
                String filiere=sc.nextLine();
                Classe cl= new Classe();
                cl.setNiveau(niveau);
                cl.setFiliere(filiere);
                classeService.ajouterClasse(cl);
                System.out.println("Classe ajoutée avec succés");
                break;
                case 4:

                System.out.println("les classes ci-aprés");
                  List<Classe> Classes= classeService.ListerClasse();
                  for (Classe Classe : Classes) {
                    System.out.println(Classe.getNiveau()+" "+Classe.getFiliere());

                  }
                    break;

                case 5:
                Professeur professeur = new Professeur();
                  System.out.println("Entrer le nci");
                  professeur.setNci(sc.nextInt());
                  sc.nextLine();
                  System.out.println("entrez le nom complet ");
                  professeur.setNomComplet(sc.nextLine());
                  System.out.println("entrez le grade ");
                  professeur.setGrade(sc.nextLine());
                  classes=classeService.ListerClasse();
                  List<ProfClasse> ListeProfClass= new ArrayList<>();
                  int response =2;
                  do {
                    for (Classe Classe : classes) {
                        System.out.println(Classe.getId()+" "+Classe.getNiveau()+" "+ Classe.getFiliere());
                    }
                    System.out.println("Veuillez sélectionner la classe à affecter ");
                    int id=sc.nextInt();
                    cl= classeService.findClasseById(id);
                    if (cl!=null) {
                        ProfClasse profClasse=new ProfClasse();
                        profClasse.setProfesseur(professeur);

                        int in=0;
                        if (ListeProfClass.size()>0) {
                          for (ProfClasse p : ListeProfClass) {
                            if (p.getClasse().getId() == cl.getId()) {
                                in=1;
                                System.out.println("classe déjà passée au prof ");
                            }
                          } 
                          if (in==0) {
                            ListeProfClass.add(profClasse);
                          } 
                        }else{
                            ListeProfClass.add(profClasse);
                        }
                    }else{ 
                        System.out.println("Id non-existant");
                    }

                    System.out.println("voulez vous continuer  1-oui 2-Non");  

                  } while (response==1);

                  professeur.setProfdeclasse(ListeProfClass);
                  if (professeur.getProfdeclasse().size()<1) {
                    System.out.println("le professeur doit avoir au moin une classe");
                  }else{
                    professeurService.ajouterProf(professeur);
                  }
                break;
                case 6:
                System.out.println("la liste des professeurs si aprés ");
                List <Professeur> professeurs= professeurService.Listerprofesseur();
                for (Professeur prof : professeurs) {
                    System.out.println(prof.getNci()+" "+prof.getNomComplet()+" "+prof.getGrade());
                }
                break;
                default:
                    break;
            }
        } while(choix !=7);

        System.out.println("*****MERCI A BIENTOT******");

    sc.close();
}
}
