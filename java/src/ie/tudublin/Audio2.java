package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

/*

The infinite number of waves make up the mind, and all minds are made up of these waves which then interact with one another to form reality via Fourier transformations
Science is not supposed to give meaning to ones life or the reason behind their existence; science only explains the testable and provable mechanisms that run the universe

*/

public class Audio2 extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    FFT fft;

    // arrays for water effect
    int cols = 200;
    int rows = 200;
    // float[][] current = new float[cols][rows];
    // float[][] previous = new float[cols][rows];

    float[][] current;
    float[][] previous;

    float dampening = (float) 0.93;

    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
	}

    public void settings()
    {
        size(1024, 1000, P3D);
        //fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; 
        ap = minim.loadFile("[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(RGB);

        fft = new FFT(1024, 44100);

        cols = width;
        rows = height;

        current = new float[cols][rows];
        previous = new float[cols][rows];

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }

    float off = 0;

    public void draw()
    {
        background(0);
        stroke(255);
        loadPixels();
        

        fft.window(FFT.HAMMING);
        fft.forward(ab);

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


        int maxIndex = 0;

        for(int i = 0 ; i < fft.specSize(); i ++)
        {
            if (fft.getBand(i) > fft.getBand(maxIndex))
            {
                maxIndex = i;
            }
        }

        // Fill out missing code!!

        // float freq = fft.indexToFreq(maxIndex);
        float freq = fft.indexToFreq(maxIndex) / 40;
        textSize(20);
        fill(255);
        text("Freq: " + freq, 100, 200);

        
        if(freq > 15)
        {
            int x = (int)random((float)cols);
            int y = (int)random((float)rows);
            current[x][y] = 255;
        }

       

    }        
}