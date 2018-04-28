package com.rubin.aacdemo.noteItems;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rubin.aacdemo.R;
import com.rubin.aacdemo.database.NoteModel;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<NoteModel> noteList;
    private View.OnLongClickListener longClickListener;

    public NoteAdapter(List<NoteModel> noteList, View.OnLongClickListener longClickListener) {
        this.noteList = noteList;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NoteModel noteModel = noteList.get(position);

        holder.txtNoteTitle.setText(noteModel.getNoteTitle());
        holder.txtNoteDescription.setText(noteModel.getNoteDescription());

        holder.itemView.setTag(noteModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void addNote(List<NoteModel> noteList) {
        this.noteList.clear();
        this.noteList.addAll(noteList);
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView txtNoteTitle;
        TextView txtNoteDescription;

        public NoteViewHolder(View view) {
            super(view);

            txtNoteTitle = view.findViewById(R.id.txtNoteTitle);
            txtNoteDescription = view.findViewById(R.id.txtNoteDescription);
        }
    }
}
