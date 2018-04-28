package com.rubin.aacdemo.noteItems;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.rubin.aacdemo.database.NoteDatabase;
import com.rubin.aacdemo.database.NoteModel;

import java.util.List;

public class NoteListViewModel extends AndroidViewModel {

    private final LiveData<List<NoteModel>> noteList;
    private NoteDatabase noteDatabase;

    public NoteListViewModel(@NonNull Application application) {
        super(application);

        noteDatabase = NoteDatabase.getDatabase(this.getApplication());
        noteList = noteDatabase.noteItemAndNotesModel().getAllNotes();
    }

    public LiveData<List<NoteModel>> getNoteList() {
        return noteList;
    }

    public void deleteNote(NoteModel noteModel) {
        new deleteAsyncTask(noteDatabase).execute(noteModel);
    }

    private class deleteAsyncTask extends AsyncTask<NoteModel, Void, Void> {

        private NoteDatabase noteDatabase;

        public deleteAsyncTask(NoteDatabase noteDatabase) {
            this.noteDatabase = noteDatabase;
        }

        @Override
        protected Void doInBackground(NoteModel... params) {
            noteDatabase.noteItemAndNotesModel().deleteNote(params[0]);
            return null;
        }
    }
}
