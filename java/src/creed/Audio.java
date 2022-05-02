package creed;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
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
                ap.rewind();
                ap.play();
            }
        }
    }

    public void settings() {
        // size(1024, 500);
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        minim = new Minim(this);
        
        ap = minim.loadFile("creed.mp3", 2048);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[2048];
    }

    float off = 0;

    public void draw() {
        
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

        float cx = width / 2;
        float cy = height / 2;

        switch (mode) {
            case 0: {
                background(0);
                strokeWeight(2);
                for (int i = 0; i < ab.size(); i += 5) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    float r = map(i, 0, 0.5f, 100, 2000);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
                    line(0, i, lerpedBuffer[i] * halfH * 4, i);
                    line(width, i, width - (lerpedBuffer[i] * halfH * 4), i);
                    line(i, 0, i, lerpedBuffer[i] * halfH * 4);
                    line(i, height, i, height - (lerpedBuffer[i] * halfH * 4));
                    // circle(cx, cy, r);
                    circle(i, halfH + f, 5);
                    circle(i, halfH - f, 5);
                }
                break;
            }
            case 1: {
                background(0);
                strokeWeight(2);
                for (int i = 0; i < ab.size(); i += 10) {

                    float c = map(i, 0, ab.size(), 0, 255);

                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
                    line(0, i, lerpedBuffer[i] * halfH * 4, i);
                    line(width, i, width - (lerpedBuffer[i] * halfH * 4), i);
                    line(i, 0, i, lerpedBuffer[i] * halfH * 4);
                    line(i, height, i, height - (lerpedBuffer[i] * halfH * 4));
                    // circle(cx, cy, r);
                    // circle(i, halfH + f, 5);
                    // circle(i, halfH - f, 5);
                }
                break;

            }

        }

    }
}