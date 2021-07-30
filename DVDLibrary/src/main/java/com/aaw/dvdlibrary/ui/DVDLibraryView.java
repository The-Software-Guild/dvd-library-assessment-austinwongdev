/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Jul 29, 2021
 * purpose: 
 */

package com.aaw.dvdlibrary.ui;

import com.aaw.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author Austin Wong
 */
public class DVDLibraryView {

    private UserIO io;
    private String menuPrompt = "Please select from the above choices.";
    
    public DVDLibraryView(UserIO io){
        this.io = io;
    }
    
    // Menu Methods
    
    public int printLibraryMenuAndGetSelection(){
        io.print("=== Library Menu ===");
        io.print("1 - List all DVDs");
        io.print("2 - Search for DVD");
        io.print("3 - Add DVD");
        io.print("4 - Exit");
        io.print("");
        
        return io.readInt(menuPrompt, 1, 4);
    }
    
    public int printDVDMenuAndGetSelection(){
        io.print("=== DVD Menu ===");
        io.print("1 - Edit DVD Info");
        io.print("2 - Delete DVD");
        io.print("3 - Return");
        io.print("");
        
        return io.readInt(menuPrompt, 1, 3);
    }
    
    public int printEditDVDMenuAndGetSelection(){
        io.print("=== Edit DVD Info ===");
        io.print("1 - Title");
        io.print("2 - Release Date");
        io.print("3 - MPAA Rating");
        io.print("4 - Director's Name");
        io.print("5 - Studio");
        io.print("6 - Rating/Notes");
        io.print("7 - Return");
        io.print("");
        return io.readInt("Please select which field to edit.", 1, 7);
    }
    
    // Edit DVD Methods
    
    public String promptDVDTitle(){
        return io.readString("Please enter the Title");
    }
    
    public String promptReleaseDate(){
        return io.readString("Please enter the Release Date");
    }
    
    public String promptMPAARating(){
        return io.readString("Please enter the MPAA Rating");
    }
    
    public String promptDirectorName(){
        return io.readString("Please enter the Director's Name");
    }
    
    public String promptStudio(){
        return io.readString("Please enter the Studio");
    }
    
    public String promptUserNote(){
        return io.readString("Please enter your Rating/Notes for the DVD");
    }
    
    public void displayEditDVDSuccessMessage(){
        io.print("DVD Saved.\n");
    }
    
    // Add DVD Methods
    
    public void displayAddDVDBanner(){
        io.print("=== Add DVD ===");
    }
    
    public void displayAddDVDSuccessMessage(DVD newDVD){
        io.print(newDVD.getTitle() + " added.\n");
    }
    
    public DVD getNewDVDInfo(){      
        DVD newDVD = new DVD(promptDVDTitle());
        newDVD.setReleaseDate(promptReleaseDate());
        newDVD.setMPAARating(promptMPAARating());
        newDVD.setDirectorName(promptDirectorName());
        newDVD.setStudio(promptStudio());
        newDVD.setUserNote(promptUserNote());
        
        return newDVD;
    }
    
    // Delete DVD Methods
    
    public int displayDeleteDVDMenuAndGetSelection(DVD dvdToDelete){
        io.print("=== Delete DVD ===");
        io.print("Delete " + dvdToDelete.getTitle() + "?");
        io.print("1 - Yes");
        io.print("2 - No");
        return io.readInt(menuPrompt, 1, 2);
    }
    
    // List All DVDs Methods
    
    public void displayAllDVDsBanner(){
        io.print("=== All DVDs ===");
    }
    
    public void displayAllDVDs(List<DVD> allDVDs){
        for (DVD dvd : allDVDs){
            io.print(dvd.getTitle());
        }
        io.print("");
    }
    
    // Search DVD Methods
    
    public void displaySearchForDVDBanner(){
        io.print("=== DVD Search ===");
    }
    
    public void displayDVDNotFoundMessage(){
        io.print("DVD not found.\n");
    }
    
    // Display DVD Info Methods
    
    public void displayDVDInfo(DVD dvd){
        io.print("Title: " + dvd.getTitle());
        io.print("Release Date: " + dvd.getReleaseDate());
        io.print("MPAA Rating: " + dvd.getMPAARating());
        io.print("Director's Name: " + dvd.getDirectorName());
        io.print("Studio: " + dvd.getStudio());
        io.print("Rating/Notes: " + dvd.getUserNote());
        io.print("");
    }
    
    // Miscellaneous Methods
    
    public void displayUnknownCommandMessage(){
        io.print("Unknown command.");
    }
    
    public void pressEnterToContinue(){
        io.readString("Press Enter to continue.");
    }
    
    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public void printExitMessage(){
        io.print("Closing DVD Library.");
    }
    
}
