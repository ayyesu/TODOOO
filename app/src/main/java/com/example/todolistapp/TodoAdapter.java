package com.example.todolistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import java.util.ArrayList;

public class TodoAdapter extends ArrayAdapter<TodoItem> {

    public TodoAdapter(Context context, ArrayList<TodoItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.textView);
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);

        textView.setText(item.getText());
        checkBox.setChecked(item.isChecked());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setChecked(checkBox.isChecked());
            }
        });

        return convertView;
    }
}
