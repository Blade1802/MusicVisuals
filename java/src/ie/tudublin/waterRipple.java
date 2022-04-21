package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import ddf.minim.BeatDetector;
import processing.core.PApplet;
import processing.sound.*;


public class waterRipple extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    BeatDetect beat;
    BeatDetector beat2;
    FFT fft;

    // arrays for water effect
    int cols = 200;
    int rows = 200;
    // float[][] current = new float[cols][rows];
    // float[][] previous = new float[cols][rows];

    float[][] current;
    float[][] previous;

    float dampening = (float) 0.93;

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        // for ripple effect
        // previous[100][100] = 255;
        minim = new Minim(this);
        ap = minim.loadFile("[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3", 1024);
        ap.play();
        ab = ap.mix;
        beat = new BeatDetect();

        cols = width;
        rows = height;

        current = new float[cols][rows];
        previous = new float[cols][rows];
    }

    public void mousePressed() {
        // previous[mouseX][mouseY] = 255;
        current[mouseX][mouseY] = 255;

    }

    public void draw() {
        beat.detect(ab);
        background(0);
        loadPixels();
        if(beat.isOnset())
        {
            int x = (int)random((float)cols);
            int y = (int)random((float)rows);
            current[x][y] = 255;
        }
        for (int i = 1; i < cols - 1; i++) 
        {
            for (int j = 1; j < rows - 1; j++) 
            {
                // 
                current[i][j] = (previous[i - 1][j] +
                        previous[i + 1][j] +
                        previous[i][j - 1] +
                        previous[i][j + 1]) / 2 -
                        current[i][j];

                current[i][j] = current[i][j] * dampening;  // Gradually decreases the color value by 0.93%
                int index = i + j * cols;
                pixels[index] = color(current[i][j] * 255);
            } // end inner for loop
        } // end outer for loop
        updatePixels();

        float[][] temp = previous;
        previous = current;
        current = temp;
    }

}