package bill;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.NumberFormat;

public class EcommerceApp {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private DefaultListModel<String> cartListModel;
    private JLabel totalLabel;
    private double total = 0;
    private JComboBox<String> categoryComboBox;
    private JTextField searchField;
    private Map<String, Integer> itemQuantities = new HashMap<>();

    private Map<String, Integer> prices = new LinkedHashMap<String, Integer>() {{
        put("Laptop", 50000);
        put("Smartphone", 25000);
        put("Headphones", 2000);
        put("Mouse", 700);
        put("Keyboard", 1500);
        put("Monitor", 12000);
        put("Printer", 8000);
        put("Tablet", 18000);
    }};

    private Map<String, String> categories = new HashMap<String, String>() {{
        put("Laptop", "Electronics");
        put("Smartphone", "Electronics");
        put("Headphones", "Accessories");
        put("Mouse", "Accessories");
        put("Keyboard", "Accessories");
        put("Monitor", "Electronics");
        put("Printer", "Office");
        put("Tablet", "Electronics");
    }};

    public EcommerceApp() {
        frame = new JFrame("E-commerce App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(buildLoginPanel(), "login");
        mainPanel.add(buildShopPanel(), "shop");
        mainPanel.add(buildRegistrationPanel(), "register");

        frame.add(mainPanel);
        frame.setVisible(true);

        cardLayout.show(mainPanel, "login");
    }

    private JPanel buildLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("E-Commerce Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(100, 150, 200));
        registerButton.setForeground(Color.WHITE);

        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 0; panel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(usernameField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(passwordField, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 3; panel.add(loginButton, gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(registerButton, gbc);

        loginButton.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            if (user.equals("user") && pass.equals("1234")) {
                cardLayout.show(mainPanel, "shop");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "register");
        });

        return panel;
    }

    private JPanel buildRegistrationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("New User Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));

        JTextField nameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JPasswordField confirmPasswordField = new JPasswordField(20);
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");

        registerButton.setBackground(new Color(70, 130, 180));
        registerButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(100, 150, 200));
        backButton.setForeground(Color.WHITE);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 0; panel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(passwordField, gbc);
        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4; panel.add(confirmPasswordField, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 5; panel.add(registerButton, gbc);
        gbc.gridx = 1; gbc.gridy = 5; panel.add(backButton, gbc);

        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Registration successful! You can now login.", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(mainPanel, "login");
            }
        });

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "login");
        });

        return panel;
    }

    private JPanel buildShopPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top panel with search and category filter
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(70, 130, 180));
        searchButton.setForeground(Color.WHITE);
        
        String[] categoryOptions = getCategoryOptions();
        categoryComboBox = new JComboBox<>(categoryOptions);
        categoryComboBox.insertItemAt("All Categories", 0);
        categoryComboBox.setSelectedIndex(0);
        
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        
        topPanel.add(searchPanel, BorderLayout.CENTER);
        topPanel.add(categoryComboBox, BorderLayout.EAST);
        
        // Product display panel
        JPanel productPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        cartListModel = new DefaultListModel<>();
        JList<String> cartList = new JList<>(cartListModel);
        cartList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        totalLabel = new JLabel("Total: ₹0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Initialize product display
        displayProducts(productPanel, "");
        
        JScrollPane productScrollPane = new JScrollPane(productPanel);
        productScrollPane.setBorder(BorderFactory.createTitledBorder("Products"));
        
        // Cart panel
        JPanel cartPanel = new JPanel(new BorderLayout(10, 10));
        cartPanel.setBorder(BorderFactory.createTitledBorder("Your Shopping Cart"));
        cartPanel.setPreferredSize(new Dimension(300, 0));
        
        JButton removeButton = new JButton("Remove Selected");
        removeButton.setBackground(new Color(220, 80, 80));
        removeButton.setForeground(Color.WHITE);
        
        JPanel cartButtonPanel = new JPanel(new BorderLayout(5, 5));
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBackground(new Color(70, 130, 180));
        checkoutButton.setForeground(Color.WHITE);
        
        cartButtonPanel.add(removeButton, BorderLayout.WEST);
        cartButtonPanel.add(totalLabel, BorderLayout.CENTER);
        cartButtonPanel.add(checkoutButton, BorderLayout.EAST);
        
        cartPanel.add(new JScrollPane(cartList), BorderLayout.CENTER);
        cartPanel.add(cartButtonPanel, BorderLayout.SOUTH);
        
        // Add components to main panel
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(productScrollPane, BorderLayout.CENTER);
        panel.add(cartPanel, BorderLayout.EAST);
        
        // Event listeners
        searchButton.addActionListener(e -> {
            productPanel.removeAll();
            displayProducts(productPanel, searchField.getText().toLowerCase());
            productPanel.revalidate();
            productPanel.repaint();
        });
        
        categoryComboBox.addActionListener(e -> {
            productPanel.removeAll();
            displayProducts(productPanel, searchField.getText().toLowerCase());
            productPanel.revalidate();
            productPanel.repaint();
        });
        
        removeButton.addActionListener(e -> {
            int selectedIndex = cartList.getSelectedIndex();
            if (selectedIndex != -1) {
                String item = cartListModel.get(selectedIndex);
                // Extract product name and price
                String[] parts = item.split(" - ₹");
                String productName = parts[0].replaceAll(" \\(\\d+\\)$", "");
                double price = Double.parseDouble(parts[1]);
                
                // Update quantity
                int quantity = itemQuantities.getOrDefault(productName, 1);
                if (quantity > 1) {
                    itemQuantities.put(productName, quantity - 1);
                    // Update the item in cart
                    cartListModel.set(selectedIndex, productName + " (" + (quantity-1) + ") - ₹" + (price/(quantity)*(quantity-1)));
                } else {
                    itemQuantities.remove(productName);
                    cartListModel.remove(selectedIndex);
                }
                
                total -= price/quantity;
                updateTotalLabel();
            }
        });
        
        checkoutButton.addActionListener(e -> {
            if (cartListModel.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Your cart is empty.", "Cart Empty", JOptionPane.WARNING_MESSAGE);
            } else {
                int option = JOptionPane.showConfirmDialog(frame, 
                    "Proceed to checkout? Total: " + formatCurrency(total), 
                    "Confirm Order", 
                    JOptionPane.YES_NO_OPTION);
                
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, 
                        "Order placed successfully!\nTotal: " + formatCurrency(total), 
                        "Thank You", 
                        JOptionPane.INFORMATION_MESSAGE);
                    cartListModel.clear();
                    itemQuantities.clear();
                    total = 0;
                    updateTotalLabel();
                }
            }
        });
        
        return panel;
    }
    
    private void displayProducts(JPanel productPanel, String searchQuery) {
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        
        for (String product : prices.keySet()) {
            // Filter by category
            if (!selectedCategory.equals("All Categories") && !categories.get(product).equals(selectedCategory)) {
                continue;
            }
            
            // Filter by search query
            if (!searchQuery.isEmpty() && !product.toLowerCase().contains(searchQuery)) {
                continue;
            }
            
            JPanel itemPanel = new JPanel(new BorderLayout(5, 5));
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            itemPanel.setBackground(Color.WHITE);
            
            // Simulate product image (in a real app, you would load actual images)
            JLabel imageLabel = new JLabel(product, JLabel.CENTER);
            imageLabel.setPreferredSize(new Dimension(150, 150));
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            imageLabel.setOpaque(true);
            imageLabel.setBackground(new Color(240, 240, 240));
            imageLabel.setFont(new Font("Arial", Font.BOLD, 16));
            
            JLabel priceLabel = new JLabel(formatCurrency(prices.get(product)), JLabel.CENTER);
            priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
            
            JButton addButton = new JButton("Add to Cart");
            addButton.setBackground(new Color(70, 130, 180));
            addButton.setForeground(Color.WHITE);
            
            JPanel infoPanel = new JPanel(new BorderLayout(5, 5));
            infoPanel.add(new JLabel(product, JLabel.CENTER), BorderLayout.NORTH);
            infoPanel.add(priceLabel, BorderLayout.CENTER);
            infoPanel.add(addButton, BorderLayout.SOUTH);
            
            itemPanel.add(imageLabel, BorderLayout.CENTER);
            itemPanel.add(infoPanel, BorderLayout.SOUTH);
            
            addButton.addActionListener(e -> {
                // Check if item already in cart
                boolean exists = false;
                for (int i = 0; i < cartListModel.size(); i++) {
                    String item = cartListModel.get(i);
                    if (item.startsWith(product + " ")) {
                        exists = true;
                        // Update quantity
                        int quantity = itemQuantities.getOrDefault(product, 1) + 1;
                        itemQuantities.put(product, quantity);
                        
                        // Calculate new price
                        double newPrice = prices.get(product) * quantity;
                        
                        // Update the item in cart
                        cartListModel.set(i, product + " (" + quantity + ") - " + formatCurrency(newPrice));
                        break;
                    }
                }
                
                if (!exists) {
                    itemQuantities.put(product, 1);
                    cartListModel.addElement(product + " - " + formatCurrency(prices.get(product)));
                }
                
                total += prices.get(product);
                updateTotalLabel();
            });
            
            productPanel.add(itemPanel);
        }
    }
    
    private String[] getCategoryOptions() {
        Set<String> uniqueCategories = new HashSet<>(categories.values());
        return uniqueCategories.toArray(new String[0]);
    }
    
    private void updateTotalLabel() {
        totalLabel.setText("Total: " + formatCurrency(total));
    }
    
    private String formatCurrency(double amount) {
        return NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(amount);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new EcommerceApp();
        });
    }
}