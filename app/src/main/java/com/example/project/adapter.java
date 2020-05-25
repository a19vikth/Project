package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<planeter> mplaneter;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void  setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public adapter(Context context, ArrayList<planeter> exampleList){
        mContext = context;
        mplaneter = exampleList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.planeter, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        planeter currentItem = mplaneter.get(position);

        String imageUrl = currentItem.getimageurl();
        String namn = currentItem.getMnamn();


        holder.mtextview.setText(namn);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mimageview);
    }

    @Override
    public int getItemCount() {
        return mplaneter.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView mimageview;
        public TextView mtextview;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mimageview = itemView.findViewById(R.id.image_view);
            mtextview = itemView.findViewById(R.id.text_namn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}