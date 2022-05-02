package creed;

// import ddf.minim.analysis.*;
import ddf.minim.*;
// import ddf.minim.signals.*;
import ddf.minim.AudioBuffer;
// import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;

import processing.core.PApplet;
import processing.core.PImage;

public class Oscilloscope extends PApplet {
    Minim minim;
    // AudioInput in;
    AudioPlayer ap;
    AudioBuffer ab;

    float gain = 200;
    int tbase = 1024;
    float[] myBuffer;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    // for background Image
    PImage bg;

    public void settings() {
        fullScreen(P3D, SPAN);
        // size(800, 400, P3D);
        smooth();
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile(
                "[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3", 1024);
        ap.play();
        ab = ap.mix;

        myBuffer = new float[ap.bufferSize()];

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];

        // bg = loadImage("assassins creed edited.png");

    }

    float off = 0;

    // PImage creed = loadImage("assassins creed.jpg");

    public void draw() {
        colorMode(HSB);
        // colorMode(RGB);
        background(0);

        stroke(255);
        noFill();
        strokeWeight(4);

        float average = 0;
        float sum = 0;
        off += 1;

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            myBuffer[i] = lerp(myBuffer[i], ab.get(i), 0.05f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        
        float cx = width / 2;
        float cy = height / 2;

        float r = map(smoothedAmplitude, 0, 0.5f, 100, 2000);
        float r1 = map(smoothedAmplitude, 0, 0.5f, 150, 2500);

        float c = map(smoothedAmplitude, 0, 0.5f, 0, 255);
        stroke(c, 255, 255);
        circle(cx, cy, r);
        circle(cx,cy, r1);

        // ellipse(width / 2, height / 2, 240, 240);

        translate(0, height / 2);
        stroke(255);
        strokeWeight(2);

        // draw the output waveforms, so there's something to look at
        // first grab a stationary copy
        for (int i = 0; i < ap.bufferSize(); ++i) {
            myBuffer[i] = ap.left.get(i);
        }
        // find trigger point as largest +ve slope in first 1/4 of buffer
        int offset = 0;
        float maxdx = 0;
        for (int i = 0; i < myBuffer.length / 4; ++i) {
            float dx = myBuffer[i + 1] - myBuffer[i];
            if (dx > maxdx) {
                offset = i;
                maxdx = dx;
            }
        }
        // plot out that waveform
        int mylen = min(tbase, myBuffer.length - offset);
        for (int i = 0; i < mylen - 1; i++) {
            fill((frameCount + i / 2) % 360, 80, 100);

            float x1 = map(i, 0, tbase, 0, width);
            float x2 = map(i + 1, 0, tbase, 0, width);
            float x3 = map(i * 2 + 10, 0, tbase, 0, width);
            float x4 = map(i * 2 + 12, 0, tbase, 0, width);

            line(x1, 100 - myBuffer[i + offset] * gain, x2, 100 - myBuffer[i + 1 + offset] * gain);

            line(x3, 100 - myBuffer[i + offset] * gain, x4, 100 - myBuffer[i + 1 + offset] * gain);

        }
    }

    public void keyPressed() {
        switch (key) {
            case '+':
                gain = gain * 1.5f;
                break;
            case '-':
                gain = gain / 1.5f;
                break;
            case '<':
                tbase = tbase / 2;
                break;
            case '>':
                tbase = 2 * tbase;
                break;
        }
    }

    public void stop() {
        // always close Minim audio classes when you finish with them
        ap.close();
        minim.stop();

        super.stop();
    }
}