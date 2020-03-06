package com.example.todolist1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText etItem;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etItem = findViewById(R.id.etItem);
        btnsave = findViewById(R.id.btnsave);

        getSupportActionBar().setTitle("Edit Item");
        getIntent().getStringExtra(MainActivity.KEY_ITEM_TEXT);

        //when the user is done editing, they click the save button
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the data (result of editing)
                Intent intent = new Intent();
                intent.putExtra(MainActivity.KEY_ITEM_TEXT, etItem.getText()).toString();
                intent.putExtra(MainActivity.KEY_ITEM_POSTION , getIntent().getExtras().getInt(MainActivity.KEY_ITEM_POSTION));
                // set the result of the intent
                setResult(RESULT_OK, intent);
                // Finish the activity
                finish();
            }
        });
    }
}
