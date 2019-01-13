package com.example.danley.sharemanager;

import java.io.Serializable;
import java.util.Random;

public class CustomerID implements Serializable {
    String ID = null;

    public CustomerID(String newID) {
        this.ID = newID;
    }



    public void setID(String newID) {
        ID = newID ;
    }

    public static String randomID() {
        String alphanumeric = new String();
        Random rnd = new Random();
        for (int i = 0; i < 2; i++ )
        {
            char c = (char) (rnd.nextInt(26) + 'a');
            alphanumeric += c;
        }

        for (int i = 0; i < 4; i++)
        {
            int x = rnd.nextInt(9);
            alphanumeric += x;
        }
        return alphanumeric;
    }

    @Override
    public String toString() {
        return ID;
    }
}
