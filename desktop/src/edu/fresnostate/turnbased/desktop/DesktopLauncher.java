package edu.fresnostate.turnbased.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.fresnostate.turnbased.Game;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.width = 800;
		//config.height = 800;
		
		config.fullscreen=true;
		new LwjglApplication(new Game(), config);
	}
}
