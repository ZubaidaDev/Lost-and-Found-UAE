package zubaida.begum.adu.ac.ae.lostandfounduae;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private EditText searchTxt;
    private Button searchBtn, allBtn, lostBtn, foundBtn;
    private LinearLayout resultsLayout;
    private DatabaseHelper dbHelper;

    private String currentType = "all";
    private String lastKeyword = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // connect fg with xml layout
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchTxt = view.findViewById(R.id.searchTxt);
        searchBtn = view.findViewById(R.id.searchBtn);
        allBtn = view.findViewById(R.id.allBtn);
        lostBtn = view.findViewById(R.id.lostBtn);
        foundBtn = view.findViewById(R.id.foundBtn);
        resultsLayout = view.findViewById(R.id.resultsLayout);

        dbHelper = new DatabaseHelper(getContext());

        // search btn handler
        SearchBtnHandler sbh = new SearchBtnHandler();
        searchBtn.setOnClickListener(sbh);

        // filter btns handler
        FilterBtnHandler fbh = new FilterBtnHandler();
        allBtn.setOnClickListener(fbh);
        lostBtn.setOnClickListener(fbh);
        foundBtn.setOnClickListener(fbh);

        // show all active items when screen opens
        updateView("", "all");

        return view;
    }

    public void updateView(String keyword, String type) {

        //clear old search results
        resultsLayout.removeAllViews();

        lastKeyword = keyword;
        currentType = type;

        ArrayList<Item> items;

        // choose which search method to use
        if (type.equals("lost"))
            items = dbHelper.searchItemsByType(keyword, "lost");
        else if (type.equals("found"))
            items = dbHelper.searchItemsByType(keyword, "found");
        else
            items = dbHelper.searchItems(keyword);

        // show message if no items found
        if (items.isEmpty()) {
            TextView emptyTV = new TextView(getContext());
            emptyTV.setText("No active items found");
            emptyTV.setTextSize(18);
            emptyTV.setPadding(20, 20, 20, 20);
            resultsLayout.addView(emptyTV);
            return;
        }

        // show each item in result list
        for (Item item : items) {

            LinearLayout itemLayout = new LinearLayout(getContext());
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setPadding(25, 20, 25, 20);
            itemLayout.setBackgroundColor(0xFFFFFFFF);

            LinearLayout.LayoutParams itemParams =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            itemParams.setMargins(0, 0, 0, 20);

            // item name
            TextView nameTV = new TextView(getContext());
            nameTV.setText(item.getItemName());
            nameTV.setTextSize(18);
            nameTV.setTypeface(Typeface.DEFAULT_BOLD);

            // lost or found type
            TextView typeTV = new TextView(getContext());
            typeTV.setText(item.getType().toUpperCase());
            typeTV.setTextSize(14);
            typeTV.setTypeface(Typeface.DEFAULT_BOLD);

            if (item.getType().equals("lost"))
                typeTV.setTextColor(0xFFB00020);
            else
                typeTV.setTextColor(0xFF2E7D32);

            TextView descTV = new TextView(getContext());
            descTV.setText(item.getDescription());

            TextView locationTV = new TextView(getContext());
            locationTV.setText("Location: " + item.getLocation());

            TextView dateTV = new TextView(getContext());
            dateTV.setText("Date: " + item.getDate());

            // show selected image
            ImageView imageView = new ImageView(getContext());

            LinearLayout.LayoutParams imageParams =
                    new LinearLayout.LayoutParams(400, 400);

            imageParams.setMargins(0, 15, 0, 15);

            imageView.setLayoutParams(imageParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            if (item.getImageLink() != null && !item.getImageLink().isEmpty()) {
                imageView.setImageURI(Uri.parse(item.getImageLink()));
            }

            // btn for match request
            Button matchBtn = new Button(getContext());
            matchBtn.setId(item.getId());

            if (item.getType().equals("lost"))
                matchBtn.setText("I Found This");
            else
                matchBtn.setText("This Is Mine");

            MatchBtnHandler mbh = new MatchBtnHandler();
            matchBtn.setOnClickListener(mbh);

            // add all views into one item layout
            itemLayout.addView(nameTV);
            itemLayout.addView(typeTV);
            itemLayout.addView(descTV);
            itemLayout.addView(locationTV);
            itemLayout.addView(dateTV);
            itemLayout.addView(imageView);
            itemLayout.addView(matchBtn);

            resultsLayout.addView(itemLayout, itemParams);
        }
    }

    private class SearchBtnHandler implements View.OnClickListener { //inner class for search btn
        @Override
        public void onClick(View view) {

            // get keyword and search with current filter
            String keyword = searchTxt.getText().toString();
            updateView(keyword, currentType);
        }
    }

    private class FilterBtnHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            //get keyword and apply selected filter
            String keyword = searchTxt.getText().toString();

            if (view == allBtn) {
                updateView(keyword, "all");
            } else if (view == lostBtn) {
                updateView(keyword, "lost");
            } else if (view == foundBtn) {
                updateView(keyword, "found");
            }
        }
    }

    private class MatchBtnHandler implements View.OnClickListener { //inner class for match btn
        @Override
        public void onClick(View view) {

            // get item id from btn
            int itemId = view.getId();

            // move item to pending list for admin
            dbHelper.updateStatusById(itemId, "pending");

            Toast.makeText(getContext(),
                    "Request sent to admin for review",
                    Toast.LENGTH_LONG).show();

            // refresh result list
            updateView(lastKeyword, currentType);
        }
    }
}