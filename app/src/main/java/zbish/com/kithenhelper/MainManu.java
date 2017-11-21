package zbish.com.kithenhelper;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainManu extends AppCompatActivity implements ShowAllRecipe.DataPassListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manu);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        ShowAllRecipe show = new ShowAllRecipe();
        ft.replace(R.id.myFreg,show,"myshowfreg");
        ft.addToBackStack(null);
        ft.commit();

        }
    @Override
    public void passData(String data) {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        ViewRecipe show = new ViewRecipe();
        Bundle args = new Bundle();
        args.putString(ViewRecipe.DATA_RECEIVE, data);
        show.setArguments(args);
        ft.replace(R.id.myFreg,show);
        ft.addToBackStack(null);
        ft.commit();
    }
}
