package com.rana.tasktodolist.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rana.tasktodolist.R;
import com.rana.tasktodolist.database.Task;

public class AddTaskActivity extends AppCompatActivity {

    EditText eTitle, eDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        eTitle = (EditText) findViewById(R.id.e_addtask_title);
        eDescription = (EditText) findViewById(R.id.e_addtask_description);
    }

    public void addNewTask(View view) {
        if (view != null)
            view.setEnabled(false);

        String title = eTitle.getText().toString();
        String description = eDescription.getText().toString();

        Task task = new Task(title, description, -1);
        Task.save(task);
        Toast.makeText(this, "New Task Added", Toast.LENGTH_SHORT).show();
        finish();

    }
}
