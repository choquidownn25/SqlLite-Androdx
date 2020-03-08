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

public class ModifyArticuloActivity extends AppCompatActivity implements View.OnClickListener {
    //<editor-fold desc="Atributos">

    private Button updateBtn, deleteBtn;
    private EditText nombreEditText;
    private EditText descEditText;
    private EditText precEditText;
    private CheckBox checkBox3;
    private long _id;
    private DBManager dbManager;
    private DBArticulo dbArticulo;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    //</editor-fold>
    //<editor-fold desc="Metodos de acción">
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                final String nombre = nombreEditText.getText().toString();
                final String desc = descEditText.getText().toString();
                final String precio= precEditText.getText().toString();
                //boolean activo = checkBox3.isChecked();
                dbArticulo.update(_id, nombre, desc, precio);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbArticulo.delete(_id);
                this.returnHome();
                break;
        }
    }
    //Retorno al inicio
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ModifyArticuloActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
    //</editor-fold>


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_record);

        dbArticulo = new DBArticulo(this);
        dbArticulo.open();

        nombreEditText = (EditText) findViewById(R.id.edittext_nombre);
        descEditText = (EditText) findViewById(R.id.edittext_descripcion);
        precEditText = (EditText) findViewById(R.id.edittext_precio);
        //checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.saludo) );


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("nombre");
        String desc = intent.getStringExtra("desc");
        String prec = intent.getStringExtra("prec");
        //boolean datoAct = Boolean.parseBoolean(intent.getStringExtra("activo"));
        _id = Long.parseLong(id);

        nombreEditText.setText(name);
        descEditText.setText(desc);
        precEditText.setText(prec);
                //checkBox3.setChecked(datoAct);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }
}
