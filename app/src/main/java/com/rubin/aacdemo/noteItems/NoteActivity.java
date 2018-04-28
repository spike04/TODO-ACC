package com.rubin.aacdemo.noteItems;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.rubin.aacdemo.R;
import com.rubin.aacdemo.database.NoteModel;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity implements View.OnLongClickListener {

    private NoteListViewModel viewModel;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private FloatingActionButton note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Note List");

        note = findViewById(R.id.fab);
        note.setOnClickListener(v -> startActivity(new Intent(NoteActivity.this, AddNoteActivity.class)));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoteAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);
        viewModel.getNoteList().observe(this, noteModels -> {
            adapter.addNote(noteModels);
        });
    }

    @Override
    public boolean onLongClick(View v) {
        NoteModel noteModel = (NoteModel) v.getTag();
        viewModel.deleteNote(noteModel);

        Toast.makeText(this, noteModel.getNoteTitle() + " Just Deleted", Toast.LENGTH_SHORT).show();

        return true;
    }
}
