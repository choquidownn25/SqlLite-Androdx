package com.asesoftware.pruebatecnica.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.asesoftware.pruebatecnica.R;
import com.asesoftware.pruebatecnica.ui.database.DBArticulo;
import com.asesoftware.pruebatecnica.ui.database.DBManager;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class AddArticuloActivity extends AppCompatActivity implements View.OnClickListener{

    //<editor-fold desc="Atributos">
    private Button addTodoBtn;
    private EditText nombreEditText;
    private EditText descEditText;
    private EditText precEditText;
    private CheckBox checkBox3;
    private DBManager dbManager;
    private DBArticulo dbArticulo;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    //</editor-fold>
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Articulo");
        setContentView(R.layout.activity_add_record_articulo);

        nombreEditText = (EditText) findViewById(R.id.edittext_nombre);
        descEditText = (EditText) findViewById(R.id.edittext_descripcion);
        precEditText = (EditText) findViewById(R.id.edittext_precio);

        addTodoBtn = (Button) findViewById(R.id.add_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.saludo) );

        dbArticulo = new DBArticulo(this);
        dbArticulo.open();
        addTodoBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_record:

                final String nombre = nombreEditText.getText().toString();
                final String desc = descEditText.getText().toString();
                final String precio= precEditText.getText().toString();

                dbArticulo.insert(nombre, desc, precio);

                Intent main = new Intent(AddArticuloActivity.this, MainActivityArticulo.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
}
