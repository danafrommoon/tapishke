package com.example.tapishke.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tapishke.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<NewsData> newsDataList = new ArrayList<>();
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        NewsDataPrepare();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void NewsDataPrepare(){
        String url = "https://newsapi.org/v2/everything?domains=wsj.com&apiKey=35feb8422d744c8b9024b70964894acc";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
               recyclerView = recyclerView.findViewById(R.id.recycler_view);
               imageView = imageView.findViewById(R.id.imageView);
               newsAdapter = new NewsAdapter(newsDataList);
               RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
               recyclerView.setLayoutManager(manager);
               recyclerView.setAdapter(newsAdapter);
               JSONArray jsonArray = null;
               try {
                   jsonArray = response.getJSONArray("articles");
                   NewsData newsData = new NewsData(jsonArray.getJSONObject(0).get("title").toString(), jsonArray.getJSONObject(0).get("description").toString(), jsonArray.getJSONObject(0).get("url").toString(),
                           jsonArray.getJSONObject(0).get("urlToImage").toString(), imageView);
                   for (int i = 1, size = jsonArray.length(); i < size; i++) {
                       JSONObject objectInArray = jsonArray.getJSONObject(i);
                       newsData = new NewsData(objectInArray.get("title").toString(), objectInArray.get("description").toString(),
                               objectInArray.get("url").toString(), objectInArray.get("urlToImage").toString(), imageView);
                       newsDataList.add(newsData);
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
           }
       }){
           @Override
           public Map<String, String> getHeaders() throws AuthFailureError {
               HashMap<String, String> headers = new HashMap<String, String>();
               headers.put("User-Agent","Mozilla/5.0");
               return headers;
           }
       };
        requestQueue.add(jsonObjectRequest);
    }




}