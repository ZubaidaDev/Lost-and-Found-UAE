package zubaida.begum.adu.ac.ae.lostandfounduae;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class ReportFoundFragment extends Fragment {

    private FusedLocationProviderClient fusedLocationClient;

    private EditText inputName, inputDescription, inputLocation, inputDate;
    private Button btnSubmit;
    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_report_found, container, false);

        inputName = view.findViewById(R.id.input_item_name);
        inputDescription = view.findViewById(R.id.input_description);
        inputLocation = view.findViewById(R.id.input_location);
        inputDate = view.findViewById(R.id.input_date);
        btnSubmit = view.findViewById(R.id.btn_submit);

        dbHelper = new DatabaseHelper(getContext());

        // External code - GPS Android Developer (FusedLocationProviderClient)
        fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(getActivity());

        ButtonHandler bh = new ButtonHandler();
        btnSubmit.setOnClickListener(bh);

        return view;
    }

    private void submitReport() {
        String name = inputName.getText().toString();
        String description = inputDescription.getText().toString();
        String location = inputLocation.getText().toString();
        String date = inputDate.getText().toString();

        if (name.isEmpty() || description.isEmpty() || location.isEmpty() || date.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        Item item = new Item(0, name, description, location, date, "found");
        dbHelper.insertItem(item);

        Toast.makeText(getContext(), "Found item reported successfully", Toast.LENGTH_LONG).show();

        inputName.setText("");
        inputDescription.setText("");
        inputLocation.setText("");
        inputDate.setText("");
    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), location -> {

                    if (location != null) {

                        String latLng = location.getLatitude() + ", " + location.getLongitude();
                        inputLocation.setText(latLng);

                    } else {
                        Toast.makeText(getContext(),
                                "Location not available",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            getLocation();
            submitReport();
        }
    }
}
