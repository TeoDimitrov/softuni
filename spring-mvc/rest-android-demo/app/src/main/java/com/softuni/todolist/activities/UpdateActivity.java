package com.softuni.todolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.softuni.todolist.R;
import com.softuni.todolist.asyncTasks.PutAsyncTask;
import com.softuni.todolist.entities.ToDoItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity {

    private ToDoItem mToDoItem;

    private EditText mNameEditText;

    private EditText mDeadlineEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        this.mToDoItem = intent.getParcelableExtra("selectedItem");

        this.mNameEditText = (EditText) findViewById(R.id.name);
        this.mNameEditText.setText(mToDoItem.getName());

        this.mDeadlineEditText = (EditText) findViewById(R.id.deadline);
        String formattedDeadline = new SimpleDateFormat("yyyy/MM/dd").format(mToDoItem.getDeadline());
        this.mDeadlineEditText.setText(formattedDeadline);

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
                String putUrl = "http://192.168.14.127:8080/items/" + mToDoItem.getId();
                new PutAsyncTask(mToDoItem).execute(putUrl);
                Snackbar.make(view, "Updated", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
