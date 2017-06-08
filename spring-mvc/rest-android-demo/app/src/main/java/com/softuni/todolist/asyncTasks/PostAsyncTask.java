package com.softuni.todolist.asyncTasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softuni.todolist.entities.ToDoItem;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostAsyncTask extends AsyncTask<String, Void, String> {

    private ToDoItem mToDoItem;

    public PostAsyncTask(ToDoItem toDoItem) {
        this.mToDoItem = toDoItem;
    }

    @Override
    protected String doInBackground(String... urls) {
        String jsonString = null;
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            urlConnection.setRequestMethod("POST");
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
            jsonString = gson.toJson(mToDoItem, ToDoItem.class);
            OutputStreamWriter out = new OutputStreamWriter(
                    urlConnection.getOutputStream());
            out.write(jsonString);
            out.flush();
            out.close();
            urlConnection.getResponseCode();
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}
