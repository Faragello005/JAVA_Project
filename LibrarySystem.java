import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem extends JFrame {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Book> library = new ArrayList<>();
    static int capacity;

    public LibrarySystem() {
        showWelcomeDialog(); //  show this first

        setTitle("Library System");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null); // to show the screen at the center

        ImageIcon image = new ImageIcon("library.jpeg");
        setIconImage(image.getImage());

        ImageIcon background_image = new ImageIcon("img.png");
        Image img = background_image.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        JLabel background_label = new JLabel(new ImageIcon(img));
        background_label.setLayout(new BorderLayout());
        setContentPane(background_label);


        JLabel title_label = new JLabel(" Welcome to our Library System", JLabel.CENTER);
        title_label.setFont(new Font("Serif", Font.BOLD, 23));
        title_label.setForeground(new Color(0xEDF3D6B5, true));
        //add(title_label, BorderLayout.NORTH);
        //title_label.setLayout( new GridLayout(0,1,0,1));



        // Create buttons
        JButton addButton = new JButton("Add Book");
        JButton removeButton = new JButton("Remove Book");
        JButton searchButton = new JButton("Search Book");
        JButton borrowButton = new JButton("Borrow Book");
        JButton returnButton = new JButton("Return Book");
        JButton viewButton = new JButton("View Status");
        JButton exitButton = new JButton("Exit");


        // Create panel and add buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 5, 5));
        panel.setOpaque(false); // make the panel transparent
        panel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));


        panel.add(addButton);
        panel.add(removeButton);
        panel.add(searchButton); 
        panel.add(borrowButton);
        panel.add(returnButton);
        panel.add(viewButton);
        panel.add(exitButton);

        JPanel centerPanel = new JPanel(new GridBagLayout()); // this will center the inner panel
        centerPanel.setOpaque(false); // make it transparent

        centerPanel.add(panel); // add your buttons panel to the center
        add(centerPanel, BorderLayout.CENTER); // add to main frame
        panel.setLayout(new GridLayout(0, 1, 0, 15)); // 0 rows , 1 column, 0 horizontal gap, 15 vertical gap


        // Add button actions
        addButton.addActionListener(e -> addBookGUI());  // listener to get the action from the button
        removeButton.addActionListener(e -> removeBookGUI());
        searchButton.addActionListener(e -> searchBookGUI());
        borrowButton.addActionListener(e -> borrowBookGUI());
        returnButton.addActionListener(e -> returnBookGUI());
        viewButton.addActionListener(e -> viewStatusGUI());
        exitButton.addActionListener(e -> System.exit(0));


        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color buttonColor = new Color(243, 214, 181, 237);

        for (Component c : panel.getComponents()) {
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                btn.setBackground(buttonColor);
                btn.setFont(buttonFont);
                btn.setFocusPainted(false);
                btn.setPreferredSize(new Dimension(200, 50));


            }
        }

        set_button_icon(addButton, "Add.png");
        set_button_icon(removeButton, "Remove.png");
        set_button_icon(searchButton, "search.png");
        set_button_icon(borrowButton, "borrowpng.png");
        set_button_icon(returnButton, "returnpng.png");
        set_button_icon(viewButton, "view.png");
        set_button_icon(exitButton, "Exit.png");


        setVisible(true);
        showCapacityInputDialog();

    }

    private void set_button_icon(JButton button, String iconPath) {
        ImageIcon original_icon = new ImageIcon(iconPath);
        Image scaledImage = original_icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        button.setIcon(scaledIcon);
        button.setHorizontalAlignment(SwingConstants.LEFT); // icon + text aligned to left
        button.setHorizontalTextPosition(SwingConstants.RIGHT);// text to the right of icon
        button.setIconTextGap(10); // space between icon and text
    }

    private void showWelcomeDialog() {
        JDialog welcomeDialog = new JDialog(this, true);
        welcomeDialog.setUndecorated(true); // remove title bar
        welcomeDialog.setSize(500, 400);
        welcomeDialog.setLocationRelativeTo(null); // center on screen

        // Load and resize image to fit the dialog
        ImageIcon originalIcon = new ImageIcon("welcome.png");
        Image img = originalIcon.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);

        // Set image as the full background
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setLayout(new BorderLayout());

        // Transparent button over the image
        JButton Enter = new JButton("Enter Library");
        Enter.setFocusPainted(false);
        Enter.setFont(new Font("Arial", Font.BOLD, 14));
        Enter.setBackground(new Color(198, 157, 227, 237));
        Enter.addActionListener(e -> welcomeDialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(Enter);
        imageLabel.add(buttonPanel, BorderLayout.SOUTH);

        welcomeDialog.setContentPane(imageLabel);
        welcomeDialog.setVisible(true);
    }


    private void showCapacityInputDialog() {
        // Create the dialog window for capacity input
        JDialog capacityDialog = new JDialog(this, "Set Library Capacity", true);
        capacityDialog.setSize(400, 200);
        capacityDialog.setLocationRelativeTo(this);

        // Panel to hold the content of the dialog
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BorderLayout(10, 10));
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create a title label with bold font
        JLabel titleLabel = new JLabel("Enter the Library Capacity", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0xED422303, true));
        dialogPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a text field for the user to input the capacity
        JTextField capacityField = new JTextField(15);
        capacityField.setFont(new Font("Arial", Font.PLAIN, 14));
        capacityField.setHorizontalAlignment(JTextField.CENTER);  // Center the text
        capacityField.setPreferredSize(new Dimension(150, 30));
        dialogPanel.add(capacityField, BorderLayout.CENTER);

        // Panel for the OK button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Center the button
        buttonPanel.setOpaque(false);

        // Create OK button with custom styling
        JButton okButton = new JButton("Enter");
        okButton.setFont(new Font("Arial", Font.BOLD, 14));
        okButton.setBackground(new Color(0xED422303, true));
        okButton.setForeground(Color.WHITE);  // White text
        okButton.setFocusPainted(false);
        okButton.setPreferredSize(new Dimension(100, 35));

        okButton.addActionListener(e -> {
            try {
                capacity = Integer.parseInt(capacityField.getText());
                if (capacity <= 0) {
                    JOptionPane.showMessageDialog(capacityDialog, "Capacity must be a positive number.");
                    return;
                }
                capacityDialog.dispose(); // close dialog after valid input
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(capacityDialog, "Invalid number. Please enter a valid capacity.");
            }
        });

        buttonPanel.add(okButton);  // Add button to the button panel
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);



        // Set dialog content pane
        capacityDialog.setContentPane(dialogPanel);
        capacityDialog.setVisible(true);  // Show the dialog
    }


    // --- GUI methods to collect input and call original logic ---

    void addBookGUI() {
        String name = JOptionPane.showInputDialog("Enter book name:");
        if (name == null) return;

        String[] options = {"1. Biology", "2. Maths", "3. History", "4. Chemistry", "5. Politics"};
        String choice = (String) JOptionPane.showInputDialog(null, "Choose category:", "Book Category",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == null) return;

        int categoryNumber = Integer.parseInt(choice.substring(0, 1));
        input = new Scanner(name + "\n" + categoryNumber + "\n");
        addBook();
    }

    void removeBookGUI() {
        String id = JOptionPane.showInputDialog("\uD83D\uDC94 Enter book ID to remove:");
        if (id == null) return;
        input = new Scanner(id);
        removeBook();
    }
    void searchBookGUI() {
    String keyword = JOptionPane.showInputDialog("Enter book name to search:");
    if (keyword == null || keyword.trim().isEmpty()) return;

    keyword = keyword.trim().toLowerCase();
    StringBuilder result = new StringBuilder();

    for (Book b : library) {
        if (b.name.toLowerCase().contains(keyword)) {
            result.append("ID: ").append(b.id)
                  .append(", Name: ").append(b.name)
                  .append(", Category: ").append(b.category)
                  .append(", Borrowed: ").append(b.isborrowed).append("\n");

            if (b.isborrowed) {
                result.append("  Borrow Date: ").append(b.borrow_date)
                      .append(", Period: ").append(b.borrow_period).append(" days\n");
            }
        }
    }

    if (result.length() == 0) {
        JOptionPane.showMessageDialog(this, "No matching books found.");
    } else {
        JTextArea textArea = new JTextArea(result.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 200));
        JOptionPane.showMessageDialog(this, scrollPane, "Search Results", JOptionPane.INFORMATION_MESSAGE);
    }
}


