package com.example.reflectapply.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reflectapply.Entity.Course;
import com.example.reflectapply.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseItemView = itemView.findViewById(R.id.textView5);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Course current = mCourses.get(position);
                    Intent intent = new Intent(context, ViewCourse.class);
                    intent.putExtra("CourseId", current.getCourseID());
                    intent.putExtra("CourseTitle", current.getCourseTitle());
                    intent.putExtra("CourseStart", current.getCourseStart());
                    intent.putExtra("CourseEnd", current.getCourseEnd());
                    intent.putExtra("CourseStatus", current.getCourseStatus());
                    intent.putExtra("CourseInstructorName", current.getCourseInstructorName());
                    intent.putExtra("InstructorPhone", current.getInstructorPhone());
                    intent.putExtra("InstructorEmail", current.getInstructorEmail());
                    intent.putExtra("CourseNote", current.getCourseNote());
                    intent.putExtra("TermID", current.getTermID());


                    context.startActivity(intent);


                }
            });
        }

    }

    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course current = mCourses.get(position);
            String CourseName = current.getCourseTitle();

            holder.courseItemView.setText(CourseName);

        } else {
            holder.courseItemView.setText("No course name");
        }

    }

    public void setCourse(List<Course> courses) {
        mCourses = courses;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (mCourses != null) {
            return mCourses.size();
        } else
            {
            return 0;
        }
    }
}