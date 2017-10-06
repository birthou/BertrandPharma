package com.group.bertrand.bertrandpharma;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //initialisation des valeurs
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mise a jour des valeur initier
        //items=new ArrayList<>();
        readItems();
        itemsAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvitems=(ListView) findViewById(R.id.lvItems);
        lvitems.setAdapter(itemsAdapter);

        //Ajout des elements dans la liste
        //items.add("Fluconazol");
        //items.add("Foie de morrue");
        //items.add("Alpalide");
        //items.add("Flanax");
        //items.add("Tetracycline");
        //items.add("Paracetamol");
        //items.add("Anaflanine");
        //items.add("Trojan");
        //items.add("Dolgesiforte");
        //items.add("Fertone");
        //items.add("Biogrip");
        setListViewListener();
    }

    //Creation d'une methode permettant de faire l'ajout des element
    public void onAddItem(View v){
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemiext = etNewItem.getText().toString();
        itemsAdapter.add(itemiext);
        writeItems();
        Toast.makeText(getApplicationContext(),"Le nouveau produit a ete ajouter", Toast.LENGTH_SHORT).show();
        etNewItem.setText("");
    }

    //creation d'une nouvelle methode basant sur l'ecouteur
    private void setListViewListener(){
        Log.i("MainActivity", "Setting up Listener on list view");
        lvitems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
              Log.i("MainActivity", "items remove from list" +position);
                items.remove(position);
                Toast.makeText(getApplicationContext(),"Un produit a ete retirer de la liste", Toast.LENGTH_SHORT).show();
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        }

        );
    }


    private void readItems() {

        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
           items=new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items=new ArrayList<String>();
            //Log.e("MainActivity", "Erreur de lecture du fichier", e);
        }
    }

    private void writeItems() {

        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile,items);
        } catch (IOException e) {
            Log.e("MainActivity", "Erreur d'ecriture du fichier", e);
        }
    }
}
