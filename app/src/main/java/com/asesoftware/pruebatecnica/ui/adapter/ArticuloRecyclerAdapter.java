package com.asesoftware.pruebatecnica.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asesoftware.pruebatecnica.R;
import com.asesoftware.pruebatecnica.ui.activity.MainActivityArticulo;
import com.asesoftware.pruebatecnica.ui.activity.ModifyArticuloActivity;
import com.asesoftware.pruebatecnica.ui.database.DBManager;
import com.asesoftware.pruebatecnica.ui.models.Articulo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticuloRecyclerAdapter extends RecyclerView.Adapter<ArticuloRecyclerAdapter.ArticuloViewHolder>  {

    //<editor-fold desc="Atributos">
    private DBManager dbManager;
    public ImageView overflow;
    private Context mContext;
    private Context mainActivityArticulos;
    private ArrayList<Articulo> articulos;

    //</editor-fold>

    //<editor-fold desc="Constructor">
    public ArticuloRecyclerAdapter (ArrayList<Articulo> articulos, Context mContext) {
        this.articulos = articulos;
        this.mContext = mContext;
    }
    public ArticuloRecyclerAdapter(Context mainActivityArticulo, ArrayList<Articulo> articulosArrayList) {
        articulos = articulosArrayList;
        this.mainActivityArticulos= mainActivityArticulo;
    }

    //</editor-fold>

    @NonNull
    @Override
    public ArticuloViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activitycarviewsqllitearticulo, viewGroup, false);
        return new ArticuloRecyclerAdapter.ArticuloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloViewHolder articuloViewHolder, int i) {

        String datoCodigo = String.valueOf(articulos.get(i).getId());
        articuloViewHolder.idTextView.setText(datoCodigo);
        articuloViewHolder.nombre.setText(articulos.get(i).getNombre());
        articuloViewHolder.description.setText("Descripcion : "+articulos.get(i).getDescripcion());
        articuloViewHolder.precio.setText("Precio : "+articulos.get(i).getPrecio());
        // articuloViewHolder.checkBox3.setChecked(Boolean.parseBoolean(articulos.get(i).getActivo()));
        Picasso.get().load("http://homecenterco.scene7.com/is/image/SodimacCO/257403_13?producto495$&iv=hfRH2&wid=1000&hei=1000").resize(20,20).into(articuloViewHolder.imagen);


    }

    @Override
    public int getItemCount() {
        return articulos.size();
    }

    public class ArticuloViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;

        public TextView description;
        public TextView precio;
        public TextView idTextView;
        public CheckBox checkBox3;

        public ArticuloViewHolder(@NonNull View itemView) {
            super(itemView);
            //Campos para mapear
            idTextView = (TextView) itemView.findViewById(R.id.id);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            description = (TextView) itemView.findViewById(R.id.lblactivo);
            precio = (TextView) itemView.findViewById(R.id.lblprecio);
            idTextView = (TextView) itemView.findViewById(R.id.id);
            imagen= (ImageView) itemView.findViewById(R.id.imagen);
            itemView.setOnClickListener(new View.OnClickListener()  {

                @Override
                public void onClick(View v) {

                    String id = idTextView.getText().toString();
                    String nom = nombre.getText().toString();
                    String desc = description.getText().toString();
                    String prec = precio.getText().toString();
                    // boolean activo = checkBox3.isChecked();
                    Intent modify_intent = new Intent(v.getContext(), ModifyArticuloActivity.class);
                    modify_intent.putExtra("nombre", nom);
                    modify_intent.putExtra("desc", desc);
                    modify_intent.putExtra("prec", prec);
                    modify_intent.putExtra("id", id);
                    // modify_intent.putExtra("activo", activo);
                    v.getContext().startActivity(modify_intent);
                }
            });
        }
    }


}
