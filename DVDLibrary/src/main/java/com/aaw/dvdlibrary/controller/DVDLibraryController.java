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
        view.pressEnterToContinue();
    }
    
    private void searchForDVD(){
        view.displaySearchForDVDBanner();
        String dvdTitle = view.promptDVDTitle();
        DVD dvd = dao.getDVD(dvdTitle);
        if (dvd == null){
            view.displayDVDNotFoundMessage();
            view.pressEnterToContinue();
        }
        else{
            view.displayDVDInfo(dvd);
            view.pressEnterToContinue();
            boolean keepGoing = true;
            try{
                do{
                    int dvdMenuSelection = view.printDVDMenuAndGetSelection();
                    
                    switch (dvdMenuSelection){
                        case 1:
                            editDVDInfo(dvd);
                            break;
                            
                        case 2:
                            if (deleteDVD(dvd)){
                                keepGoing = false;
                            }
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
        boolean keepGoing = true;
        try{
            do{
                view.displayDVDInfo(dvd);
                int editDVDMenuSelection
                        = view.printEditDVDMenuAndGetSelection();
                switch (editDVDMenuSelection){
                    case 1:
                        String oldTitle = dvd.getTitle();
                        dvd.setTitle(view.promptDVDTitle());
                        dao.updateDVDTitleInLibrary(oldTitle);
                        view.displayEditDVDSuccessMessage();
                        break;
                    case 2:
                        dvd.setReleaseDate(view.promptReleaseDate());
                        view.displayEditDVDSuccessMessage();
                        break;
                    case 3:
                        dvd.setMPAARating(view.promptMPAARating());
                        view.displayEditDVDSuccessMessage();
                        break;
                    case 4:
                        dvd.setDirectorName(view.promptDirectorName());
                        view.displayEditDVDSuccessMessage();
                        break;
                    case 5:
                        dvd.setStudio(view.promptStudio());
                        view.displayEditDVDSuccessMessage();
                        break;
                    case 6:
                        dvd.setUserNote(view.promptUserNote());
                        view.displayEditDVDSuccessMessage();
                        break;
                    case 7:
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
    
    private boolean deleteDVD(DVD dvd){
        int deleteDVDMenuSelection
                = view.displayDeleteDVDMenuAndGetSelection(dvd);
        // ***AAW: Why don't you need try/catch and do/while statements here?
        switch (deleteDVDMenuSelection){
            case 1:
                dao.deleteDVD(dvd);
                view.displayDeleteDVDSuccessMessage(dvd);
                return true;
            case 2:
                return false;
            default:
                displayUnknownCommand();
        }
        return false;
    }
    
    private void addDVD(){
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD);
        view.displayAddDVDSuccessMessage(newDVD);
        view.pressEnterToContinue();
    }
    
    private void displayUnknownCommand(){
        view.displayUnknownCommandMessage();
    }
    
    private void displayErrorMessage(String errorMsg){
        view.displayErrorMessage(errorMsg);
    }
}
