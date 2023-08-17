package Commands;

import Managers.Container;
import Managers.SessionManager;

public class Clear extends AbstractCommand{
    @Override
    public Container execute(Container container) {
        SessionManager.getHandlingChannel();
        return null;
    }
}
