package zubaida.begum.adu.ac.ae.lostandfounduae;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReportFoundFragment extends Fragment {

    private EditText inputName, inputDescription, inputLocation;
    private Button btnSubmit;
    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_found, container, false);

        inputName        = view.findViewById(R.id.input_item_name);
        inputDescription = view.findViewById(R.id.input_description);
        inputLocation    = view.findViewById(R.id.input_location);
        btnSubmit        = view.findViewById(R.id.btn_submit);

        dbHelper = new DatabaseHelper(getContext());

        btnSubmit.setOnClickListener(v -> submitReport());

        return view;
    }

    private void submitReport() {
        String name        = inputName.getText().toString().trim();
        String description = inputDescription.getText().toString().trim();
        String location    = inputLocation.getText().toString().trim();

        // Basic validation
        if (name.isEmpty() || description.isEmpty() || location.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get today's date
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        // Save to SQLite
        dbHelper.insertFoundItem(name, description, location, date);

        Toast.makeText(getContext(), "Found item reported successfully", Toast.LENGTH_SHORT).show();

        // Send data back to SearchFragment using Bundle
        SearchFragment searchFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("keyword", name); // auto-search for the item just reported
        searchFragment.setArguments(bundle);

        // Navigate to SearchFragment and refresh results
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, searchFragment)
                .addToBackStack(null)
                .commit();
    }
}
