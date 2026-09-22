import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Classe principale gérant les opérations sur la base de données pour les articles du magasin.
 */
public class ShopArticle {
	public static ResultSet request(Connection conn, String sql){
		try {return conn.prepareStatement(sql).executeQuery();
			
		}catch (SQLException e) {
            System.err.println(e);
			return null;
        }
		
	}
    
    public static ArrayList<Article> requestDao(Connection conn, String sql){
		ResultSet result = request(conn, sql);
		if((result != null)) {
			try {
				ArrayList<Article> articles = new ArrayList<Article>();
				while(result.next())articles.add(new Article(result.getString(1), 
				result.getString(2), 
				result.getDouble(3)));
				return articles;
			}catch (SQLException e) {
				System.err.println(e);
				return new ArrayList<Article>();
			}
		}
		return new ArrayList<Article>();

	}

    public static void requestNoDao(Connection conn, String sql){
		ResultSet result = request(conn, sql);
		if((result != null)) {
			try {
				while(result.next())System.out.println(result.getString(1));
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
		
		
	}
	 /**
     * Exécute une requête SQL préparée avec trois paramètres (description, marque, prix unitaire).
     *
     * @param conn        la connexion active à la base de données
     * @param sql         la requête SQL paramétrée à exécuter
	 * @param priceNew    le nouveau prix de l'article
     * @param description la description de l'article
     * @param brand       la marque de l'article
     * @param priceOld    l'ancien prix unitaire de l'article
     */
    public static void request(Connection conn, String sql, Double priceNew,String description, String brand, Double priceOld) {
        try (PreparedStatement request = conn.prepareStatement(sql)) {
            request.setString(2, description);
            request.setString(3, brand);
            request.setDouble(4, priceOld);
			request.setDouble(1, priceNew);
            if (request.executeUpdate() == 1) {
                System.out.println("requête effectué");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Exécute une requête SQL préparée avec trois paramètres (description, marque, prix unitaire).
     *
     * @param conn        la connexion active à la base de données
     * @param sql         la requête SQL paramétrée à exécuter
     * @param description la description de l'article
     * @param brand       la marque de l'article
     * @param price       le prix unitaire de l'article
     */
    public static void request(Connection conn, String sql, String description, String brand, Double price) {
        try (PreparedStatement request = conn.prepareStatement(sql)) {
            request.setString(1, description);
            request.setString(2, brand);
            request.setDouble(3, price);
            if (request.executeUpdate() == 1) {
                System.out.println("requête effectué");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Lit le contenu du fichier SQL de création de structure.
     *
     * @return le contenu du fichier sous forme de chaîne de caractères, ou une chaîne vide en cas d'erreur
     */
    private static String file() {
        try {
            return new String(Files.readAllBytes(Paths.get("src/Shop_modif.sql")));
        } catch (IOException e) {
            System.err.println(e);
            return "";
        }
    }

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
}