package edu.fresnostate.turnbased;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.glkit.GLKViewDrawableColorFormat;
import org.robovm.apple.glkit.GLKViewDrawableDepthFormat;
import org.robovm.apple.uikit.UIApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import edu.fresnostate.turnbased.Game;

public class IOSLauncher extends IOSApplication.Delegate {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        config.colorFormat = GLKViewDrawableColorFormat.RGB565;
        config.depthFormat = GLKViewDrawableDepthFormat.None;
        config.allowIpod = false;
        config.orientationLandscape = true;
        config.orientationPortrait = true;
        config.preferredFramesPerSecond = 30;
        config.preventScreenDimming = true;
        config.useAccelerometer = false;
        config.useCompass = false;
        return new IOSApplication(new Game(), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
}