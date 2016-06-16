package Views;

/**
 * Created by Worker2 on 06/06/2016.
 */
import Models.Ingredient;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teddysoft.worker2.applicationcookmaster.IngredientsActivity;
import com.teddysoft.worker2.applicationcookmaster.R;

import java.util.List;

public class ListIngredientAdapterView extends ArrayAdapter<Ingredient>{


    public ListIngredientAdapterView(Context context, int resource, List<Ingredient> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v == null)
        {
            LayoutInflater lif = LayoutInflater.from(getContext());
            v = lif.inflate(R.layout.list_ingredients_layout,null);
            final Ingredient  myIngredient= getItem(position);
            if(myIngredient != null)
            {
                TextView quantity = (TextView) v.findViewById(R.id.textviewQuantity);
                quantity.setText(myIngredient.getQuantity()+" ");

                TextView metric = (TextView) v.findViewById(R.id.textviewMetric);
                metric.setText(myIngredient.get_metric());

                TextView description = (TextView) v.findViewById(R.id.textviewIngredientDescription);
                description.setText(myIngredient.getDescription());

                description.setTag(myIngredient.getId_Ingredient());

                TextView texPreparation = (TextView) v.findViewById(R.id.textViewPreparation);
                texPreparation.setText(myIngredient.getPreparation());

                LinearLayout covert = (LinearLayout) v.findViewById(R.id.linearLayoutList);
                covert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IngredientsActivity.setIngredientforUpdate(myIngredient);
                    }
                });

            }
        }
        return v;

    }
}
