package creed;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;

public class circle2 extends PApplet {
    Minim minim;
    AudioPlayer mySound;
    AudioInput ai;
    AudioBuffer ab;

    // circle particle effect
    float n = 0;
    float n3 = 0;
    float n5 = 0;
    float speed2 = 0;

    int c = 0;
    int[][] RGBval = { {255, 0, 0}, {255, 165, 0}, {255, 255, 0}, {0, 128, 0}, {0, 0, 255}, {75, 0, 130}, {238, 130, 238} };

    public void settings() {
        size(1024, 800, P3D);
        // fullScreen(P3D);
        smooth();
        // frameRate(24);
    }

    public void setup() {
        minim = new Minim(this);
        mySound = minim.loadFile(
                "creed.mp3", 1024);

        mySound.play();
        ab = mySound.mix;
        // colorMode(HSB)

    }

    public void draw() {
        fill(0, 20);
        noStroke();
        rect(0, 0, width, height);
        translate(width / 2, height / 2);

        for (int i = 0; i < mySound.bufferSize() - 1; i++) {

            float angle = sin(i + (n - 2)) * 40;
            float x = sin(radians(i)) * (n / angle);

            float leftLevel = mySound.left.level() * 20;
            ellipse(i, i, leftLevel, leftLevel);
            rotateZ((float) (n * -PI / 3 * 0.05));

            
            //fill(51,204,255); // Light Blue
            fill(RGBval[c][0], RGBval[c][1], RGBval[c][2]); // Light Blue
            c = (c + 1) % 7;
            //System.out.println(c1 + " " + c2 + " " + c3);
            //fill(i%255,i%255,i%255);

        }

        n += 0.008;
        n3 += speed2;
        n5 += speed2;

    }
}
