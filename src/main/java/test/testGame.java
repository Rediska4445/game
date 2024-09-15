package test;

import com.dev.core.Start;
import com.dev.core.entity.Model;
import com.dev.core.manager.ILogic;
import com.dev.core.manager.ObjectLoader;
import com.dev.core.manager.RenderManager;
import com.dev.core.manager.WindowManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class testGame implements ILogic {

    private int direction = 0;
    private float colour = 0.0f;

    private final RenderManager renderer;
    private final ObjectLoader objectLoader;
    private final WindowManager window;

    private Model model;

    public testGame() {
        renderer = new RenderManager();
        window = Start.getWindow();
        objectLoader = new ObjectLoader();
    }

    @Override
    public void init() throws Exception {
        renderer.init();

        float[] vertices = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
            -0.5f, 0.5f, 0f
        };

        int[] indices = {
            0,2,3,
            3,1,2
        };

        model = objectLoader.loadModel(vertices, indices);
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
            GL11.glViewport(0,0, window.getWidth(), window.getHeight());
            window.setResize(true);
        }

        window.setClearColour(colour, colour, colour, 0.0f);
        renderer.render(model);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        objectLoader.cleanup();
    }
}
