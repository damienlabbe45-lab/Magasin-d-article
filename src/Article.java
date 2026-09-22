public class Article {
    private String description;
    private String brand;
    private Double price;


    /**
     * Constructeur de la classe Article.
     *
     * @param description la description de l'article
     * @param brand       la marque de l'article
     * @param price       le prix unitaire de l'article
     */
    public Article(String description, String brand, Double price) {
        this.description = description;
        this.brand = brand;
        this.price = price;
    }

/**
     * Retourne une représentation textuelle de l'article.
     *
     * @return une chaîne décrivant l'article
     */
    @Override    
    public String toString(){
        return "l'article " + description + " a comme marque " + brand + " et coûte " + price + " euros.";
    }

    /**
     * Retourne le prix unitaire de l'article.
     *
     * @return le prix sous forme de Double
     */
    public Double toDouble(){
        return price;
    }
    
}
