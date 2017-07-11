package com.example.shekhar.queres;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends Activity {

    private final String TAG = getClass().getSimpleName();
    private Button statusButton ;
    private Button saveButton;
    private ListView questionList;
    List<String> stringList;
    List<EditText> editTextsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initListener();
    }

    private void init(){
        statusButton = (Button)findViewById(R.id.status_button);
        saveButton = (Button)findViewById(R.id.save);
        editTextsList = new ArrayList<EditText>();
        editTextsList.add((EditText)findViewById(R.id.ed1));
        editTextsList.add((EditText)findViewById(R.id.ed2));
        editTextsList.add((EditText)findViewById(R.id.ed3));
        editTextsList.add((EditText)findViewById(R.id.ed4));
        editTextsList.add((EditText)findViewById(R.id.ed5));
        stringList = new ArrayList<String>();
        showQueries();
    }

    //To populate the previous stored queries
    private void showQueries(){
        List<String> query = new SavePreferences(this).getStringSetPref();
        int len = query.size();
        for(int i = 0; i<len; i++){
            editTextsList.get(i).setText(query.get(i));
        }
    }

    private void initListener(){

        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getNetworkConnectedStatus();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQueries();
            }
        });
    }

    //For saving the queries in the preferences
    private void saveQueries(){
        for(int i = editTextsList.size() - 1; i >= 0; i--){
            String value = editTextsList.get(i).getText().toString();
            if(!value.isEmpty())
                stringList.add(value);
        }
        new SavePreferences(this).saveStringSetPref(stringList);
    }

    private void getNetworkConnectedStatus(){
        //To do
    }
}
