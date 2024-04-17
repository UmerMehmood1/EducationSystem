package com.appsqueeze.springclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsqueeze.springclient.model.Course;
import com.genuinecoder.springclient.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> courses;
    private Context context;

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_cardview, parent, false);
        return new CourseViewHolder(view);
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.courseName.setText(course.getName());
        holder.instructor.setText("Instructor: "+course.getInstructorName());
        holder.schedule.setText("Schedule: "+course.getScheduleDateTime());
        holder.seatsAvailable.setText("Available Seats: "+ String.valueOf(course.getAvailableSeats()));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView courseName, instructor, schedule, seatsAvailable;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.courseName);
            instructor = itemView.findViewById(R.id.instructor);
            schedule = itemView.findViewById(R.id.schedule);
            seatsAvailable = itemView.findViewById(R.id.availablSeats);
        }
    }
}
