/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Jul 29, 2021
 * purpose: 
 */

package com.aaw.dvdlibrary.controller;

import com.aaw.dvdlibrary.dao.DVDLibraryDao;
import com.aaw.dvdlibrary.dao.DVDLibraryDaoException;
import com.aaw.dvdlibrary.dto.DVD;
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
        view.displayAllDVDsBanner();
        view.displayAllDVDs(dao.getAllDVDs());
    }
    
    private void searchForDVD(){
        view.displaySearchForDVDBanner();
        String dvdTitle = view.getDVDTitleChoice();
        DVD dvd = dao.getDVD(dvdTitle);
        if (dvd == null){
            view.displayDVDNotFoundMessage();
        }
        else{
            view.displayDVDInfo(dvd);
            boolean keepGoing = true;
            try{
                do{
                    int dvdMenuSelection = view.printDVDMenuAndGetSelection();
                    
                    switch (dvdMenuSelection){
                        case 1:
                            // editDVDInfo(dvd);
                            break;
                            
                        case 2:
                            // deleteDVD(dvd);
                            break;
                            
                        case 3:
                            keepGoing = false;
                            break;
                            
                        default:
                            displayUnknownCommand();
                    }
                } while (keepGoing);
            } catch (Exception ex){ // ***AAW: Change to ClassRosterDaoException
                view.displayErrorMessage(ex.getMessage());
            }
        }
    }
    
    private void editDVDInfo(DVD dvd){
        
    }
    
    private void deleteDVD(DVD dvd){
        
    }
    
    private void addDVD(){
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD);
        view.displayAddDVDSuccessBanner(newDVD);
    }
    
    private void displayUnknownCommand(){
        view.displayUnknownCommandMessage();
    }
    
    private void displayErrorMessage(String errorMsg){
        view.displayErrorMessage(errorMsg);
    }
}
