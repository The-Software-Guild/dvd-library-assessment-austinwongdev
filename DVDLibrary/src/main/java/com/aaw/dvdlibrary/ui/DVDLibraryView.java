/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Jul 29, 2021
 * purpose: 
 */

package com.aaw.dvdlibrary.ui;

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
