/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Jul 29, 2021
 * purpose: 
 */

package com.aaw.dvdlibrary.dao;

import com.aaw.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Austin Wong
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    private static final String LIBRARY_FILE = "library.txt";
    private static final String delimiter = "::";
    private Map<String, DVD> dvdLibrary = new HashMap<>();
    
    @Override
    public void addDVD(DVD newDVD) {
        dvdLibrary.put(newDVD.getTitle(), newDVD);
    }
    
    @Override
    public DVD getDVD(String dvdTitle) {
        return dvdLibrary.get(dvdTitle);
    }
    
    /**
     * Updates the dvdLibrary HashMap when the DVD title is changed.
     * @param oldTitle - The String value of the previous key to the DVD in 
     * dvdLibrary.
     */
    @Override
    public void updateDVDTitleInLibrary(String oldTitle){
        DVD dvd = getDVD(oldTitle);
        String newTitle = dvd.getTitle();
        if (!newTitle.equals(oldTitle)){
            dvdLibrary.put(newTitle, dvd);
            dvdLibrary.remove(oldTitle);
        }
    }
    
    @Override
    public void deleteDVD(DVD dvd) {
        dvdLibrary.remove(dvd.getTitle());
    }
    
    @Override
    public List<DVD> getAllDVDs() {
        return new ArrayList<DVD>(dvdLibrary.values());
    }
    
    private String marshallDVD(DVD dvd){
        String dvdAsText = dvd.getTitle() + delimiter;
        dvdAsText += dvd.getReleaseDate() + delimiter;
        dvdAsText += dvd.getMPAARating() + delimiter;
        dvdAsText += dvd.getDirectorName() + delimiter;
        dvdAsText += dvd.getStudio() + delimiter;
        dvdAsText += dvd.getUserNote() + delimiter;
        return dvdAsText;
    }
    
    private DVD unmarshallDVD(String dvdAsText){
        String[] dvdTokens = dvdAsText.split(delimiter);
        String dvdTitle = dvdTokens[0];
        DVD dvd = new DVD(dvdTitle);
        dvd.setReleaseDate(dvdTokens[1]);
        dvd.setMPAARating(dvdTokens[2]);
        dvd.setDirectorName(dvdTokens[3]);
        dvd.setStudio(dvdTokens[4]);
        dvd.setUserNote(dvdTokens[5]);
        
        return dvd;
    }
    
    @Override
    public void writeLibrary() throws DVDLibraryDaoException {
        
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        }
        catch (IOException ex){
            throw new DVDLibraryDaoException("Could not save DVD data.", ex);
        }
        
        for (DVD dvd: getAllDVDs()){
            String dvdAsText = marshallDVD(dvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }
    
    @Override
    public void loadLibrary() throws DVDLibraryDaoException {
        
        Scanner scanner;
        
        try{
            scanner = new Scanner(
                    new BufferedReader(new FileReader(LIBRARY_FILE)));
        }
        catch (FileNotFoundException ex){
            throw new DVDLibraryDaoException(
                    "Could not load DVD Library into memory.", ex);
        }
        
        String currentLine;
        DVD currentDVD;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            dvdLibrary.put(currentDVD.getTitle(), currentDVD);
        }
        
        scanner.close();
        
    }

}
