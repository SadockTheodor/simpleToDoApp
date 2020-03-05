package com.example.todolist1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.io.FileUtils;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List <String> items;
    Button btnAdd;
    EditText txtText;
    RecyclerView listItem;
    ItemsAdapter itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.bntAdd);
        txtText = findViewById(R.id.txtText);
        listItem = findViewById(R.id.itemList);

       loadItems();

        ItemsAdapter.onLongClickListener onLongClickListener = new ItemsAdapter.onLongClickListener() {
            @Override
            public void onItemLongClicked(int position) {
                //Delete the item from the model
                items.remove(position);
                //Notify the adapter
                itemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(),"Item was removed successfully", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        };
        itemsAdapter = new  ItemsAdapter (items, onLongClickListener);
      listItem.setAdapter(itemsAdapter);
      listItem.setLayoutManager( new LinearLayoutManager(this));

      btnAdd.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String todoItem = txtText.getText().toString();
              //Add item to the model
              items.add(todoItem);

              // Notify the adapter that an item is inserted

              itemsAdapter.notifyItemInserted(items.size() - 1);
              txtText.setText("");

              Toast.makeText(getApplicationContext(),"Item was added successfully", Toast.LENGTH_SHORT).show();
              saveItems();
          }
      });

    }
    private File getDataFile(){
    return new File(getFilesDir(), "data.txt");

}
    // This function will load items by reading every lines of the data files
    private void  loadItems(){
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e("MainActivity","Error reading items", e);
            items = new ArrayList<>();
        }

    }
        private void saveItems(){
            try {
                FileUtils.writeLines(getDataFile(),items);
            } catch (IOException e) {
                Log.e("MainActivity","Error writing items", e);

            }
        }

    //This function saves item by writing into the data file

}