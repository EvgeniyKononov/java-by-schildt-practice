package org.example;

import java.util.concurrent.Exchanger;

public class MyString implements Runnable {
    Exchanger<String> ex;
    String str;

    MyString(Exchanger<String> c) {
        ex = c;
        str = "моя строка";
    }

    public void run() {
                   try {
                // Exchange a full buffer for an empty one.
                str = ex.exchange(str);
            } catch(InterruptedException exc) {
                System.out.println(exc);
            }
        }
    }

