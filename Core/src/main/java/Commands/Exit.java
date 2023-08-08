package Commands;

import Managers.Container;

public class Exit implements Command{
    @Override
    public Container execute(Container container) {
        return null;
    }

    @Override
    public String toString() {
        return "Exit";
    }

}
