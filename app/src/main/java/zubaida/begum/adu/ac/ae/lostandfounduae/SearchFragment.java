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
import android.widget.Toast;
import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private EditText searchInput;
    private Button searchBtn, allBtn, lostBtn, foundBtn;
    private LinearLayout resultsLayout;
    private DatabaseHelper dbHelper;
    private String currentType = "all";
    private String lastKeyword = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchInput = view.findViewById(R.id.search_input);
        searchBtn = view.findViewById(R.id.search_btn);
        allBtn = view.findViewById(R.id.all_btn);
        lostBtn = view.findViewById(R.id.lost_btn);
        foundBtn = view.findViewById(R.id.found_btn);
        resultsLayout = view.findViewById(R.id.results_layout);

        dbHelper = new DatabaseHelper(getContext());

        SearchButtonHandler sbh = new SearchButtonHandler();
        searchBtn.setOnClickListener(sbh);

        FilterButtonHandler fbh = new FilterButtonHandler();
        allBtn.setOnClickListener(fbh);
        lostBtn.setOnClickListener(fbh);
        foundBtn.setOnClickListener(fbh);

        updateView("", "all");

        return view;
    }

    public void updateView(String keyword, String type) {

        resultsLayout.removeAllViews();

        lastKeyword = keyword;
        currentType = type;

        ArrayList<Item> items;

        if (type.equals("lost"))
            items = dbHelper.searchItemsByType(keyword, "lost");
        else if (type.equals("found"))
            items = dbHelper.searchItemsByType(keyword, "found");
        else
            items = dbHelper.searchItems(keyword);

        if (items.isEmpty()) {
            TextView emptyTV = new TextView(getContext());
            emptyTV.setText("No active items found");
            emptyTV.setTextSize(18);
            emptyTV.setPadding(20, 20, 20, 20);
            resultsLayout.addView(emptyTV);
        }

        for (Item item : items) {

            LinearLayout itemLayout = new LinearLayout(getContext());
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setPadding(25, 20, 25, 20);
            itemLayout.setBackgroundColor(0xFFFFFFFF);

            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            itemParams.setMargins(0, 0, 0, 20);

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

            TextView imageTV = new TextView(getContext());
            imageTV.setTextSize(15);

            if (item.getImageLink() != null && item.getImageLink().length() > 0)
                imageTV.setText("Image: " + item.getImageLink());
            else
                imageTV.setText("Image: Not provided");

            Button matchBtn = new Button(getContext());
            matchBtn.setId(item.getId());

            if (item.getType().equals("lost"))
                matchBtn.setText("I Found This");
            else
                matchBtn.setText("This Is Mine");

            MatchButtonHandler mbh = new MatchButtonHandler();
            matchBtn.setOnClickListener(mbh);

            itemLayout.addView(nameTV);
            itemLayout.addView(typeTV);
            itemLayout.addView(descTV);
            itemLayout.addView(locationTV);
            itemLayout.addView(dateTV);
            itemLayout.addView(imageTV);
            itemLayout.addView(matchBtn);

            resultsLayout.addView(itemLayout, itemParams);
        }
    }

    private class SearchButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String keyword = searchInput.getText().toString();
            updateView(keyword, currentType);
        }
    }

    private class FilterButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String keyword = searchInput.getText().toString();

            if (view == allBtn)
                updateView(keyword, "all");
            else if (view == lostBtn)
                updateView(keyword, "lost");
            else if (view == foundBtn)
                updateView(keyword, "found");
        }
    }

    private class MatchButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int itemId = view.getId();

            dbHelper.updateStatusById(itemId, "pending");

            Toast.makeText(getContext(),
                    "Request sent to admin for review",
                    Toast.LENGTH_LONG).show();

            updateView(lastKeyword, currentType);
        }
    }
}