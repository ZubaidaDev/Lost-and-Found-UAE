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
import android.widget.TextView;
import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private EditText searchInput;
    private Button searchBtn;
    private LinearLayout resultsLayout;
    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchInput = view.findViewById(R.id.search_input);
        searchBtn = view.findViewById(R.id.search_btn);
        resultsLayout = view.findViewById(R.id.results_layout);

        dbHelper = new DatabaseHelper(getContext());

        updateView("");

        searchBtn.setOnClickListener(new ButtonHandler());

        return view;
    }

    public void updateView(String keyword) {
        resultsLayout.removeAllViews();

        ArrayList<Item> items = dbHelper.searchItems(keyword);

        for (Item item : items) {
            LinearLayout itemLayout = new LinearLayout(getContext());
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setPadding(25, 20, 25, 20);
            itemLayout.setBackgroundColor(0xFFFFFFFF);

            TextView nameTV = new TextView(getContext());
            nameTV.setText(item.getItemName());
            nameTV.setTextSize(18);
            nameTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            TextView typeTV = new TextView(getContext());
            typeTV.setText(item.getType().toUpperCase());
            typeTV.setTextSize(14);
            typeTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            if (item.getType().equals("lost"))
                typeTV.setTextColor(0xFFB00020);
            else
                typeTV.setTextColor(0xFF2E7D32);

            TextView descTV = new TextView(getContext());
            descTV.setText(item.getDescription());
            descTV.setTextSize(15);

            TextView locationTV = new TextView(getContext());
            locationTV.setText("Location: " + item.getLocation());
            locationTV.setTextSize(15);

            TextView dateTV = new TextView(getContext());
            dateTV.setText("Date: " + item.getDate());
            dateTV.setTextSize(15);

            itemLayout.addView(nameTV);
            itemLayout.addView(typeTV);
            itemLayout.addView(descTV);
            itemLayout.addView(locationTV);
            itemLayout.addView(dateTV);

            resultsLayout.addView(itemLayout);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String keyword = searchInput.getText().toString();
            updateView(keyword);
        }
    }
}
