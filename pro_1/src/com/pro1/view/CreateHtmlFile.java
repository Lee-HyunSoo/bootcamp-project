package com.pro1.view;

import java.io.FileWriter;
import java.io.IOException;

public class CreateHtmlFile {

    public static void filewrite(String tags) {
        FileWriter fr;
        try {
            fr = new FileWriter("C:\\filetest\\pos.html");
            fr.write(tags);
            fr.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

}
