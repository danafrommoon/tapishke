package com.example.tapishke.ui.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.tapishke.R;
import com.example.tapishke.RestaurantAdapter;
import com.example.tapishke.restaurant;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private ArrayList<restaurant> resList;

    private RecyclerView mRecyclerView;
    private RestaurantAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createResList();
        buildRecyclerView();

        EditText editText = (EditText) getView().findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

            private void filter(String text) {
                ArrayList<restaurant> filteredList = new ArrayList<>();

                for (restaurant item : resList) {
                    if (item.getText1().toLowerCase().contains(text.toLowerCase())) {
                        filteredList.add(item);
                    }
            }
                mAdapter.filterList(filteredList);
        }


    private void buildRecyclerView() {
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mAdapter = new RestaurantAdapter(resList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void createResList() {
        resList = new ArrayList<>();
        resList.add(new restaurant(R.drawable.ic_baseline_home_24, "Restaurant1", "Nur-Sultan"));
        resList.add(new restaurant(R.drawable.ic_baseline_home_24, "Restaurant1", "Nur-Sultan"));
        resList.add(new restaurant(R.drawable.ic_baseline_home_24, "Restaurant1", "Nur-Sultan"));
        resList.add(new restaurant(R.drawable.ic_baseline_home_24, "Restaurant1", "Nur-Sultan"));
        resList.add(new restaurant(R.drawable.ic_baseline_home_24, "Restaurant1", "Nur-Sultan"));
        resList.add(new restaurant(R.drawable.ic_baseline_home_24, "Restaurant1", "Nur-Sultan"));
        resList.add(new restaurant(R.drawable.ic_baseline_home_24, "Restaurant1", "Nur-Sultan"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}

