package com.example.inclassexamples_w19;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuExample extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_example);
        Toolbar tBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tBar);

        /*Snackbar code:
        Snackbar sb = Snackbar.make(tBar, "Hello world", Snackbar.LENGTH_LONG)
        .setAction("Action text", e -> Log.e("Menu Example", "Clicked Undo"));
        sb.show();*/


        Button alertDialogButton = (Button)findViewById(R.id.insert);
        alertDialogButton.setOnClickListener( clik ->   alertExample()  );

        //Show the toast immediately:
        Toast.makeText(this, "Welcome to Menu Example", Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.example_menu, menu);


	    /* slide 15 material:
	    MenuItem searchItem = menu.findItem(R.id.search_item);
        SearchView sView = (SearchView)searchItem.getActionView();
        sView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }  });

	    */

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            //what to do when the menu item is selected:
            case R.id.item1:

                break;
        }
        return true;
    }

    public void alertExample()
    {
        View middle = getLayoutInflater().inflate(R.layout.view_extra_stuff, null);
        Button btn = (Button)middle.findViewById(R.id.view_button);
        EditText et = (EditText)middle.findViewById(R.id.view_edit_text);
        btn.setOnClickListener( clk -> et.setText("You clicked my button!"));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("The Message")
        .setPositiveButton("Positive", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // What to do on Accept
                 }
            })
        .setNegativeButton("Negative", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // What to do on Cancel
                }
        }).setView(middle);

        builder.create().show();
    }
}