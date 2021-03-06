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
public interface UserIO {

    void print(String msg);
    
    void printWithBanner(String msg);
    
    String readString(String prompt);
    
    String readNonEmptyString(String prompt);
    
    int readInt(String prompt);
    
    int readInt(String prompt, int min, int max);
}
