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
    /**
     * Loads the DVD library, displays the main menu for the DVD library,
     * and saves the DVD library.
     */
    public void run(){
        
        try{
            dao.loadLibrary();
        }
        catch (DVDLibraryDaoException ex){
            displayErrorMessage(ex.getMessage());
        }
        
        displayLibraryMenu();
        
        try{
            dao.writeLibrary();
            view.displaySaveSuccessMessage();
        }
        catch (DVDLibraryDaoException ex){
            displayErrorMessage(ex.getMessage());
        }
        
        displayExitMessage();
    }
    
    private void displayLibraryMenu(){
        boolean keepGoing = true;
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
    }
    
    private int getLibraryMenuSelection(){
        return view.printLibraryMenuAndGetSelection();
    }
    
    private void listAllDVDs(){
        view.displayAllDVDsBanner();
        view.displayAllDVDs(dao.getAllDVDs());
        view.pressEnterToContinue();
    }
    
    private void addDVD(){
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD);
        view.displayAddDVDSuccessMessage(newDVD);
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
            displayDVDMenu(dvd);
        }
    }
    
    /**
     * Displays user options for a single DVD.
     * @param dvd - Currently selected DVD object
     */
    private void displayDVDMenu(DVD dvd){
        view.displayDVDInfo(dvd);
        view.pressEnterToContinue();
        boolean keepGoing = true;
        do{
            int dvdMenuSelection = view.printDVDMenuAndGetSelection();

            switch (dvdMenuSelection){
                case 1:
                    displayEditDVDMenu(dvd);
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
    }
    
    /**
     * Displays user options for editing a single DVD.
     * @param dvd - DVD object to be edited.
     */
    private void displayEditDVDMenu(DVD dvd){
        boolean keepGoing = true;
        do{
            view.displayDVDInfo(dvd);
            int editDVDMenuSelection
                    = view.printEditDVDMenuAndGetSelection();
            switch (editDVDMenuSelection){
                case 1:
                    editTitle(dvd);
                    break;
                case 2:
                    editReleaseDate(dvd);
                    break;
                case 3:
                    editMPAARating(dvd);
                    break;
                case 4:
                    editDirectorName(dvd);
                    break;
                case 5:
                    editStudio(dvd);
                    break;
                case 6:
                    editUserNote(dvd);
                    break;
                case 7:
                    keepGoing = false;
                    break;
                default:
                    displayUnknownCommand();
            }
        } while (keepGoing);
    }
    
    private void editTitle(DVD dvd) {
        String oldTitle = dvd.getTitle();
        dvd.setTitle(view.promptDVDTitle());
        dao.updateDVDTitleInLibrary(oldTitle);
        view.displayEditDVDSuccessMessage();
    }
    
    private void editReleaseDate(DVD dvd){
        dvd.setReleaseDate(view.promptReleaseDate());
        view.displayEditDVDSuccessMessage();
    }
    
    private void editMPAARating(DVD dvd){
        dvd.setMPAARating(view.promptMPAARating());
        view.displayEditDVDSuccessMessage();
    }
    
    private void editDirectorName(DVD dvd){
        dvd.setDirectorName(view.promptDirectorName());
        view.displayEditDVDSuccessMessage();
    }
    
    private void editStudio(DVD dvd){
        dvd.setStudio(view.promptStudio());
        view.displayEditDVDSuccessMessage();
    }
    
    private void editUserNote(DVD dvd){
        dvd.setUserNote(view.promptUserNote());
        view.displayEditDVDSuccessMessage();
    }
    
    private boolean deleteDVD(DVD dvd){
        int deleteDVDMenuSelection
                = view.displayDeleteDVDMenuAndGetSelection(dvd);
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
    
    private void displayExitMessage(){
        view.printExitMessage();
    }
    
    private void displayUnknownCommand(){
        view.displayUnknownCommandMessage();
    }
    
    private void displayErrorMessage(String errorMsg){
        view.displayErrorMessage(errorMsg);
    }
}
