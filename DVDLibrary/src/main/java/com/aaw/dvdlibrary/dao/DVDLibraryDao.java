/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Jul 29, 2021
 * purpose: 
 */

package com.aaw.dvdlibrary.dao;

import com.aaw.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author Austin Wong
 */
public interface DVDLibraryDao {

    void addDVD(DVD newDVD);

    DVD getDVD(String dvdTitle);
    
    void updateDVDTitleInLibrary(String oldTitle);
    
    void deleteDVD(DVD dvd);
    
    List<DVD> getAllDVDs();

    void loadLibrary() throws DVDLibraryDaoException;
    
    void writeLibrary() throws DVDLibraryDaoException;
}
