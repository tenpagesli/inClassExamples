package com.example.inclassexamples_w19;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class FragmentExample extends AppCompatActivity {

    public static final String ITEM_SELECTED = "ITEM";
    public static final String ITEM_POSITION = "POSITION";
    public static final String ITEM_ID = "ID";
    public static final int EMPTY_ACTIVITY = 345;

    ArrayAdapter<String> theAdapter;

    ArrayList<String> source = new ArrayList<>( Arrays.asList( "One", "Two", "Three", "Four" ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_example);

        ListView theList = (ListView)findViewById(R.id.theList);
        boolean isTablet = findViewById(R.id.fragmentLocation) != null;

        theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, source);
        theList.setAdapter( theAdapter );
        theList.setOnItemClickListener( (list, item, position, id) -> {

            Bundle dataToPass = new Bundle();
            dataToPass.putString(ITEM_SELECTED, source.get(position) );
            dataToPass.putInt(ITEM_POSITION, position);
            dataToPass.putLong(ITEM_ID, id);

            if(isTablet)
            {
                DetailFragment dFragment = new DetailFragment();
                dFragment.setArguments( dataToPass );
                dFragment.setTablet(true);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragmentLocation, dFragment)
                        .addToBackStack("AnyName")
                        .commit();
            }
            else //isPhone
            {
                Intent nextActivity = new Intent(FragmentExample.this, EmptyActivity.class);
                nextActivity.putExtras(dataToPass);
                startActivityForResult(nextActivity, EMPTY_ACTIVITY);
            }
        });
    }

    //This function only gets called on the phone. The tablet never goes to a new activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == EMPTY_ACTIVITY)
        {
            if(resultCode == RESULT_OK)
            {
                long id = data.getLongExtra(ITEM_ID, 0);
                deleteMessageId((int)id);
            }
        }
    }


    public void deleteMessageId(int id)
    {
        Log.i("Delete this message:" , " id="+id);
        source.remove(id);
        theAdapter.notifyDataSetChanged();
    }
}
