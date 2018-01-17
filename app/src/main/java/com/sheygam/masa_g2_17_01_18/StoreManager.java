package com.sheygam.masa_g2_17_01_18;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gregorysheygam on 17/01/2018.
 */

public class StoreManager {
    private static final String AUTH_SP = "AUTH";
    private static final String CONTACTS_SP = "CONTACTS";
    private static final String KEY_TOKEN = "TOKEN";
    private static final StoreManager ourInstance = new StoreManager();
    private Context context;

    public static StoreManager getInstance() {
        return ourInstance;
    }

    private StoreManager() {
        context = App.getContext();
    }

    public void auth(Auth auth){
        context.getSharedPreferences(AUTH_SP,Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_TOKEN,auth.toString())
                .commit();
    }

    public void logOut(){
        context.getSharedPreferences(AUTH_SP,Context.MODE_PRIVATE)
                .edit()
                .remove(KEY_TOKEN)
                .commit();
    }

    public boolean isAuth(){
        return context.getSharedPreferences(AUTH_SP,Context.MODE_PRIVATE)
                .getString(KEY_TOKEN,null) != null;
    }

    public List<Contact> getContacts(){
        List<Contact> contacts = new ArrayList<>();
        String token = getToken();
        if(token == null){
            throw new RuntimeException("Token is null");
        }
        String tmp = context.getSharedPreferences(CONTACTS_SP,Context.MODE_PRIVATE)
                .getString(token,null);
        if(tmp != null){
            String[] arr = tmp.split(";");
            for (String str : arr) {
                contacts.add(Contact.newInstance(str));
            }
        }

        return contacts;
    }

    public void addContact(Contact contact){
        String token = getToken();
        if (token == null){
            throw new RuntimeException("Token is null");
        }
        String str = context.getSharedPreferences(CONTACTS_SP,Context.MODE_PRIVATE)
                .getString(token,null);
        if (str == null){
            str = contact.toString();
        }else{
            str += ";" + contact.toString();
        }
        context.getSharedPreferences(CONTACTS_SP,Context.MODE_PRIVATE)
                .edit()
                .putString(token,str)
                .commit();
    }

    public void updateContact(Contact contact, int position){
        if(position < 0){
            return;
        }
        String token = getToken();
        if(token == null){
            throw new RuntimeException("Token is null");
        }

        String tmp = context.getSharedPreferences(CONTACTS_SP,Context.MODE_PRIVATE)
                .getString(token,null);
        if (tmp == null){
            throw new RuntimeException("Something was wrong!WTF!");
        }
        String[] arr = tmp.split(";");
        List<String> contacts = Arrays.asList(arr);
        contacts.set(position,contact.toString());
        for (int i = 0; i < contacts.size(); i++) {
            if(i == 0){
                tmp = contacts.get(i);
                continue;
            }

            tmp += ";" + contacts.get(i);
        }

        context.getSharedPreferences(CONTACTS_SP,Context.MODE_PRIVATE)
                .edit()
                .putString(token,tmp)
                .commit();

    }

    private String getToken(){
        return context.getSharedPreferences(AUTH_SP,Context.MODE_PRIVATE)
                .getString(KEY_TOKEN,null);
    }
}
