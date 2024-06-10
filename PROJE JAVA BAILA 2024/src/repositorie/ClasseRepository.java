package repositorie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Classe;

public class ClasseRepository extends Database {
    private final String SQL_SELECT_CLASSE="select * from classe";
    private final String SQL_INSERT_CLASSE="INSERT INTO classe (filiere, niveau) VALUES (?, ?)";
    private final String SQL_SELECT_BY_ID="SELECT * FROM classe where id like ? ";


    public List <Classe> selectAll(){
        List <Classe> classes= new ArrayList<>();
        try{
            ouvrirConnexion();
            initPrepareStatement(SQL_SELECT_CLASSE);
            ResultSet rs= executeSelect();
            while(rs.next()){
                Classe classe=new Classe();
                classe.setId(rs.getInt("id"));
                classe.setNiveau(rs.getString("niveau"));
                classe.setFiliere(rs.getString("filiere"));
                classes.add(classe);
            }
            rs.close();
            closeConnexion();
        }
        catch(SQLException e){
            System.out.println("Erreur de connexion a la BD");
        }
        return classes;

    }
    private void closeConnexion() {
    }
    public void insertClasse(Classe classe){
        try {
             ouvrirConnexion();
             initPrepareStatement(SQL_INSERT_CLASSE);
              statement.setString(1,classe.getNiveau());
              statement.setString(2, classe.getFiliere());
             executeUpdate();
        } catch (SQLException e) {
          
            e.printStackTrace();
        }
    }

    
       public Classe selectClasseById(int id){
        Classe classe = null;
        try{
            ouvrirConnexion();
            initPrepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1,id);
            ResultSet rs=executeSelect();
            if(rs.next()){
                 classe=new Classe();
                 classe.setId(rs.getInt ("id"));
                 classe.setNiveau(rs.getString("niveau"));
                 classe.setFiliere(rs.getString("filiere"));
                 
              

            }
            statement.close();
            rs.close();
            conn.close();

        }catch (SQLException e) {
            System.out.println("Erreur de connexion à la BD");
        }
        return classe;
    }
    
}
