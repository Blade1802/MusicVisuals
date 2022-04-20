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
	public void Ripple_Effect()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Ripple_Effect());		
	}
	public void circle()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new circle());		
	}
	public void circle2()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new circle2());		
	}





	public static void main(String[] args)
	{
		Main main = new Main();
		// main.Audio();
		// main.Ripple_Effect();	
		main.circle2();
			
	}
}