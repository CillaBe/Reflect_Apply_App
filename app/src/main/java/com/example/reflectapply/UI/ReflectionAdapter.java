package com.example.reflectapply.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reflectapply.Entity.Reflection;
import com.example.reflectapply.R;

import java.util.List;

public class ReflectionAdapter extends RecyclerView.Adapter<ReflectionAdapter.ReflectionViewHolder> {
    class ReflectionViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;

        public ReflectionViewHolder(@NonNull View itemView) {
            super(itemView);
            courseItemView = itemView.findViewById(R.id.textView5);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Reflection current = mReflections.get(position);
                    Intent intent = new Intent(context, ViewReflection.class);
                    intent.putExtra("ReflectionID", current.getReflectionID());
                    intent.putExtra("ReflectionSummary", current.getReflectionSummary());
                    intent.putExtra("ReflectionApplication", current.getReflectionApplication());
                    intent.putExtra("ReflectionPrayer", current.getReflectionPrayer());
                    intent.putExtra("ReflectionWord", current.getReflectionWord());
                    intent.putExtra("PassageID", current.getPassageID());



                    context.startActivity(intent);


                }
            });
        }

    }

    private List<Reflection> mReflections;
    private final Context context;
    private final LayoutInflater mInflater;

    public ReflectionAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ReflectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
        return new ReflectionViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ReflectionViewHolder holder, int position) {
        if (mReflections != null) {
            Reflection current = mReflections.get(position);
            String CourseName = current.getReflectionSummary();

            holder.courseItemView.setText(CourseName);

        } else {
            holder.courseItemView.setText("No course name");
        }

    }

    public void setReflections(List<Reflection> reflections) {
        mReflections = reflections;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (mReflections != null) {
            return mReflections.size();
        } else
            {
            return 0;
        }
    }
}