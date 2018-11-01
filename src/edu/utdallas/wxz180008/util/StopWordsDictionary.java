package edu.utdallas.wxz180008.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class StopWordsDictionary {

    private static volatile StopWordsDictionary instance = null;
    private List<String> noises;

    private StopWordsDictionary() {
        initialize();
    }

    public static StopWordsDictionary getInstance() {
        if (instance == null) {
            synchronized(StopWordsDictionary.class) {
                if (instance == null) {
                    instance = new StopWordsDictionary();
                }
            }
        }

        return instance;
    }

    private void initialize() {
        ResourceBundle rb = ResourceBundle.getBundle("filters");
        noises = Arrays.asList(rb.getString("noises").split(","));
    }

    public List<String> getNoises() {
        return noises;
    }
}
