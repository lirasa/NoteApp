package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {

    EditText txtTitle, txtNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        txtTitle = findViewById(R.id.txtTitle);
        txtNote = findViewById(R.id.txtNote);
    }

    public void onAddNoteClick(View view) {

        String title = txtTitle.getText().toString().trim();
        String content = txtNote.getText().toString().trim();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Both title and content must be filled out.", Toast.LENGTH_SHORT).show();
            return; // Do not proceed with saving
        }

        String combinedNote = title + ": " + content;

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();

        Set<String> oldSet = sp.getStringSet("notes", new HashSet<String>());

        Set<String> newStrSet = new HashSet<>(oldSet);
        newStrSet.add(combinedNote);

        spEd.putStringSet("notes", newStrSet);
        spEd.apply();

        finish();
    }
}
