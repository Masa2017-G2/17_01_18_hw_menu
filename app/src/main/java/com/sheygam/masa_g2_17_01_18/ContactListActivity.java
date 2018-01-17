package com.sheygam.masa_g2_17_01_18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ContactListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private MyAdapter adapter;
    private ListView myList;
    private TextView emptyTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        myList = findViewById(R.id.my_list);
        emptyTxt = findViewById(R.id.empty_list_txt);
        myList.setOnItemClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Contact> contacts = StoreManager.getInstance().getContacts();
        adapter = new MyAdapter(contacts);
        if (contacts.isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
        }else{
            emptyTxt.setVisibility(View.GONE);
        }
        myList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contac_list_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout_item){
            setResult(RESULT_OK);
            finish();
        }else if(item.getItemId() == R.id.add_item){
            Intent intent = new Intent(this,AddContactActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contact contact = (Contact) adapter.getItem(position);
        Intent intent = new Intent(this,ViewContactActivity.class);
        intent.putExtra("DATA",contact);
        intent.putExtra("POS",position);
        startActivity(intent);
    }
}
