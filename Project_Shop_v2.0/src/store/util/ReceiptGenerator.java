package store.util;

import store.models.Receipt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptGenerator {
    public static void generateReceiptFile(Receipt receipt) throws IOException {
        String filename = "receipt_" + receipt.getId() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(receipt.toString());
        }
    }
}
