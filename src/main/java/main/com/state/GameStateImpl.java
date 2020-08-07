package main.com.state;

import main.com.utils.BOException;
import main.com.utils.Recorder;


public class GameStateImpl implements State {
    private final Recorder recorder;
    private final StateService service;

    public GameStateImpl(Recorder recorder, StateService stateService) {
        this.service = stateService;
        this.recorder = recorder;
    }

    public StateDTO getState() throws BOException {
        return this.service.readFile(this.recorder.getFile());
    }

    public void writeState(StateDTO currentState) throws BOException {
        String s = this.service.writeObject(currentState);
        this.recorder.writeData(s);
    }
}
