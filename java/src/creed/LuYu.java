package creed;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PImage;

public class LuYu extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;
    AudioInput ai;
    PImage bg;

    public void settings() {
        // size(1024, 800, P3D);
        fullScreen(P3D, SPAN);
        smooth();
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile(
                "creed.mp3", 2048);
        ap.play();
        ab = ap.mix;
        
    }

    int n = 0;
    float c1 = 51;
    float c2 = 204;
    float c3 = 255;

    // for white
    float w1 = 255;

    public void draw() {
        // background(10, 180, 155);
        background(0);
        // background(bg);
        // background(184, 134, 11);
        translate(width / 2, height / 2);
        for (int i = 0; i < ap.bufferSize() - 1; i++) {
            rotateX((float) (n * -PI / 4 * 0.05));
            // rotateY(n*-PI/3*0.05);
            // fill(255, 150);
            // fill(255, 105, 180);// hot pink
            fill(255, 20, 147);// dark pink
            // fill(random(w1++ % 255), random(w1++ % 255));

            float leftLevel = ap.left.level() * 100;
            ellipse(i, i, leftLevel, leftLevel);
            rotateZ((float) (n * -PI / 5 * 0.05)); //3 -> 1
            // fill(0, 150);
            // fill(255, 0, 69);
            // fill(34, 139, 34);// green
            fill(124, 252, 0);// lawn green
            // fill(255, 140, 0);

            float rightLevel = ap.right.level() * 100;
            rect(i, i, rightLevel, rightLevel);
        }
        n++;
    }

    // mute out
    public void keyPressed() {
        if (key == 'm' || key == 'M') {
            if (ap.isMuted()) {
                ap.unmute();
            } else {
                ap.mute();
            }
        }
    }

    public void stop() {
        ap.close();
        minim.stop();
        super.stop();
    }

}