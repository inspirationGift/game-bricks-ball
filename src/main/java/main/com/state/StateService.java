package main.com.state;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.com.utils.BOException;

import java.io.File;

public class StateService {

    private ObjectMapper mapper;

    public StateService() {
        this.mapper = new ObjectMapper();
    }

    public StateDTO readFile(File file) throws BOException {
        try {
            return this.mapper.readValue(file, StateDTO.class);
        } catch (Exception e) {
            throw new BOException(e);
        }
    }

    public String writeObject(StateDTO object) throws BOException {
        try {
            return this.mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new BOException(e);
        }
    }
}

