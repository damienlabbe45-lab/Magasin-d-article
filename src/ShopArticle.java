import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe principale gérant les opérations sur la base de données pour les articles du magasin.
 */
public class ShopArticle {
	

    /**
     * Exécute les instructions SQL contenues dans le fichier de script.
     *
     * @param conn la connexion active à la base de données
     */
    public static void fileRequest(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            String sql = file();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
	/**
	 * la liste de tout les tests de requêtes sql;
	 * d'abord une insertion puis un update
	 * @param conn la connexion à la base de donnée
	 */
	private static void testRequest(Connection conn){
		fileRequest(conn);
        request(conn, "INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( ? ,? ,?)",
        "disque dur externe 890 To", "SATA", 34.0);
		request(conn, "UPDATE T_Articles SET UnitaryPrice = ? WHERE Description = ? AND Brand = ? AND UnitaryPrice = ?",
		30.0, "disque dur externe 890 To", "SATA", 34.0);
		request(conn, "DELETE FROM T_articles WHERE Description = ? AND Brand = ? AND UnitaryPrice = ?", 
		"disque dur externe 890 To", "SATA", 30.0);
	}

    /**
     * Point d'entrée principal du programme.
     * Établit la connexion à MariaDB, exécute le script SQL de structure puis appelle testRequest.
     *
     * @param args les arguments transmis en ligne de commande (non utilisés)
     * @throws Exception en cas d'erreur de chargement de classe ou d'exécution
     */
    public static void main(String[] args) throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Shop?allowMultiQueries=true",
                "root", "")) {
            System.out.println("Connexion réussie !");
            testRequest(conn);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}