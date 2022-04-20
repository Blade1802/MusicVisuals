package creed;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import ddf.minim.analysis.*;
import ddf.minim.*;
import processing.core.PApplet;

public class radiohead_visual extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    FFT fft;
    AudioInput in;
    float[] angle;
    float[] y, x;

    public void settings() {
        size(1024, 800, P3D);
    }

    public void setup() {

        minim = new Minim(this);
        in = minim.getLineIn(Minim.STEREO, 2048, (float) 192000.0);
        fft = new FFT(in.bufferSize(), in.sampleRate());
        y = new float[fft.specSize()];
        x = new float[fft.specSize()];
        angle = new float[fft.specSize()];
        frameRate(240);

        ap = minim.loadFile(
                "[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3", 1024);

        ap.play();
        ab = ap.mix;

    }

    public void draw() {
        background(0);
        fft.forward(in.mix);
        doubleAtomicSprocket();

    }

    private void doubleAtomicSprocket() {
        noStroke();
        pushMatrix();
        translate(width / 2, height / 2);
        for (int i = 0; i < fft.specSize(); i++) {
            y[i] = y[i] + fft.getBand(i) / 100;
            x[i] = x[i] + fft.getFreq(i) / 100;
            angle[i] = angle[i] + fft.getFreq(i) / 2000;
            rotateX(sin(angle[i] / 2));
            rotateY(cos(angle[i] / 2));
            // stroke(fft.getFreq(i)*2,0,fft.getBand(i)*2);
            fill(fft.getFreq(i) * 2, 0, fft.getBand(i) * 2);
            pushMatrix();
            translate((x[i] + 50) % width / 3, (y[i] + 50) % height / 3);
            box(fft.getBand(i) / 20 + fft.getFreq(i) / 15);
            popMatrix();
        }
        popMatrix();
        pushMatrix();
        translate(width / 2, height / 2, 0);

        for (int i = 0; i < fft.specSize(); i++) {
            y[i] = y[i] + fft.getBand(i) / 1000;
            x[i] = x[i] + fft.getFreq(i) / 1000;
            angle[i] = angle[i] + fft.getFreq(i) / 100000;
            rotateX(sin(angle[i] / 2));
            rotateY(cos(angle[i] / 2));
            // stroke(fft.getFreq(i)*2,0,fft.getBand(i)*2);
            fill(0, 255 - fft.getFreq(i) * 2, 255 - fft.getBand(i) * 2);
            pushMatrix();
            translate((x[i] + 250) % width, (y[i] + 250) % height);
            box(fft.getBand(i) / 20 + fft.getFreq(i) / 15);
            popMatrix();
        }
        popMatrix();

    }

    public void stop() {
        // always close Minim audio classes when you finish with them
        ap.close();
        minim.stop();

        super.stop();
    }
}
