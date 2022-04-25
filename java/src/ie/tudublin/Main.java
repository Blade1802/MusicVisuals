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
		processing.core.PApplet.runSketch(a, new circle_effect());
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

	public void osclliopscope() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new Oscilloscope());
	}

	public void Ripple() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new waterRipple());
	}

	public void case_menu() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new switch_menu());
	}

	public static void main(String[] args) {
		Main main = new Main();
		// main.startUI();
		// main.Circle_Effect();
		// main.radioActive();
		// main.recordPlayer();
		// main.musicViz();
		main.LuYu();
		// main.MeshLine();
		// main.flower();
		// main.osclliopscope();
		// main.Ripple();
		// main.case_menu();

	}
}