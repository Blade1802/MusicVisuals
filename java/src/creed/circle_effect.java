package creed;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import processing.core.PApplet;

public class circle_effect extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    // circle particle effect
    float n4 = 0;
    float n6 = 0;

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
        // colorMode(HSB);

    }

    public void draw() {
        colorMode(RGB);

        fill(0, 50);
        noStroke();
        rect(0, 0, width, height);
        translate(width / 2, height / 2);

        for (int i = 0; i < ap.bufferSize() - 1; i++) {

            float angle1 = sin(i + n4) * 10;
            float angle2 = sin(i + n6) * 300;

            float x = sin(radians(i)) * (angle2 + 30);
            float y = cos(radians(i)) * (angle2 + 30);

            float x3 = sin(radians(i)) * (500 / angle1);
            float y3 = cos(radians(i)) * (500 / angle1);

            fill(255, 255, 0); // yellow color
            // fill(255, 69, 0);// orange color
            // fill(255, random(255), 255);

            ellipse(x, y, ap.left.get(i) * 10, ap.left.get(i) * 10);

            fill(255);// white color
            rect(x, y, ap.right.get(i) * 10, ap.left.get(i) * 10);//10 -> 20

            fill(255, 69, 0);// orange color
            // fill(124, 252, 0); //grass green color
            // fill(255, 0, 0);//red color code
            // fill(random(0), 255, random(255));// aqua blue color code
            rect(x, y, ap.right.get(i) * 10, ap.left.get(i) * 10);

            // fill(random(255), random(255), random(255));// white color
            fill(255, 255,255);// white color
            rect(x3, y3, ap.right.get(i) * 10, ap.right.get(i) * 20);

        }

        n4 += 0.008;
        n6 += 0.04;
    }
}
