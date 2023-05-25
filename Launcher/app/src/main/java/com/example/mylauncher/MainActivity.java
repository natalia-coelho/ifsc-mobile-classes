package com.example.mylauncher;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView appRecyclerView;
    private AppListAdapter appListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appRecyclerView = findViewById(R.id.appRecyclerView);
        appRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        appListAdapter = new AppListAdapter();
        appRecyclerView.setAdapter(appListAdapter);
    }

    private class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.AppViewHolder> {

        private List<ApplicationInfo> appList;
        private PackageManager packageManager;

        public AppListAdapter() {
            appList = new ArrayList<>();
            packageManager = getPackageManager();
            loadAppList();
        }

        private void loadAppList() {
            List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
            for (ApplicationInfo packageInfo : packages) {
                if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    appList.add(packageInfo);
                }
            }
        }

        @Override
        public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new AppViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AppViewHolder holder, int position) {
            ApplicationInfo appInfo = appList.get(position);
            String appName = packageManager.getApplicationLabel(appInfo).toString();
            Drawable appIcon = packageManager.getApplicationIcon(appInfo);
            holder.bind(appName, appIcon);
        }

        @Override
        public int getItemCount() {
            return appList.size();
        }

        public class AppViewHolder extends RecyclerView.ViewHolder {

            private ImageView appIconImageView;
            private TextView appNameTextView;

            public AppViewHolder(View itemView) {
                super(itemView);
                appIconImageView = itemView.findViewById(R.id.appIconImageView);
                appNameTextView = itemView.findViewById(android.R.id.text1);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(itemView.getContext(), "Não vou abrir o app de fato, amigão", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            public void bind(String appName, Drawable appIcon) {
                appNameTextView.setText(appName);
                appIconImageView.setImageDrawable(appIcon);
            }
        }
    }
}