import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandProductMaker extends JFrame {
    private JTextField nameField, descriptionField, idField, costField, countField;
    private int recordCount = 0;

    public RandProductMaker() {
        setTitle("Random Product Maker");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        // Initialize components
        nameField = new JTextField(35);
        descriptionField = new JTextField(75);
        idField = new JTextField(6);
        costField = new JTextField(10);
        countField = new JTextField(5);
        countField.setEditable(false);

        // Add components to the frame
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Description:"));
        add(descriptionField);
        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Cost:"));
        add(costField);
        add(new JLabel("Record Count:"));
        add(countField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });
        add(addButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(quitButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addProduct() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String id = idField.getText();
        double cost = Double.parseDouble(costField.getText());

        try (RandomAccessFile raf = new RandomAccessFile("products.dat", "rw")) {
            raf.seek(raf.length()); // Move to the end of the file
            raf.writeUTF(padString(name, 35));
            raf.writeUTF(padString(description, 75));
            raf.writeUTF(padString(id, 6));
            raf.writeDouble(cost);
            recordCount++;
            countField.setText(String.valueOf(recordCount));
            clearFields();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String padString(String str, int length) {
        if (str.length() > length) {
            return str.substring(0, length);
        } else {
            StringBuilder padded = new StringBuilder(str);
            while (padded.length() < length) {
                padded.append(" ");
            }
            return padded.toString();
        }
    }

    private void clearFields() {
        nameField.setText("");
        descriptionField.setText("");
        idField.setText("");
        costField.setText("");
    }

    public static void main(String[] args) {
        new RandProductMaker();
    }
}
