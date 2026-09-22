import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestArticle {
        public static void main(String[] args) throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Shop?allowMultiQueries=true",
                "root", "")) {
            System.out.println("Connexion réussie !");
            ShopArticle.testRequest(conn);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
