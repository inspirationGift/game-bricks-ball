package main.com.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecorderTest {

    private Recorder recorder;
    private String path;

    @BeforeEach
    void setUp() throws BOException {
        this.path = "test.txt";
        this.recorder = new Recorder(System.getProperty("java.io.tmpdir") + path);
    }

    @Test
    void readFileIfNullStrings() throws BOException {
        List<String> strings = this.recorder.readFile();
        assertEquals(0, Integer.parseInt(strings.get(0)));
        assertEquals(0, Integer.parseInt(strings.get(0)));
    }

    @Test
    void readFile_Should_Throw_Exception() throws BOException {
//        Recorder mock = mock(Recorder.class);
//        when(mock.readFile()).thenThrow(IOException.class);
//        assertThrows(IOException.class, (Executable) mock.readFile());
    }


    @Test
    void writeData() throws BOException {
        this.recorder.writeData("Hello");
        List<String> strings = this.recorder.readFile();
        assertEquals("Hello", strings.get(0));
        boolean delete = this.recorder.delete();
        assertTrue(delete);
    }
}