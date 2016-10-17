package com.rana.tasktodolist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rana.tasktodolist.R;
import com.rana.tasktodolist.database.Task;
import com.rana.tasktodolist.datastruct.Constants;

import java.util.ArrayList;

/**
 * Created by sandeeprana on 17/10/16.
 * License is only applicable to individuals and non-profits
 * and that any for-profit company must
 * purchase a different license, and create
 * a second commercial license of your
 * choosing for companies
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private ArrayList<Task> taskArrayList;

    public TaskListAdapter(ArrayList<Task> taskArrayList) {

        this.taskArrayList = taskArrayList;
    }

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskListAdapter.ViewHolder holder, int position) {
        Task task = this.taskArrayList.get(position);
        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
        switch (task.getStatus()) {
            case Constants.STATUS_DONE:
                holder.status.setText("Completed");
                break;
            default:
                holder.status.setText("Incomplete");
                break;
        }
        holder.buttonMarkComplete.setTag(task);
    }

    @Override
    public int getItemCount() {
        return this.taskArrayList.size();
    }

    private void notifyDataSetChangedCustom() {
        this.taskArrayList = (ArrayList<Task>) Task.listAll(Task.class);
        super.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, status;
        Button buttonMarkComplete;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.t_item_title);
            description = (TextView) itemView.findViewById(R.id.t_item_description);
            status = (TextView) itemView.findViewById(R.id.t_item_status);
            buttonMarkComplete = (Button) itemView.findViewById(R.id.b_item_completed);

            this.buttonMarkComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Task task = (Task) v.getTag();
                    task.setStatus(Constants.STATUS_DONE);
                    task.save();
                    notifyDataSetChangedCustom();
                }


            });
        }

    }
}