void borrowBookGUI() {
    String id = JOptionPane.showInputDialog("Enter book ID to borrow:");
    if (id == null) return;

    try {
        Integer.parseInt(id);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid numeric book ID.");
        return;
    }

    String days = JOptionPane.showInputDialog("\uD83D\uDCC6 Enter number of days:");
    if (days == null) return;

    try {
        Integer.parseInt(days);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number of days.");
        return;
    }

    String date = JOptionPane.showInputDialog("Enter borrow date (e.g. 20/04/2025):");
    if (date == null) return;

    // Date format check
    if (!isValidDate(date)) {
        JOptionPane.showMessageDialog(this, "Invalid date format. Please use dd/MM/yyyy (e.g. 20/04/2025).");
        return;
    }

    input = new Scanner(id + "\n" + days + "\n" + date);
    borrowBook();
}

private boolean isValidDate(String dateStr) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false); // strict check
    try {
        sdf.parse(dateStr);
        return true;
    } catch (ParseException e) {
        return false;
    }
}




    void returnBookGUI() {

        String id = JOptionPane.showInputDialog("Enter book ID to return:" );
        if (id == null) return;
        input = new Scanner(id);
        returnBook();
    }

    void viewStatusGUI() {
        String message = "";
        int totalBorrowed = 0;
        int bio = 0, math = 0, hist = 0, chem = 0, pol = 0;

        if (library.isEmpty()) {  // "this" will show the message at the center of the frame "اللي هو كدة اصلا"
            JOptionPane.showMessageDialog(this, "Library is currently empty.");
            return;
        }

        for (Book b : library) {
            message += "ID: " + b.id + ", Name: " + b.name + ", Category: " + b.category + ", Borrowed: " + b.isborrowed + "\n";
            if (b.isborrowed) {
                message += "  Borrow Date: " + b.borrow_date + ", Period: " + b.borrow_period + " days\n";
                totalBorrowed++;
            }

            if (b.category.equals("Biology")) bio++;
            else if (b.category.equals("Maths")) math++;
            else if (b.category.equals("History")) hist++;
            else if (b.category.equals("Chemistry")) chem++;
            else if (b.category.equals("Politics")) pol++;
        }

        message += "\nBooks per category:\n";
        message += "Biology: " + bio + "\n";
        message += "Maths: " + math + "\n";
        message += "History: " + hist + "\n";
        message += "Chemistry: " + chem + "\n";
        message += "Politics: " + pol + "\n";
        message += "Total borrowed books is: " + totalBorrowed;

        JTextArea textArea = new JTextArea(message);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 200));
        JOptionPane.showMessageDialog(this, scrollPane, "Library Status", JOptionPane.INFORMATION_MESSAGE);
    }

    // --- Original methods ---

    static void addBook() {
    if (library.size() >= capacity) {
        JOptionPane.showMessageDialog(null, "Library is full.");
        return;
    }

    String name = input.nextLine().trim();
    int catNum = input.nextInt();
    input.nextLine();

    // Check for duplicate book name
    for (Book b : library) {
        if (b.name.equalsIgnoreCase(name)) {
            JOptionPane.showMessageDialog(null, "Book with this name already exists.");
            return;
        }
    }

    String category = "";
    if (catNum == 1) category = "Biology";
    else if (catNum == 2) category = "Maths";
    else if (catNum == 3) category = "History";
    else if (catNum == 4) category = "Chemistry";
    else if (catNum == 5) category = "Politics";
    else {
        JOptionPane.showMessageDialog(null, "Invalid category.");
        return;
    }

    Book book = new Book(name, category);
    library.add(book);
    JOptionPane.showMessageDialog(null, "Book added with ID: " + book.id);
}

    

    static void removeBook() {
        try {
            int id = input.nextInt();

            for (int i = 0; i < library.size(); i++) {
                Book b = library.get(i);
                if (b.id == id) {
                    if (b.isborrowed) {
                        JOptionPane.showMessageDialog(null, "Book is borrowed. Cannot remove.");
                        return;
                    } else {
                        library.remove(i);
                        JOptionPane.showMessageDialog(null, "Book removed.");
                        return;
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "Book not found.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
        }
    }
    void searchBook() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter book name to search: ");
    String keyword = scanner.nextLine().toLowerCase();

    boolean found = false;
    for (Book b : library) {
        if (b.name.toLowerCase().contains(keyword)) {
            System.out.println("ID: " + b.id + ", Name: " + b.name + ", Category: " + b.category + ", Borrowed: " + b.isborrowed);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No matching books found.");
    }
}


    static void borrowBook() {
        int id = input.nextInt();
        input.nextLine();

        for (Book b : library) {
            if (b.id == id) {
                if (b.isborrowed) {
                    JOptionPane.showMessageDialog(null, "Book is already borrowed.");
                    return;
                }

                b.borrow_period = input.nextInt();
                if (b.borrow_period > 30) {  // 30-day maximum
                    JOptionPane.showMessageDialog(null,
                            "Maximum borrowing duration is 30 days. reset to 30.");
                    b.borrow_period = 30;
                }
                if (b.borrow_period <= 0) {
                    JOptionPane.showMessageDialog(null, "Borrow period must be positive. Set to 1 day.");
                    b.borrow_period = 1;
                }

                input.nextLine();
                b.borrow_date = input.nextLine();
                b.isborrowed = true;
                JOptionPane.showMessageDialog(null, "Book borrowed.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Book not found.");
    }

    static void returnBook() {
        int id = input.nextInt();

        for (Book b : library) {
            if (b.id == id) {
                if (!b.isborrowed) {
                    JOptionPane.showMessageDialog(null, "Book isn't borrowed.");
                    return;
                }

                b.isborrowed = false;
                b.borrow_date = "";
                b.borrow_period = 0;
                JOptionPane.showMessageDialog(null, "Book returned.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Book not found.");

    }

    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
    }
}
