public class Article {
    private String description;
    private String brand;
    private Double price;

    public Article(String description, String brand, Double price) {
        this.description = description;
        this.brand = brand;
        this.price = price;
    }
    public String toString(){
        return "l'article " + description + " a comme marque " + brand + " et coûte " + price + " euros.";
    }
    
}
