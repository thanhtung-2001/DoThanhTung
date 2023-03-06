package com.example.thanhtng_dawd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edName, edDes, edSalary;
    Button btAdd, btUpdate, btDelete;
    AppDatabase db;

    RecyclerView rvEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getAppDatabase(this);
        initView();
        getAllEmployee();
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edDes = findViewById(R.id.edDes);
        edSalary = findViewById(R.id.edSalary);
        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(this);
        btUpdate = findViewById(R.id.btUpdate);
        btUpdate.setOnClickListener(this);
        btDelete = findViewById(R.id.btDelete);
        btDelete.setOnClickListener(this);
    }

    private void onAdd() {
        if (!validate()) {
            return;
        }
        Employee employee = new Employee();
        employee.name = edName.getText().toString();
        employee.des = edDes.getText().toString();
        employee.salary = edSalary.getText().toString();
        long id = db.employeeDao().insertEmployee(employee);
        if (id > 0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }

    }

    private void getAllEmployee() {
        db = AppDatabase.getAppDatabase(this);
        List<Employee> list = db.employeeDao().getAllEmployee();
        for (Employee employee : list) {
            Log.d("TAG", "id: "+employee.id + " - Name: " +employee.name);
        }

        EmployeeAdapter adapter = new EmployeeAdapter(this, list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rvEmployee = findViewById(R.id.rvEmployee);
        rvEmployee.setLayoutManager(layoutManager);
        rvEmployee.setAdapter(adapter);
    }

    private void onUpdate() {
        Employee employeeUpdate = db.employeeDao().findEmployee(2);
        employeeUpdate.name = edName.getText().toString();
        employeeUpdate.des = edDes.getText().toString();
        employeeUpdate.salary = edSalary.getText().toString();
        db.employeeDao().updateEmployee(employeeUpdate);
    }

    private void onDelete() {
        Employee employeeUpdate = db.employeeDao().findEmployee(5);
        db.employeeDao().deleteEmployee(employeeUpdate);
    }

    private boolean validate() {
        String mes = null;
        if (edName.getText().toString().trim().isEmpty()) {
            mes = "Chưa nhập Name";
        } else if (edDes.getText().toString().trim().isEmpty()) {
            mes = "Chưa nhập Designation";
        } else if (edSalary.getText().toString().trim().isEmpty()) {
            mes = "Chưa nhập Salary";
        }
        if (mes != null) {
            Toast.makeText(this, mes, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btAdd:
                onAdd();
                break;
            case R.id.btUpdate:
                onUpdate();
                break;
            case R.id.btDelete:
                onDelete();
                break;
            default:

        }
    }
}
