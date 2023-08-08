package Commands;

import Managers.Container;

public class Info implements Command{
    @Override
    public Container execute(Container container) {
        return null;
    }

    @Override
    public String toString() {
        return "Info";
    }
}
