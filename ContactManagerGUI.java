import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Contact {
    String name;
    String phoneNumber;
    String email;

    Contact(String n, String p, String e) {
        name = n;
        phoneNumber = p;
        email = e;
    }
}

class ContactManager {

    private ArrayList<Contact> contacts = new ArrayList<>();

    void addContact(Contact contact) {
        contacts.add(contact);
        JOptionPane.showMessageDialog(null, "Contact added successfully!");
    }

    void viewContacts() {
        if (contacts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No contacts available.");
            return;
        }

        StringBuilder contactsList = new StringBuilder("Contacts List:\n");
        for (Contact contact : contacts) {
            contactsList.append("Name: ").append(contact.name).append("\n");
            contactsList.append("Phone: ").append(contact.phoneNumber).append("\n");
            contactsList.append("Email: ").append(contact.email).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, contactsList.toString());
    }
}

public class ContactManagerGUI extends JFrame implements ActionListener {

    private final ContactManager contactManager = new ContactManager();
    private final JTextField nameField;
    private final JTextField phoneNumberField;
    private final JTextField emailField;

    public ContactManagerGUI() {
        setTitle("Contact Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        JButton addButton = new JButton("Add Contact");
        JButton viewButton = new JButton("View Contacts");

        addButton.addActionListener(this);
        viewButton.addActionListener(this);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumberField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(addButton);
        panel.add(viewButton);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Contact")) {
            String name = nameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String email = emailField.getText();

            if (!name.isEmpty() && !phoneNumber.isEmpty() && !email.isEmpty()) {
                Contact newContact = new Contact(name, phoneNumber, email);
                contactManager.addContact(newContact);
            } else {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            }
        } else if (e.getActionCommand().equals("View Contacts")) {
            contactManager.viewContacts();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ContactManagerGUI gui = new ContactManagerGUI();
            gui.setVisible(true);
        });
    }
}
