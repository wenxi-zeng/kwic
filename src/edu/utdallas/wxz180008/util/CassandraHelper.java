package edu.utdallas.wxz180008.util;

import java.util.ResourceBundle;

public class CassandraHelper {
    private static volatile CassandraHelper instance = null;

    private CassandraHelper() {
        initialize();
    }

    private String ip;

    private int port;

    public static CassandraHelper getInstance() {
        if (instance == null) {
            synchronized(CassandraHelper.class) {
                if (instance == null) {
                    instance = new CassandraHelper();
                }
            }
        }

        return instance;
    }

    private void initialize() {
        ResourceBundle rb = ResourceBundle.getBundle("cassandra");
        ip = rb.getString("ip");
        port = Integer.valueOf(rb.getString("port"));
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
