package Assignment;

import processing.core.PApplet;

public class MusicViz extends PApplet {
    Visualiser pa;
    int r = 200;
    float rad = 0;
    


    public MusicViz(Visualiser pa) {
        this.pa = pa;
    }

    public void render() {
        background(0);
        colorMode(HSB);
        // float t = map(mouseX, 0, width, 0, 1);
        beat.detect(ap.mix);
        // fill(26, 31, 24);
        // fill(0);

        noStroke();
        rect(0, 0, width, height);
        translate(width / 2, height / 2);
        noFill();
        // fill(-1, 10);
        // fill(random(255), random(255), random(255));
        fill(255);
        if (beat.isOnset())
            rad = (float) (rad * 0.8);
        else
            rad = 70;
        ellipse(0, 0, 2 * rad, 2 * rad);
        stroke(-1, 50);
        int bsize = ap.bufferSize();

        for (int i = 0; i < bsize - 1; i += 5) {
            stroke(map(i, 0, bsize - 1, 0, 255), 255, 255);
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
        // if (flag)
        // showMeta();
    }
    
}
