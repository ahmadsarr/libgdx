package fr.ul.duckseditor.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.datafactory.TextureFactory;

public class DesktopLauncher {
	public static void main (String[] arg) {
//		TextureFactory.load();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=800;
		config.height=480;
		config.a=8;
		new LwjglApplication(new DucksEditor(), config);
	}
}
