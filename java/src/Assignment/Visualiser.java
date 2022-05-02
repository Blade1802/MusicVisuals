package Assignment;

import processing.core.PApplet;
import ie.tudublin.*;

public class Visualiser extends Visual {
    // Class Objects
    circle circle_2;


    public void settings() {
        // size(1024, 800, P3D);
        fullScreen(P3D, SPAN);
        smooth();
    }

    int mode = 0;

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        if (keyCode == ' ') {
            if (getAudioPlayer().isPlaying()) {
                getAudioPlayer().pause();
            } else {
                getAudioPlayer().rewind();
                getAudioPlayer().play();
            }
        }
    }

    public void setup() {
        startMinim();
        loadAudio("[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3");
        getAudioPlayer().play();

        // Object Intialisation
        circle_2 = new circle(this);

    }

    public void draw() {

        switch(mode)
        {
            case 0:
            {
                circle_2.render();
                break;
            }

            case 1:
            {
                // cs.render();
                break;
            }

            case 2:
            {
                // cs.render();
                break;
            }

            case 3:
            {
                // cs.render();
                break;
            }

            case 4:
            {
                // cs.render();
                break;
            }

            case 5:
            {
                // cs.render();
                break;
            }

            case 6:
            {
                // cs.render();
                break;
            }
            
            case 7:
            {
                // cs.render();
                break;
            }
        }
        
    }

}
