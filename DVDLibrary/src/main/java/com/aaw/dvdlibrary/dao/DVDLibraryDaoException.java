/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Jul 29, 2021
 * purpose: 
 */

package com.aaw.dvdlibrary.dao;

/**
 *
 * @author Austin Wong
 */
public class DVDLibraryDaoException extends Exception {

    public DVDLibraryDaoException(String msg){
        super(msg);
    }
    
    public DVDLibraryDaoException(String msg, Throwable cause){
        super(msg, cause);
    }
    
}
