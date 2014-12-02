package edu.fresnostate.turnbased.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.fresnostate.turnbased.Game;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.r = 5;
		config.g = 6;
		config.b = 5;
		config.a = 0;
		config.allowSoftwareMode = true;
		config.backgroundFPS = 5;
		config.depth = 0;
		config.foregroundFPS = 30;
		config.resizable = true;
		config.stencil = 0;
		config.title = "Turn-Based";
		config.vSyncEnabled = true;
		config.width = 800;
		config.height = 600;
		config.fullscreen=false;
		new LwjglApplication(new Game(), config);
	}
}
