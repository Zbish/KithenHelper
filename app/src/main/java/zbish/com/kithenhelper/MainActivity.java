package zbish.com.kithenhelper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static zbish.com.kithenhelper.UnitOfMeasure.Grams;
import static zbish.com.kithenhelper.UnitOfMeasure.Liter;
import static zbish.com.kithenhelper.UnitOfMeasure.Unit;


public class MainActivity extends AppCompatActivity {
    public static RecipeList rpl = new RecipeList();
    public static Context myContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myContext = this;
//Ingredient apple = new Ingredient("תפוח\u200e",10,Unit);
//Ingredient flower = new Ingredient("קמח\u200e",0.5,Grams);
//Ingredient oil = new Ingredient("שמן\u200e",1.5,Liter);
//Ingredient chiken = new Ingredient("עוף\u200e",10,Unit);
//Ingredient breadcrumb = new Ingredient("פירורי-לחם\u200e",0.5,Grams);
//Ingredient water = new Ingredient("מים\u200e",1.5,Liter);
//List<Ingredient> tt = new ArrayList<>();
//tt.add(apple);
//tt.add(flower);
//tt.add(oil);
//List<Ingredient> ee = new ArrayList<>();
//ee.add(chiken);
//ee.add(breadcrumb);
//ee.add(water);
//Recipe r1 = new Recipe("עוגה\u200e",tt,3,"לוקחים קמח, מערבבים, לשים קצת, מחממים איזה כיף יש עוגה\u200e");
//Recipe r2 = new Recipe("שניצל\u200e",ee,2,"חזה עוף בביצה ותבלינים לערבב קצת, לטגן והוף שניצל\u200e");
//DataBase md1 = new DataBase(getApplicationContext(),"stam",null,1);
//int id = md1.add_new_recipe(r1);
//int id2 = md1.add_new_recipe(r2);
//        r1.setRowID(id);
//        r2.setRowID(id2);
//        MainActivity.rpl.addRecipe(r1);
//        MainActivity.rpl.addRecipe(r2);
        asyncWebService tr = new asyncWebService();
        tr.execute();
    }
    class asyncWebService extends AsyncTask<Void,Void,Void>
    {
        List<Recipe> myrec;

        @Override
        protected Void doInBackground(Void... voids) {

            DataBase md1 = new DataBase(getApplicationContext(),"stam",null,1);
             myrec = md1.getallRecipe();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            rpl.setMylist(myrec);
            Intent in1 = new Intent(getApplicationContext(),MainManu.class);
            startActivity(in1);
        }
    }
}
