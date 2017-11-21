package zbish.com.kithenhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omri on 19/07/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table if not exists recipe(IDRECIPE INTEGER PRIMARY KEY, json text)";
        db.execSQL(query);
    }
    public int add_new_recipe (Recipe newRecipe)
    {

        SQLiteDatabase db;
        db = getWritableDatabase();

        String jsonRecipe = convertToJson(newRecipe);
        ContentValues cn = new ContentValues();
        cn.put("json",jsonRecipe);
        db.insert("recipe", null, cn);

        final String MY_QUERY2 = "SELECT * \n" +
                "    FROM    recipe\n" +
                "    WHERE   IDRECIPE = (SELECT MAX(IDRECIPE) FROM recipe);";
        Cursor cur2 = db.rawQuery(MY_QUERY2, null);
        cur2.moveToFirst();
        int ID2 = cur2.getInt(0);
        cur2.close();
        Log.d("a14", "second" + String.valueOf(ID2));
        return ID2;
//        newRecipe.setRowID(ID2);
//        String jsonRecipe2 = convertToJson(newRecipe);
//        ContentValues cn2 = new ContentValues();
//        cn.put("json",jsonRecipe2);
//        db.insert("recipe", null, cn);

    }
    public void deleteRow(int value)
    {
        SQLiteDatabase db;
        db = getWritableDatabase();
        String[] selectionArgs = {String.valueOf(value)};
        String selection = "IDRECIPE" + " LIKE ?";
        db.delete("recipe",selection,selectionArgs);
//        Log.d("a14", "delete delte");
    }
    public List<Recipe> getallRecipe()
    {
        List<Recipe> res = new ArrayList<Recipe>();
        String query = "SELECT * FROM recipe";
        SQLiteDatabase db;
        db = getWritableDatabase();
        Cursor cr1 = db.rawQuery(query,null);
        if (cr1.moveToFirst())
        {
            do{
                int idTag = cr1.getInt(0);
                String json = cr1.getString(1);
                Recipe re = converToRecipe(json);
               re.setRowID(idTag);
                res.add(re);
            }while(cr1.moveToNext());

        }

        return res;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    private String convertToJson(Recipe rec)
    {
        Gson gson = new Gson();
// 2. Java object to JSON, and assign to a String
        String jsonInString = gson.toJson(rec);
        return jsonInString;
    }
    private Recipe converToRecipe(String json)
    {
        Gson gson = new Gson();
// 2. JSON to Java object, read it from a Json String.
//        Recipe recipe = gson.fromJson(json, Recipe.class);
//        Recipe recipe = gson.fromJson(json, new TypeToken<List<Recipe>>(){}.getType());
        Recipe wrapper = gson.fromJson(json, Recipe.class);
        return wrapper;
    }
}
