package com.sheygam.masa_g2_17_01_18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {
    private EditText inputName, inputEmail, inputPhone, inputDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        inputName = findViewById(R.id.input_name);
        inputEmail = findViewById(R.id.input_email);
        inputPhone = findViewById(R.id.input_phone);
        inputDesc = findViewById(R.id.input_desc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_cont_act_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_item){
            String name = inputName.getText().toString();
            String email = inputEmail.getText().toString();
            String phone = inputPhone.getText().toString();
            String desc = inputDesc.getText().toString();
            Contact contact = new Contact(name,email,phone,desc);
            StoreManager.getInstance().addContact(contact);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
