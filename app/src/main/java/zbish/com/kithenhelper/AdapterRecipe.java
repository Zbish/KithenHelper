package zbish.com.kithenhelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Omri on 18/07/2017.
 */

public class AdapterRecipe extends ArrayAdapter<Recipe> {
    public AdapterRecipe(Context context, List<Recipe> resource) {
        super(context,0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Recipe recipe1 = getItem(position);
        TextView name,amount,count,description;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapterrecipelayout,parent,false);
        }
        name = (TextView)convertView.findViewById(R.id.TXVrecipename);
        count = (TextView)convertView.findViewById(R.id.TXTcount);
        amount = (TextView)convertView.findViewById(R.id.TXTamount);
        description = (TextView)convertView.findViewById(R.id.TXTrecipedescripation);
        name.setText(recipe1.getName().toString());
        count.setText(Integer.toString(recipe1.getIngridiant().size()));
        amount.setText(Integer.toString(recipe1.getAmount()));
        description.setText(recipe1.getWayToMake().toString());
        Log.d("a12",recipe1.getWayToMake().toString());
        return convertView;
    }
}
