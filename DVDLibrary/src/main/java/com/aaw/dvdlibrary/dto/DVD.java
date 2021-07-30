/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Jul 29, 2021
 * purpose: 
 */

package com.aaw.dvdlibrary.dto;

/**
 *
 * @author Austin Wong
 */
public class DVD {

    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userNote;
    
    public DVD(String title){
        this.title = title;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getReleaseDate(){
        return this.releaseDate;
    }
    
    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }
    
    public String getMPAARating(){
        return this.mpaaRating;
    }
    
    public void setMPAARating(String mpaaRating){
        this.mpaaRating = mpaaRating;
    }
    
    public String getDirectorName(){
        return this.directorName;
    }
    
    public void setDirectorName(String directorName){
        this.directorName = directorName;
    }
    
    public String getStudio(){
        return this.studio;
    }
    
    public void setStudio(String studio){
        this.studio = studio;
    }
    
    public String getUserNote(){
        return this.userNote;
    }
    
    public void setUserNote(String userNote){
        this.userNote = userNote;
    }

}
