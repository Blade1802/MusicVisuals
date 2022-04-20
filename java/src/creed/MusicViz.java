package creed;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import ddf.minim.analysis.*;
import ddf.minim.*;
import processing.core.PApplet;
import processing.core.PImage;

public class MusicViz extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioMetaData meta;
    BeatDetect beat;
    int r = 200;
    float rad = 0;
    PImage assassin;

    public void settings() {
        size(800, 600, P3D);
    }

    public void setup() {

        minim = new Minim(this);
        ap = minim.loadFile("heroplanet.mp3", 1024);
        meta = ap.getMetaData();
        beat = new BeatDetect();
        assassin = loadImage("circle_img.png");

        ap.loop();
        // player.play();
        background(0);
        noCursor();

    }

    public void draw() {
        float t = map(mouseX, 0, width, 0, 1);
        beat.detect(ap.mix);
        fill(26, 31, 24);
        // fill(0);

        noStroke();
        rect(0, 0, width, height);
        translate(width / 2, height / 2);
        noFill();
        // fill(-1, 10);
        fill(random(255), random(255), random(255));
        if (beat.isOnset())
            rad = (float) (rad * 0.9);
        else
            rad = 70;
            //draws incenter
            imageMode(CENTER);
        
        stroke(255);
        noFill();
        ellipse(0,0, 300, 300);
        image(assassin,0,0,4 * rad, 4 * rad);
        stroke(-1, 50);
        int bsize = ap.bufferSize();

        // for (int i = 0; i < bsize - 1; i += 5) {
        //     float x = (r) * cos(i * 2 * PI / bsize);
        //     float y = (r) * sin(i * 2 * PI / bsize);
        //     float x2 = (r + ap.left.get(i) * 100) * cos(i * 2 * PI / bsize);
        //     float y2 = (r + ap.left.get(i) * 100) * sin(i * 2 * PI / bsize);
        //     line(x, y, x2, y2);
        // }

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
        // if (flag)
        // showMeta();
    }

    public void showMeta() {
        int time = meta.length();
        textSize(50);
        textAlign(CENTER);
        text((int) (time / 1000 - millis() / 1000) / 60 + ":" + (time / 1000 - millis() / 1000) % 60, -7, 21);
    }

    boolean flag = false;

    public void mousePressed() {
        if (dist(mouseX, mouseY, width / 2, height / 2) < 150)
            flag = !flag;
    }

    //
    // public boolean sketchFullScreen() {
    //     return true;
    // }

    public void keyPressed() {
        if (key == ' ')
            exit();
        if (key == 's')
            saveFrame("###.jpeg");
    }
}
