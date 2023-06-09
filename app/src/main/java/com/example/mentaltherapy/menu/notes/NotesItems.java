package com.example.mentaltherapy.menu.notes;


public class NotesItems {
    String notesTitle;
    String notesDescription;

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public void setNotesDescription(String notesDescription) {
        this.notesDescription = notesDescription;
    }

    public NotesItems(String notesTitle, String notesDescription) {
        this.notesTitle = notesTitle;
        this.notesDescription = notesDescription;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public String getNotesDescription() {
        return notesDescription;
    }


}
