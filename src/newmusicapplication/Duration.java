/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newmusicapplication;

import java.text.DecimalFormat;

/**
 *
 * @author George
 */
//much of this code is based/ or is code from the C/W 1 demonstration conducted by steve, in this class and the others used in C/W
public class Duration implements Comparable<Duration> {

    //some declared variables - hours,minutes,seconds
    private int hours;
    private int minutes;
    private int seconds;
    //formatting duration with two decimal places
    private static DecimalFormat twoDecimal = new DecimalFormat("00");

    //a duration constructor with declared variables set to values of 0 (not null)
    public Duration() {
        hours = 0;
        minutes = 0;
        seconds = 0;

    }

    //another duration constructor with hours,minutes and seconds and a new method called normalise which sets our durations to a suitable format 
    public Duration(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        normalise();
    }

    // duration constructor with a string using split to define hours,minutes and seconds as spilt by : eg 01:02:33 is 1hour 2minutes and 33 seconds again with normalise
    public Duration(String hhmmss) {
        String[] units = hhmmss.split(":");
        hours = Integer.parseInt(units[0]);
        minutes = Integer.parseInt(units[1]);
        seconds = Integer.parseInt(units[2]);
        normalise();
    }

    //compareTo method to compare two different durations together which uses a duration as convertedToSeconds
    @Override
    public int compareTo(Duration anotherDuration) {
        return Integer.compare(this.convertToSeconds(), anotherDuration.convertToSeconds());
    }

    //converts all units to seconds eg 1 hour = 3600 seconds
    public int convertToSeconds() {

        return hours * 3600 + minutes * 60 + seconds;
    }

    //getter method for hours
    public int getHours() {
        return hours;
    }

    //getter method for minutes
    public int getMinutes() {
        return minutes;
    }

    //getter method for seconds
    public int getSeconds() {
        return seconds;
    }

    // a new method which sorts durations so that hours are always made of up to 3600 seconds, minutes are always up to 59, seconds are 59, using a new variable "totalSeconds" 
    private void normalise() {
        int totalSeconds = convertToSeconds();
        hours = totalSeconds / 3600;
        minutes = (totalSeconds % 3600) / 60;
        seconds = totalSeconds % 60;
    }

    //adds multiple durations together again with normalisation
    public void add(Duration anotherDuration) {

        this.hours += anotherDuration.hours;
        this.minutes += anotherDuration.minutes;
        this.seconds += anotherDuration.seconds;
        normalise();
    }

    //sends the duration to a toString method with the decimalformat
    public String toString() {

        return twoDecimal.format(hours) + ":" + twoDecimal.format(minutes) + ":" + twoDecimal.format(seconds);

    }

    //test harness
    public static void main(String[] args) {

    }

}
