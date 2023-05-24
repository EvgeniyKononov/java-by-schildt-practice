package org.example;

// Demonstrate CardLayout.
import java.awt.*;
import java.awt.event.*;

public class CardLayoutDemo extends Frame {

    Checkbox windows10, windows7, windows8, android, linux, mac;
    Panel osCards;
    CardLayout cardLO;
    Button win, other;

    public CardLayoutDemo() {

        // Use a flow layout for the main frame.
        setLayout(new FlowLayout());

        win = new Button("Windows");
        other = new Button("Other");
        add(win);
        add(other);

        // Set osCarsds panel to use CardLayout.
        cardLO = new CardLayout();
        osCards = new Panel();
        osCards.setLayout(cardLO);

        windows7 = new Checkbox("Windows 7",true);
        windows8 = new Checkbox("Windows 8");
        windows10 = new Checkbox("Windows 10");
        android = new Checkbox("Android");
        linux = new Checkbox("Linux");
        mac = new Checkbox("Mac OS");

        // Add Windows check boxes to a panel.
        Panel winPan = new Panel();
        winPan.add(windows7);
        winPan.add(windows8);
        winPan.add(windows10);

        // Add other OS check boxes to a panel.
        Panel otherPan = new Panel();
        otherPan.add(android);
        otherPan.add(linux);
        otherPan.add(mac);

        // Add panels to card deck panel.
        osCards.add(winPan, "Windows");
        osCards.add(otherPan, "Other");

        // Add cards to main frame panel.
        add(osCards);

        // Use lambda expressions to handle button events.
        win.addActionListener((ae) -> cardLO.show(osCards, "Windows"));
        other.addActionListener((ae) -> cardLO.show(osCards, "Other"));

        // Register for mouse pressed events.
        addMouseListener(new MouseAdapter() {
            // Cycle through panels.
            public void mousePressed(MouseEvent me) {
                cardLO.next(osCards);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        CardLayoutDemo appwin = new CardLayoutDemo();

        appwin.setSize(new Dimension(300, 220));
        appwin.setTitle("CardLayoutDemo");
        appwin.setVisible(true);
    }
}