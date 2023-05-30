package org.example;

// Demonstrate image filters.

import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.io.*;
import java.lang.reflect.*;

public class ImageFilterDemo extends Frame implements ActionListener {
    Image img;
    PlugInFilter pif;
    Image fimg;
    Image curImg;
    LoadedImage lim;
    Label lab;
    Button reset;

    // Names of the filters.
    String[] filters = {"Grayscale", "Invert", "Contrast",
            "Blur", "Sharpen"};

    public ImageFilterDemo() {
        Panel p = new Panel();
        add(p, BorderLayout.SOUTH);

        // Create Reset button.
        reset = new Button("Reset");
        reset.addActionListener(this);
        p.add(reset);

        // Add the filter buttons.
        for (String fstr : filters) {
            Button b = new Button(fstr);
            b.addActionListener(this);
            p.add(b);
        }

        // Create the top label.
        lab = new Label("");
        add(lab, BorderLayout.NORTH);

        // Load the image.
        try {
            File imageFile = new File("test.jpg");

            // Load the image.
            img = ImageIO.read(imageFile);
        } catch (IOException exc) {
            System.out.println("Cannot load image file.");
            System.exit(0);
        }

        // Get a LoadedImage and add it to the center.
        lim = new LoadedImage(img);
        add(lim, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        String a = "";

        a = ae.getActionCommand();
        if (a.equals("Reset")) {
            lim.set(img);
            lab.setText("Normal");
        } else {
            if (a.equals("Grayscale")) {
                // Get the selected filter.
                pif = new Grayscale();
            } else if (a.equals("Invert")) {
                pif = new Invert();
            } else if (a.equals("Contrast")) {
                pif = new Contrast();
            } else if (a.equals("Blur")) {
                pif = new Blur();
            } else if (a.equals("Sharpen")) {
                pif = new Sharpen();
            }
            fimg = pif.filter(this, img);
            lim.set(fimg);
            lab.setText("Filtered: " + a);
        }
        repaint();
    }

    public static void main(String[] args) {
        ImageFilterDemo appwin = new ImageFilterDemo();

        appwin.setSize(new Dimension(420, 420));
        appwin.setTitle("ImageFilterDemo");
        appwin.setVisible(true);
    }
}