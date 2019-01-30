package com.example.inclassexamples_w19;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class ListViewExample extends AppCompatActivity {

    int numObjects = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //ListAdapter adt = new MyArrayAdapter(new String[] {"A", "B", "C"});
        ListAdapter adt = new MyOwnAdapter();


        ListView theList = (ListView)findViewById(R.id.the_list);
        SwipeRefreshLayout refresher = (SwipeRefreshLayout)findViewById(R.id.refresher) ;
        refresher.setOnRefreshListener(()-> {
            numObjects *= 2;
            ((MyOwnAdapter) adt).notifyDataSetChanged();
            refresher.setRefreshing( false );
        });


        theList.setAdapter(adt);


        
        //This listens for items being clicked in the list view
        theList.setOnItemClickListener(( parent,  view,  position,  id) -> {
            Log.e("you clicked on :" , "item "+ position);

        numObjects = 20;
        ((MyOwnAdapter) adt).notifyDataSetChanged();
        });
    }

    //This class needs 4 functions to work properly:
    protected class MyOwnAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return numObjects;
        }

        public Object getItem(int position){
            return "SHow this in row "+ position;
        }

        public View getView(int position, View old, ViewGroup parent)
        {
            LayoutInflater inflater = getLayoutInflater();

            View newView = inflater.inflate(R.layout.single_row, parent, false );


            TextView rowText = (TextView)newView.findViewById(R.id.textOnRow);
            String stringToShow = getItem(position).toString();
            rowText.setText( stringToShow );
            //return the row:
            return newView;
        }

        public long getItemId(int position)
        {
            return position;
        }
    }

    //A copy of ArrayAdapter. You just give it an array and it will do the rest of the work.
    protected class MyArrayAdapter<E> extends BaseAdapter
    {
        private List<E> dataCopy = null;

        //Keep a reference to the data:
        public MyArrayAdapter(List<E> originalData)
        {
            dataCopy = originalData;
        }

        //You can give it an array
        public MyArrayAdapter(E [] array)
        {
            dataCopy = Arrays.asList(array);
        }


        //Tells the list how many elements to display:
        public int getCount()
        {
            return dataCopy.size();
        }


        public E getItem(int position){
            return dataCopy.get(position);
        }

        public View getView(int position, View old, ViewGroup parent)
        {
            //get an object to load a layout:
            LayoutInflater inflater = getLayoutInflater();

            //Recycle views if possible:
            TextView root = (TextView)old;
            //If there are no spare layouts, load a new one:
            if(old == null)
                root = (TextView)inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            //Get the string to go in row: position
            String toDisplay = getItem(position).toString();

            //Set the text of the text view
            root.setText(toDisplay);

            //Return the text view:
            return root;
        }


        //Return 0 for now. We will change this when using databases
        public long getItemId(int position)
        {
            return 0;
        }
    }
}
