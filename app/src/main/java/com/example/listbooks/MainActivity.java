package com.example.listbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
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
    ListView listView;


    public void addBook(View view)
    {
        Intent intent = new Intent(this, AddBookActivity.class);
        startActivity(intent);
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
            bookList.add(new Book(title, author, Integer.parseInt(year))); //add the item
            libSQL.moveToNext();
        }


        listView = findViewById(R.id.myList);
        LinkedList<HashMap<String,String>> library = new LinkedList<>();
        for (int i = 0; i < bookList.size(); i++) {
            HashMap<String, String> book = new HashMap<>();
            book.put("author", bookList.get(i).author);
            book.put("title", bookList.get(i).title);
            book.put("year", Integer.valueOf(bookList.get(i).year).toString());
            library.add(book);
        }
        String[] from = {"author", "title", "year"};
        int[] to = {R.id.author, R.id.title, R.id.year};

       // ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, bookList);

        SimpleAdapter adapter = new SimpleAdapter(this, library, R.layout.list_item, from, to);
        listView.setAdapter(adapter);

        //Book book = new Book("Мы", "Замятин", 1924);
        //ArrayList<Book> booksList = new ArrayList<>();

    }
}