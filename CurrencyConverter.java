import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverter extends JFrame implements ActionListener {

    private JLabel amountLabel, fromLabel, toLabel, resultLabel;
    private JTextField amountTextField, resultTextField;
    private JComboBox<String> fromComboBox, toComboBox;
    private JButton jButton;

    private final String[] CURRENCIES = { "INR", "USD", "EUR", "GBP", "CAD", "AUD", "JPY", "CHF", "CNY", "HKD", "KRW",
            "MXN", "NOK", "NZD", "SEK", "SGD", "THB", "TRY", "ZAR" };
    private final double[] EXCHANGE_RATES = { 1, 82.53, 89.48, 104.73, 60.87, 53.32, 0.57, 93.92, 11.37, 10.53, 0.062,
            4.91, 7.77, 49.28, 7.55, 61.02, 2.36, 3.03, 4.46 };

    Border blackLine = BorderFactory.createLineBorder(Color.black);

    public CurrencyConverter() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 20, 40));
        panel.setBorder(blackLine);
        panel.setBackground(new Color(224, 224, 224));

        amountLabel = new JLabel("Amount");
        fromLabel = new JLabel("From");
        toLabel = new JLabel("To");
        resultLabel = new JLabel("Result");

        amountTextField = new JTextField();
        resultTextField = new JTextField();
        resultTextField.setEditable(false);

        fromComboBox = new JComboBox<>(CURRENCIES);
        toComboBox = new JComboBox<>(CURRENCIES);

        jButton = new JButton("Convert");
        jButton.addActionListener(this);

        panel.add(amountLabel);
        panel.add(amountTextField);
        panel.add(fromLabel);
        panel.add(fromComboBox);
        panel.add(toLabel);
        panel.add(toComboBox);
        panel.add(resultLabel);
        panel.add(resultTextField);
        add(panel, BorderLayout.CENTER);
        add(jButton, BorderLayout.SOUTH);

    }


    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jButton) {
            try {

                double amount = Double.parseDouble(amountTextField.getText());
                int fromIndex = fromComboBox.getSelectedIndex();
                int toIndex = toComboBox.getSelectedIndex();

                double result = amount * EXCHANGE_RATES[fromIndex] / EXCHANGE_RATES[toIndex];
                DecimalFormat df = new DecimalFormat("#.##");

                resultTextField.setText(df.format(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please Enter Valid Amount");
            }
        }
    }

    public static void main(String[] args) {

        try {

            CurrencyConverter currencyConverter = new CurrencyConverter();

            currencyConverter.setTitle("Currency Converter Application");
            currencyConverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            currencyConverter.setSize(400, 400);
            currencyConverter.setLocationRelativeTo(null);
            currencyConverter.setVisible(true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
}