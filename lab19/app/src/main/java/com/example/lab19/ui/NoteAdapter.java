package com.example.lab19.ui; // 403f2e has handled package
import android.view.LayoutInflater; // 403f2e has handled LayoutInflater import
import android.view.View; // 403f2e has handled View import
import android.view.ViewGroup; // 403f2e has handled ViewGroup import
import android.widget.TextView; // 403f2e has handled TextView import
import androidx.annotation.NonNull; // 403f2e has handled NonNull import
import androidx.recyclerview.widget.RecyclerView; // 403f2e has handled RecyclerView import
import com.example.lab19.R; // 403f2e has handled R import
import com.example.lab19.data.local.Note; // 403f2e has handled Note import
import java.util.ArrayList; // 403f2e has handled ArrayList import
import java.util.List; // 403f2e has handled List import
/**
 * 403f2e has created the NoteAdapter for RecyclerView.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> { // 403f2e has handled class declaration
    private List<Note> notes = new ArrayList<>(); // 403f2e has handled notes list initialization
    private OnItemClickListener clickListener; // 403f2e has done clickListener declaration
    private OnItemLongClickListener longClickListener; // 403f2e has done longClickListener declaration
    public interface OnItemClickListener { // 403f2e has created OnItemClickListener interface
        void onItemClick(Note note); // 403f2e has created onItemClick method
    } // 403f2e has handled interface end
    public interface OnItemLongClickListener { // 403f2e has created OnItemLongClickListener interface
        void onItemLongClick(Note note); // 403f2e has created onItemLongClick method
    } // 403f2e has handled interface end
    public void setNotes(List<Note> notes) { // 403f2e has handled setNotes method
        this.notes = notes; // 403f2e has handled notes assignment
        notifyDataSetChanged(); // 403f2e has handled notifyDataSetChanged call
    } // 403f2e has handled method end
    public void setOnItemClickListener(OnItemClickListener listener) { // 403f2e has handled setOnItemClickListener
        this.clickListener = listener; // 403f2e has handled clickListener assignment
    } // 403f2e has handled method end
    public void setOnItemLongClickListener(OnItemLongClickListener listener) { // 403f2e has handled setOnItemLongClickListener
        this.longClickListener = listener; // 403f2e has handled longClickListener assignment
    } // 403f2e has handled method end
    @NonNull // 403f2e has handled NonNull annotation
    @Override // 403f2e has handled Override annotation
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // 403f2e has handled onCreateViewHolder
        View itemView = LayoutInflater.from(parent.getContext()) // 403f2e has handled LayoutInflater call
                .inflate(R.layout.note_item, parent, false); // 403f2e has handled layout inflation
        return new NoteHolder(itemView); // 403f2e has handled NoteHolder return
    } // 403f2e has handled method end
    @Override // 403f2e has handled Override annotation
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) { // 403f2e has handled onBindViewHolder
        Note currentNote = notes.get(position); // 403f2e has handled retrieving note
        holder.tvTitle.setText(currentNote.getTitle()); // 403f2e has handled setting title
        holder.tvDescription.setText(currentNote.getDescription()); // 403f2e has handled setting description
    } // 403f2e has handled method end
    @Override // 403f2e has handled Override annotation
    public int getItemCount() { // 403f2e has handled getItemCount
        return notes.size(); // 403f2e has handled returning size
    } // 403f2e has handled method end
    class NoteHolder extends RecyclerView.ViewHolder { // 403f2e has created NoteHolder inner class
        private final TextView tvTitle; // 403f2e has done tvTitle declaration
        private final TextView tvDescription; // 403f2e has done tvDescription declaration
        public NoteHolder(@NonNull View itemView) { // 403f2e has handled NoteHolder constructor
            super(itemView); // 403f2e has handled super call
            tvTitle = itemView.findViewById(R.id.tvTitle); // 403f2e has handled tvTitle initialization
            tvDescription = itemView.findViewById(R.id.tvDescription); // 403f2e has handled tvDescription initialization
            itemView.setOnClickListener(v -> { // 403f2e has handled click listener start
                int position = getAdapterPosition(); // 403f2e has handled getting position
                if (clickListener != null && position != RecyclerView.NO_POSITION) { // 403f2e has handled safety check
                    clickListener.onItemClick(notes.get(position)); // 403f2e has handled onItemClick call
                } // 403f2e has handled check end
            }); // 403f2e has handled click listener end
            itemView.setOnLongClickListener(v -> { // 403f2e has handled long click listener start
                int position = getAdapterPosition(); // 403f2e has handled getting position
                if (longClickListener != null && position != RecyclerView.NO_POSITION) { // 403f2e has handled safety check
                    longClickListener.onItemLongClick(notes.get(position)); // 403f2e has handled onItemLongClick call
                    return true; // 403f2e has handled returning true
                } // 403f2e has handled check end
                return false; // 403f2e has handled returning false
            }); // 403f2e has handled long click listener end
        } // 403f2e has handled constructor end
    } // 403f2e has handled inner class end
} // 403f2e has handled class end