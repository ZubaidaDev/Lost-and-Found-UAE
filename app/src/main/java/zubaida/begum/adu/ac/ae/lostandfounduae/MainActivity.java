package zubaida.begum.adu.ac.ae.lostandfounduae;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set main screen layout
        setContentView(R.layout.activity_main);
        setTitle("Lost and Found UAE");

        //show search screen first when app starts
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.myFragment, new SearchFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //add menu items to action bar
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //get selected menu item id first
        int id = item.getItemId();

        if (id == R.id.menu_search) {
            setTitle("Lost and Found UAE");

            // open search fg
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.myFragment, new SearchFragment())
                    .commit();

        } else if (id == R.id.menu_report_lost) {
            setTitle("Report Lost Item");

            //open lost item report fg
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.myFragment, new ReportLostFragment())
                    .commit();

        } else if (id == R.id.menu_report_found) {
            setTitle("Report Found Item");

            //open found item report fg
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.myFragment, new ReportFoundFragment())
                    .commit();

        } else if (id == R.id.menu_admin_delete) {
            setTitle("Admin Delete");

            //open admin delete fg
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.myFragment, new AdminDeleteFragment())
                    .commit();

        } else if (id == R.id.menu_about) {
            setTitle("About");

            // open about fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.myFragment, new AboutFragment())
                    .commit();
        }

        return true;
    }
}