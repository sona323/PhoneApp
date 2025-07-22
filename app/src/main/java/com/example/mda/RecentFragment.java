package com.example.mda;  // Make sure your package name is correct
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import java.util.List;
import androidx.room.Room;
import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;

public class RecentFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecentCallAdapter adapter;
    private List<CallLogEntity> callLogs = new ArrayList<>();

    public RecentFragment() {
        // Required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        AppDatabase db = Room.databaseBuilder(requireContext(),
                        AppDatabase.class, "my_database")
                .allowMainThreadQueries()  // Avoid in production!
                .build();

        callLogs = db.callLogDao().getAllCallLogs();

        adapter = new RecentCallAdapter(callLogs, callEntity -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + callEntity.phone));
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);

        return view;
    }
}
