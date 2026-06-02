package zubaida.begum.adu.ac.ae.lostandfounduae;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class AdminDeleteFragment extends Fragment {

    private EditText adminPassword;
    private Button loginBtn;
    private LinearLayout pendingLayout;
    private DatabaseHelper dbHelper;
    private boolean loggedIn = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_delete, container, false);

        adminPassword = view.findViewById(R.id.admin_password);
        loginBtn = view.findViewById(R.id.admin_login_btn);
        pendingLayout = view.findViewById(R.id.pending_layout);

        dbHelper = new DatabaseHelper(getContext());

        ButtonHandler bh = new ButtonHandler();
        loginBtn.setOnClickListener(bh);

        return view;
    }

    public void updateView() {

        pendingLayout.removeAllViews();

        TextView headingTV = new TextView(getContext());
        headingTV.setText("Pending Items");
        headingTV.setTextSize(22);
        headingTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        headingTV.setPadding(0, 10, 0, 20);
        pendingLayout.addView(headingTV);

        ArrayList<Item> items = dbHelper.selectPendingItems();

        if (items.isEmpty()) {
            TextView emptyTV = new TextView(getContext());
            emptyTV.setText("No pending items");
            emptyTV.setTextSize(18);
            pendingLayout.addView(emptyTV);
            return;
        }

        RadioGroup group = new RadioGroup(getContext());

        for (Item item : items) {
            RadioButton rb = new RadioButton(getContext());
            rb.setId(item.getId());

            String text =  item.getItemName();
            text += "\n" + item.getDescription();
            text += "\nLocation: " + item.getLocation();
            text += "\nDate: " + item.getDate();
            text += "\nType: " + item.getType().toUpperCase();

            if (item.getImageLink() != null && item.getImageLink().length() > 0)
                text += "\nImage: " + item.getImageLink();

            rb.setText(text);
            rb.setTextSize(15);
            rb.setPadding(10, 15, 10, 15);

            group.addView(rb);
        }

        RadioButtonHandler rbh = new RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);

        pendingLayout.addView(group);
    }

    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String password = adminPassword.getText().toString();

            if (password.equals("1234")) {
                loggedIn = true;
                Toast.makeText(getContext(), "Admin login successful", Toast.LENGTH_LONG).show();
                updateView();
            } else {
                Toast.makeText(getContext(), "Wrong password", Toast.LENGTH_LONG).show();
            }
        }
    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (loggedIn) {
                dbHelper.deleteById(checkedId);
                Toast.makeText(getContext(), "Item deleted", Toast.LENGTH_LONG).show();
                updateView();
            }
        }
    }
}