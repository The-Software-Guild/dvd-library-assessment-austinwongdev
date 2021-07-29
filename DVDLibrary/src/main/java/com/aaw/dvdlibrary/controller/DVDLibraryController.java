/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Jul 29, 2021
 * purpose: 
 */

package com.aaw.dvdlibrary.controller;

import com.aaw.dvdlibrary.dao.DVDLibraryDao;
import com.aaw.dvdlibrary.dao.DVDLibraryDaoException;
import com.aaw.dvdlibrary.ui.DVDLibraryView;

/**
 *
 * @author Austin Wong
 */
public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryDao dao;
    
    public DVDLibraryController(DVDLibraryView view, DVDLibraryDao dao){
        this.view = view;
        this.dao = dao;
    }
    
    public void run(){
        
        boolean keepGoing = true;
        try{
            do{
                int libraryMenuSelection = getLibraryMenuSelection();

                switch (libraryMenuSelection){
                    case 1:
                        listAllDVDs();
                        break;
                    case 2:
                        searchForDVD();
                        break;
                    case 3:
                        addDVD();
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        displayUnknownCommand();
                }            
            } while (keepGoing);

            printExitMessage();
        } catch (Exception ex){  // ***AAW: Replace with DVDLibraryDaoException
            displayErrorMessage(ex.getMessage());
        }
    }
    
    private int getLibraryMenuSelection(){
        return view.printLibraryMenuAndGetSelection();
    }
    
    private void printExitMessage(){
        view.printExitMessage();
    }
    
    private void listAllDVDs(){
    }
    
    private void searchForDVD(){
        /*
        Search for DVD
        
        if DVD is null, print msg and return to library menu
        
        else display DVD info and displayAndGetSelection from DVD menu
        */
    }
    
    private void addDVD(){
    }
    
    private void displayUnknownCommand(){
        view.displayUnknownCommandMessage();
    }
    
    private void displayErrorMessage(String errorMsg){
        view.displayErrorMessage(errorMsg);
    }
}
