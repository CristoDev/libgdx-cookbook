package com.cookbook.samples.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cookbook.samples.GdxSample;
import com.cookbook.samples.GdxSamples;

import javax.swing.*;
import java.io.IOException;

public class SampleMainProcess extends JFrame implements SampleList.SampleLauncher {
    @Override
    public boolean launchSample(String sampleName) {
        GdxSample sample = GdxSamples.newSample(sampleName);

        if (sample != null) {
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.width = 1280;
            config.height = 720;
            config.useGL30=true;
            config.title = sampleName;
            config.forceExit = false;
            sample.setPlatformResolver(new DesktopResolver());

            new LwjglApplication(sample, config);

            try {
                int status = JavaProcess.exec(SampleMainProcess.class); //This will launch the game
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            dispose();

            return true;
        }

        return false;
    }
}
