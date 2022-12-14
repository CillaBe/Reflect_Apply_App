package com.example.reflectapply.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reflectapply.Database.Repository;
import com.example.reflectapply.Entity.Passage;
import com.example.reflectapply.Entity.Reflection;
import com.example.reflectapply.R;

import java.util.List;

public class PassageAdapter extends RecyclerView.Adapter<PassageAdapter.PassageViewHolder> {
    class PassageViewHolder extends RecyclerView.ViewHolder{
        private final TextView passageNameItemView;
        private final TextView DateItemView;
        Repository repo;

        private PassageViewHolder(View itemView){
            super (itemView);
            passageNameItemView = itemView.findViewById(R.id.PassageName);
            DateItemView = itemView.findViewById(R.id.PassageDate);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Passage current= mPassages.get(position);
                    Intent intent = new Intent(context, ViewPassage.class);
                    intent.putExtra("id",current.getPassageID());
                    intent.putExtra("name",current.getPassageName());
                    intent.putExtra("date",current.getPassageDate());
                    int PassageID = current.getPassageID();



                    context.startActivity(intent);


                }
            });
        }
    }
    private List<Passage> mPassages;
    private List<Reflection> mAllReflectionsByID;

    private final Context context;
    private final LayoutInflater mInflater;
    public PassageAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context =context;
    }
    @NonNull
    @Override
    public PassageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView=mInflater.inflate(R.layout.passage_list_item,parent,false);
        return new PassageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PassageViewHolder holder, int position) {
        if(mPassages!=null){
            Passage current=mPassages.get(position);
            String name=current.getPassageName();
            String date= current.getPassageDate();

            holder.passageNameItemView.setText(name);
            holder.DateItemView.setText(date);

        }
        else{
            holder.passageNameItemView.setText("No passage name");
        }

    }
    public void setPassages (List<Passage> passages){
        mPassages = passages;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (mPassages != null) {
            return mPassages.size();
        }
        else{
            return 0;
        }
    }
}
