package com.softuni.todolist.entities;


import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;

import java.util.Date;

public class ToDoItem implements Parcelable {

    private long id;

    private String name;

    private Date deadline;

    private boolean isEnabled;

    private String categoryName;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return this.deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ToDoItem() {
    }

    protected ToDoItem(Parcel in) {
        id = in.readLong();
        name = in.readString();
        long tmpDeadline = in.readLong();
        deadline = tmpDeadline != -1 ? new Date(tmpDeadline) : null;
        isEnabled = in.readByte() != 0x00;
        categoryName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeLong(deadline != null ? deadline.getTime() : -1L);
        dest.writeByte((byte) (isEnabled ? 0x01 : 0x00));
        dest.writeString(categoryName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ToDoItem> CREATOR = new Parcelable.Creator<ToDoItem>() {
        @Override
        public ToDoItem createFromParcel(Parcel in) {
            return new ToDoItem(in);
        }

        @Override
        public ToDoItem[] newArray(int size) {
            return new ToDoItem[size];
        }
    };
}