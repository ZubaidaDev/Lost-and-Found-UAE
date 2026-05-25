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
        setContentView(R.layout.activity_main);

        setTitle("Lost and Found UAE");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new SearchFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_search) {
            setTitle("Lost and Found UAE");

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SearchFragment())
                    .commit();

        } else if (id == R.id.menu_report_lost) {
            setTitle("Report Lost Item");

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ReportLostFragment())
                    .commit();

        } else if (id == R.id.menu_report_found) {
            setTitle("Report Found Item");

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ReportFoundFragment())
                    .commit();

        } else if (id == R.id.menu_about) {
            setTitle("About");

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AboutFragment())
                    .commit();
        }

        return true;
    }
}