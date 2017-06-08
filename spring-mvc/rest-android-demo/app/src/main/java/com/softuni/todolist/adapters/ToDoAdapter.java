package com.softuni.todolist.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softuni.todolist.R;
import com.softuni.todolist.activities.UpdateActivity;
import com.softuni.todolist.entities.ToDoItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private List<ToDoItem> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mName, mDeadline, mCategory;

        public Context mContext;

        public ViewHolder(View view, Context context) {
            super(view);
            this.mName = (TextView) view.findViewById(R.id.name);
            this.mDeadline = (TextView) view.findViewById(R.id.deadline);
            this.mCategory = (TextView) view.findViewById(R.id.category);
            this.mContext = context;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ToDoItem selectedItem = getSelectedItem(position);
            Intent intent = new Intent(this.mContext, UpdateActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("selectedItem", (Parcelable) selectedItem);
            this.mContext.startActivity(intent);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ToDoAdapter() {
        mDataset = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ToDoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        Context currentContext = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.to_do_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(itemView, currentContext);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ToDoItem currentToDoItem = mDataset.get(position);
        holder.mName.setText(currentToDoItem.getName());
        String formattedDeadline = new SimpleDateFormat("EEE MMM yyyy").format(currentToDoItem.getDeadline());
        holder.mDeadline.setText(formattedDeadline);
        holder.mCategory.setText(currentToDoItem.getCategoryName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addItem(ToDoItem toDoItem){
        this.mDataset.add(toDoItem);
        notifyDataSetChanged();
    }

    public void clear(){
        this.mDataset.clear();
        notifyDataSetChanged();
    }

    public ToDoItem getSelectedItem(int position){
        return mDataset.get(position);
    }

    public void deleteById(int id){
        this.mDataset.remove(id);
        notifyDataSetChanged();
    }
}
