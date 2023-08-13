package Commands;

import Managers.CollectionManager;
import Managers.Container;

public interface Command{

    CollectionManager collectionManager = new CollectionManager();
    Container execute(Container container);
    String toString();
}