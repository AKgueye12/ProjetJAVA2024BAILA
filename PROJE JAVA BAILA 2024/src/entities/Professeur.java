package entities;

import java.util.List;

public class Professeur {
    private String nomComplet;
    private int nci;
    private String grade;
    Classe classe;
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    List<ProfClasse>  Profdeclasse;
    public List<ProfClasse> getProfdeclasse() {
        return Profdeclasse;
    }
    public void setProfdeclasse(List<ProfClasse> profdeclasse) {
        Profdeclasse = profdeclasse;
    }
    public Professeur() {
    }
    public String getNomComplet() {
        return nomComplet;
    }
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public int getNci() {
        return nci;
    }
    public void setNci(int nci) {
        this.nci = nci;
    }
    
}
