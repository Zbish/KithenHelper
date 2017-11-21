package zbish.com.kithenhelper;

/**
 * Created by Omri on 19/07/2017.
 */

public class Ingredient {
    private String Name;
    private double amount;
    private UnitOfMeasure myMeasure;

    public Ingredient() {
    }

    public Ingredient(String name, double amount, UnitOfMeasure myMeasure) {
        Name = name;
        this.amount = amount;
        this.myMeasure = myMeasure;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UnitOfMeasure getMyMeasure() {
        return myMeasure;
    }

    public void setMyMeasure(UnitOfMeasure myMeasure) {
        this.myMeasure = myMeasure;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "Name='" + Name + '\'' +
                ", amount=" + amount +
                ", myMeasure=" + myMeasure +
                '}';
    }

}