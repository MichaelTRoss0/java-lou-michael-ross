/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.dvdlibrary.dao;

import com.mtross.dvdlibrary.dto.DVD;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mike
 */
public interface DVDLibraryDao {
	
	/**
     * Adds the given DVD to the library and associates it with the given 
     * title. If there is already a DVD associated with the given 
     * title it will return that DVD object, otherwise it will 
     * return null.
     * 
     * @param title title with which DVD is to be associated
	 * @param dvd DVD to be added to the library
     * @return the DVD object previously associated with the given  
     * title if it exists, null otherwise
	 * @throws DVDLibraryDaoException
     */
	DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;
	
	/**
     * Returns a String array containing the titles of all 
     * DVDs in the library.
     * 
     * @return String array containing the titles of all the DVDs 
     * in the library
	 * @throws DVDLibraryDaoException
     */
	List<DVD> getAllDVDs() throws DVDLibraryDaoException;
	
	/**
     * Returns the DVD object associated with the given title.
     * Returns null if no such DVD exists
     * 
     * @param title title of the DVD to retrieve
     * @return the DVD object associated with the given title,  
     * null if no such DVD exists
	 * @throws DVDLibraryDaoException
     */
	DVD getDVD(String title) throws DVDLibraryDaoException;
	
	/**
     * Removes from the library the DVD associated with the given title. 
     * Returns the DVD object that is being removed or null if 
     * there is no DVD associated with the given title
     * 
     * @param title title of DVD to be removed
     * @return DVD object that was removed or null if no DVD 
     * was associated with the given title
	 * @throws DVDLibraryDaoException
     */
	DVD removeDVD(String title) throws DVDLibraryDaoException;
	
	/**
	 * Sets the release date of a DVD object to the selected String.
	 * 
	 * @param title the title of the DVD to have its release date edited
	 * @param newReleaseDate the new release date
	 * @return the String object previously associated with the
	 * given DVD parameter
	 * @throws DVDLibraryDaoException 
	 */
	String editReleaseDate(String title, String newReleaseDate) throws DVDLibraryDaoException;
	
	/**
	 * Sets the rating of a DVD object to the selected String.
	 * 
	 * @param title the title of the DVD to have its rating edited
	 * @param newRating
	 * @return the String object previously associated with the
	 * given DVD parameter
	 * @throws DVDLibraryDaoException 
	 */
	String editRating(String title, String newRating) throws DVDLibraryDaoException;
	
	/**
	 * Sets the director of a DVD object to the selected String.
	 * 
	 * @param title the title of the DVD to have its director edited
	 * @param newDirector
	 * @return the String object previously associated with the
	 * given DVD parameter
	 * @throws DVDLibraryDaoException 
	 */
	String editDirector(String title, String newDirector) throws DVDLibraryDaoException;
	
	/**
	 * Sets the studio of a DVD object to the selected String.
	 * 
	 * @param title the title of the DVD to have its studio edited
	 * @param newStudio
	 * @return the String object previously associated with the
	 * given DVD parameter
	 * @throws DVDLibraryDaoException 
	 */
	String editStudio(String title, String newStudio) throws DVDLibraryDaoException;
	
	/**
	 * Sets the note of a DVD object to the selected String.
	 * 
	 * @param title the title of the DVD to have its note edited
	 * @param newNote
	 * @return the String object previously associated with the
	 * given DVD parameter
	 * @throws DVDLibraryDaoException 
	 */
	String editNote(String title, String newNote) throws DVDLibraryDaoException;
	
	/**
	 * Calls the loadLibrary() method to read the library data from
	 * the file into memory.
	 * 
	 * @return Map object which is the library that was just retrieved
	 * @throws DVDLibraryDaoException 
	 */
	Map<String, DVD> retrieveLibrary() throws DVDLibraryDaoException;
	
	/**
	 * Calls the writeLibrary() method to write the library data
	 * into the file from memory.
	 * 
	 * @return Map object which is the library as it was before
	 * writing it into memory
	 * @throws DVDLibraryDaoException 
	 */
	Map<String, DVD> saveLibrary() throws DVDLibraryDaoException;
	
}
