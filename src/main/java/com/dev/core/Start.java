package com.dev.core;

import com.dev.core.manager.EngineManager;
import com.dev.core.manager.WindowManager;
import com.dev.core.utils.Consts;
import org.lwjgl.Version;
import test.testGame;

public class Start {
    private static WindowManager window;
    private static testGame game;

    public static void main(String[] args) {
        System.out.println(Version.getVersion());
        window = new WindowManager(Consts.TITLE, 1280, 768, false);
        game = new testGame();
        EngineManager engine = new EngineManager();

        try {
            engine.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static testGame getGame() {
        return game;
    }

    public static WindowManager getWindow() {
        return window;
    }
}