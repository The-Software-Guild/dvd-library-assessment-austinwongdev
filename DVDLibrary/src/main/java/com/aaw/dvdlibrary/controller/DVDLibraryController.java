/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Jul 29, 2021
 * purpose: 
 */

package com.aaw.dvdlibrary.controller;

import com.aaw.dvdlibrary.dao.DVDLibraryDao;
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
        
        int libraryMenuSelection = view.printMenuAndGetSelection();
        view.printExitMessage();
    }
}
