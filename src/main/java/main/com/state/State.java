package main.com.state;

import main.com.utils.BOException;

public interface State {

    StateDTO getState() throws BOException;

    void writeState(StateDTO stateDTO) throws BOException;

}
