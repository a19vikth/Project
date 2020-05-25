package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SecondFragment extends Fragment implements adapter.OnItemClickListener {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "NAME";
    public static final String EXTRA_COMPANY = "company";
    public static final String EXTRA_LOCATION = "location";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_SIZE = "size";
    public static final String EXTRA_COST = "cost";

    private RecyclerView mRecyclerView;
    private adapter madapter;
    private ArrayList<planeter> planeterList;
    private View rootView;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        rootView = inflater.inflate(R.layout.fragment_second, container, false);
        mRecyclerView =  rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        planeterList = new ArrayList<>();
        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a19vikth");
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(getActivity(),DetailActivity.class);
        planeter clickedItem = planeterList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getimageurl());
        detailIntent.putExtra(EXTRA_NAME, clickedItem.getMnamn());
        detailIntent.putExtra(EXTRA_COMPANY, clickedItem.getMcompany());
        detailIntent.putExtra(EXTRA_LOCATION, clickedItem.getMlocation());
        detailIntent.putExtra(EXTRA_CATEGORY, clickedItem.getMcategory());
        detailIntent.putExtra(EXTRA_SIZE, clickedItem.getMsize());
        detailIntent.putExtra(EXTRA_COST, clickedItem.getMcost());

        startActivity(detailIntent);
    }

    @SuppressLint("StaticFieldLeak")
    private class JsonTask extends AsyncTask<String, String, String> {

        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        protected String doInBackground(String... params) {
            try {
                URL url = new URL("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a19vikth");
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    builder.append(line).append("\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            try {

                JSONArray json1 = new JSONArray(json);
                for (int i = 0; i < 8; i++) {
                    JSONObject JsonObject = json1.getJSONObject(i);


                    String aux = JsonObject.getString("auxdata");
                    String name = JsonObject.getString("name");
                    String company = JsonObject.getString("company");
                    int location = JsonObject.getInt("location");
                    String category = JsonObject.getString("category");
                    int size = JsonObject.getInt("size");
                    int cost = JsonObject.getInt("cost");

                    planeterList.add(new planeter(aux, name, company, location, category, size, cost));

                }
                madapter = new adapter(getActivity(),planeterList);
                mRecyclerView.setAdapter(madapter);
                madapter.setOnItemClickListener(SecondFragment.this);


            } catch (JSONException e) {
                Log.e("brom", "E:" + e.getMessage());
            }
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}
