package creed;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PImage;

public class Circle extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;
    AudioInput ai;
    PImage bg;
    int cellsize = 2; // Dimensions of each cell in the grid
    int cols, rows;   // Number of columns and rows in our system


    public void settings() {
        size(1280, 720, P3D);
        // fullScreen(P3D);
        smooth();
        // frameRate(24);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("song.mp3", 1024);
        ap.play();
        ab = ap.mix;
        
        // colorMode(HSB)
        // frameRate(15);

        bg = loadImage("image2.jpg");
        background(0);
        cols = width/cellsize;              // Calculate # of columns
        rows = height/cellsize;             // Calculate # of rows

    }

    int n = 0;
    float c1 = 51;
    float c2 = 204;
    float c3 = 255;

    // for white
    float w1 = 255;

    public void draw() {
        // Begin loop for columns
        for (int i = 0; i < cols; i++ ) {
            // Begin loop for rows
            for (int j = 0; j < rows; j++ ) {
            int x = i*cellsize + cellsize/2; // x position
            int y = j*cellsize + cellsize/2; // y position
            int loc = x + y*width;           // Pixel array location
            int c = bg.pixels[loc];       // Grab the color
            // Map brightness to a z position as a function of mouseX
            float z = map(brightness(bg.pixels[loc]), 0, 255, 0, mouseX/2);
            // Translate to the location, set fill and stroke, and draw the rect
            pushMatrix();
            translate(x, y, z); 
            fill(c);
            noStroke();
            rectMode(CENTER);
            rect(0, 0, cellsize, cellsize);
            popMatrix();
            }
        }
    }

    // mute out
    public void keyPressed() {
        if (key == 'm' || key == 'M') {
            if (ap.isMuted()) {
                ap.unmute();
            } else {
                ap.mute();
            }
        }
    }

    public void stop() {
        ap.close();
        minim.stop();
        super.stop();


    }
}
