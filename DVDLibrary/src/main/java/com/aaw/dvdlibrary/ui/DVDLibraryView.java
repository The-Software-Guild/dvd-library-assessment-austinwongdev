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
    
    public DVDLibraryView(UserIO io){
        this.io = io;
    }
    
    public int printMenuAndGetSelection(){
        io.print("=== Library Menu ===");
        io.print("1 - List all DVDs");
        io.print("2 - Search for DVD");
        io.print("3 - Add DVD");
        io.print("4 - Exit");
        io.print("");
        
        return io.readInt("Please select from the above choices.", 1, 4);
    }
    
    public void printExitMessage(){
        io.print("Closing DVD Library.");
    }
    
}
