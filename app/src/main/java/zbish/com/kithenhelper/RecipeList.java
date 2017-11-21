package zbish.com.kithenhelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omri on 19/07/2017.
 */

public class RecipeList {

    private List<Recipe> mylist;

    public RecipeList() {
        this.mylist = new ArrayList<Recipe>();
    }

    public List<Recipe> getMylist() {
        return mylist;
    }

    public void setMylist(List<Recipe> mylist) {
        this.mylist = mylist;
    }
    public Recipe getRecipebypos(int pos)
    {
        return mylist.get(pos);
    }
    public void addRecipe(Recipe recipe)
    {
        mylist.add(recipe);
    }
    public void deleteFromList(int pos)
    {
        mylist.remove(pos);
    }
}
