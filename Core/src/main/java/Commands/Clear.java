package Commands;

import Managers.Container;

public class Clear implements Command{
    @Override
    public Container execute(Container container) {
        return null;
    }

    @Override
    public String toString() {
        return "Clear";
    }
}
