package net.ivanvega.soportediferentespantallasb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.Vector;

public class MiAdaptadorPersonaliza extends
        Adapter<MiAdaptadorPersonaliza.ViewHolder>{
    protected Vector<Libro> libros;
    private View.OnClickListener onClickItemListener;
    private View.OnLongClickListener onLongClickItenListener;

    public MiAdaptadorPersonaliza(Vector<Libro> libros){
        this.libros = libros;
    }

    public void setOnClickItemListener (View.OnClickListener onClickListener){
        this.onClickItemListener = onClickListener;
    }

    public  void setOnLongClickItemListener (View.OnLongClickListener onLongClickItemListener){
        this.onLongClickItenListener = onLongClickItemListener;
    }

    public MiAdaptadorPersonaliza(Context contexto, Vector<Libro> libros){
        this.libros = libros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View v =   LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_selector_layout, parent,
                        false);

        v.setOnClickListener(this.onClickItemListener);
        v.setOnLongClickListener(this.onLongClickItenListener);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {
        Libro libro =
                this.libros.elementAt(position);

        holder.getTitulo().setText(libro.getTitulo());
        holder.getPortada().setImageResource(libro.getRecursoImagen());

    }

    @Override
    public int getItemCount() {
        return libros.size();
    }

    public  static class ViewHolder extends
            RecyclerView.ViewHolder {
        private final TextView titulo;

        public TextView getTitulo() {
            return titulo;
        }

        public ImageView getPortada() {
            return portada;
        }

        private final ImageView portada;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            portada = itemView.findViewById(R.id.portada);
        }
    }

}
