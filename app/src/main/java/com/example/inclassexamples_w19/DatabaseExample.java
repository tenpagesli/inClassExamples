package com.example.inclassexamples_w19;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseExample extends AppCompatActivity {

    ArrayList<Contact> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_layout);

        //Get the fields from the screen:
        EditText nameEdit = (EditText)findViewById(R.id.nameInput);
        EditText emailEdit = (EditText) findViewById(R.id.emailInput);
        Button insertButton = (Button)findViewById(R.id.insert);
        ListView theList = (ListView)findViewById(R.id.the_list);


        //get a database:
        MyDatabaseOpenHelper dbOpener = new MyDatabaseOpenHelper(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();

        String [] columns = {MyDatabaseOpenHelper.COL_ID, MyDatabaseOpenHelper.COL_EMAIL, MyDatabaseOpenHelper.COL_NAME};

        Cursor results = db.query(false, MyDatabaseOpenHelper.TABLE_NAME, columns, null, null, null, null, null, null);
        int numResultsFound = results.getCount();

        int emailColumnIndex = results.getColumnIndex(MyDatabaseOpenHelper.COL_EMAIL);
        int nameColIndex = results.getColumnIndex(MyDatabaseOpenHelper.COL_NAME);
        int idColIndex = results.getColumnIndex(MyDatabaseOpenHelper.COL_ID);

        //iterate over the results:
        while(!results.isAfterLast())
        {
            String name = results.getString(nameColIndex);
            String email = results.getString(emailColumnIndex);
            long id = results.getLong(idColIndex);
            contactsList.add(new Contact(name, email, id));
        }

        ListAdapter adt = new MyOwnAdapter();
        theList.setAdapter(adt);


        
        //This listens for items being clicked in the list view
        theList.setOnItemClickListener(( parent,  view,  position,  id) -> {
            Log.e("you clicked on :" , "item "+ position);


        ((MyOwnAdapter) adt).notifyDataSetChanged();
        });
    }

    //This class needs 4 functions to work properly:
    protected class MyOwnAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return contactsList.size();
        }

        public Contact getItem(int position){
            return contactsList.get(position);
        }

        public View getView(int position, View old, ViewGroup parent)
        {
            LayoutInflater inflater = getLayoutInflater();

            View newView = inflater.inflate(R.layout.contact_row, parent, false );


            Contact thisRow = getItem(position);
            TextView rowName = (TextView)newView.findViewById(R.id.row_name);
            TextView rowEmail = (TextView)newView.findViewById(R.id.row_email);

            TextView rowId = (TextView)newView.findViewById(R.id.row_id);

            rowName.setText("Name:" + thisRow.getName());
            rowEmail.setText("Email:" + thisRow.getEmail());
            rowId.setText("id:" + thisRow.getId());
            //return the row:
            return newView;
        }

        public long getItemId(int position)
        {
            return position;
        }
    }

}
