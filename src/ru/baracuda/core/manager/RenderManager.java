package ru.baracuda.core.manager;

import org.lwjgl.opengl.GL11;
import ru.Start;

public class RenderManager {
    private final WindowManager window;

    public RenderManager() {
        window = Start.getWindow();
    }

    public void init() {

    }

    public void render() {

    }

    public void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void cleanup() {

    }
}
