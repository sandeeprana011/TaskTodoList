package com.rana.tasktodolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rana.tasktodolist.R;
import com.rana.tasktodolist.adapters.TaskListAdapter;
import com.rana.tasktodolist.database.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewTask = (RecyclerView) findViewById(R.id.rv_tasklist);

    }

    @Override
    protected void onResume() {
        super.onResume();

        TaskListAdapter taskListAdapter = new TaskListAdapter((ArrayList<Task>) Task.listAll(Task.class));
        recyclerViewTask.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTask.setAdapter(taskListAdapter);
    }

    public void openActivityAddNewTask(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }
}
