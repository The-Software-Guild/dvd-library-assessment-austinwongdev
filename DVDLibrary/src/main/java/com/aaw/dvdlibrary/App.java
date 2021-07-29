/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Jul 29, 2021
 * purpose: 
 */

package com.aaw.dvdlibrary;

import com.aaw.dvdlibrary.controller.DVDLibraryController;
import com.aaw.dvdlibrary.dao.DVDLibraryDao;
import com.aaw.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.aaw.dvdlibrary.ui.DVDLibraryView;
import com.aaw.dvdlibrary.ui.UserIO;
import com.aaw.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author Austin Wong
 */
public class App {
    public static void main(String[] args){
        
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        
        DVDLibraryController myController = 
                new DVDLibraryController(myView, myDao);
        myController.run();
    }
}
