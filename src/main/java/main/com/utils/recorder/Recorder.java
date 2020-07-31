package main.com.utils.recorder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Recorder {
    public File file;
    public File state;

    public Recorder() {

        String path = "";
        try {
            path = getClass().getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String fileN = path + "records/record.txt";
        String stateN = path + "records/state.json";

        this.file = new File(fileN);
        this.state = new File(stateN);

        if (!file.exists()) createFile(file);
        if (!state.exists()) createFile(state);
    }

    public int getRecord() {
        return readRecord(file) == null ? 0 : Integer.parseInt(readRecord(file).get(0));
    }

    public List<String> readRecord(File file) {
        List<String> str = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.lines().forEach(s -> {
                if (s != null) str.add(s);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.size() != 0 ? str : null;
    }

    public boolean createFile(File file) {
        try {
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void writeData(String s, String type) {
        File file;
        if (type.equals("record"))
            file = this.file;
        else
            file = this.state;
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(file))) {
            wr.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public StateDTO getState() {
        StateDTO stateDTO = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            stateDTO = mapper.readValue(this.state, StateDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stateDTO;
    }

    public void writeState(StateDTO currentState) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String s = mapper.writeValueAsString(currentState);
            writeData(s, "");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
