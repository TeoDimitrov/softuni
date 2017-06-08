package com.softuni.todolist.asyncTasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softuni.todolist.entities.ToDoItem;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteAsyncTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        String jsonString = null;
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            urlConnection.setRequestMethod("DELETE");
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            int i = urlConnection.getResponseCode();
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}
