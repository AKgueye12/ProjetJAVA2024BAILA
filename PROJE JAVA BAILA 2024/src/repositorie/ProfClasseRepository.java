package repositorie;

import java.sql.SQLException;
import entities.ProfClasse;

public class ProfClasseRepository extends Database {
    private final String SQL_INSERT_PROFESSEUR_CLASSE = "INSERT INTO profclasse (id,nci, id_cl`) VALUES (?,?,?)";
    
    public void insert(ProfClasse  profClasse){
        try {
             ouvrirConnexion();
             initPrepareStatement(SQL_INSERT_PROFESSEUR_CLASSE);
             statement.setInt(1,profClasse.getId());
             statement.setInt(2,profClasse.getProfesseur().getNci());
             statement.setInt(3,profClasse.getClasse().getId());
             executeUpdate();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }

}
