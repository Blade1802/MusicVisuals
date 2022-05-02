package ie.tudublin;

import processing.core.PApplet;
import ie.tudublin.*;

public class circlemain extends Visual {
    circlesub cs;

    public void settings() {
        // size(1024, 800, P3D);
        fullScreen(P3D, SPAN);
        smooth();
    }

    public void setup() {
        startMinim();
        loadAudio("[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3");
        
        cs = new circlesub(this);
    }

    public void draw()
    {
        cs.render();
    }
    
}
