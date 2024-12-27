import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DistanceConverter {
    private JFrame frame;
    private JComboBox<String> unitFrom;
    private JComboBox<String> unitTo;
    private JTextField inputField;
    private JLabel resultLabel;
    private JLabel factLabel;
    private JLabel imageLabel;

    public DistanceConverter() {
        createUI();
    }

    private void createUI() {
        // Frame setup
        frame = new JFrame("Distance Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);

        // Background setup
        JLabel background = new JLabel();
        background.setBounds(0, 0, 2000, 900);
        background.setIcon(new ImageIcon("background.jpeg")); // Use a background image here
        frame.add(background);

        // Title
        JLabel titleLabel = new JLabel("Distance Converter", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        titleLabel.setBounds(0, 10, 800, 50);
        titleLabel.setForeground(Color.BLACK);
        background.add(titleLabel);

        // Unit selection
        JLabel fromLabel = new JLabel("Convert from:");
        fromLabel.setFont(new Font("Arial", Font.BOLD, 18));
        fromLabel.setBounds(50, 100, 200, 30);
        fromLabel.setForeground(Color.BLACK);
        background.add(fromLabel);

        unitFrom = new JComboBox<>(new String[]{"Kilometers", "Meters", "Miles", "Feet"});
        unitFrom.setBounds(300, 100, 200, 40);
        unitFrom.setFont(new Font("Arial", Font.PLAIN, 16));
        unitFrom.addActionListener(e -> updateFactAndImage());
        background.add(unitFrom);

        JLabel toLabel = new JLabel("Convert to:");
        toLabel.setFont(new Font("Arial", Font.BOLD, 18));
        toLabel.setBounds(50, 160, 200, 30);
        toLabel.setForeground(Color.BLACK);
        background.add(toLabel);

        unitTo = new JComboBox<>(new String[]{"Kilometers", "Meters", "Miles", "Feet"});
        unitTo.setBounds(300, 160, 200, 40);
        unitTo.setFont(new Font("Arial", Font.PLAIN, 16));
        background.add(unitTo);

        // Input field
        JLabel inputLabel = new JLabel("Enter value:");
        inputLabel.setFont(new Font("Arial", Font.BOLD, 18));
        inputLabel.setBounds(50, 220, 200, 30);
        inputLabel.setForeground(Color.BLACK);
        background.add(inputLabel);

        inputField = new JTextField();
        inputField.setBounds(300, 220, 200, 40);
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                performConversion();
            }
        });
        background.add(inputField);

        // Result label
        resultLabel = new JLabel("Result: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultLabel.setBounds(50, 280, 700, 40);
        resultLabel.setForeground(Color.BLACK);
        background.add(resultLabel);

        // Fact label
        factLabel = new JLabel("Fun Fact: ", SwingConstants.LEFT);
        factLabel.setFont(new Font("Calibri", Font.ITALIC | Font.BOLD, 22));
        factLabel.setBounds(50, 340, 700, 80);
        factLabel.setForeground(Color.BLACK);
        background.add(factLabel);

        // Image label
        imageLabel = new JLabel();
        imageLabel.setBounds(550, 100, 200, 200);
        background.add(imageLabel);

        frame.setVisible(true);
    }

    private void performConversion() {
        try {
            double input = Double.parseDouble(inputField.getText());
            String from = (String) unitFrom.getSelectedItem();
            String to = (String) unitTo.getSelectedItem();
            double result = convertDistance(input, from, to);
            resultLabel.setText("Result: " + result + " " + to);
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid number");
        }
    }

    private double convertDistance(double value, String from, String to) {
        double factorFrom = getConversionFactor(from);
        double factorTo = getConversionFactor(to);
        return value * (factorFrom / factorTo);
    }

    private double getConversionFactor(String unit) {
        switch (unit) {
            case "Kilometers": return 1000;
            case "Meters": return 1;
            case "Miles": return 1609.34;
            case "Feet": return 0.3048;
            default: return 1;
        }
    }

    private void updateFactAndImage() {
        String selectedUnit = (String) unitFrom.getSelectedItem();
        String fact;
        String imagePath;

        switch (selectedUnit) {
            case "Kilometers":
                fact = "1 kilometer is approximately 0.62 miles. Fun fact: The Great Wall of China is about 21,196 kilometers long!";
                imagePath = "kilometers.png";
                break;
            case "Meters":
                fact = "1 meter is the length of a typical guitar. It's also the SI base unit of length.";
                imagePath = "meters.png";
                break;
            case "Miles":
                fact = "1 mile is approximately 1.6 kilometers. The longest road in the world is the Pan-American Highway at 19,000 miles!";
                imagePath = "miles.png";
                break;
            case "Feet":
                fact = "1 foot is 12 inches. Did you know? The Statue of Liberty stands 305 feet tall!";
                imagePath = "feet.png";
                break;
            default:
                fact = "Select a unit to see a fun fact!";
                imagePath = null;
        }

        factLabel.setText("<html><b><i><u>Fun Fact:</u></i></b><br><u>" + fact + "</u></html>");
        if (imagePath != null) {
            imageLabel.setIcon(new ImageIcon(imagePath));
        } else {
            imageLabel.setIcon(null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DistanceConverter::new);
    }
}
