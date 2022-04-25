package creed;

import ddf.minim.analysis.*;
import ddf.minim.*;
import ddf.minim.signals.*;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;

import processing.core.PApplet;

public class Oscilloscope extends PApplet {
    Minim minim;
    // AudioInput in;
    AudioPlayer ap;
    AudioBuffer ab;

    float gain = 200;
    int tbase = 1024;
    float[] myBuffer;

    public void settings() {
        // size(1024, 800, P3D);
        fullScreen(P3D);
        smooth();
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile(
                "[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3", 1024);
        ap.play();
        // in = minim.getLineIn(Minim.MONO, 2048);
        ap.play();
        ab = ap.mix;

        myBuffer = new float[ap.bufferSize()];
    }

    public void draw() {
        background(0);
        colorMode(HSB);
        translate(0, height / 2);
        stroke(255);
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

            line(x1, 100 - myBuffer[i + offset] * gain, x2, 100 - myBuffer[i + 1 + offset] * gain);

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