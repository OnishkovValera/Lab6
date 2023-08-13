package Commands;

import Managers.Container;

import java.io.Serializable;

public class Show implements Command, Serializable {
    @Override
    public Container execute(Container container) {
        return null;
    }

    @Override
    public String toString() {
        return "Show";
    }
}
