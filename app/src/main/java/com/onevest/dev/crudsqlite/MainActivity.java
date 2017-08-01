package com.onevest.dev.crudsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.onevest.dev.crudsqlite.helper.DatabaseHelper;
import com.onevest.dev.crudsqlite.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText idET, nameET, phoneET;
    private Button createBT, readBT, updateBT, deleteBT;
    private TextView viewTV;
    private DatabaseHelper dbHelper;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIObject();
        dbHelper = new DatabaseHelper(this);
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
            case R.id.createBT:
                addContact();
                break;
            case R.id.readBT:
                readContacts();
                break;
            case R.id.updateBT:
                updateContact();
                break;
            case R.id.deleteBT:
                deleteContact();
                break;
        }
    }

    private void deleteContact() {
        dbHelper.deleteContact(Integer.parseInt(idET.getText().toString().trim()));
        readContacts();
    }

    private void updateContact() {
        contact = new Contact();
        int id = Integer.parseInt(idET.getText().toString().trim());
        String name = nameET.getText().toString().trim();
        String phone = phoneET.getText().toString().trim();
        if (idET.getText().toString().trim().equals("") || name.equals("")
                || phone.equals("")) {
            Toast.makeText(this, "Please complete the form", Toast.LENGTH_LONG).show();
        } else {
            contact.setId(id);
            contact.setName(name);
            contact.setPhone(phone);
            dbHelper.updateContact(contact);
            readContacts();
        }
    }

    private void readContacts() {
        List<Contact> contactList = dbHelper.getAllContact();
        StringBuilder builder = new StringBuilder();
        if (contactList != null) {
            for (Contact con : contactList) {
                String string = con.getId() + " " + con.getName() + " " + con.getPhone();
                builder.append(string);
                builder.append("\n");
            }
            viewTV.setText(builder.toString());
        } else {
            viewTV.setText("No Database");
        }
        idET.setText("");
        nameET.setText("");
        phoneET.setText("");
        idET.requestFocus();
    }

    private void addContact() {
        contact = new Contact();
        contact = new Contact();
        int id = Integer.parseInt(idET.getText().toString().trim());
        String name = nameET.getText().toString().trim();
        String phone = phoneET.getText().toString().trim();
        if (idET.getText().toString().trim().equals("") || name.equals("")
                || phone.equals("")) {
            Toast.makeText(this, "Please complete the form", Toast.LENGTH_LONG).show();
        } else {
            contact.setId(id);
            contact.setName(name);
            contact.setPhone(phone);
            dbHelper.addContact(contact);
            readContacts();
        }
    }
}
