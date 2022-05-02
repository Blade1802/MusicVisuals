package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class Mesh_Line extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;

    // for record player
    AudioBuffer ab;
    FFT fft;

    int x;              // used to make the circle spin
    int radius = 200;   // radius in pixels of the circle

    int mode = 0;

    float[] lerpedBuffer;
    // float[] lerpedBuffer2;
    float lerpedAverage = 0;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                // ap.rewind();
                ap.play();
            }
        } 
    }

    public void settings() {
        // size(1024, 800, P3D);
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        background(0);
        noCursor();

        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;
        ap = minim.loadFile(
                "[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3", 2048);
        ap.play();
        ab = ap.mix;

        // Initialize audio analyzer
        fft = new FFT(ap.bufferSize(), ap.sampleRate());

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[2048];
        x = 0;

    }

    float off = 0;

    public void draw() {
        // background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }

        average = sum / (float) ab.size();
        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        background(0);
        colorMode(HSB);

        strokeWeight(2);

        for (int i = 0; i < ab.size(); i += 10) {

            float c = map(i, 0, ab.size(), 0, 255);
            float f = lerpedBuffer[i] * halfH * 1.0f;

            stroke(c, 255, 255);

            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);

            // Bands around the sides of the screens
            line(0, i, lerpedBuffer[i] * halfH * 4, i);
            line(width, i, width - (lerpedBuffer[i] * halfH * 4), i);
            line(i, 0, i, lerpedBuffer[i] * halfH * 4);
            line(i, height, i, height - (lerpedBuffer[i] * halfH * 4));
            
            // Center line
            circle(i, halfH + f, 5);
            circle(i, halfH - f, 5);

        } // end for

    }

}