package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PImage;

public class LuYu_edit extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;
    AudioInput ai;
    PImage bg;

    public void settings() {
        // size(1024, 800, P3D);
        fullScreen(P3D, SPAN);
        smooth();
        // frameRate(24);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile(
                "[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3", 2048);
        ap.play();
        ab = ap.mix;

    }

    int n = 0;

    public void draw() {
        
        background(0);
        
        translate(width / 2, height / 2);

        for (int i = 0; i < ap.bufferSize() - 1; i++) {
            rotateX((float) (n * -PI / 4 * 0.05));
            
            fill(255, 20, 147); // dark pink

            float leftLevel = ap.left.level() * 100;
            ellipse(i, i, leftLevel, leftLevel);

            rotateZ((float) (n * -PI / 5 * 0.05)); //3 -> 1
           
            fill(124, 252, 0);  // lawn green

            float rightLevel = ap.right.level() * 100;
            rect(i, i, rightLevel, rightLevel);
        }
        n++;
    }

}