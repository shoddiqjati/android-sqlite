package com.onevest.dev.crudsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText idET, nameET, phoneET;
    private Button createBT, readBT, updateBT, deleteBT;
    private TextView viewTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIObject();
    }

    private void initUIObject() {
        idET = (EditText) findViewById(R.id.idET);
        nameET = (EditText) findViewById(R.id.nameET);
        phoneET = (EditText) findViewById(R.id.phoneET);

        createBT = (Button) findViewById(R.id.createBT);
        readBT = (Button) findViewById(R.id.readBT);
        updateBT = (Button) findViewById(R.id.updateBT);
        deleteBT = (Button) findViewById(R.id.deleteBT);

        createBT.setOnClickListener(this);
        readBT.setOnClickListener(this);
        updateBT.setOnClickListener(this);
        deleteBT.setOnClickListener(this);

        viewTV = (TextView) findViewById(R.id.viewTV);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }


}
