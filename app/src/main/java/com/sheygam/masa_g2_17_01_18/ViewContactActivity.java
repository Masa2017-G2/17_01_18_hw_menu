package com.sheygam.masa_g2_17_01_18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ViewContactActivity extends AppCompatActivity {
    private EditText inputName, inputEmail, inputPhone, inputDesc;
    private TextView nameTxt, emailTxt, phoneTxt, descTxt;
    private MenuItem saveItem, editItem;
    private Contact contact;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        inputEmail = findViewById(R.id.input_email);
        inputName = findViewById(R.id.input_name);
        inputPhone = findViewById(R.id.input_phone);
        inputDesc = findViewById(R.id.input_desc);
        nameTxt = findViewById(R.id.name_txt);
        emailTxt = findViewById(R.id.email_txt);
        phoneTxt = findViewById(R.id.phone_txt);
        descTxt = findViewById(R.id.desc_txt);
        Intent intent = getIntent();
        contact = (Contact) intent.getSerializableExtra("DATA");
        position = intent.getIntExtra("POS",-1);
        nameTxt.setText(contact.getName());
        emailTxt.setText(contact.getEmail());
        phoneTxt.setText(contact.getPhone());
        descTxt.setText(contact.getDescription());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_cont_act_menu,menu);
        editItem = menu.findItem(R.id.edit_item);
        saveItem = menu.findItem(R.id.save_item);
        return super.onCreateOptionsMenu(menu);
    }

    private void showEdit(){
        inputName.setText(contact.getName());
        inputEmail.setText(contact.getEmail());
        inputPhone.setText(contact.getPhone());
        inputDesc.setText(contact.getDescription());
        findViewById(R.id.input_wrapper).setVisibility(View.VISIBLE);
        findViewById(R.id.text_view_wrapper).setVisibility(View.GONE);
        editItem.setVisible(false);
        saveItem.setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.edit_item){
            showEdit();
        }else if(item.getItemId() == R.id.save_item){
            contact.setName(inputName.getText().toString());
            contact.setEmail(inputEmail.getText().toString());
            contact.setPhone(inputPhone.getText().toString());
            contact.setDescription(inputDesc.getText().toString());
            StoreManager.getInstance().updateContact(contact,position);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
