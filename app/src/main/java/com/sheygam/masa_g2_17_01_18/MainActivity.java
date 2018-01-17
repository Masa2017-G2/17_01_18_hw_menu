package com.sheygam.masa_g2_17_01_18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText inputEmail, inputPassword;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(StoreManager.getInstance().isAuth()){
            startListActivity();
        }
        setContentView(R.layout.activity_main);
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_btn){
            String email = inputEmail.getText().toString();
            String password = inputPassword.getText().toString();
            StoreManager.getInstance().auth(new Auth(email,password));
            startListActivity();
        }
    }

    private void startListActivity(){
        Intent intent = new Intent(this,ContactListActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            StoreManager.getInstance().logOut();
        }else{
            finish();
        }
    }
}
