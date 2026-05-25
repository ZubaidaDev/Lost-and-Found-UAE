package zubaida.begum.adu.ac.ae.lostandfounduae;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ReportFoundFragment extends Fragment {

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

    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            submitReport();
        }
    }
}