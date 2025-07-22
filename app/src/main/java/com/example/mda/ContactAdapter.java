package com.example.mda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<ContactEntity> contactList;
    private OnContactClickListener listener;
    private Context context;

    public interface OnContactClickListener {
        void onContactClick(ContactEntity contact);
    }

    public ContactAdapter(List<ContactEntity> contactList, OnContactClickListener listener, Context context) {
        this.contactList = contactList;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactEntity contact = contactList.get(position);
        holder.nameTextView.setText(contact.name);
        holder.phoneTextView.setText(contact.phone);

        holder.favoriteButton.setImageResource(contact.isFavorite ? R.drawable.ic_star_filled : R.drawable.ic_star_border);

        holder.itemView.setOnClickListener(v -> listener.onContactClick(contact));

        holder.favoriteButton.setOnClickListener(v -> {
            contact.isFavorite = !contact.isFavorite;
            AppDatabase.getInstance(context).contactDao().updateFavoriteStatus(contact.id, contact.isFavorite);
            notifyItemChanged(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, phoneTextView;
        ImageButton favoriteButton;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.txtContactName);
            phoneTextView = itemView.findViewById(R.id.txtContactPhone);
            favoriteButton = itemView.findViewById(R.id.btnFavorite);
        }
    }
}
