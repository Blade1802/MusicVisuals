package creed;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class Mesh_Line extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    BeatDetect beat;
    FFT fft;

    // circle particle effect
    float n4 = 0;
    float n6 = 0;

    // circle particle effect
    float n = 0;
    float n3 = 0;
    float n5 = 0;
    float speed2 = 0;

    int c = 0;
    int[][] RGBval = { { 255, 0, 0 }, { 255, 165, 0 }, { 255, 255, 0 }, { 0, 128, 0 }, { 0, 0, 255 }, { 75, 0, 130 },
            { 238, 130, 238 } };
    int c1 = 51;
    int c2 = 204;
    int c3 = 255;
    // arrays for water effect
    int cols = 200;
    int rows = 200;
    // float[][] current = new float[cols][rows];
    // float[][] previous = new float[cols][rows];

    float[][] current;
    float[][] previous;

    float dampening = (float) 0.93;

    int x; // used to make the circle spin
    int radius = 200;// radius in pixels of the circle

    int mode = 0;

    float[] lerpedBuffer;
    float gain = 200;
    int tbase = 1024;
    float[] myBuffer;
    // float[] lerpedBuffer2;
    float lerpedAverage = 0;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
    }

    public void settings() {
        // size(1024, 800, P3D);
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        background(0);
        noCursor();

        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;
        ap = minim.loadFile(
                "creed.mp3", 2048);
        ap.play();
        ab = ap.mix;
        myBuffer = new float[ap.bufferSize()];

        // Initialize audio analyzer
        fft = new FFT(ap.bufferSize(), ap.sampleRate());

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[2048];
        x = 0;
        beat = new BeatDetect();
        beat.setSensitivity(50);

        cols = width;
        rows = height;

        current = new float[cols][rows];
        previous = new float[cols][rows];

    }

    float off = 0;

    public void mousePressed() {
        // previous[mouseX][mouseY] = 255;
        current[mouseX][mouseY] = 255;

    }

    public void draw() {
        // background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
            // lerpedBuffer2[i] = lerp(lerpedBuffer2[i], ab.get(i), 0.05f);

        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        float cx = width / 2;
        float cy = height / 2;

        switch (mode) {
            case 0: {
                background(0);
                colorMode(HSB);

                strokeWeight(2);
                for (int i = 0; i < ab.size(); i += 10) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    float r = map(i, 0, 0.5f, 100, 2000);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
                    line(0, i, lerpedBuffer[i] * halfH * 4, i);
                    line(width, i, width - (lerpedBuffer[i] * halfH * 4), i);
                    line(i, 0, i, lerpedBuffer[i] * halfH * 4);
                    line(i, height, i, height - (lerpedBuffer[i] * halfH * 4));
                    // circle(cx, cy, r);
                    circle(i, halfH + f, 5);
                    circle(i, halfH - f, 5);
                }
                break;
            }
            case 1: {
                // form mesh effect
                background(0);
                colorMode(HSB);

                strokeWeight(2);
                for (int i = 0; i < ab.size(); i += 10) {

                    float c = map(i, 0, ab.size(), 0, 255);

                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
                    // lerpedBuffer2[i] = lerp(lerpedBuffer2[i], ab.get(i), 0.3f);

                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    line(0, i, f, i);
                    line(width, i, width - f, i);
                    line(i, 0, i, f);// upper
                    line(i, height, i, height - f);// lower
                    // circle(cx, cy, r);
                    // circle(i, halfH + f, 5);
                    // circle(i, halfH - f, 5);
                }

                // --RECORD PLAYER Effect
                // record player effect
                colorMode(RGB);

                translate(width / 2, height / 2);
                // translate(mouseX, mouseY);

                // Create circle interior
                noStroke();
                fill(200, 0, 0);
                circle(0, 0, 120); // White circle
                fill(255);
                circle(cos(radians(x)) * 5, sin(radians(x)) * 5, 80); // Red circle
                fill(0);
                circle(0, 0, 10); // Black circle
                if (ap.isPlaying())
                    x += 2; // Circle only rotates while music is playing

                // Audio Visualization
                fft.forward(ap.mix);
                float bands = fft.specSize();

                for (int i = 0; i < bands * 2; i++) {

                    // Starting positions of line
                    float start_x = radius * cos(PI * (i + x) / bands);
                    float start_y = radius * sin(PI * (i + x) / bands);

                    // Draw line based on sound
                    stroke(255);
                    // stroke(random(255));
                    strokeWeight(5);
                    if (i < bands) {
                        // Line based on band length
                        line(start_x, start_y, start_x + fft.getBand(i) * 7 * cos(PI * (i + x) / bands),
                                start_y + fft.getBand(i) * 7 * sin(PI * (i + x) / bands));
                    } else {
                        // Line based on frequency
                        line(start_x, start_y, start_x + fft.getFreq(i) * 5 * cos(PI * (i + x) / bands),
                                start_y + fft.getFreq(i) * 5 * sin(PI * (i + x) / bands));
                    }
                }
            }

                break;
            case 2: {
                background(0);
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
                    rect(x, y, ap.right.get(i) * 10, ap.left.get(i) * 10);// 10 -> 20

                    fill(255, 69, 0);// orange color
                    // fill(124, 252, 0); //grass green color
                    // fill(255, 0, 0);//red color code
                    // fill(random(0), 255, random(255));// aqua blue color code
                    rect(x, y, ap.right.get(i) * 10, ap.left.get(i) * 10);

                    // fill(random(255), random(255), random(255));// white color
                    fill(255, 255, 255);// white color
                    rect(x3, y3, ap.right.get(i) * 10, ap.right.get(i) * 20);

                }

                n4 += 0.008;
                n6 += 0.04;
            }
                break;

            case 3: {
                beat.detect(ab);
                fill(0, 20);
                noStroke();
                rect(0, 0, width, height);
                translate(width / 2, height / 2);

                for (int i = 0; i < ap.bufferSize() - 1; i++) {

                    float leftLevel = ap.left.level() * 20;
                    ellipse(i, i, leftLevel, leftLevel);
                    rotateZ((float) (n * -PI / 3 * 0.05));

                    if (beat.isOnset()) {
                        c = (c + 1) % 7;

                        float c = map(i, 0, ap.bufferSize(), 0, 255);
                        fill(c, 255, 255);

                    }

                    fill(c1++ % 256, c2++ % 256, c3++ % 256);

                }

                n += 0.008;
                n3 += speed2;
                n5 += speed2;
                break;
            }

            case 4: {
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
                    rotateZ((float) (n * -PI / 5 * 0.05)); // 3 -> 1
                    // fill(0, 150);
                    // fill(255, 0, 69);
                    // fill(34, 139, 34);// green
                    fill(124, 252, 0);// lawn green
                    // fill(255, 140, 0);

                    float rightLevel = ap.right.level() * 100;
                    rect(i, i, rightLevel, rightLevel);
                }
                n++;
                break;
            }
            case 5: {
                beat.detect(ab);
                fill(0, 20);
                noStroke();
                rect(0, 0, width, height);
                translate(width / 2, height / 2);

                for (int i = 0; i < ap.bufferSize() - 1; i++) {

                    // float angle = sin(i + (n - 2)) * 40;
                    // float x = sin(radians(i)) * (n / angle);

                    float leftLevel = ap.left.level() * 20;
                    ellipse(i, i, leftLevel, leftLevel);
                    rotateZ((float) (n * -PI / 3 * 0.05));

                    // fill(51,204,255); // Light Blue
                    // fill(RGBval[c][0], RGBval[c][1], RGBval[c][2]); // Light Blue
                    // float color1 = map(i, 0, ap.bufferSize(), 0, 255);
                    // fill(color1, 255, 255);

                    if (beat.isOnset()) {
                        c = (c + 1) % 7;
                        // fill(RGBval[c][0], RGBval[c][1], RGBval[c][2]);
                        // fill(c1++%256,c2++%256,c3++%256);
                        float c = map(i, 0, ap.bufferSize(), 0, 255);
                        fill(c, 255, 255);

                    }
                    // c = (c + 1) % 7;
                    // fill(RGBval[c][0], RGBval[c][1], RGBval[c][2]);
                    fill(c1++ % 256, c2++ % 256, c3++ % 256);

                }

                n += 0.008;
                n3 += speed2;
                n5 += speed2;
                break;

            }
            case 6: {
                colorMode(HSB);
                // colorMode(RGB);
                background(0);

                stroke(255);
                noFill();
                strokeWeight(4);

                // Calculate sum and average of the samples
                // Also lerp each element of buffer;
                for (int i = 0; i < ab.size(); i++) {
                    sum += abs(ab.get(i));
                    myBuffer[i] = lerp(myBuffer[i], ab.get(i), 0.05f);
                }
                average = sum / (float) ab.size();

                smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

                float r = map(smoothedAmplitude, 0, 0.5f, 100, 2000);
                float r1 = map(smoothedAmplitude, 0, 0.5f, 150, 2500);

                float c = map(smoothedAmplitude, 0, 0.5f, 0, 255);
                stroke(c, 255, 255);
                circle(cx, cy, r);
                circle(cx, cy, r1);

                // ellipse(width / 2, height / 2, 240, 240);

                translate(0, height / 2);
                stroke(255);
                strokeWeight(2);

                // draw the output waveforms, so there's something to look at
                // first grab a stationary copy
                for (int i = 0; i < ap.bufferSize(); ++i) {
                    myBuffer[i] = ap.left.get(i);
                }
                // find trigger point as largest +ve slope in first 1/4 of buffer
                int offset = 0;
                float maxdx = 0;
                for (int i = 0; i < myBuffer.length / 4; ++i) {
                    float dx = myBuffer[i + 1] - myBuffer[i];
                    if (dx > maxdx) {
                        offset = i;
                        maxdx = dx;
                    }
                }
                // plot out that waveform
                int mylen = min(tbase, myBuffer.length - offset);
                for (int i = 0; i < mylen - 1; i++) {
                    fill((frameCount + i / 2) % 360, 80, 100);

                    float x1 = map(i, 0, tbase, 0, width);
                    float x2 = map(i + 1, 0, tbase, 0, width);
                    float x3 = map(i * 2 + 10, 0, tbase, 0, width);
                    float x4 = map(i * 2 + 12, 0, tbase, 0, width);

                    line(x1, 100 - myBuffer[i + offset] * gain, x2, 100 - myBuffer[i + 1 + offset] * gain);

                    line(x3, 100 - myBuffer[i + offset] * gain, x4, 100 - myBuffer[i + 1 + offset] * gain);
                }

            }
        }
    }
}
