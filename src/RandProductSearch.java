import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandProductSearch extends JFrame {
    private JTextField searchField;
    private JTextArea resultArea;

    public RandProductSearch() {
        setTitle("Random Product Search");
        setSize(700, 700);
        setLayout(new BorderLayout());

        // Initialize components
        searchField = new JTextField(35);
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProduct();
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter Product Name:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void searchProduct() {
        String searchName = searchField.getText().trim().toLowerCase();
        resultArea.setText("");

        try (RandomAccessFile raf = new RandomAccessFile("products.dat", "r")) {
            while (raf.getFilePointer() < raf.length()) {
                String name = raf.readUTF().trim();
                String description = raf.readUTF().trim();
                String id = raf.readUTF().trim();
                double cost = raf.readDouble();

                if (name.toLowerCase().contains(searchName)) {
                    resultArea.append("Name: " + name + "\n");
                    resultArea.append("Description: " + description + "\n");
                    resultArea.append("ID: " + id + "\n");
                    resultArea.append("Cost: $" + cost + "\n\n");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RandProductSearch();
    }
}
