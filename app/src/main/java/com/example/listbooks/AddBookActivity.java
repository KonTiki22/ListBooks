package com.example.listbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {

    public void addOnClick(View view)
    {
        String title = ((EditText) this.findViewById(R.id.title)).getText().toString();
        String author = ((EditText) this.findViewById(R.id.author)).getText().toString();
        Integer year = Integer.valueOf(((EditText) this.findViewById(R.id.year)).getText().toString());
        Intent intent = new Intent(this, MainActivity.class);
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("library.db", MODE_PRIVATE, null);
        db.execSQL(String.format("INSERT INTO books VALUES (null, '%s', '%s', %d);", title, author, year));
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);



       // Button button = (Button) findViewById(R.id.add_book);





    }
}