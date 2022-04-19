package ie.tudublin;

import creed.*;
// import example.CubeVisual;
// import example.MyVisual;
// import example.RotatingAudioBands;

public class Main
{	

	public void Audio()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.Audio();			
	}
}