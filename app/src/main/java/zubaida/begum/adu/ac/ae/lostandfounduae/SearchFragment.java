package noor.jouhar.adu.ac.lostandfoundproject;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText searchInput;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchInput = view.findViewById(R.id.search_input);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dbHelper = new DatabaseHelper(getContext());

        // Check if a keyword was sent from ReportFoundFragment
        String incoming = "";
        if (getArguments() != null) {
            incoming = getArguments().getString("keyword", "");
            searchInput.setText(incoming);
        }

        // Load all items on start (or pre-fill with incoming keyword)
        refreshResults(incoming);

        // Search as user types
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                refreshResults(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        return view;
    }

    public void refreshResults(String keyword) {
        List<Item> results = dbHelper.searchItems(keyword);
        adapter = new ItemAdapter(results);
        recyclerView.setAdapter(adapter);
    }
}