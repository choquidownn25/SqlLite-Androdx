package com.asesoftware.pruebatecnica.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asesoftware.pruebatecnica.R;
import com.asesoftware.pruebatecnica.ui.adapter.ArticuloRecyclerAdapter;
import com.asesoftware.pruebatecnica.ui.database.DBArticulo;
import com.asesoftware.pruebatecnica.ui.database.DBManager;
import com.asesoftware.pruebatecnica.ui.helper.DatabaseHelper;
import com.asesoftware.pruebatecnica.ui.models.Articulo;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivityArticulo extends AppCompatActivity {

    //<editor-fold desc="Atributos">
    /*
    Declarar instancias globales
     */
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private DBManager dbManager;
    private DBArticulo dbArticulo;
    final String[] from = new String[] {
            DatabaseHelper._ID,
            DatabaseHelper.NOMBRE,
            DatabaseHelper.DESCRIPCION,
            DatabaseHelper.PREC
            //DatabaseHelpers.ACTIVO
    };

    //final int[] to = new int[] { R.id.id, R.id.title, R.id. };
    //final int[] Activo = new int[] { R.id.id, R.id.nombre, R.id.lblactivo};
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private ArticuloRecyclerAdapter adapter;
    ArrayList<Articulo> articulosArrayList=new ArrayList<>();
    //</editor-fold>


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sqllite_articulo);
        dbArticulo = new DBArticulo(this);
        dbArticulo.open();
        Cursor cursor = dbArticulo.fetch();
        // Inicializar Animes

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SHOW INPUT DIALOG
                //showDialog();
                Intent intent = new Intent(MainActivityArticulo.this, AddArticuloActivity.class);
                startActivity(intent);
            }
        });

        //recycler views
        recycler= (RecyclerView) findViewById(R.id.articuloList);

        //propiedad
        recycler.setLayoutManager(new LinearLayoutManager(this));

        recycler.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        adapter=new ArticuloRecyclerAdapter(this,articulosArrayList);

        //metodo para listar
        retrieve();
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.saludo) );
    }

    //<editor-fold desc="Retorna todo">
    private void retrieve() {
        articulosArrayList.clear();

        DBManager dbs=new DBManager(this);
        DBArticulo db=new DBArticulo(this);
        db.open();

        //Recibe el cursor
        Cursor c=db.getAll();

        //
        //Agregar a la Lista de arreglos
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String nombre=c.getString(1);
            String description=c.getString(2);
            String precio=c.getString(3);;
            //String datoActivo=c.getString(3);
            Articulo articulo=new Articulo(id,nombre,description, precio);

            //ADD al array lis del pais
            articulosArrayList.add(articulo);
        }

        //Comprueba si la lista no esta vacía
        if(!(articulosArrayList.size()<1))
        {
            recycler.setAdapter(adapter);
        }

        db.close();;

    }
    //</editor-fold>
}
