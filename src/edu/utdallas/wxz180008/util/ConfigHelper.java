package edu.utdallas.wxz180008.util;

import java.util.ResourceBundle;

public class ConfigHelper {
    private static volatile ConfigHelper instance = null;

    private ConfigHelper() {
        initialize();
    }

    private boolean autoMode;

    private String dataFolder;

    public static ConfigHelper getInstance() {
        if (instance == null) {
            synchronized(ConfigHelper.class) {
                if (instance == null) {
                    instance = new ConfigHelper();
                }
            }
        }

        return instance;
    }

    private void initialize() {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        autoMode = Boolean.valueOf(rb.getString("automode"));
        dataFolder = rb.getString("datafolder");
    }

    public boolean isAutoMode() {
        return autoMode;
    }

    public void setAutoMode(boolean autoMode) {
        this.autoMode = autoMode;
    }

    public String getDataFolder() {
        return dataFolder;
    }

    public void setDataFolder(String dataFolder) {
        this.dataFolder = dataFolder;
    }
}
