package edu.utdallas.wxz180008;

import edu.utdallas.wxz180008.testpanel.TestPanelView;

import javax.swing.*;
import java.awt.*;

public class ContainerApplet {
    public static void main(String[] args) {

        // create and set up the applet
        TestPanelView applet = new TestPanelView();
        applet.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        applet.init();

        // create a frame to host the applet, which is just another type of Swing Component
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add the applet to the frame and show it
        mainFrame.getContentPane().add(applet);
        mainFrame.pack();
        mainFrame.setVisible(true);

        // start the applet
        applet.start();
    }
}