package com.example.reflectapply.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reflectapply.Entity.Assessment;
import com.example.reflectapply.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {
    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemView;

        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.assessmentListItem);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Assessment current = mAssessments.get(position);
                    Intent intent = new Intent(context, ViewAssessment.class);
                    intent.putExtra("CourseID",current.getCourseID());
                    intent.putExtra("AssessmentID", current.getAssessmentID());
                    intent.putExtra("AssessmentTitle", current.getAssessmentTitle());
                    intent.putExtra("AssessmentType", current.getAssessmentType());
                    intent.putExtra("AssessmentEnd", current.getAssessmentEnd());
                    intent.putExtra("AssessmentStart", current.getAssessmentStart());



                    context.startActivity(intent);



                }
            });
        }

    }

    private List<Assessment> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessments != null) {
            Assessment current = mAssessments.get(position);
            String AssessmentName = current.getAssessmentTitle();

            holder.assessmentItemView.setText(AssessmentName);

        } else {
            holder.assessmentItemView.setText("No assessment name");
        }

    }

    public void setAssessments(List<Assessment> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (mAssessments != null) {
            return mAssessments.size();
        } else
        {
            return 0;
        }
    }
}

