package com.rubin.aacdemo.noteItems;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.rubin.aacdemo.R;
import com.rubin.aacdemo.database.NoteModel;

public class AddNoteActivity extends AppCompatActivity {

    private AddNoteViewModel viewModel;
    private EditText noteTitle, noteDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Note List");

        noteTitle = findViewById(R.id.noteTitle);
        noteDescription = findViewById(R.id.noteDescription);

        viewModel = ViewModelProviders.of(this).get(AddNoteViewModel.class);

        FloatingActionButton done = findViewById(R.id.fab_done);
        done.setOnClickListener(v -> {
            if (noteTitle.getText() == null || noteDescription.getText() == null) {
                Toast.makeText(AddNoteActivity.this, "Something is missing", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.addNote(new NoteModel(noteTitle.getText().toString().trim(), noteDescription.getText().toString().trim()));
                finish();
            }
        });
    }
}
