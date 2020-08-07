package main.com.utils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Recorder {
    private File file;
    private String path;

    public Recorder(String path) throws BOException {
        try {
            this.path = getClass().getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath() + path;
        } catch (URISyntaxException e) {
            throw new BOException(e);
        }

        this.file = new File(this.path);
        if (!this.file.exists()) createFile(this.file);
    }

    public List<String> readFile() throws BOException {
        List<String> str = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            br.lines().forEach(s -> {
                if (s != null) str.add(s);
            });
        } catch (IOException e) {
            throw new BOException(e);
        }
        if (str.size() == 0)
            str.add("0");
        return str;
    }

    public File getFile() {
        return file.getAbsoluteFile();
    }

    public boolean writeData(String s) throws BOException {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(this.file.getAbsolutePath()))) {
            wr.write(s);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean createFile(File file) throws BOException {
        try {
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            return file.createNewFile();
        } catch (IOException e) {
            throw new BOException(e);
        }
    }

    public boolean delete() {
        return this.file.delete();
    }

}
