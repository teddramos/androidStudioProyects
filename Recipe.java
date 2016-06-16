package Models;

import android.graphics.Bitmap;

import java.util.Date;


/**
 * Created by Worker2 on 14/05/2016.
 */
public class Recipe {
    private int id_recipe;
    private String description;
    private int id_Author;
    private Bitmap image;
    private String recipe_date;

    public Recipe(){}
    public Recipe(int id_recipe,String description,int id_Author,Bitmap image,String date)
    {
        this.id_recipe = id_recipe;
        this.description = description;
        this.id_Author = id_Author;
        this.image = image;
        this.recipe_date = date;

    }

    public int getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(int id_recipe) {
        this.id_recipe = id_recipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_Author() {
        return id_Author;
    }

    public void setId_Author(int id_Author) {
        this.id_Author = id_Author;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getRecipe_date() {
        return recipe_date;
    }

    public void setRecipe_date(String recipe_date) {
        this.recipe_date = recipe_date;
    }
}
