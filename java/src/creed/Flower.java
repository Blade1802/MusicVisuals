package creed;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class Flower extends PApplet {
    /* Same as float num; float pathR; etc... */
    float num, pathR, pathG, pathB;
    int backgroundColor = 0;
    boolean doOnce = true;
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
        ap = minim.loadFile("creed.mp3", 1024);
        ap.play();
        ab = ap.mix;
        // colorMode(HSB)
    }


    public void draw() {
        fill(backgroundColor, 20);
        /* We minus 1 and plus 1 so we don't see the stroke of the square. */
        rect(-1, -1, width + 1, height + 1);
        translate(width / 2, height / 2);
        for (int i = 0; i < 360; i += 2) {
            /* This gives the humps on the circle. Also helps with color and size. */
            float angle = sin(i + num) * 50;
            /* Locations of circles. */
            float x = sin(radians(i)) * (150 + angle);
            float y = cos(radians(i)) * (150 + angle);
            float x2 = sin(radians(i)) * (100 + angle);
            float y2 = cos(radians(i)) * (100 + angle);

            /*
             * Color. We only effect the red and blue values but there is no reason why you
             * can not effect the green value to.
             * 
             * To help break the pathR calculations down here is an explanation:
             * 
             * (float) is used to help the computer know we are dealing with "floats" and
             * not "ints". (MUST USE)!
             * 255/width*mouseX is the formular for getting a percentage. We want 100% or
             * width to eqaul 255 (The max a color can go).
             * Angle is multiplied by sin(i + num (Which is constantly changing giving a
             * slight change) and then we multiply num by 3
             * to speed the color change up.
             */
            pathR = (((float) 255 / width) * mouseX + angle) + 125 * sin(i + num * 3);
            pathG = (50 + angle) + 125 * sin(i + num * 3);
            pathB = (((float) 255 / height) * mouseY + angle) + 125 * sin(i + num * 3);

            /*
             * Throw these values in both stroke and fill. Stroke is for the lines and fill
             * is for the cirlces.
             */
            stroke(pathR, pathG, pathB);
            fill(pathR, pathG, pathB);

            /*
             * The first circles have the size angle/5. Angle goes up to 50. So we divide by
             * 10 to keep them small.
             */
            ellipse(x, y, angle / 5, angle / 5);
            ellipse(y2, x2, 5, 5);
            line(x, y, x2, y2);
        }
        num += 0.01;
    }

    public void mousePressed() {
        if (doOnce && backgroundColor == 255) {
            backgroundColor = 0;
            doOnce = false;
        }
        if (doOnce && backgroundColor == 0) {
            backgroundColor = 255;
            doOnce = false;
        }
    }

    public void mouseReleased() {
        doOnce = true;
    }

}
