package com.rubin.aacdemo.noteItems;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.rubin.aacdemo.database.NoteDatabase;
import com.rubin.aacdemo.database.NoteModel;

public class AddNoteViewModel extends AndroidViewModel {

    private NoteDatabase noteDatabase;

    public AddNoteViewModel(@NonNull Application application) {
        super(application);

        noteDatabase = NoteDatabase.getDatabase(this.getApplication());
    }

    public void addNote(NoteModel noteModel) {
        new addAsyncTask(noteDatabase).execute(noteModel);
    }

    private class addAsyncTask extends AsyncTask<NoteModel, Void, Void> {

        private NoteDatabase noteDatabase;

        public addAsyncTask(NoteDatabase noteDatabase) {
            this.noteDatabase = noteDatabase;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteDatabase.noteItemAndNotesModel().insertNote(noteModels[0]);
            return null;
        }
    }
}
