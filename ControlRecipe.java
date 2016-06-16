package Controls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import DataPersistence.*;
import Models.Recipe;

/**
 * Created by Worker2 on 25/05/2016.
 */
public class ControlRecipe {
    private DBControlOperations _control;
    private Recipe _my_recipe;
    public static Recipe _recipeforPassing;

    public ControlRecipe(Context context,Recipe recipe)
    {
        this._control = new DBControlOperations(context);
        this._control.openDB();
        this.set_my_recipe(recipe);



    }
    public void insertRecipe()throws Exception
    {
        Bitmap bmp = null;

        try

        {
           if(! get_my_recipe().getImage().equals(null))
           {
               bmp = get_my_recipe().getImage();
           }
        }
        catch (Exception ex)
        {
           bmp = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_4444);

        }

       QuerryArg[] list = {new QuerryArg("description", get_my_recipe().getDescription()),
                             new QuerryArg("Id_author", get_my_recipe().getId_Author()+""),
                             new QuerryArg("image",this.convertBMPToString(bmp)),
                             new QuerryArg("recipe_date",get_my_recipe().getRecipe_date())};

        if(!searchForRecipeDescription()) {

            _control.insertDataOnTable("Recipe", list);
            updateRecipeId();
        }
        else throw new Exception("duplicate Descriptions are not allowed");
    }
    public void updateRecipe() throws Exception
    {

        Bitmap bmp = null;

        try

        {
            if(! get_my_recipe().getImage().equals(null))
            {
                bmp = get_my_recipe().getImage();
            }
        }
        catch (Exception ex)
        {
            bmp = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_4444);

        }
        QuerryArg[] list = {new QuerryArg("description", get_my_recipe().getDescription()),
                new QuerryArg("Id_author", get_my_recipe().getId_Author()+""),
                new QuerryArg("image",this.convertBMPToString(bmp))};
        if(!(searchForRecipeDescription() && searchForRecipeImage())) {

                _control.updateDataOnTable("Recipe", list, new QuerryArg("id_recipe", get_my_recipe().getId_recipe() + ""));


        }
        else
            throw new Exception("duplicate Descriptions are not allowed");

    }
    public Recipe getFromTable(Integer id_recipe)
    {
        QuerryArg arg = new QuerryArg("id_recipe",id_recipe.toString());

        List<String[]> cur = _control.getDatafromTable("Recipe",arg);


       for(String[] row: cur) {

           this.get_my_recipe().setId_recipe(Integer.parseInt(row[0]));
           this.get_my_recipe().setDescription(row[1]);
           this.get_my_recipe().setId_Author(Integer.parseInt(row[3]));
           byte[] im = row[2].getBytes();

           Bitmap bmp = BitmapFactory.decodeByteArray(im, 0, im.length);

           this.get_my_recipe().setImage(bmp);
       }

        return get_my_recipe();
    }

    public List<Recipe> getListOfallRecipes()
    {
        List<Recipe> list = new ArrayList<>();

        List<String[]> cur = _control.getDatafromTable("Recipe", null);


        for(String[] row: cur) {

            byte[] im = Base64.decode(row[2],Base64.DEFAULT);

            Bitmap bmp = BitmapFactory.decodeByteArray(im, 0, im.length);

            list.add(new Recipe(Integer.parseInt(row[0]),row[1],Integer.parseInt(row[3]),bmp,row[4]));
        }

        return list;
    }

   static public  String convertBMPToString(Bitmap image)
   {
       ByteArrayOutputStream stream = new ByteArrayOutputStream();
       image.compress(Bitmap.CompressFormat.JPEG,50,stream);
       byte[] bytes = stream.toByteArray();
       String chain = Base64.encodeToString(bytes,Base64.DEFAULT);

        return chain;
   }
    static public Bitmap convertStringToBitmap(String chain)
    {
        byte[] im = Base64.decode(chain,Base64.DEFAULT);

        Bitmap bmp = BitmapFactory.decodeByteArray(im, 0, im.length);
        return bmp;
    }

    public void deleteRecipe(Integer id_Recipe)
    {
        _control.DeleteRowOnTable("Recipe",new QuerryArg("id_recipe",id_Recipe.toString()));
    }

    public Recipe get_my_recipe() {
        return _my_recipe;
    }

    public void set_my_recipe(Recipe _my_recipe) {
        this._my_recipe = _my_recipe;
    }

    public boolean searchForRecipeDescription()
    {
        List<Recipe> list = new ArrayList<>();

        List<String[]> cur = _control.getDatafromTable("Recipe", new QuerryArg("description",this.get_my_recipe().getDescription()));
        if(cur.size()== 0)
        {
            return false;
        }
        else return true;
    }
    public boolean searchForRecipeImage()
    {
        List<Recipe> list = new ArrayList<>();

        List<String[]> cur = _control.getDatafromTable("Recipe", new QuerryArg("image",convertBMPToString(this.get_my_recipe().getImage())));
        if(cur.size()== 0)
        {
            return false;
        }
        else return true;
    }
    private void updateRecipeId()
    {


        List<String[]> cur = _control.getDatafromTable("Recipe",new QuerryArg("description",get_my_recipe().getDescription()));

        for(String[] row : cur)
        {
            get_my_recipe().setId_recipe(Integer.parseInt(row[0]));
            break;
        }


    }

}
