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


public class TapePlayer extends PApplet {

    Minim minim;
    AudioPlayer ap;
    FFT fft;
    PImage background;

    int x; // Used to make the circle spin
    int radius = 200; // Radius in pixels of the circle

    public void settings() {
        size(1920, 1080, P3D);

    }

    public void setup() {
        colorMode(RGB);
        // fullScreen();
        noCursor();
        frameRate(200);


        minim = new Minim(this);

        // Load Music
        ap = minim.loadFile("song.mp3", 1024);
        ap.play();

        // Initialize audio analyzer
        fft = new FFT(ap.bufferSize(), ap.sampleRate());

        x = 0;

        background = loadImage("wallpaper.jpg");
        imageMode(CENTER);
        background(0);
        imageMode(CENTER);
        image(background, 0, 0,1920,1080);
        loadPixels();
        background.loadPixels();
        for(int x=0; x<width-1;x++){
          for(int y =0; y<height; y++){
            int loc1 = x+y*width;
            int loc2 = (x+1) +y*width;
            float b1 = brightness(background.pixels[loc1]);
            float b2 = brightness(background.pixels[loc2]);
            
            float diff = abs(b1-b2);
            pixels[loc1] = color(diff);
            }
        }  
        updatePixels();
    }

    public void draw() {
        translate(width / 2, height / 2);
        background(0);
        imageMode(CENTER);
        image(background, 0, 0,1920,1080);
        loadPixels();
        background.loadPixels();
        for(int x=0; x<width-1;x++){
          for(int y =0; y<height; y++){
            int loc1 = x+y*width;
            int loc2 = (x+1) +y*width;
            float b1 = brightness(background.pixels[loc1]);
            float b2 = brightness(background.pixels[loc2]);
            
            float diff = abs(b1-b2);
            pixels[loc1] = color(diff);
            }
        }  
        updatePixels();


        // Create circle interior
        noStroke();
        fill(200, 0, 0);
        circle(0, 0, 120); // White circle
        fill(255);
        circle(cos(radians(x)) * 5, sin(radians(x)) * 5, 110); // Red circle
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

            colorMode(RGB);
            
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
}
