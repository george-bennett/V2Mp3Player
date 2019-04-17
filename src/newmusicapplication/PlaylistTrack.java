/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newmusicapplication;

import java.util.ArrayList;

/**
 *
 * @author George
 */
public class PlaylistTrack extends Track {
    //album object declaration
    Album album;
    Track track;

    //playlistTrack constructor can use all aspects of track objects due to the presence of super
    public PlaylistTrack(Duration dur, String trackTitle, Album album) {
        super(dur, trackTitle);
        this.album = album;
    }
    // to string method 
    public String toString(){
    
    return this.getTrackTitle() + " (" + album.getHeader()+ ")";
    }
    
    // an album method whichreturns albums 
    public Album getAlbum() {
        return album;
    }
    public PlaylistTrack getTracks() {
       return (PlaylistTrack) track;
    }
}
