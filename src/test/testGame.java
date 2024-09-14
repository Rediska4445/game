package test;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import ru.Start;
import ru.baracuda.core.manager.ILogic;
import ru.baracuda.core.manager.RenderManager;
import ru.baracuda.core.manager.WindowManager;

public class testGame implements ILogic {

    private int direction = 0;
    private float colour = 0.0f;

    private final RenderManager renderer;
    private final WindowManager window;

    public testGame() {
        renderer = new RenderManager();
        window = Start.getWindow();
    }

    @Override
    public void init() {
        renderer.init();
    }

    @Override
    public void input() {
        if(window.isKeyPressed(GLFW.GLFW_KEY_W))
            direction = 1;
        else if (window.isKeyPressed(GLFW.GLFW_KEY_S)) {
            direction = -1;
        }
        else
            direction = 0;
    }

    @Override
    public void update() {
        colour += direction * 0.01f;
        if(colour > 1)
            colour = 1.0f;
        else if (colour <= 0)
            colour = 0.0f;
    }

    @Override
    public void render() {
        if(window.isResize()) {
            GL11.glViewport(0,0,window.getWidth(), window.getWidth());
        }

        window.setClearColour(colour, colour, colour, 0.0f);
        renderer.clear();
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
    }
}
