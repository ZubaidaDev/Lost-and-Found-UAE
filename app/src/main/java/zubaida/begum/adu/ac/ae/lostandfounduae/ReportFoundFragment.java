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

        if (!isValidDate(date)) {
            Toast.makeText(getContext(), "Date must be like 25/05/2026", Toast.LENGTH_LONG).show();
            return;
        }

        if (!isValidDate(date)) {
            Toast.makeText(getContext(), "Date must be like 25/05/2026", Toast.LENGTH_LONG).show();
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

    private boolean isValidDate(String date) {

        if (date.length() != 10)
            return false;

        if (date.charAt(2) != '/' || date.charAt(5) != '/')
            return false;

        for (int i = 0; i < date.length(); i++) {
            if (i != 2 && i != 5) {
                if (date.charAt(i) < '0' || date.charAt(i) > '9')
                    return false;
            }
        }

        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));

        if (year < 2022 || year > 2027)
            return false;

        if (month < 1 || month > 12)
            return false;

        if (day < 1)
            return false;

        if (month == 2) {
            if (day > 29)
                return false;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day > 30)
                return false;
        } else {
            if (day > 31)
                return false;
        }

        return true;
    }

    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            submitReport();
        }
    }
}