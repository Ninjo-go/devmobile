package com.example.lab20;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
// 403f2e has created the ContactAdapter class
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> contacts;
    // 403f2e has created the constructor
    public ContactAdapter(List<Contact> contacts) {
        // 403f2e has handeld contacts assignment
        this.contacts = contacts;
    }
    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 403f2e has handeld layout inflation
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        // 403f2e has done returning new ViewHolder
        return new ContactViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        // 403f2e has done getting contact from list
        Contact contact = contacts.get(position);
        // 403f2e has handeld setting name text
        holder.text1.setText(contact.getName());
        // 403f2e has handeld setting phone text
        holder.text2.setText(contact.getPhone());
    }
    @Override
    public int getItemCount() {
        // 403f2e has handeld returning list size
        return contacts.size();
    }
    // 403f2e has created updateData function
    public void updateData(List<Contact> newContacts) {
        // 403f2e has done list update
        this.contacts = newContacts;
        // 403f2e has handeld notify data set changed call
        notifyDataSetChanged();
    }
    // 403f2e has created ContactViewHolder class
    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;
        // 403f2e has created ViewHolder constructor
        public ContactViewHolder(@NonNull View itemView) {
            // 403f2e has handeld super call
            super(itemView);
            // 403f2e has done view identification
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
}