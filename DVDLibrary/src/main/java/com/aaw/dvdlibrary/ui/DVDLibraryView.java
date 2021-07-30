/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Jul 29, 2021
 * purpose: 
 */

package com.aaw.dvdlibrary.ui;

import com.aaw.dvdlibrary.dto.DVD;
import java.util.List;
import java.util.Map;

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
    
    public DVD getNewDVDInfo(){
        String title = io.readString("Please enter the Title");
        String releaseDate = io.readString("Please enter the Release Date");
        String mpaaRating = io.readString("Please enter the MPAA Rating");
        String directorName = io.readString("Please enter the Director's Name");
        String studio = io.readString("Please enter the Studio");
        String userNote = io.readString("Please enter your rating or notes for "
                + "the DVD");
        
        DVD newDVD = new DVD(title);
        newDVD.setReleaseDate(releaseDate);
        newDVD.setMPAARating(mpaaRating);
        newDVD.setDirectorName(directorName);
        newDVD.setStudio(studio);
        newDVD.setUserNote(userNote);
        
        return newDVD;
    }
    
    public void displayAddDVDBanner(){
        io.print("=== Add DVD ===");
    }
    
    public void displayAddDVDSuccessBanner(DVD newDVD){
        io.print(newDVD.getTitle() + " added.\n");
    }
    
    public void displayEditDVDInfoBanner(){
        io.print("=== Edit DVD Info ===");
    }
    
    public int displayDeleteDVDMenuAndGetSelection(DVD dvdToDelete){
        io.print("=== Delete DVD ===");
        io.print("Delete " + dvdToDelete.getTitle() + "?");
        io.print("1 - Yes");
        io.print("2 - No");
        return io.readInt(menuPrompt, 1, 2);
    }
    
    public void displayAllDVDsBanner(){
        io.print("=== All DVDs ===");
    }
    
    public void displayAllDVDs(List<DVD> allDVDs){
        for (DVD dvd : allDVDs){
            io.print(dvd.getTitle());
        }
        io.print("");
    }
    
    public void displayUnknownCommandMessage(){
        io.print("Unknown command.");
    }
    
    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public void printExitMessage(){
        io.print("Closing DVD Library.");
    }
    
}
