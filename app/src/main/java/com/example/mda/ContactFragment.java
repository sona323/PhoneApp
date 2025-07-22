package com.example.mda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private AppDatabase db;

    public ContactFragment() {
        // Required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        db = AppDatabase.getInstance(getContext());

        List<ContactEntity> contactList = db.contactDao().getAllContacts();

        adapter = new ContactAdapter(contactList, contact -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + contact.phone));
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);

        return view;
    }
}
