/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newmusicapplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author George
 */
public class Playlist {
//declared variables of type ArrayList and AlbumCollection

    private ArrayList<PlaylistTrack> tracks;
    private AlbumCollection albumCollection;
//a playlist constructor with an albumCollection arguement

    public Playlist(AlbumCollection albumCollection) {

        this.albumCollection = albumCollection;
        tracks = new ArrayList<>();

    }
//a file reading method for PlaylistFile , contains various methods

    public void readFile(String fileName) {

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.replace(")", "");
                String[] units = line.split(" \\(");
                String trackTitle = units[0];
                Album album = albumCollection.getAlbumByHeader(units[1]);
                Duration dur = album.getDurationByTrackTitle(trackTitle);
                PlaylistTrack playlistTrack = new PlaylistTrack(dur, trackTitle, album);
                tracks.add(playlistTrack);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Playlist.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

//a add method to add tracks to a playlist
    public void add(PlaylistTrack playlistTrack) {

        tracks.add(playlistTrack);
    }
// a duration method to calculate the total duration of all tracks within the playlist

    public Duration getDur() {

        Duration dur = new Duration();
        for (PlaylistTrack track : tracks) {

            dur.add(track.getDur());
        }
        return dur;
    }

    
    //creates and returns list of strings which match the format of playlist tracks
    public List<String> getPlaylistTracks() {
        List<String> playlistTracks = new ArrayList();
        for (PlaylistTrack tracks : tracks) {
            playlistTracks.add(tracks.toString());
        }
        return playlistTracks;
    }

//a toString method with a enhanced for loop
    @Override
    public String toString() {

        StringBuilder strBld = new StringBuilder();
        for (PlaylistTrack track : tracks) {
            strBld.append(track + "\n");

        }
        return strBld.toString().trim();
    }

}
