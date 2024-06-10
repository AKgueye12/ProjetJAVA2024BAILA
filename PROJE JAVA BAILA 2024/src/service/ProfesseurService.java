package service;

import java.util.List;

import entities.Professeur;
import repositorie.ProfClasseRepository;
import repositorie.ProfesseurRepository;

public class ProfesseurService {
    ProfesseurRepository professeurRepository= new ProfesseurRepository();
    ProfClasseRepository ProfClasseRepository = new ProfClasseRepository();
    
     public void ajouterProf(Professeur professeur){
        professeurRepository.insertProfesseur(professeur);
    }

    public List<Professeur>Listerprofesseur(){
        return professeurRepository.selectAll();
    }
}
