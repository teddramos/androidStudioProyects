package Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.teddysoft.worker2.applicationcookmaster.IngredientsActivity;
import com.teddysoft.worker2.applicationcookmaster.R;
import com.teddysoft.worker2.applicationcookmaster.RecipeActivity;

import Controls.ControlIngredients;
import Models.Ingredient;

/**
 * Created by Worker2 on 06/06/2016.
 */
public class IngredientCreateView extends BaseAdapter{
    private Context context;
    private Ingredient myIngredient;
    ControlIngredients control;

    public IngredientCreateView(Context c,Ingredient ingredient)
    {

        context = c;
        this.myIngredient = ingredient;

    }
    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rav;
        if(convertView == null) {
            rav = new View(context);
            rav = inflater.inflate(R.layout.ingredient_create_layout,null);


            //spinner code
            final Spinner spinner = (Spinner)rav.findViewById(R.id.spinnerMetric);
            String[] list = new String[]{"TEASPOON","TEASPOONS","TABLESPOON","TABLESPOONS","CUP","CUPS","PIECE","PIECES","SLICE","SLICES"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,list);

            spinner.setAdapter(adapter);

            Button buttonCancel,buttonAdd;
            buttonAdd =(Button) rav.findViewById(R.id.buttonAddNewIngredient);
            final AutoCompleteTextView autoComplete = (AutoCompleteTextView)rav.findViewById(R.id.autoCompleteTextViewDescription);

            final EditText editQuantity = (EditText)rav.findViewById(R.id.editTextQuantity) ;
            final EditText editPreparation = (EditText)rav.findViewById(R.id.editTextPreparation);

            boolean update = false;
            //...
            if(myIngredient.getDescription()!=null)
            {
                autoComplete.setText(myIngredient.getDescription());
                spinner.setSelection(adapter.getPosition(myIngredient.get_metric()));
                editQuantity.setText(myIngredient.getQuantity()+"");
                editPreparation.setText(myIngredient.getPreparation());
                autoComplete.setTag(myIngredient.getId_Ingredient());

                update = true;

            }


            //cancel button

            buttonCancel = (Button)rav.findViewById(R.id.buttonCancelCreateIngredient);
            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IngredientsActivity.apearLayout(true);
                    IngredientsActivity.desapearIngredientForm();
                }
            });
            //buttonAdd


            if(update)
            {
                buttonAdd.setText("update");
                buttonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        control = new ControlIngredients(context);
                        Ingredient ingredient = new Ingredient(Integer.parseInt( autoComplete.getTag().toString()),
                                autoComplete.getText().toString(),
                                spinner.getSelectedItem().toString(),
                                Double.parseDouble(editQuantity.getText().toString()),
                                RecipeActivity.ActualRecipe.getId_recipe(),
                                editPreparation.getText().toString());
                        control.set_my_ingredient(ingredient);
                        try {
                        control.updateIngrediet();
                            IngredientsActivity.updateIngredientList(context);
                            IngredientsActivity.desapearIngredientForm();
                            IngredientsActivity.apearLayout(true);
                        } catch (Exception EX) {
                            String excep = EX.getMessage();
                        }

                    }
                });

            }else {

                buttonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            Ingredient ingredient = new Ingredient(0, autoComplete.getText().toString(),
                                    spinner.getSelectedItem().toString(),
                                    Double.parseDouble(editQuantity.getText().toString()),
                                    RecipeActivity.ActualRecipe.getId_recipe(),
                                    editPreparation.getText().toString());

                            control = new ControlIngredients(context);
                            control.set_my_ingredient(ingredient);
                            try {
                                control.inserIngredient();
                                IngredientsActivity.updateIngredientList(context);
                            } catch (Exception EX) {
                                String excep = EX.getMessage();
                            }
                        } catch (Exception ex) {
                            String execp = ex.getMessage();
                        }
                    }
                });
            }
        }
        else {
            rav = (View)convertView;
        }
        return rav;
    }

}
