package newmusicapplication;

/**
 * A sample java program for playing mp3 file Description: It uses JFilechooser
 * to allow user to choose an MP3 file from disk and then creates a thread to
 * play the chosen mp3 file in background.
 *
 * @author wjw
 *
 * modified from the original source code made by Kevin Wayne source:
 * http://introcs.cs.princeton.edu/java/faq/mp3/mp3.html
 *
 * Note: 1. Use it in Netbeans project: (1) add this java class file into the
 * package in your project (2) you also need to add jLayer.jar, available from
 * MA23's blackboard, onto the Libraries of your Java Project.
 *
 *
 * 2. If you want to run it standalone, use command below in DOS (jLayer.jar
 * should be in the same folder as this java program.) To compile: javac
 * -classpath .;jLayer.jar MP3Player0.java To run: java -classpath .;jLayer.jar
 * MP3Player0
 *
 * Hint: you can use the methods: play() and close() in this java class by (1)
 * adding it onto your package (2) creating instance of it and (3) then use the
 * instance to access its methods
 *
 * date: 22/12/2014 version: v0
 *
 *
 */
import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Slider;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MP3Player {

    private String filename;
    private Player player;
    private int songFinished;
    //for multiple methods 
    FileInputStream fis;
    BufferedInputStream bis;
    //these are for the pause function 
    public long pauseLocation;
    public long songTotalLength;

    public String mp3FileLocation;

    // a default constructor
    public MP3Player() {
    }

    // Constructor takes a given file name 
    public MP3Player(String filename) {
        this.filename = filename;
    }
//stops current song playback

    public void close() {
        //this represents songs duration to be set to the begginning  
        pauseLocation = 0;
        songTotalLength = 0;
        try {
            if (player != null) {
                player.close();

            }
        } catch (Exception e) {

        }
    }

//pauses the currently playing song, so that it can be resumed
    public void pause() {
        if (player != null) {

            try {
                pauseLocation = fis.available();
                player.close();
            } catch (IOException ex) {

            }
        }
    }
//play or resume fuction

    public void play(String mp3filename) {
        try {
            fis = new FileInputStream(mp3filename);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);

            songTotalLength = fis.available();

            mp3FileLocation = mp3filename + " ";

        } catch (IOException | JavaLayerException e) {
            System.out.println("Problem in playing: " + mp3filename);
            System.out.println(e);
        }

        // creata a thread to play music in background
        new Thread() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    System.out.println(e);
                }
            }
        }.start();
    }

    public void resume() {
        try {
            fis = new FileInputStream(mp3FileLocation);
            bis = new BufferedInputStream(fis);

            player = new Player(bis);

            fis.skip(songTotalLength - pauseLocation);

        } catch (IOException | JavaLayerException e) {
            System.out.println("Problem in playing: " + mp3FileLocation);
            System.out.println(e);
        }

        // creata a thread to play music in background
        new Thread() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }.start();
    }



    // test MP3Player
    public static void main(String[] args) {
        //String filename = args[0];
        String filename = "NamesOfTheFiles";
        // String filepath ="C:\\awork\\teaching\\ma23\\lab6"; 
        String fileType;

        // use JFileChooser to open an MP3 file
        try {
            //use a preset file path 
            //JFileChooser openFC = new JFileChooser(filepath);

            JFileChooser openFC = new JFileChooser();
            Component c1 = null;
            openFC.showOpenDialog(c1);
            File mp3file = openFC.getSelectedFile();
            filename = mp3file.getAbsolutePath();

            //check the file format 
            //fileType= openFC.getTypeDescription(mp3file);
            //System.out.print("file type= "+ fileType);
        } catch (Exception e) {
        }

        //MP3Player0 mp3 = new MP3Player(filename); 
        // creat an instance of MP3Player using default constructor
        MP3Player mp3 = new MP3Player();
        mp3.play(filename);
        System.out.println("\n Playing mp3 file: " + filename);
        // the programn will stop when the track ends. 
        //mp3.close();
    }
}
