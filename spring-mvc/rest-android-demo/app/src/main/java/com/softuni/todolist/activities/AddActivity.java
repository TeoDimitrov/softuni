package com.softuni.todolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.softuni.todolist.R;
import com.softuni.todolist.asyncTasks.PostAsyncTask;
import com.softuni.todolist.asyncTasks.PutAsyncTask;
import com.softuni.todolist.entities.ToDoItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private ToDoItem mToDoItem;

    private EditText mNameEditText;

    private EditText mDeadlineEditText;

    private Spinner mCategorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.mToDoItem = new ToDoItem();

        this.mNameEditText = (EditText) findViewById(R.id.name);
        this.mDeadlineEditText = (EditText) findViewById(R.id.deadline);
        this.mCategorySpinner = (Spinner) findViewById(R.id.category_spinner);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mToDoItem.setName(mNameEditText.getText().toString());
                Date deadline = null;
                try {
                    deadline = new SimpleDateFormat("yyyy/MM/dd").parse(mDeadlineEditText.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                mToDoItem.setDeadline(deadline);
                mToDoItem.setCategoryName(mCategorySpinner.getSelectedItem().toString());
                String postUrl = "http://192.168.14.127:8080/items";
                new PostAsyncTask(mToDoItem).execute(postUrl);
                Snackbar.make(view, "Added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
