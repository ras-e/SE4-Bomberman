package dk.sdu.mmmi.bomberman.common.services;

public interface IGameState {

    void init();
    void update();
    void dispose();
    void draw();
    void handleInput();
}
