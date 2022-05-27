package dk.sdu.mmmi.bomberman.common.data;

import dk.sdu.mmmi.bomberman.common.events.Event;
import dk.sdu.mmmi.bomberman.common.services.IGamePluginService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameData {

    private float delta;
    private int displayWidth;
    private int displayHeight;
    private final GameKeys keys = new GameKeys();
    private List<Event> events = new CopyOnWriteArrayList<>();
    private List<IGamePluginService> gamePluginList = new CopyOnWriteArrayList<>();
    private boolean Evictory = false;
    private boolean Pvictory = false;

    //Sets the game status (gameover, victory)
    public void setEVictory(boolean gameOver) {
        this.Evictory = Evictory;
    }

    public void setPVictory(boolean victory) {
        this.Pvictory = Pvictory;
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public void removeEvent(Event e) {
        events.remove(e);
    }

    public List<Event> getEvents() {
        return events;
    }

    public GameKeys getKeys() {
        return keys;
    }

    public float getDelta() {
        return delta;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public List<IGamePluginService> getGamePlugins() {
        return this.gamePluginList;
    }

    public <E extends Event> List<Event> getEvents(Class<E> type, String sourceID) {
        List<Event> r = new ArrayList();
        for (Event event : events) {
            if (event.getClass().equals(type) && event.getSource().getID().equals(sourceID)) {
                r.add(event);
            }
        }

        return r;
    }
}
