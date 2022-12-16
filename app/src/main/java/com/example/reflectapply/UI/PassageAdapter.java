package com.example.reflectapply.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reflectapply.Database.Repository;
import com.example.reflectapply.Entity.Passage;
import com.example.reflectapply.Entity.Reflection;
import com.example.reflectapply.R;

import java.util.ArrayList;
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
                    intent.putExtra ("summary",current.getReflectionSummary());
                    intent.putExtra("application",current.getReflectionApplication());
                    intent.putExtra("prayer",current.getReflectionPrayer());
                    intent.putExtra("word",current.getReflectionWord());




                    context.startActivity(intent);


                }
            });
        }
    }
    //added mpPassaged = newArrayList<>();
    private List<Passage> mPassages = new ArrayList<>();

    //added below
    private List<Passage> filteredPassages = new ArrayList<>();
    //end of additions
    private final Context context;
    private final LayoutInflater mInflater;
    //addeded mPassges here and this.mPassages
    public PassageAdapter( List<Passage> mPassages,Context context){
        mInflater = LayoutInflater.from(context);
        this.context =context;
        this.mPassages = mPassages;
        this.filteredPassages = mPassages;
    }
    //added below

    public Filter filterList() {
        Filter filter = new Filter() {
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
             mPassages = (List<Passage>) results.values;
             notifyDataSetChanged();
            }
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Passage> filteredResults = new ArrayList<>();
                String searchEntry= constraint.toString().toLowerCase();
                String[] split = searchEntry.split(",");
                ArrayList<String> searchPassages = new ArrayList<>(split.length);
                for(String pSplit : split){
                    String crop = pSplit.trim();
                    if(crop.length()>0)
                        searchPassages.add(crop);
                }
                for (Passage passages : filteredPassages){
                    if(passages.getPassageName().toLowerCase().trim().contains(searchEntry)){
                        filteredResults.add(passages);
                    }

                }
                results.count = filteredResults.size();
                results.values = filteredResults;
                return results;
            }
        };
        return filter;
    }



    //end of additions
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
    //new stuff
    public void filteredList(List<Passage> filetredList){
        mPassages = filetredList;
        notifyDataSetChanged();
    }


}
