package edu.utdallas.wxz180008;

import edu.utdallas.wxz180008.autoindex.AutoIndexView;

import java.io.IOException;

public class Kwic {
    public static void main(String[] args) {
        AutoIndexView view = new AutoIndexView();
        try {
            view.processFile("C:\\Users\\wenxi\\Desktop\\data_2018-10-31 13%3A16%3A47.178715.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
