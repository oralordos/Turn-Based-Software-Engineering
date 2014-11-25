package edu.fresnostate.turnbased.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import edu.fresnostate.turnbased.Game;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.r = 5;
		config.g = 6;
		config.b = 5;
		config.a = 0;
		config.depth = 0;
		config.getTouchEventsForLiveWallpaper = false;
		config.hideStatusBar = true;
		config.stencil = 0;
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = false;
		initialize(new Game(), config);
	}
}
