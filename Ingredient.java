package Models;

/**
 * Created by Worker2 on 21/05/2016.
 */
public class Ingredient {
    private int id_Ingredient;
    private String description;
    private String metric;
    private double quantity;
    private int id_Recipe;
    private String preparation;

    public Ingredient(){}

    public Ingredient(int id_Ingredient,String description,String metric,double quantity,int id_Recipe,String preparation){
        this.setId_Ingredient(id_Ingredient);
        this.setDescription(description);
        this.set_metric(metric);
        this.setQuantity(quantity);
        this.setId_Recipe(id_Recipe);
        this.setPreparation(preparation);
    }

    public int getId_Ingredient() {
        return id_Ingredient;
    }

    public void setId_Ingredient(int id_Ingredient) {
        this.id_Ingredient = id_Ingredient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String get_metric() {
        return metric;
    }

    public void set_metric(String id_metric) {
        this.metric = id_metric;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getId_Recipe() {
        return id_Recipe;
    }

    public void setId_Recipe(int id_Recipe) {
        this.id_Recipe = id_Recipe;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }
}
