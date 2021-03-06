package com.example.todolist1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

//Responsible for displaying data from the model into a row in the recyclerview
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface onLongClickListener{
        void onItemLongClicked(int position);
    }
    public interface OnclickListener{
        void OnItemClicked(int position);
    }
    List <String> items;
    onLongClickListener longClickListener;
    OnclickListener clickListener;
    public ItemsAdapter(List<String> items, onLongClickListener longClickListener, OnclickListener clickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout inflator to inflate the view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent,false);
        return new ViewHolder(todoView);
    }
    // Responsible to binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    // Grab the item at the position
       String item =items.get(position);
    // Bind the item into the specify view holder
       holder.bind(item);
    }
    // Tells the recycleviewer how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // container to provide easy access to views that represents each rows of the list
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }
        //Update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.OnItemClicked(getAdapterPosition());

                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                  //Notify the listener which position was long pressed.
                  longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
