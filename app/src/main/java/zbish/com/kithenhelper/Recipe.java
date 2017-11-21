package zbish.com.kithenhelper;

import java.util.List;

/**
 * Created by Omri on 18/07/2017.
 */

public class Recipe {
    private int rowID;
    private String Name;
    private List<Ingredient> Ingridiant;
    private int Amount;
    private String wayToMake;

    public Recipe()
    {

    }
    public Recipe(String name, List<Ingredient> ingridiant, int amount, String wayToMake) {
        Name = name;
        Ingridiant = ingridiant;
        Amount = amount;
        this.wayToMake = wayToMake;
    }
    public Recipe(int rowID, String name, List<Ingredient> ingridiant, int amount, String wayToMake) {
        this.rowID = rowID;
        Name = name;
        Ingridiant = ingridiant;
        Amount = amount;
        this.wayToMake = wayToMake;
    }

    public int getRowID() {
        return rowID;
    }

    public void setRowID(int rowID) {
        this.rowID = rowID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Ingredient> getIngridiant() {
        return Ingridiant;
    }

    public void setIngridiant(List<Ingredient> ingridiant) {
        Ingridiant = ingridiant;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getWayToMake() {
        return wayToMake;
    }

    public void setWayToMake(String wayToMake) {
        this.wayToMake = wayToMake;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "rowID=" + rowID +
                ", Name='" + Name + '\'' +
                ", Ingridiant=" + Ingridiant +
                ", Amount=" + Amount +
                ", wayToMake='" + wayToMake + '\'' +
                '}';
    }
}
