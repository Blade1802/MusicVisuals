package creed;

import ddf.minim.AudioBuffer;
// import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import ddf.minim.analysis.*;
import ddf.minim.*;
import processing.core.PApplet;

public class MusicViz extends PApplet {

    Minim minim;
    AudioPlayer ap;
    // AudioMetaData meta;
    BeatDetect beat;
    int r = 200;
    float rad = 0;
    AudioBuffer ab;

    float[] lerpedBuffer;
    float lerpedAverage = 0;

    public void settings() {
        // size(800, 600, P3D);
        fullScreen(P3D,SPAN);
    }

    public void setup() {

        minim = new Minim(this);
        ap = minim.loadFile(
                "creed.mp3", 2048);
        // meta = ap.getMetaData();
        beat = new BeatDetect();

        ap.loop();
        
        background(0);
        noCursor();
        lerpedBuffer = new float[width];

    }

    public void draw() {
        float halfH = height / 2;
        // float t = map(mouseX, 0, width, 0, 1);
        beat.detect(ap.mix);
        fill(26, 31, 24);
        // fill(0);

        noStroke();
        rect(0, 0, width, height);
        translate(width / 2, height / 2);
        noFill();
        // fill(-1, 10);
        fill(255, 255, 255);
        if (beat.isOnset())
            rad = (float) (rad * 0.9);
        else
            rad = 70; 

        // ellipse(0, 0, 2 * rad, 2 * rad);
        stroke(-1, 50);
        int bsize = ap.bufferSize();

        for (int i = 0; i < bsize - 1; i += 5) {
            float x = (r) * cos(i * 2 * PI / bsize);
            float y = (r) * sin(i * 2 * PI / bsize);
            float x2 = (r + ap.left.get(i) * 100) * cos(i * 2 * PI / bsize);
            float y2 = (r + ap.left.get(i) * 100) * sin(i * 2 * PI / bsize);
            line(x, y, x2, y2);

        }

        beginShape();
        noFill();
        stroke(-1, 50);
        for (int i = 0; i < bsize; i += 30) {
            float x2 = (r + ap.left.get(i) * 100) * cos(i * 2 * PI / bsize);
            float y2 = (r + ap.left.get(i) * 100) * sin(i * 2 * PI / bsize);
            
            vertex(x2, y2);
            pushStyle();
            stroke(-1);
            strokeWeight(2);
            point(x2, y2);
            popStyle();
        }
        endShape();
    }



}