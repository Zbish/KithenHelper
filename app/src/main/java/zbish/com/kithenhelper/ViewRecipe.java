package zbish.com.kithenhelper;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewRecipe extends Fragment {
    TextView txtRecipeName,txtNumOfIngridient,txtAmount;
    ListView l4;
    Button change,delete;
    EditText changeInt;
    int posi;
    static String DATA_RECEIVE = "data_receive";
    public ViewRecipe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.fragment_view_recipe, container, false);

        txtRecipeName = (TextView)myview.findViewById(R.id.viewRecipe);
        txtNumOfIngridient = (TextView)myview.findViewById(R.id.TXTCOUNT);
        txtAmount = (TextView)myview.findViewById(R.id.TXTAMOUNT);
        l4 = (ListView) myview.findViewById(R.id.ListIngridient);
        change = (Button)myview.findViewById(R.id.BTNchange);
        delete = (Button)myview.findViewById(R.id.BTNDelete);
        changeInt = (EditText)myview.findViewById(R.id.TXTchange);
        return myview;
    }
    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            int pos = Integer.parseInt(args.getString(DATA_RECEIVE));
            posi = pos;
            Recipe r4 = MainActivity.rpl.getRecipebypos(posi);
            txtRecipeName.setText(r4.getName().toString());
            txtNumOfIngridient.setText(Integer.toString(r4.getIngridiant().size()));
            txtAmount.setText(Integer.toString(r4.getAmount()));
            final AdapterIngridient emd2 = new AdapterIngridient(MainActivity.myContext,r4.getIngridiant());
            l4.setAdapter(emd2);
            change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                if(changeInt.length() == 0)
                {
                    String errorMassege = getString(R.string.aountNewNUm);
                    Toast.makeText(MainActivity.myContext,errorMassege,
                            Toast.LENGTH_SHORT).show();
                }
                    else
                {
                    async mytask = new async();
                    mytask.execute();
                    emd2.notifyDataSetChanged();
                    InputMethodManager inputMgr = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMgr.hideSoftInputFromWindow(change.getWindowToken(), 0);
                    changeInt.setText("");
                }

                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DataBase md1 = new DataBase(MainActivity.myContext,"stam",null,1);
                    int rowid = MainActivity.rpl.getRecipebypos(posi).getRowID();
                    md1.deleteRow(rowid);
                    Log.d("a14","rowid" +  String.valueOf(rowid));
                    md1.close();
                    MainActivity.rpl.deleteFromList(posi);
                    getActivity().getFragmentManager().popBackStack();
                }
            });
        }
    }

        class async extends AsyncTask<Void,Void,Void>
        {
            Recipe r5 = MainActivity.rpl.getRecipebypos(posi);
            int newInt = Integer.valueOf(String.valueOf(changeInt.getText()));
            List<Ingredient> myingre = r5.getIngridiant();
            @Override
            protected Void doInBackground(Void... voids) {
                for(int i = 0 ; i <= myingre.size()-1 ; i++)
                {
                    try {
                        double o = myingre.get(i).getAmount();

                        double newValue = (o/r5.getAmount())*newInt;
                        myingre.get(i).setAmount(newValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                MainActivity.rpl.getRecipebypos(posi).setIngridiant(myingre);
                MainActivity.rpl.getRecipebypos(posi).setAmount(newInt);
                txtAmount.setText(String.valueOf(newInt));
                super.onPostExecute(aVoid);
            }
        }

}
