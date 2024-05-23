package com.example.languageinterviewq.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.languageinterviewq.Models.Category;
import com.example.languageinterviewq.R;
import com.example.languageinterviewq.Screens.AndroidQuestionActivity;
import com.example.languageinterviewq.Screens.DartQuestionActivity;
import com.example.languageinterviewq.Screens.FlutterQuestionActivity;
import com.example.languageinterviewq.Screens.JavaQuestionActivity;
import com.example.languageinterviewq.Screens.KotlinQuestionActivity;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
   Context context;
    ArrayList<Category> categoryModals;

    public CategoryAdapter(Context context, ArrayList<Category> categoryModals) {
        this.context = context;
        this.categoryModals = categoryModals;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
  Category modal= categoryModals.get(position);
        holder.txtCategory.setText(modal.getCategoryName());
        Glide.with(context)
                .load(modal.getCategoryImage())
                .into(holder.categoryImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCatId= modal.getCategoryId();
                if("m7Z02CJuriwrZVBdSibk".equals(selectedCatId)) {
                    Intent intent = new Intent(context, JavaQuestionActivity.class);
                    context.startActivity(intent);
                }
                else if("an0tzy5usT0Alu5aLtuQ".equals(selectedCatId))
                {
                      Intent intent = new Intent(context, AndroidQuestionActivity.class);

                    context.startActivity(intent);
                } else if ("lR1aJ0UdI8TEe88WeOqK" .equals(selectedCatId)) {
                    Intent intent = new Intent(context, FlutterQuestionActivity.class);

                    context.startActivity(intent);
                }
                else if ("282XHZF8Ip4DrpEL3xx0" .equals(selectedCatId)) {
                    Intent intent = new Intent(context, DartQuestionActivity.class);

                    context.startActivity(intent);
                }
                else if ("dqpITdd6sVfI4t784dBY" .equals(selectedCatId)) {
                    Intent intent = new Intent(context, KotlinQuestionActivity.class);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModals.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImg;
        TextView txtCategory;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImg=itemView.findViewById(R.id.categoryImg);
            txtCategory=itemView.findViewById(R.id.txtCategory);
        }
    }
}
