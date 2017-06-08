package com.softuni.todolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.softuni.todolist.R;
import com.softuni.todolist.adapters.ToDoAdapter;
import com.softuni.todolist.asyncTasks.DeleteAsyncTask;
import com.softuni.todolist.asyncTasks.GetAsyncTask;
import com.softuni.todolist.entities.ToDoItem;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String GET_URL = "http://192.168.14.127:8080/items";

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;

    private ToDoAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.content_main);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);

        this.mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        this.mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        this.mLayoutManager = new LinearLayoutManager(this);
        this.mRecyclerView.setLayoutManager(mLayoutManager);

        this.mAdapter = new ToDoAdapter();
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                ToDoItem swipedToDoItem = mAdapter.getSelectedItem(position);
                String deleteUrl = "http://192.168.14.127:8080/items/delete/" + swipedToDoItem.getId();
                new DeleteAsyncTask().execute(deleteUrl);
                mAdapter.deleteById(position);
                Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(this.mRecyclerView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        this.mAdapter.clear();
        new GetAsyncTask(this.mAdapter).execute(GET_URL);
        this.mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.mAdapter.clear();
        new GetAsyncTask(this.mAdapter).execute(GET_URL);
    }
}
