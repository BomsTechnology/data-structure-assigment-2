package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    public static <T> void write(String fileName, T[] arr) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (T element : arr) {
                writer.write(element.toString());
                writer.newLine();
            }

            System.out.println("File written successfully ► " + fileName);

        } catch (IOException e) {
            System.out.println("Error writing file ► " + fileName);
            e.printStackTrace();
        }
    }
}
