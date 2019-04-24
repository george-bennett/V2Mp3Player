/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newmusicapplication;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author George
 */
public class AlbumCollection {

    private String albumTitle;
    private String albumArtist;
    //private Arraylist declaration albums
    private ArrayList<Album> albums;

    //constructor including ArrayList albums
    public AlbumCollection() {

        albums = new ArrayList<>();
    }

    //this is a more compact way of reading in files compared with the bufferedReader method I used
    public void readFile(String fileName) {
        //try catch block for error exception handling of filelocation ("albums.txt")
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            Album album = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.contains(" - ")) {//means this is an album as the album does not contain a " - " as signalled by the !, albums are then added to the album collection
                    album = new Album(line);
                    albums.add(album);
                    // Collections.sort(albums);
                } else { // there everything else is a track and tracks are then added to an album
                    Track track = new Track(line);
                    album.addTrack(track);
                }
            }
            //catches exceptions such as Filenotfound   
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AlbumCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//a sort method - collections.sort will sort albums alphabetically. 
    public void sort() {

        Collections.sort(albums);

    }

    public void addAlbum(Album album) {
        //not sure if this will work as intended
        albums.add(album);
    }

    //this returns the longest track in the collection of albums - due to the addition of and changing Track track : tracks" to "Album album : albums". The additon of "Track track = album.getLongest track" 
    public Track getLongestTrack() {
        Track longestTrack = new Track(new Duration(), "");
        for (Album album : albums) {//albums is an ArrayList of Album objects
            Track track = album.getLongestTrack();
            if (track.compareTo(longestTrack) > 0) {
                longestTrack = track;

            }
        }
        return longestTrack;
    }

//    public Album getAlbumByTrack(String selectedSong) {
//        Album selectedAlbum = new Album("", "");
//        for (Album album : albums) {
//            Track track = album.getAlbumByTrackName(selectedSong);
//            if (track.equals(selectedSong));
//        }
//        return selectedAlbum;
//    }
// this is somewhat working, although 00:03:32 is not as intended. there are durations which are smaller.
    public Track getShortestTrack() {

        Track shortestTrack = new Track(new Duration(), "");
        for (Album album : albums) {
            Track track = album.getShortestTrack();
            if (track.compareTo(shortestTrack) > 0) {
                shortestTrack = track;
            } else if (track.compareTo(shortestTrack) <= 0) {
                shortestTrack = track;
            }

        }
        return shortestTrack;
    }

    // a method that calculates the album which containts the most tracks by using a track counter method in track
    public Album getAlbumMostTracks() {

        Album mostTracksAlbum = new Album("", "");
        for (Album album : albums) {
            if (album.getTrackCount() > mostTracksAlbum.getTrackCount()) {
                mostTracksAlbum = album;
            }
        }
        return mostTracksAlbum;
    }

//a method that gets Albums by Header (Unsure as to how this works or its purpose, but returns nothing in AlbumCollection.) 
    public Album getAlbumByHeader(String header) {
        for (Album album : albums) {
            if (album.getHeader().equals(header)) {
                return album;
            }
        }
        return null;
    }

//a duration method that calculates durations together based on artist name eg every song made by Pink Floyd
    public Duration getDurationByArtist(String albumArtist) {

        Duration duration = new Duration();
        for (Album album : albums) {
            if (album.getAlbumArtist().equals(albumArtist)) {
                duration.add(album.getDuration());
            }

        }
        return duration;
    }

    public Duration getDurationByAlbumHeader(String header) {
        Duration duration = new Duration();
        for (Album album : albums) {
            if (album.getHeader().equals(header)) {
                duration.add(album.getDuration());
            }
        }
        return duration;
    }

    public Duration getDurationByAlbumTitle(String albumTitle) {
        Duration duration = new Duration();
        for (Album album : albums) {
            if (album.getAlbumTitle().equals(albumTitle)) {
                duration.add(album.getDuration());
            }

        }
        return duration;

    }

    public Album getAlbumByArtist(String albumArtist) {
        Album album = new Album("", "");

        if (album.getAlbumArtist().equals(albumArtist)) {

            return album;
        }

        return album;
    }

    public Album getAlbumByAlbumTitle(String albumTitle) {
        for (Album album : albums) {
            if (album.getAlbumTitle().equals(albumTitle)) {
                return album;
            }
        }
        return null;
    }

    public Album getAlbumBySongTitle(String song) {
        Album album = new Album("", "");
        if (album.getTrackName().equals(song)) {
            return album;
        }

        return album;
    }

    public List<String> getAlbumHeaderList() {
        List<String> albumList = new ArrayList();
        for (Album album : albums) {

            albumList.add(album.getHeader());
        }
        return albumList;
    }

//too string returning albums - using StringBuilder
    public String toString() {

        StringBuilder strBld = new StringBuilder();
        for (Album album : albums) {
            strBld.append(album + "\n");
        }
        return strBld.toString().trim(); //not sure the need for .trim();

    }

}
