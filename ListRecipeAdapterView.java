package Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teddysoft.worker2.applicationcookmaster.R;
import com.teddysoft.worker2.applicationcookmaster.RecipeActivity;

import java.util.List;

import Controls.ControlRecipe;
import Models.Recipe;

/**
 * Created by Worker2 on 20/05/2016.
 */
public class ListRecipeAdapterView extends ArrayAdapter<Recipe> {

   private Context context;

    public ListRecipeAdapterView(Context context, int resource, List<Recipe> objects) {
        super(context, resource, objects);

    }
@Override
public View getView(int position, View convertView, ViewGroup parent) {
    View v = convertView;
    if (v == null) {
        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        v = vi.inflate(R.layout.list_recipe_layout, null);
        final Recipe myrecipe = getItem(position);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.itemRecipelayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(),"se cliqueo",Toast.LENGTH_SHORT);
                ControlRecipe._recipeforPassing = myrecipe;
                RecipeActivity.searchForRecipe();
            }
        });
        if(myrecipe != null)
        {
            TextView text =(TextView) v.findViewById(R.id.textViewDescriptionRecipe);
            if(text !=null)
            {
                text.setText(myrecipe.getDescription());
                text.setTag(myrecipe.getId_recipe());

            }

        }
    }
    return v;

}
    void sendRecipeValue(Recipe recipe)
    {
        ControlRecipe._recipeforPassing = recipe;
    }

    public void testDB()
    {
      /*  DBControlOperations control = new DBControlOperations(context);
         control.openDB();
        Recipe rep = new Recipe();
        editText = (EditText)theView.findViewById(R.id.editTextNameRecipe);
        imageView = (ImageView)theView.findViewById(R.id.imageView1);

        rep.setDescription(editText.getText().toString());
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        rep.setImage(drawable.getBitmap());
        ControlRecipe con = new ControlRecipe(this.context,rep);

       con.insertRecipe();

        //Recipe dato = con.getFromTable("1");
      List<Recipe> todos = con.getListOfallRecipes();

        //  con.deleteRecipe(7);
     }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;*/
    }
}
