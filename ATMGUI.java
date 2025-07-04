import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMGUI extends JFrame implements ActionListener {

    // Default user details
    private final String USER_ID = "123456";
    private final String PIN = "7890";
    private double balance = 10000.00;

    // Components
    private JTextField userField;
    private JPasswordField pinField;
    private JFrame loginFrame, menuFrame;

    public ATMGUI() {
        createLoginScreen();
    }

    // Login Window
    private void createLoginScreen() {
        loginFrame = new JFrame("ATM Login");
        loginFrame.setSize(300, 200);
        loginFrame.setLayout(new GridLayout(4, 1));

        userField = new JTextField();
        pinField = new JPasswordField();

        loginFrame.add(new JLabel("Enter User ID:"));
        loginFrame.add(userField);
        loginFrame.add(new JLabel("Enter PIN:"));
        loginFrame.add(pinField);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(this);
        loginFrame.add(loginBtn);

        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        loginFrame.add(new JLabel("By: Ashirwad Shukla !"));
    }

    // Main ATM Menu
    private void createMenuScreen() {
        menuFrame = new JFrame("ATM Menu");
        menuFrame.setSize(400, 300);
        menuFrame.setLayout(new GridLayout(5, 1));

        JLabel label = new JLabel("Welcome to Ashirwad ATM", JLabel.CENTER);
        JButton balanceBtn = new JButton("Check Balance");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton exitBtn = new JButton("Exit");

        balanceBtn.addActionListener(e -> showMessage("Balance: ₹" + balance));
        depositBtn.addActionListener(e -> handleDeposit());
        withdrawBtn.addActionListener(e -> handleWithdraw());
        exitBtn.addActionListener(e -> {
            menuFrame.dispose();
            JOptionPane.showMessageDialog(null, "Thank you for using Java ATM!");
            System.exit(0);
        });

        menuFrame.add(label);
        menuFrame.add(balanceBtn);
        menuFrame.add(depositBtn);
        menuFrame.add(withdrawBtn);
        menuFrame.add(exitBtn);

        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
    }

    // Handle Login Button
    public void actionPerformed(ActionEvent e) {
        String enteredUser = userField.getText();
        String enteredPin = new String(pinField.getPassword());

        if (enteredUser.equals(USER_ID) && enteredPin.equals(PIN)) {
            loginFrame.dispose();
            createMenuScreen();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid User ID or PIN", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Deposit functionality
    private void handleDeposit() {
        String input = JOptionPane.showInputDialog("Enter amount to deposit:");
        try {
            double amount = Double.parseDouble(input);
            if (amount > 0) {
                balance += amount;
                showMessage("₹" + amount + " deposited successfully!");
            } else {
                showMessage("Invalid amount entered.");
            }
        } catch (NumberFormatException ex) {
            showMessage("Please enter a valid number.");
        }
    }

    // Withdraw functionality
    private void handleWithdraw() {
        String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
        try {
            double amount = Double.parseDouble(input);
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                showMessage("₹" + amount + " withdrawn successfully!");
            } else {
                showMessage("Invalid or insufficient balance.");
            }
        } catch (NumberFormatException ex) {
            showMessage("Please enter a valid number.");
        }
    }

    // Show info messages
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(menuFrame, message);
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMGUI());
    }
}
