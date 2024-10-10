import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShadowCiphersRestaurant extends JFrame {
    private JCheckBox[] starterCheckBoxes, soupCheckBoxes, mainCourseCheckBoxes, dessertCheckBoxes, beverageCheckBoxes;
    private JTextArea orderSummary;
    private JLabel totalPriceLabel, gstLabel, serviceChargeLabel, finalPriceLabel;
    private JTextField discountCodeField;
    private JButton applyDiscountButton, submitOrderButton, resetButton, exitButton;
    private double total, gst, serviceCharge, finalPrice, discount;

    public ShadowCiphersRestaurant() {
        setTitle("Shadow Ciphers Restaurant Ordering System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Create menu items
    String[] starters = {
            "Spring Rolls - ₹50", "Samosa - ₹20", "Paneer Tikka - ₹100",
            "Stuffed Mushrooms - ₹80", "Onion Bhaji - ₹30", "Chicken Wings - ₹150",
            "Vegetable Pakora - ₹40", "Dahi Puri - ₹60", "Chili Paneer - ₹90",
            "Bruschetta - ₹70", "Garlic Bread - ₹40", "Nachos - ₹60",
            "Chicken Satay - ₹120", "Fish Fingers - ₹130", "Veg Hakka Noodles - ₹90",
            "Tandoori Broccoli - ₹110", "Prawn Koliwada - ₹160", "Cheese Quesadilla - ₹120",
            "Fried Calamari - ₹140", "Falafel Platter - ₹100", "Stuffed Jalapenos - ₹80",
            "Masala Dosa - ₹60", "Aloo Tikki - ₹30", "Vegetable Spring Roll - ₹50",
            "Hummus with Pita - ₹70", "Cucumber Sandwich - ₹40", "Mini Tacos - ₹80",
            "Chicken Tenders - ₹150", "Vegetable Samosa - ₹20", "Nacho Cheese Fries - ₹90",
            "Buffalo Cauliflower Bites - ₹110", "Stuffed Bell Peppers - ₹120"
    };
    double[] starterPrices = {50, 20, 100, 80, 30, 150, 40, 60, 90, 70, 40, 60, 120, 130, 90, 110, 160, 120, 140, 100, 80, 60, 30, 50, 70, 40, 80, 150, 20, 90, 110, 120};

    String[] soups = {
            "Tomato Soup - ₹40", "Mushroom Soup - ₹50", "Vegetable Soup - ₹30",
            "Chicken Soup - ₹60", "Lentil Soup - ₹40", "Minestrone Soup - ₹50",
            "Spicy Thai Soup - ₹70", "Pumpkin Soup - ₹60", "French Onion Soup - ₹80",
            "Hot and Sour Soup - ₹40", "Cream of Broccoli Soup - ₹50", "Carrot Ginger Soup - ₹60",
            "Chickpea Soup - ₹40", "Beetroot Soup - ₹50", "Corn Chowder - ₹60",
            "Spinach Soup - ₹50", "Pea Soup - ₹40", "Noodle Soup - ₹70",
            "Fish Chowder - ₹90", "Creamy Tomato Basil Soup - ₹70"
    };
    double[] soupPrices = {40, 50, 30, 60, 40, 50, 70, 60, 80, 40, 50, 60, 40, 50, 60, 50, 40, 70, 90, 70};

    String[] mainCourses = {
            "Butter Naan - ₹25","Butter Chicken - ₹250", "Paneer Butter Masala - ₹200", "Chole Bhature - ₹150",
            "Chicken Biryani - ₹180", "Vegetable Biryani - ₹150", "Dal Makhani - ₹130",
            "Pasta Alfredo - ₹200", "Lasagna - ₹250", "Grilled Fish - ₹300",
            "Mixed Vegetable Curry - ₹150", "Palak Paneer - ₹180", "Rogan Josh - ₹240",
            "Thai Green Curry - ₹220", "Tandoori Chicken - ₹270", "Egg Curry - ₹160",
            "Kadai Paneer - ₹210", "Prawn Masala - ₹290", "Paneer Tikka Masala - ₹220",
            "Szechuan Noodles - ₹180", "Vegetable Fried Rice - ₹150",
            "Mutton Biryani - ₹300", "Fish Curry - ₹250", "Vegetable Khichdi - ₹120",
            "Pasta Primavera - ₹180", "Szechuan Fried Rice - ₹180",
            "Paneer Tikka - ₹220", "Kadai Chicken - ₹250", "Chettinad Chicken - ₹270",
            "Vegan Burger - ₹200", "Veg Pizza - ₹230", "Chicken Pizza - ₹250"
    };
    double[] mainCoursePrices = {25,250, 200, 150, 180, 150, 130, 200, 250, 300, 150, 180, 240, 220, 270, 160, 210, 290, 220, 180, 150, 300, 250, 120, 180, 180, 220, 250, 270, 200, 230, 250};

    String[] desserts = {
            "Gulab Jamun - ₹50", "Rasgulla - ₹40", "Kheer - ₹60",
            "Chocolate Mousse - ₹80", "Cheesecake - ₹100", "Fruit Salad - ₹70",
            "Ice Cream (Single Scoop) - ₹50", "Brownie with Ice Cream - ₹90",
            "Carrot Halwa - ₹70", "Pineapple Upside Down Cake - ₹80",
            "Pudding - ₹60", "Ladoo - ₹50", "Tiramisu - ₹120",
            "Custard - ₹60", "Baklava - ₹100", "Macarons - ₹150",
            "Churros - ₹70", "Chocolate Cake - ₹90", "Jalebi - ₹50",
            "Peda - ₹40", "Mango Mousse - ₹80", "Mousse Cake - ₹90",
            "Almond Cake - ₹80", "Coconut Ladoo - ₹50", "Raspberry Tart - ₹100"
    };
    double[] dessertPrices = {50, 40, 60, 80, 100, 70, 50, 90, 70, 80, 60, 50, 120, 60, 100, 150, 70, 90, 50, 40, 80, 90, 80, 50, 100};

    String[] beverages = {
            "Masala Chai - ₹15", "Coffee - ₹20", "Lassi - ₹30",
            "Fresh Lime Soda - ₹30", "Mango Lassi - ₹40", "Mint Lemonade - ₹35",
            "Soft Drink - ₹25", "Iced Tea - ₹30", "Coconut Water - ₹40",
            "Beer - ₹150", "Cocktail - ₹200", "Mocktail - ₹180",
            "Fruit Juice - ₹50", "Hot Chocolate - ₹40", "Thums Up - ₹25",
            "Sprite - ₹25", "Fanta - ₹25", "Watermelon Juice - ₹60",
            "Pineapple Juice - ₹60", "Berry Smoothie - ₹70",
            "Peach Iced Tea - ₹40", "Lemon Iced Tea - ₹40", "Strawberry Milkshake - ₹60",
            "Chocolate Milkshake - ₹60", "Vanilla Milkshake - ₹60", "Cold Brew Coffee - ₹50"
    };
    double[] beveragePrices = {15, 20, 30, 30, 40, 35, 25, 30, 40, 150, 200, 180, 50, 40, 25, 25, 25, 60, 60, 70, 40, 40, 60, 60, 60, 50};


        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2));

        // Add menu sections with checkboxes
        mainPanel.add(createScrollablePanel("Starters", starters, starterPrices));
        mainPanel.add(createScrollablePanel("Soups", soups, soupPrices));
        mainPanel.add(createScrollablePanel("Main Courses", mainCourses, mainCoursePrices));
        mainPanel.add(createScrollablePanel("Desserts", desserts, dessertPrices));
        mainPanel.add(createScrollablePanel("Beverages", beverages, beveragePrices));

        // Add order summary and controls
        orderSummary = new JTextArea(10, 40);
        orderSummary.setEditable(false);
        JScrollPane orderScrollPane = new JScrollPane(orderSummary);
        mainPanel.add(orderScrollPane);

        // Bottom panel for total, discount, and buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(0, 2));

        totalPriceLabel = new JLabel("Total: ₹0.0");
        bottomPanel.add(totalPriceLabel);

        gstLabel = new JLabel("GST (5%): ₹0.0");
        bottomPanel.add(gstLabel);

        serviceChargeLabel = new JLabel("Service Charge (10%): ₹0.0");
        bottomPanel.add(serviceChargeLabel);

        finalPriceLabel = new JLabel("Final Price: ₹0.0");
        bottomPanel.add(finalPriceLabel);

        discountCodeField = new JTextField("Enter discount code (optional)", 20);
        bottomPanel.add(discountCodeField);

        applyDiscountButton = new JButton("Apply Discount");
        applyDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyDiscount(); // Update totals and summary
            }
        });
        bottomPanel.add(applyDiscountButton);

        submitOrderButton = new JButton("Submit Order");
        submitOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitOrder();
            }
        });
        bottomPanel.add(submitOrderButton);

        resetButton = new JButton("Reset Order");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetOrder();
            }
        });
        bottomPanel.add(resetButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });
        bottomPanel.add(exitButton);

        mainPanel.add(bottomPanel);

        // Add main panel to the frame
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createScrollablePanel(String title, String[] items, double[] prices) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 3)); // Change to 3 columns for side-by-side display
        panel.setBorder(BorderFactory.createTitledBorder(title));

        // Create checkboxes for each item
        JCheckBox[] checkBoxes = new JCheckBox[items.length];
        for (int i = 0; i < items.length; i++) {
            checkBoxes[i] = new JCheckBox(items[i]);
            checkBoxes[i].setActionCommand(Double.toString(prices[i]));
            checkBoxes[i].addActionListener(e -> updateOrderSummary()); // Update summary on checkbox change
            panel.add(checkBoxes[i]);
        }

        // Store the checkboxes in arrays for later processing
        if (title.equals("Starters")) starterCheckBoxes = checkBoxes;
        else if (title.equals("Soups")) soupCheckBoxes = checkBoxes;
        else if (title.equals("Main Courses")) mainCourseCheckBoxes = checkBoxes;
        else if (title.equals("Desserts")) dessertCheckBoxes = checkBoxes;
        else if (title.equals("Beverages")) beverageCheckBoxes = checkBoxes;

        return panel;
    }

    private void applyDiscount() {
        String code = discountCodeField.getText().trim();
        discount = 0;

        // Calculate discount based on the provided code
        if (code.equalsIgnoreCase("SAVE10")) {
            discount = total * 0.1; // 10% discount
            JOptionPane.showMessageDialog(this, "Discount of 10% applied!", "Discount", JOptionPane.INFORMATION_MESSAGE);
        } else if (code.equalsIgnoreCase("SAVE20")) {
            discount = total * 0.2; // 20% discount
            JOptionPane.showMessageDialog(this, "Discount of 20% applied!", "Discount", JOptionPane.INFORMATION_MESSAGE);
}else if (code.equalsIgnoreCase("RAJ")) {
            discount = total * 0.5; // 50% discount
            JOptionPane.showMessageDialog(this, "Discount of 50% applied!", "Discount", JOptionPane.INFORMATION_MESSAGE);
}else if (code.equalsIgnoreCase("SHADOWCIPHER")) {
            discount = total * 0.35; // 35% discount
            JOptionPane.showMessageDialog(this, "Discount of 35% applied!", "Discount", JOptionPane.INFORMATION_MESSAGE);
}else if (code.equalsIgnoreCase("MAPLE")) {
            discount = total * 1; // 100% discount
            JOptionPane.showMessageDialog(this, "Discount of 100% applied! HURRAY FREE !!!!! only tax ", "Discount", JOptionPane.INFORMATION_MESSAGE);
        } else if (!code.isEmpty()) {
            JOptionPane.showMessageDialog(this, "This code does not exist.", "Invalid Code", JOptionPane.ERROR_MESSAGE);
            discount = 0; // Reset discount
        }

        updateOrderSummary(); // Update summary to reflect discount
    }

    private void submitOrder() {
        String name = JOptionPane.showInputDialog(this, "Enter your name:");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        updateOrderSummary(); // Ensure the order summary is updated before submission
        String message = "Order Submitted by: " + name + "\n" + orderSummary.getText();
        JOptionPane.showMessageDialog(this, message, "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);

        // Save order to a text file
        saveOrderToFile(name);

        // Thank you message
        JOptionPane.showMessageDialog(this, "Raj Da Dhaba\n\nOrder Karne Ke Liye Dhanyawaad\nKripya Krke Pratiksha Krre", "Thank You", JOptionPane.INFORMATION_MESSAGE);

        resetOrder();
    }

    private void saveOrderToFile(String name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write("Order by: " + name + " at " + timestamp + "\n");
            writer.write(orderSummary.getText() + "\n");
            writer.write("-------------------------------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetOrder() {
        total = 0;
        discount = 0;
        orderSummary.setText("");
        totalPriceLabel.setText("Total: ₹0.0");
        gstLabel.setText("GST (5%): ₹0.0");
        serviceChargeLabel.setText("Service Charge (10%): ₹0.0");
        finalPriceLabel.setText("Final Price: ₹0.0");
        discountCodeField.setText("Enter discount code (optional)");
        clearCheckBoxes(starterCheckBoxes);
        clearCheckBoxes(soupCheckBoxes);
        clearCheckBoxes(mainCourseCheckBoxes);
        clearCheckBoxes(dessertCheckBoxes);
        clearCheckBoxes(beverageCheckBoxes);
    }

    private void clearCheckBoxes(JCheckBox[] checkBoxes) {
        if (checkBoxes != null) {
            for (JCheckBox checkBox : checkBoxes) {
                checkBox.setSelected(false);
            }
        }
    }

    private void updateOrderSummary() {
        orderSummary.setText("");

        // Calculate total from selected items
        total = calculateTotal(starterCheckBoxes) + calculateTotal(soupCheckBoxes) +
                calculateTotal(mainCourseCheckBoxes) + calculateTotal(dessertCheckBoxes) +
                calculateTotal(beverageCheckBoxes);

        gst = total * 0.05; // 5% GST
        serviceCharge = total * 0.10; // 10% service charge
        finalPrice = total + gst + serviceCharge - discount;

        // Update labels
        totalPriceLabel.setText("Total: ₹" + total);
        gstLabel.setText("GST (5%): ₹" + gst);
        serviceChargeLabel.setText("Service Charge (10%): ₹" + serviceCharge);
        finalPriceLabel.setText("Final Price: ₹" + finalPrice);

        // Prepare order summary
        orderSummary.append("Order Summary:\n");
        appendSelectedItems(starterCheckBoxes);
        appendSelectedItems(soupCheckBoxes);
        appendSelectedItems(mainCourseCheckBoxes);
        appendSelectedItems(dessertCheckBoxes);
        appendSelectedItems(beverageCheckBoxes);

        // Display discount if applicable
        if (discount > 0) {
            orderSummary.append("Discount: -₹" + discount + "\n");
        }
        orderSummary.append("Total (after discounts, GST, and service charge): ₹" + finalPrice + "\n");
    }

    private void appendSelectedItems(JCheckBox[] checkBoxes) {
        if (checkBoxes != null) {
            for (JCheckBox checkBox : checkBoxes) {
                if (checkBox.isSelected()) {
                    orderSummary.append(checkBox.getText() + " - ₹" + checkBox.getActionCommand() + "\n");
                }
            }
        }
    }

    private double calculateTotal(JCheckBox[] checkBoxes) {
        double total = 0;
        if (checkBoxes != null) {
            for (JCheckBox checkBox : checkBoxes) {
                if (checkBox.isSelected()) {
                    total += Double.parseDouble(checkBox.getActionCommand());
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        new ShadowCiphersRestaurant();
    }
}
