package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TodoItem> items;
    private TodoAdapter itemsAdapter;
    private ListView listView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(v);
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new TodoAdapter(this, items);
        listView.setAdapter(itemsAdapter);

        deleteListViewListener();
    }

    private void deleteListViewListener() {
        listView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onSwipeRight(int position) {
                deleteItem(position);
            }

            @Override
            public void onSwipeLeft(int position) {
                deleteItem(position);
            }

            @Override
            public void onSwipeTop(int position) {

            }

            @Override
            public void onSwipeBottom(int position) {

            }

            @Override
            public void onClick() {

            }

            @Override
            public int getPosition() {
                return 0;
            }
        });
    }

    private void deleteItem(int position) {
        Context context = getApplicationContext();
        // Sending a toast message after an item is Deleted
        Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
        // Deleting item at a particular position
        items.remove(position);
        // making sure list is up to date
        itemsAdapter.notifyDataSetChanged();
    }

    private void addItem(View v) {
        EditText input = findViewById(R.id.editTextText);
        String itemText = input.getText().toString();

        if (!itemText.isEmpty()) {
            // Adding an item in itemsAdapter
            Toast.makeText(getApplicationContext(), "Added Todo", Toast.LENGTH_LONG).show();
            TodoItem newItem = new TodoItem(itemText, false);
            itemsAdapter.add(newItem);
            // Set the input to an empty string after an item has been added
            input.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "Todo cannot be empty!", Toast.LENGTH_LONG).show();
        }
    }
}
