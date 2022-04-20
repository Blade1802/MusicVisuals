package creed;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class LuYu_edit extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;
    AudioInput ai;

    public void settings() {
        size(1024, 800, P3D);
        // fullScreen(P3D);
        smooth();
        // frameRate(24);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile(
                "[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3", 1024);
        ap.play();
        ab = ap.mix;
        // colorMode(HSB)
    }

    int n = 0;

    public void draw() {
        background(10, 180, 155);
        translate(width / 2, height / 2);
        for (int i = 0; i < ap.bufferSize() - 1; i++) {
            rotateX((float) (n * -PI / 3 * 0.05));
            // rotateY(n*-PI/3*0.05);
            fill(255, 150);
            float leftLevel = ap.left.level() * 100;
            ellipse(i, i, leftLevel, leftLevel);
            rotateZ((float) (n * -PI / 3 * 0.05));
            fill(0, 150);
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
