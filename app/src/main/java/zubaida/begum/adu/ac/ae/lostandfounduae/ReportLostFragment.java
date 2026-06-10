package zubaida.begum.adu.ac.ae.lostandfounduae;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

public class ReportLostFragment extends Fragment {

    private EditText nameTxt, descriptionTxt, locationTxt, dateTxt;

    private Button btnSubmit;
    private Button btnPickImage;

    private ImageView imagePreview;

    private DatabaseHelper dbHelper;

    private String imageLink = "";

    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //used to get img selected from gallery
        resultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        new ImageResultHandler());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_report_lost, container, false);

        nameTxt = view.findViewById(R.id.nameTxt);
        descriptionTxt = view.findViewById(R.id.descriptionTxt);
        locationTxt = view.findViewById(R.id.locationTxt);
        dateTxt = view.findViewById(R.id.dateTxt);

        btnSubmit = view.findViewById(R.id.btn_submit);
        btnPickImage = view.findViewById(R.id.btnPickImage);

        imagePreview = view.findViewById(R.id.imagePreview);

        dbHelper = new DatabaseHelper(getContext());

        //btn to choose img
        PickImageHandler pih = new PickImageHandler();
        btnPickImage.setOnClickListener(pih);

        // button to save report
        ButtonHandler bh = new ButtonHandler();
        btnSubmit.setOnClickListener(bh);

        return view;
    }

    private void submitReport() {

        //get user input
        String name = nameTxt.getText().toString();
        String description = descriptionTxt.getText().toString();
        String location = locationTxt.getText().toString();
        String date = dateTxt.getText().toString();

        //check empty fields
        if (name.isEmpty() || description.isEmpty() || location.isEmpty() || date.isEmpty()) {

            Toast.makeText(getContext(),
                    "Please fill all required fields",
                    Toast.LENGTH_LONG).show();
            return;
        }

        // check date format
        if (!isValidDate(date)) {

            Toast.makeText(getContext(),
                    "Date must be like 25/05/2026",
                    Toast.LENGTH_LONG).show();
            return;
        }

        // create lost item and keep it active
        Item item = new Item(
                0,
                name,
                description,
                location,
                date,
                imageLink,
                "lost",
                "active"
        );

        // save item in db
        dbHelper.insertItem(item);

        Toast.makeText(getContext(),
                "Lost item reported successfully",
                Toast.LENGTH_LONG).show();

        // clear fields after submit
        nameTxt.setText("");
        descriptionTxt.setText("");
        locationTxt.setText("");
        dateTxt.setText("");

        imagePreview.setImageDrawable(null);
        imageLink = "";
    }

    private boolean isValidDate(String date) {

        // date should be like 25/05/2026
        if (date.length() != 10)
            return false;

        if (date.charAt(2) != '/' || date.charAt(5) != '/')
            return false;

        // check numbers only except /
        for (int i = 0; i < date.length(); i++) {
            if (i != 2 && i != 5) {
                if (date.charAt(i) < '0' || date.charAt(i) > '9')
                    return false;
            }
        }

        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));

        if (year < 2025)
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

    //inner classes
    private class PickImageHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            // open gallery
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");

            resultLauncher.launch(intent);
        }
    }

    private class ImageResultHandler implements ActivityResultCallback<ActivityResult> {

        @Override
        public void onActivityResult(ActivityResult result) {

            // get selected image
            if (result.getData() != null) {

                Uri imageUri = result.getData().getData();

                imageLink = imageUri.toString();

                // show selected image in screen
                if (imagePreview != null) {
                    imagePreview.setImageURI(imageUri);
                }
            }
        }
    }

    private class ButtonHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            submitReport();
        }
    }
}