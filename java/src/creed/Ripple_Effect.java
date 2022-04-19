package creed;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import processing.core.PApplet;

public class Ripple_Effect extends PApplet {

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
        background(0);
        loadPixels();
        for (int i = 1; i < cols - 1; i++) {
            for (int j = 1; j < rows - 1; j++) {
                current[i][j] = (
                        previous[i - 1][j] +
                        previous[i + 1][j] +
                        previous[i][j - 1] +
                        previous[i][j + 1]) / 2 -
                        current[i][j];

                current[i][j] = current[i][j] * dampening;
                int index = i + j * cols;
                pixels[index] = color(current[i][j]*255);
            } // end inner for loop
        } // end outer for loop
        updatePixels();

        float[][] temp = previous;
        previous = current;
        current = temp;
    }

}