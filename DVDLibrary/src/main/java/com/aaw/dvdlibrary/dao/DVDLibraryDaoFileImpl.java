/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Jul 29, 2021
 * purpose: 
 */

package com.aaw.dvdlibrary.dao;

import com.aaw.dvdlibrary.dto.DVD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Austin Wong
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    private Map<String, DVD> dvdLibrary = new HashMap<>();
    
    @Override
    public void addDVD(DVD newDVD){
        dvdLibrary.put(newDVD.getTitle(), newDVD);
    }
    
    @Override
    public List<DVD> getAllDVDs(){
        return new ArrayList<DVD>(dvdLibrary.values());
    }
    
    @Override
    public DVD getDVD(String dvdTitle){
        return dvdLibrary.get(dvdTitle);
    }
    
    @Override
    public void updateDVDTitleInLibrary(String oldTitle){
        DVD dvd = getDVD(oldTitle);
        String newTitle = dvd.getTitle();
        dvdLibrary.put(newTitle, dvd);
        dvdLibrary.remove(oldTitle);
    }
    
    @Override
    public void deleteDVD(DVD dvd){
        dvdLibrary.remove(dvd.getTitle());
    }
}
