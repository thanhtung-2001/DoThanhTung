package com.example.thanhtng_dawd;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter {
    Activity activity;
    List<Employee> listEmployee;

    public EmployeeAdapter(Activity activity, List<Employee> listEmployee) {
        this.activity = activity;
        this.listEmployee = listEmployee;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.activity_item, parent,false);
        EmployeeHolder holder = new EmployeeHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmployeeHolder vh = (EmployeeHolder) holder;
        Employee employee = listEmployee.get(position);
        vh.tvName.setText(employee.name);
        vh.tvDes.setText(employee.des);
        vh.tvSalary.setText(employee.salary);
    }

    @Override
    public int getItemCount() {
        return listEmployee.size();
    }

    public class EmployeeHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDes, tvSalary;

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDes = itemView.findViewById(R.id.tvDes);
            tvSalary = itemView.findViewById(R.id.tvSalary);
        }
    }
}
