package ie.tudublin;

import creed.*;

public class Main {

	// public void startUI()
	// {
	// String[] a = {"MAIN"};
	// processing.core.PApplet.runSketch( a, new MyVisual());
	// }

	public void Circle_Effect() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new test_audio_1());
	}

	public void Circle_Pattern() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new test_audio_circle_pattern());
	}

	public void radioActive() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new radiohead_visual());
	}

	public void recordPlayer() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new Tape_Player());
	}

	public void musicViz() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new MusicViz());
	}

	public void Perlin() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new Perlin_noise());
	}

	public void LuYu() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new LuYu_edit());
	}

	public void MeshLine() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new Mesh_Line());
	}

	public void flower() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new Flower_Random());
	}


	public static void main(String[] args) {
		Main main = new Main();
		// main.startUI();
		// main.Circle_Effect();
		// main.Circle_Pattern();
		// main.radioActive();
		// main.recordPlayer();
		// main.musicViz();
		// main.Perlin();
		// main.LuYu();
		// main.MeshLine();
		main.flower();

	}
}