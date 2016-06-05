package ru.spbau.mit.yulia.jenkins_api_for_android;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StatusActivity extends AppCompatActivity {

    private RecyclerView mainList;
    private ProjectAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        mainList = (RecyclerView) findViewById(R.id.recycler);

    }

    private static class MyData {
        private String name;
        private boolean isOk;

        public MyData(String name, boolean isOk) {
            this.name = name;
            this.isOk = isOk;
        }

        public boolean isOk() {
            return isOk;
        }

        public String getName() {
            return name;
        }
    }

    private class ProjectAdapter extends RecyclerView.Adapter {

        private List<MyData> mJobs;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = StatusActivity.this.getLayoutInflater().inflate(R.layout.status_panel, null);
            return new ProjectHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final MyData jobEntity = mJobs.get(position);
            ((TextView) holder.itemView.findViewById(R.id.commit)).setText(jobEntity.getName());
            ((ImageView) holder.itemView.findViewById(R.id.imageView)).setImageDrawable(
                    (jobEntity.isOk()) ? getDrawable(R.mipmap.ic_launcher) : null // TODO
            );
        }

        @Override
        public int getItemCount() {
            return mJobs == null ? 0 : mJobs.size();
        }

        public void loadData(List<MyData> jobs) {
            mJobs = jobs;
            notifyDataSetChanged();
        }

        public class ProjectHolder extends RecyclerView.ViewHolder {
            public ProjectHolder(View itemView) {
                super(itemView);
            }
        }
    }

}
