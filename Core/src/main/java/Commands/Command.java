package Commands;

import Managers.Container;

public interface Command {
    Container execute(Container container);
    String toString();
}