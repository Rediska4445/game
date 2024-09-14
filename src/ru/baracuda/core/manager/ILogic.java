package ru.baracuda.core.manager;

public interface ILogic {
    void init();

    void input();

    void update();

    void render();

    void cleanup();
}
