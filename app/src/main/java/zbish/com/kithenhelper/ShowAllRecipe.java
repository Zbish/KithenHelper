package zbish.com.kithenhelper;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowAllRecipe extends Fragment {
    ListView l1;
    DataPassListener mCallback;
    ImageButton Addrecipe;
    public ShowAllRecipe() {
        // Required empty public constructor
    }
    public interface DataPassListener{
        public void passData(String data);
    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try
        {
            mCallback = (MainManu) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()+ " must implement DatapassListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View myview =inflater.inflate(R.layout.fragment_show_all_recipe, container, false);
        l1 = (ListView) myview.findViewById(R.id.listrecipe);
        AdapterRecipe emd2 = new AdapterRecipe(MainActivity.myContext,MainActivity.rpl.getMylist());
        l1.setAdapter(emd2);
        Log.d("a14",MainActivity.rpl.getMylist().toString());
        Addrecipe = (ImageButton)myview.findViewById(R.id.BTNADDrecipe);
        Addrecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentManager fm = getFragmentManager();
                android.app.FragmentTransaction ft = fm.beginTransaction();
                AddRecipe add = new AddRecipe();
                ft.replace(R.id.myFreg,add);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return myview;
    }

    @Override
    public void onStart() {
        super.onStart();
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mCallback != null) {
                    mCallback.passData(String.valueOf(i));
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
          l1.invalidate();

    }
}
