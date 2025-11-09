import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

// GUI class
public class MovieTicketsGUI extends JFrame {

    private JComboBox<String> cbMovies;
    private JTextField txtTickets, txtPrice;
    private JTextArea txtReport;
    private MovieTickets movieTickets; // your logic class

    public MovieTicketsGUI() {
        // Window setup
        setTitle("Movie Ticket Sales");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        movieTickets = new MovieTickets(); // instantiate logic class

        // ComboBox for movies
        cbMovies = new JComboBox<>(new String[]{"Napoleon", "Oppenheimer", "Damsel"});
        add(new JLabel("Select Movie:"));
        add(cbMovies);

        // Number of tickets input
        txtTickets = new JTextField(10);
        add(new JLabel("Number of Tickets:"));
        add(txtTickets);

        // Ticket price input
        txtPrice = new JTextField(10);
        add(new JLabel("Ticket Price:"));
        add(txtPrice);

        // Report text area
        txtReport = new JTextArea(10, 30);
        txtReport.setEditable(false);
        add(new JScrollPane(txtReport));

        // Menu
        createMenu();

        setVisible(true);
    }

    // Create JMenuBar
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

        // File → Exit
        JMenu menuFile = new JMenu("File");
        JMenuItem menuExit = new JMenuItem("Exit");
        menuExit.addActionListener(e -> System.exit(0));
        menuFile.add(menuExit);

        // Tools → Process, Clear
        JMenu menuTools = new JMenu("Tools");
        JMenuItem menuProcess = new JMenuItem("Process");
        JMenuItem menuClear = new JMenuItem("Clear");

        menuProcess.addActionListener(e -> processTicket());
        menuClear.addActionListener(e -> clearFields());

        menuTools.add(menuProcess);
        menuTools.add(menuClear);

        menuBar.add(menuFile);
        menuBar.add(menuTools);

        setJMenuBar(menuBar);
    }

    // Process ticket sales
    private void processTicket() {
        try {
            String movie = (String) cbMovies.getSelectedItem();
            int tickets = Integer.parseInt(txtTickets.getText());
            double price = Double.parseDouble(txtPrice.getText());

            MovieTicketData data = new MovieTicketData(movie, tickets, price);

            if (movieTickets.ValidateData(data)) {
                double total = movieTickets.CalculateTotalTicketPrice(tickets, price);

                String report = "Movie: " + movie +
                        "\nTickets: " + tickets +
                        "\nPrice per Ticket: " + price +
                        "\nTotal (including VAT 14%): " + total;

                txtReport.setText(report);

                // Save report to file
                try (FileWriter writer = new FileWriter("report.txt")) {
                    writer.write(report);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
                }

            } else {
                JOptionPane.showMessageDialog(this, "Invalid data. Please check inputs.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for tickets and price.");
        }
    }

    // Clear input fields
    private void clearFields() {
        cbMovies.setSelectedIndex(0);
        txtTickets.setText("");
        txtPrice.setText("");
        txtReport.setText("");
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MovieTicketsGUI());
    }
}