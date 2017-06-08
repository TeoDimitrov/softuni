package com.softuni.todolist.asyncTasks;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.widget.Adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.softuni.todolist.adapters.ToDoAdapter;
import com.softuni.todolist.entities.ToDoItem;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class GetAsyncTask extends AsyncTask<String, Void, String> {

    private ToDoAdapter mAdapter;

    public GetAsyncTask(ToDoAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    protected String doInBackground(String... urls) {
        String jsonString = null;
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            jsonString = builder.toString();

            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    @Override
    protected void onPostExecute(String jsonData) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
        Type type = new TypeToken<List<ToDoItem>>() {}.getType();
        List<ToDoItem> items = gson.fromJson(jsonData, type);
        for (ToDoItem item : items) {
            this.mAdapter.addItem(item);
        }
    }
}
