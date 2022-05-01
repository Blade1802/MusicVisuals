package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class circle2 extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    // circle particle effect
    float n = 0;

    int c1 = 51;
    int c2 = 204;
    int c3 = 255;

    public void settings() {
        // size(1024, 800, P3D);
        fullScreen(P3D, SPAN);
        smooth();
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3", 2048);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
    }

    public void draw() {
        fill(0, 20);
        noStroke();
        rect(0, 0, width, height);
        translate(width / 2, height / 2);

        for (int i = 0; i < ap.bufferSize() - 1; i++) {

            float leftLevel = ap.left.level() * 20;
            ellipse(i, i, leftLevel, leftLevel);
            rotateZ((float) (n * - PI / 3 * 0.05));
            
            fill(c1++%256,c2++%256,c3++%256);
            
        }

        n += 0.008;

    }
}
