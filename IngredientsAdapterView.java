

package Views;

/**
 * Created by Worker2 on 21/05/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.teddysoft.worker2.applicationcookmaster.R;

public class IngredientsAdapterView extends BaseAdapter{

    private Context context;
    public IngredientsAdapterView(Context c)
    {
        context = c;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rav;
        if(convertView == null) {
            rav = new View(context);
            rav = inflater.inflate(R.layout.ingredientstab,null);
        }
        else {
            rav = (View)convertView;
        }
        return rav;
    }
}
