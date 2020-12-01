package com.example.listbooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;


    public void addBook(View view)
    {
        Intent intent = new Intent(this, AddBookActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //menu.findItem(R.id.about).setIntent(new Intent(MainActivity.this, AboutActivity.class));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;
            case R.id.exit:
                finishAffinity();
                System.exit(0);
                break;
            default: break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Book> bookList = new ArrayList<>();

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("library.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS books (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, author TEXT, year INTEGER)");

        Cursor libSQL = db.rawQuery("Select * from books",null);
        if(libSQL.getCount() == 0) {
            db.execSQL("INSERT INTO books VALUES (null, \"Моби Дик\", \"Герман Мелвилл\", 2005);");
            db.execSQL("INSERT INTO books VALUES (null, \"О Дивный Новый Мир\", \"Олдос Хаксли\", 2016);");
            db.execSQL("INSERT INTO books VALUES (null, \"451 градус по Фаренгейту\", \"Рэй Бредбери\", 2008);");
            db.execSQL("INSERT INTO books VALUES (null, \"Конец детства\", \"Артур Кларк\", 2000);");
            db.execSQL("INSERT INTO books VALUES (null, \"Мы\", \"Евгений Замятин\", 1984);");
        }
        libSQL = db.rawQuery("Select * from books",null);
        libSQL.moveToFirst();
        while(!libSQL.isAfterLast()) {
            String title = libSQL.getString(libSQL.getColumnIndex("title"));
            String author = libSQL.getString(libSQL.getColumnIndex("author"));
            String year = libSQL.getString(libSQL.getColumnIndex("year"));
            bookList.add(new Book(title, author, Integer.parseInt(year)));
            libSQL.moveToNext();
        }


        recyclerView = findViewById(R.id.recList);
        MyAdapter adapter = new MyAdapter(this, bookList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);


    }
}