package zbish.com.kithenhelper;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddRecipe extends Fragment {
    EditText recepeName, ingriName,ingriAmount,description,amountRecipe;
    Button addIngri,addRecipe;
    ListView mylist;
    RadioGroup masureSelect;
    AdapterIngridient emd2;
    UnitOfMeasure radiovalue = UnitOfMeasure.Unit;
    List<Ingredient> mylistof = new ArrayList<>();

    public AddRecipe() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View myview = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        recepeName = (EditText)myview.findViewById(R.id.TXTrecipeName);
        amountRecipe = (EditText)myview.findViewById(R.id.EDTAmountRecipe);
        ingriName = (EditText)myview.findViewById(R.id.TXTINgridiantName);
        description= (EditText)myview.findViewById(R.id.EDTDesciption);
        ingriAmount = (EditText)myview.findViewById(R.id.TXTINgridiantAmount);
        masureSelect = (RadioGroup)myview.findViewById(R.id.radioMasure);
            isChecked iC = new isChecked();
                masureSelect.setOnCheckedChangeListener(iC);
        addIngri = (Button)myview.findViewById(R.id.BTNAddIngridiant);
            addIngidient aI = new addIngidient();
                addIngri.setOnClickListener(aI);
        addRecipe = (Button)myview.findViewById(R.id.BTAaddrecipe1);
            addRecipe ar = new addRecipe();
                addRecipe.setOnClickListener(ar);
        mylist = (ListView)myview.findViewById(R.id.myListOfIngridient);
            emd2 = new AdapterIngridient(MainActivity.myContext,mylistof);
                mylist.setAdapter(emd2);
        return myview;
    }
    class isChecked implements RadioGroup.OnCheckedChangeListener
    {


        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch(i){
                case R.id.Gram:
                    radiovalue = UnitOfMeasure.Grams;
                    break;
                case R.id.Liter:
                    radiovalue = UnitOfMeasure.Liter;
                    break;
                case R.id.Unit:
                    radiovalue = UnitOfMeasure.Unit;
                    break;
            }
        }
    }
    class addIngidient implements View.OnClickListener
    {

        @Override
        public void onClick(View view) {
            String name,amount;
                name = ingriName.getText().toString();
                amount = ingriAmount.getText().toString();
            if (name.equals("")) {
                Toast.makeText(MainActivity.myContext," please wright a Name",
                        Toast.LENGTH_SHORT).show();
            }
                else if (amount.equals("")) {
                    Toast.makeText(MainActivity.myContext," please wright an Amount",
                            Toast.LENGTH_SHORT).show();
                }
                    else
                    {
                        ingriAmount.setText("");
                        ingriName.setText("");

                        Ingredient newIngri = new Ingredient();
                        newIngri.setAmount(Double.parseDouble(amount));
                        newIngri.setName(name);
                        newIngri.setMyMeasure(radiovalue);
                        mylistof.add(newIngri);
                        emd2.notifyDataSetChanged();

                        Toast.makeText(MainActivity.myContext,name + " add to recipe",
                                Toast.LENGTH_SHORT).show();
                        ingriName.requestFocus();
                    }

        }
    }
    class addRecipe implements View.OnClickListener
    {

        @Override
        public void onClick(View view) {
            String name,way,amount;
                name = recepeName.getText().toString();
                way = description.getText().toString();
                amount = amountRecipe.getText().toString();
            if(name.equals(""))
            {
                Toast.makeText(MainActivity.myContext," please wright a Recipe Name",
                        Toast.LENGTH_SHORT).show();
            }
                else if (way.equals(""))
                {
                    Toast.makeText(MainActivity.myContext," please wright way of make",
                            Toast.LENGTH_SHORT).show();
                }
                    else if (amount.equals("") || Integer.parseInt(amount) == 0)
                    {
                        Toast.makeText(MainActivity.myContext," please wright an Amount bigger then 0",
                                Toast.LENGTH_SHORT).show();
                    }
            else{
                Recipe r1 = new Recipe();
                r1.setIngridiant(mylistof);
                r1.setAmount(Integer.parseInt(amount));
                r1.setName(name);
                r1.setWayToMake(way);

                DataBase md1 = new DataBase(MainActivity.myContext,"stam",null,1);
                int id = md1.add_new_recipe(r1);
                r1.setRowID(id);
                MainActivity.rpl.addRecipe(r1);

                Toast.makeText(MainActivity.myContext,r1.getName().toString() + "  " + getString(R.string.addTheRecipe),
                        Toast.LENGTH_SHORT).show();
                getActivity().getFragmentManager().popBackStack();
            }

        }
    }

}
