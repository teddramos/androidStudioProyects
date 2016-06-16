package Controls;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import DataPersistence.DBControlOperations;
import DataPersistence.QuerryArg;
import Models.Ingredient;

/**
 * Created by Worker2 on 01/06/2016.
 */
public class ControlIngredients {
    private DBControlOperations _control;
    private Ingredient _my_ingredient;

    public Ingredient get_my_ingredient() {
        return _my_ingredient;
    }

    public void set_my_ingredient(Ingredient _my_ingredient) {
        this._my_ingredient = _my_ingredient;
    }
    public ControlIngredients(Context context)
    {
        this._control = new DBControlOperations(context);
        this._control.openDB();
    }
    public void updateIngrediet() throws  Exception
    {
        QuerryArg[] list = {new QuerryArg("description", get_my_ingredient().getDescription()),
                new QuerryArg("metric", get_my_ingredient().get_metric()),
                new QuerryArg("quantity",get_my_ingredient().getQuantity()+""),
                new QuerryArg("id_recipe",get_my_ingredient().getId_Recipe()+""),
                new QuerryArg("preparation",get_my_ingredient().getPreparation())};
        try {

            _control.updateDataOnTable("Ingredient", list,new QuerryArg("id_ingredient",get_my_ingredient().getId_Ingredient()+""));
        }
        catch (Exception ex)
        {
            throw ex;
        }

    }
    public void inserIngredient()throws Exception
    {



        QuerryArg[] list = {new QuerryArg("description", get_my_ingredient().getDescription()),
                new QuerryArg("metric", get_my_ingredient().get_metric()),
                new QuerryArg("quantity",get_my_ingredient().getQuantity()+""),
                new QuerryArg("id_recipe",get_my_ingredient().getId_Recipe()+""),
                new QuerryArg("preparation",get_my_ingredient().getPreparation())};

        try {

            _control.insertDataOnTable("Ingredient", list);
        }
        catch (Exception ex)
        {
            throw ex;
        }

    }
    public List<Ingredient> getIngredientsByRecipe(int id_recipe)
    {
        List<Ingredient> list = new ArrayList<>();
        List<String[]> cur = _control.getDatafromTable("Ingredient",new QuerryArg("id_recipe",id_recipe+""));
        for(String[] row: cur)
        {
            list.add(new Ingredient(Integer.parseInt(row[0]),row[1],row[2],Double.parseDouble(row[3]),Integer.parseInt(row[4]),row[5]));
        }
        return list;
    }
    public void deleteIngredient()
    {
        _control.DeleteRowOnTable("Ingredients",new QuerryArg("id_ingredient",get_my_ingredient().getId_Ingredient()+""));
    }
}
