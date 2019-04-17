/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newmusicapplication;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author George
 */
public class Album implements Comparable<Album> {

    //private variables declared albumTitle, albumArtist, and a ArrayList of tracks
    private String albumTitle;   
    private String albumArtist;
    private ArrayList<Track> tracks;
    private String trackTitle;

    //a constructor with albumTitle, AlbumArtist and the arraylist - although not passed int the argument
    public Album(String albumTitle, String albumArtist) {

        this.albumArtist = albumArtist;
        this.albumTitle = albumTitle;
        this.tracks = new ArrayList<>();

    }

    //a constructor with a string "line" which spilts the album details into albumArtist albumTitle and tracks.
    public Album(String line) {
        String[] units = line.split(" : ");
        this.albumArtist = units[0];
        this.albumTitle = units[1];
        this.tracks = new ArrayList<>();

    }

    //method to addtracks (track objects) to an album 
    public void addTrack(Track track) {

        tracks.add(track);

    }

    //getter method to return AlbumTitle
    public String getAlbumTitle() {
        return albumTitle;
    }

    //getter method to return AlbumArtist
    public String getAlbumArtist() {
        return albumArtist;
    }

    //a string method to return the "Header" which contains both albumArtist and albumTitle variables as a string
    public String getHeader() {
        return albumArtist + " : " + albumTitle;
    }

    public String getAlbumImageName() {
        return albumArtist + "_" + albumTitle;
    }

    public String getTrackForPlayer(String selectedSong) {
        return albumArtist + "-" + trackTitle;
    }

    //getter method to get number tracks in an album
    public int getTrackCount() {

        return tracks.size();

    }

    //getter method to get the total duration of the tracks in an album 
    public Duration getDuration() {

        Duration dur = new Duration();
        for (Track track : tracks) {//for as long as there are track objects in the album, add the durations of each together to return the duration of the album
            dur.add(track.getDur());

        }
        return dur;
    }

    // public Duration getDurationByTrackTitle (String trackTitle){
    //}
    //finding the longesttrack in an album
    public Track getLongestTrack() {
        Track longestTrack = new Track(new Duration(), "");
        for (Track track : tracks) {

            if (track.compareTo(longestTrack) > 0) {
                longestTrack = track;

            }
        }
        return longestTrack;
    }
// an attempt at returning the shortest track - doesnt return what I wanted

    public Track getShortestTrack() {

        Track shortestTrack = new Track(new Duration(), "");
        for (Track track : tracks) {

            if (track.compareTo(shortestTrack) > 0) {
                shortestTrack = track;
            } else if (track.compareTo(shortestTrack) < 0) {
                shortestTrack = track;
            }
        }
        return shortestTrack;
    }

    //toString method using a stringbuilder to return albumArtist and AlbumTitle together
    @Override
    public String toString() {

        StringBuilder strBld = new StringBuilder();
        strBld.append(albumArtist + " : " + albumTitle);
        for (Track track : tracks) {
            strBld.append("\n" + track);
        }
        return strBld.toString();
    }

//toString method using Stringbuilder to return albumArtist and Title with _ to match format of image.
    public String getImageName(String selectedAlbum) {
        StringBuilder strBld = new StringBuilder();
        {
            strBld.append(albumArtist + "_" + albumTitle);
        }
        return strBld.toString();
    }

    //compareTo method for comparing albums with one another
    @Override
    public int compareTo(Album anotherAlbum) {
        return (albumArtist + albumTitle).compareTo(anotherAlbum.albumArtist + anotherAlbum.albumTitle);
    }

    //a duration method which compares track durations against one another and calculates the highest one. Although the result is not returned in the album class
    public Duration getDurationByTrackTitle(String trackTitle) {

        for (Track track : tracks) {
            if (track.getTrackTitle().equals(trackTitle)) {
                return track.getDur();
            }
        }
        return null;
    }

//test harness
    public static void main(String[] args) {

    }

    public List<String> getTrackList() {
        List<String> trackList = new ArrayList();
        for (Track tracks : tracks) {
            trackList.add(tracks.getTrack());
        }
        return trackList;
    }

    public List<String> getTrackListNames() {
        List<String> trackList = new ArrayList();
        for (Track tracks : tracks) {
            trackList.add(tracks.getTrackTitle());
        }
        return trackList;
    }
}
