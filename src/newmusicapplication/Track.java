/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newmusicapplication;

/**
 *
 * @author George
 */
public class Track implements Comparable<Track> {

    //variables duration (from duration class) and trackTitle
    private Duration dur;
    private String trackTitle;
    private String albumArtist;
   

    //track constructor with duration object and string trackTitle
        public Track(Duration dur, String trackTitle) {
        this.dur = dur;
        this.trackTitle = trackTitle;

    }

    //another track constructor with a String array which spilts duration and tracktitle into units 0 (1) and 1 (2) and needs an arguement of string 
    public Track(String str) {

        String[] units = str.split(" - ");
        dur = new Duration(units[0]);
        trackTitle = units[1];

    }

    //getter method for duration
    public Duration getDur() {
        return dur;
    }

    //getter method for trackTitle
    public String getTrackTitle() {
        return trackTitle;
    }

    public String getTrack(){
    return dur + " - " + trackTitle;
    }
    

 
    //compareTo method to compare two different tracks durations
    @Override
    public int compareTo(Track anotherTrack) {

        return this.dur.compareTo(anotherTrack.dur);

    }

    //a too string method returning the duration and the trackTitle together with - in between
    public String toString() {

        return dur + " - " + trackTitle;
    }
    //test harness
    public static void main(String[] args) {

        Track t1 = new Track(new Duration("01:02:22"), "Test");
        System.out.println(t1);
        Track t2 = new Track(new Duration("00:22:22"), "Test2");
        System.out.println(t2);
        
        
    }

}
