package org.mwo.agh.edu.utils;

import org.mwo.agh.edu.model.Spreadsheet;

import java.io.*;

public class Serializer {

    private final String filename = "spreadsheet.ser";

    public void serialize(Spreadsheet spreadsheet) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(spreadsheet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Spreadsheet unserialize() {
        Spreadsheet spreadsheet;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            spreadsheet = (Spreadsheet) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return spreadsheet;
    }
}
