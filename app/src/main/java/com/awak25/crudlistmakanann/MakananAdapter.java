 package com.awak25.crudlistmakanann;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.awak25.crudlistmakanann.model.DataItem;
import com.bumptech.glide.Glide;

import java.util.List;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.MakananViewHolder> {

    List<DataItem> dataItems;
    Context context;

    public MakananAdapter (List<DataItem> listData, Context dataContext){
        this.dataItems = listData;
        this.context = dataContext;
    }

    @NonNull
    @Override
    public MakananAdapter.MakananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_makanan, parent, false);
        return new MakananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MakananAdapter.MakananViewHolder holder, int position) {
        holder.textName.setText(dataItems.get(position).getMenuNama());
        holder.textPrice.setText(dataItems.get(position).getMenuHarga());

        Glide.with(context).load(dataItems.get(position).getMenuGambar())
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class MakananViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgView;
        private TextView textName, textPrice;

        public MakananViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.tv_nama);
            textPrice = itemView.findViewById(R.id.tv_price);
            imgView = itemView.findViewById(R.id.img_gambar);

        }
    }
}
