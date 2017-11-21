package zbish.com.kithenhelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Omri on 30/07/2017.
 */

public class AdapterIngridient extends ArrayAdapter<Ingredient> {
public AdapterIngridient(Context context, List<Ingredient> resource) {
        super(context,0, resource);
        }

@NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Ingredient ingredient1 = getItem(position);
        TextView name,amount,masure;
        Button ChangeIngriAmount;
        if(convertView == null)
        {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapteringridientlayout,parent,false);
        }
        name = (TextView)convertView.findViewById(R.id.INGName);
        amount = (TextView)convertView.findViewById(R.id.INGamount);
        masure = (TextView)convertView.findViewById(R.id.INGMasure);
//        ChangeIngriAmount = (Button)convertView.findViewById(R.id.BTNChangeIngriAmount);
        name.setText(ingredient1.getName().toString());
        double i2= ingredient1.getAmount();
        String  myMasure = masureHebrew(ingredient1.getMyMeasure());
        masure.setText(myMasure);
        myNUm(amount,ingredient1.getMyMeasure(),i2);

        return convertView;
        }
        private String masureHebrew(UnitOfMeasure masure2)
        {
                String mystring="";
                if(masure2 == UnitOfMeasure.Grams)
                {
                        mystring=getContext().getString(R.string.gram);
                }
                else if(masure2 == UnitOfMeasure.Liter)
                {
                        mystring=getContext().getString(R.string.liter);
                }
                else if(masure2 == UnitOfMeasure.Unit)
                {
                        mystring=getContext().getString(R.string.unit);
                }

                return mystring;
        }
        private void myNUm(TextView tv,UnitOfMeasure masure, double i2)
        {
                if(masure == UnitOfMeasure.Grams || masure == UnitOfMeasure.Liter)
                {
                        tv.setText(String.format("%4.3f", i2));
                }
                else
                {
                        tv.setText(String.valueOf(i2));
                }
        }


}