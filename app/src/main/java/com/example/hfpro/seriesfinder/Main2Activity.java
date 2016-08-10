package com.example.hfpro.seriesfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

public class Main2Activity extends AppCompatActivity {

    EditText editText ;
    Switch switch1 ;
    ImageButton imageButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editText = (EditText) findViewById(R.id.editText) ;
        switch1 = (Switch) findViewById(R.id.switch1) ;
        imageButton = (ImageButton) findViewById(R.id.imageButton);

    }
    public void search(View view)
    {
        imageButton.animate().alpha(0.5f).setDuration(500) ;
        String url = "api.tvmaze.com/search/shows?q=" ;
        String toLook = editText.getText().toString() ;
        Intent intent = new Intent(Main2Activity.this , Main3Activity.class) ;
        intent.putExtra("URL" , url+toLook) ;
        startActivity(intent);
    }

}
