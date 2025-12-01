import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.border.Border;

public class GymManagementSystem {
    private JFrame frame;
    private List<Member> members = new ArrayList<>();
    private List<Trainer> trainers = new ArrayList<>();
    private List<MembershipPlan> plans = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    // Color scheme
    private final Color BACKGROUND_COLOR = new Color(30, 30, 30);
    private final Color PRIMARY_COLOR = new Color(0, 150, 0);
    private final Color SECONDARY_COLOR = new Color(200, 200, 200);
    private final Color TEXT_COLOR = Color.BLACK;

    // Classes for data storage
    static class Member {
        String name, age, gender, plan, trainer;

        Member(String name, String age, String gender, String plan, String trainer) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.plan = plan;
            this.trainer = trainer;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Plan: " + plan + ", Trainer: " + trainer;
        }
    }

    static class Trainer {
        String name, specialization;

        Trainer(String name, String specialization) {
            this.name = name;
            this.specialization = specialization;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Specialization: " + specialization;
        }
    }

    static class MembershipPlan {
        String name, duration, price;

        MembershipPlan(String name, String duration, String price) {
            this.name = name;
            this.duration = duration;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Duration: " + duration + " months, Price: $" + price;
        }
    }

    static class Product {
        String name, price, description;

        Product(String name, String price, String description) {
            this.name = name;
            this.price = price;
            this.description = description;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Price: $" + price + ", Description: " + description;
        }
    }

    public GymManagementSystem() {
        // Initialize predefined data
        initializeSampleData();

        // Main frame
        frame = new JFrame("Gym Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        applyDarkTheme(frame.getContentPane());
        showWelcomePage();
        frame.setVisible(true);
    }

    private void initializeSampleData() {
        // Initialize predefined trainers
        trainers.add(new Trainer("John Doe", "Weight Training"));
        trainers.add(new Trainer("Jane Smith", "Yoga"));
        trainers.add(new Trainer("Mike Johnson", "Cardio"));

        // Initialize predefined plans
        plans.add(new MembershipPlan("Basic", "1", "30"));
        plans.add(new MembershipPlan("Standard", "3", "75"));
        plans.add(new MembershipPlan("Premium", "6", "120"));
        plans.add(new MembershipPlan("Annual", "12", "200"));

        // Initialize predefined products
        products.add(new Product("Protein Powder", "50", "Whey protein isolate, 5lbs"));
        products.add(new Product("Gym Gloves", "20", "Padded weightlifting gloves"));
        products.add(new Product("Water Bottle", "10", "1L insulated stainless steel"));
        products.add(new Product("Resistance Bands", "25", "Set of 5 with different resistance levels"));
        products.add(new Product("Fitness Tracker", "80", "Heart rate monitor and step counter"));
    }

    private void applyDarkTheme(Container container) {
        container.setBackground(BACKGROUND_COLOR);
        for (Component comp : container.getComponents()) {
            if (comp instanceof Container) {
                applyDarkTheme((Container) comp);
            }
            if (comp instanceof JLabel || comp instanceof JButton) {
                comp.setForeground(TEXT_COLOR);
            }
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setBackground(PRIMARY_COLOR);
                button.setFocusPainted(false);
                button.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR.darker(), 2));
                button.setFont(new Font("Arial", Font.BOLD, 14));
            }
            if (comp instanceof JTextField || comp instanceof JPasswordField) {
                comp.setBackground(new Color(60, 60, 60));
                comp.setForeground(TEXT_COLOR);
                if (comp instanceof JComponent) {
                    ((JComponent) comp).setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR, 1));
                }
            }
            if (comp instanceof JComboBox) {
                JComboBox<?> combo = (JComboBox<?>) comp;
                combo.setBackground(new Color(60, 60, 60));
                combo.setForeground(TEXT_COLOR);
                combo.setRenderer(new DefaultListCellRenderer() {
                    @Override
                    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                            boolean isSelected, boolean cellHasFocus) {
                        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        setBackground(new Color(60, 60, 60));
                        setForeground(TEXT_COLOR);
                        if (isSelected) {
                            setBackground(PRIMARY_COLOR);
                        }
                        return this;
                    }
                });
            }
            if (comp instanceof JTextArea) {
                JTextArea area = (JTextArea) comp;
                area.setBackground(new Color(40, 40, 40));
                area.setForeground(TEXT_COLOR);
                area.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                area.setFont(new Font("Monospaced", Font.PLAIN, 14));
            }
        }
    }

    private void showWelcomePage() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("Welcome to Gym Management System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(PRIMARY_COLOR);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        JButton adminButton = createStyledButton("Admin Login");
        JButton userButton = createStyledButton("User Registration");

        adminButton.addActionListener(e -> showLoginPage());
        userButton.addActionListener(e -> showUserAdmissionForm());

        buttonPanel.add(adminButton);
        buttonPanel.add(userButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(welcomeLabel, gbc);
        gbc.gridy = 1;
        centerPanel.add(buttonPanel, gbc);

        panel.add(centerPanel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR.darker(), 2));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(200, 50));
        return button;
    }

    private void showLoginPage() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Admin Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = createStyledButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.equals("admin") && password.equals("12345")) {
                showAdminMainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showAdminMainMenu() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel titleLabel = new JLabel("Admin Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(PRIMARY_COLOR);

        JButton membersButton = createStyledButton("Manage Members");
        JButton trainersButton = createStyledButton("Manage Trainers");
        JButton plansButton = createStyledButton("Manage Membership Plans");
        JButton productsButton = createStyledButton("Manage Products");
        JButton logoutButton = createStyledButton("Logout");

        membersButton.addActionListener(e -> showMemberManagement());
        trainersButton.addActionListener(e -> showTrainerManagement());
        plansButton.addActionListener(e -> showPlanManagement());
        productsButton.addActionListener(e -> showProductManagement());
        logoutButton.addActionListener(e -> showWelcomePage());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(membersButton, gbc);
        gbc.gridx = 1;
        panel.add(trainersButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(plansButton, gbc);
        gbc.gridx = 1;
        panel.add(productsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(logoutButton, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    // Member Management
    private void showMemberManagement() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Member Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);

        JButton addButton = createStyledButton("Add Member");
        JButton viewButton = createStyledButton("View Members");
        JButton updateButton = createStyledButton("Update Member");
        JButton deleteButton = createStyledButton("Delete Member");
        JButton backButton = createStyledButton("Back to Dashboard");

        addButton.addActionListener(e -> showAddMemberForm());
        viewButton.addActionListener(e -> showViewMembers());
        updateButton.addActionListener(e -> showUpdateMemberForm());
        deleteButton.addActionListener(e -> showDeleteMemberForm());
        backButton.addActionListener(e -> showAdminMainMenu());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(addButton, gbc);
        gbc.gridx = 1;
        panel.add(viewButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(updateButton, gbc);
        gbc.gridx = 1;
        panel.add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showAddMemberForm() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Add New Member");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);

        JTextField nameField = new JTextField(20);
        JTextField ageField = new JTextField(20);
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JComboBox<String> planCombo = new JComboBox<>(plans.stream().map(p -> p.name).toArray(String[]::new));
        JComboBox<String> trainerCombo = new JComboBox<>(trainers.stream().map(t -> t.name).toArray(String[]::new));
        JButton submitButton = createStyledButton("Add Member");
        JButton backButton = createStyledButton("Back");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        panel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        panel.add(genderCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Membership Plan:"), gbc);
        gbc.gridx = 1;
        panel.add(planCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Trainer:"), gbc);
        gbc.gridx = 1;
        panel.add(trainerCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(submitButton, gbc);
        gbc.gridx = 1;
        panel.add(backButton, gbc);

        submitButton.addActionListener(e -> {
            if (nameField.getText().isEmpty() || ageField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            members.add(new Member(
                nameField.getText(),
                ageField.getText(),
                (String) genderCombo.getSelectedItem(),
                (String) planCombo.getSelectedItem(),
                (String) trainerCombo.getSelectedItem()
            ));

            JOptionPane.showMessageDialog(frame, "Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showMemberManagement();
        });

        backButton.addActionListener(e -> showMemberManagement());

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showUpdateMemberForm() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Update Member");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);

        if (members.isEmpty()) {
            JLabel noMembersLabel = new JLabel("No members available to update.");
            noMembersLabel.setForeground(TEXT_COLOR);
            JButton backButton = createStyledButton("Back");
            
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);
            
            gbc.gridy = 1;
            panel.add(noMembersLabel, gbc);
            
            gbc.gridy = 2;
            panel.add(backButton, gbc);

            backButton.addActionListener(e -> showMemberManagement());
        } else {
            JComboBox<String> memberCombo = new JComboBox<>(members.stream().map(m -> m.name).toArray(String[]::new));
            JTextField nameField = new JTextField(20);
            JTextField ageField = new JTextField(20);
            JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
            JComboBox<String> planCombo = new JComboBox<>(plans.stream().map(p -> p.name).toArray(String[]::new));
            JComboBox<String> trainerCombo = new JComboBox<>(trainers.stream().map(t -> t.name).toArray(String[]::new));
            JButton submitButton = createStyledButton("Update Member");
            JButton backButton = createStyledButton("Back");

            // Load selected member data
            memberCombo.addActionListener(e -> {
                int index = memberCombo.getSelectedIndex();
                if (index >= 0) {
                    Member m = members.get(index);
                    nameField.setText(m.name);
                    ageField.setText(m.age);
                    genderCombo.setSelectedItem(m.gender);
                    planCombo.setSelectedItem(m.plan);
                    trainerCombo.setSelectedItem(m.trainer);
                }
            });

            // Load first member's data initially
            if (!members.isEmpty()) {
                Member first = members.get(0);
                nameField.setText(first.name);
                ageField.setText(first.age);
                genderCombo.setSelectedItem(first.gender);
                planCombo.setSelectedItem(first.plan);
                trainerCombo.setSelectedItem(first.trainer);
            }

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 1;
            panel.add(new JLabel("Select Member:"), gbc);
            gbc.gridx = 1;
            panel.add(memberCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(new JLabel("Name:"), gbc);
            gbc.gridx = 1;
            panel.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(new JLabel("Age:"), gbc);
            gbc.gridx = 1;
            panel.add(ageField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            panel.add(new JLabel("Gender:"), gbc);
            gbc.gridx = 1;
            panel.add(genderCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            panel.add(new JLabel("Plan:"), gbc);
            gbc.gridx = 1;
            panel.add(planCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            panel.add(new JLabel("Trainer:"), gbc);
            gbc.gridx = 1;
            panel.add(trainerCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.gridwidth = 1;
            panel.add(submitButton, gbc);
            gbc.gridx = 1;
            panel.add(backButton, gbc);

            submitButton.addActionListener(e -> {
                int index = memberCombo.getSelectedIndex();
                if (index >= 0) {
                    members.set(index, new Member(
                        nameField.getText(),
                        ageField.getText(),
                        (String) genderCombo.getSelectedItem(),
                        (String) planCombo.getSelectedItem(),
                        (String) trainerCombo.getSelectedItem()
                    ));
                    JOptionPane.showMessageDialog(frame, "Member updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    showMemberManagement();
                }
            });
            backButton.addActionListener(e -> showMemberManagement());
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showViewMembers() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("Member List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        if (members.isEmpty()) {
            textArea.append("No members registered yet.");
        } else {
            for (Member m : members) {
                textArea.append(m.toString() + "\n\n");
            }
        }

        JButton backButton = createStyledButton("Back");
        backButton.addActionListener(e -> showMemberManagement());

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showDeleteMemberForm() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Delete Member");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);

        if (members.isEmpty()) {
            JLabel noMembersLabel = new JLabel("No members available to delete.");
            noMembersLabel.setForeground(TEXT_COLOR);
            JButton backButton = createStyledButton("Back");

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            panel.add(noMembersLabel, gbc);

            gbc.gridy = 2;
            panel.add(backButton, gbc);

            backButton.addActionListener(e -> showMemberManagement());
        } else {
            JComboBox<String> memberCombo = new JComboBox<>(members.stream().map(m -> m.name).toArray(String[]::new));
            JButton deleteButton = createStyledButton("Delete Member");
            JButton backButton = createStyledButton("Back");

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 1;
            panel.add(new JLabel("Select Member:"), gbc);
            gbc.gridx = 1;
            panel.add(memberCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            panel.add(deleteButton, gbc);

            gbc.gridy = 3;
            panel.add(backButton, gbc);

            deleteButton.addActionListener(e -> {
                int index = memberCombo.getSelectedIndex();
                if (index >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to delete this member?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        members.remove(index);
                        JOptionPane.showMessageDialog(frame, "Member deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        showMemberManagement();
                    }
                }
            });
            backButton.addActionListener(e -> showMemberManagement());
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    // Trainer Management
    private void showTrainerManagement() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel titleLabel = new JLabel("Trainer Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);

        JButton addButton = createStyledButton("Add Trainer");
        JButton viewButton = createStyledButton("View Trainers");
        JButton updateButton = createStyledButton("Update Trainer");
        JButton deleteButton = createStyledButton("Delete Trainer");
        JButton backButton = createStyledButton("Back to Dashboard");

        addButton.addActionListener(e -> showAddTrainerForm());
        viewButton.addActionListener(e -> showViewTrainers());
        updateButton.addActionListener(e -> showUpdateTrainerForm());
        deleteButton.addActionListener(e -> showDeleteTrainerForm());
        backButton.addActionListener(e -> showAdminMainMenu());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(addButton, gbc);
        gbc.gridx = 1;
        panel.add(viewButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(updateButton, gbc);
        gbc.gridx = 1;
        panel.add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showAddTrainerForm() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Add New Trainer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);

        JTextField nameField = new JTextField(20);
        JTextField specializationField = new JTextField(20);
        JButton submitButton = createStyledButton("Add Trainer");
        JButton backButton = createStyledButton("Back");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Specialization:"), gbc);
        gbc.gridx = 1;
        panel.add(specializationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(submitButton, gbc);
        gbc.gridx = 1;
        panel.add(backButton, gbc);

        submitButton.addActionListener(e -> {
            if (nameField.getText().isEmpty() || specializationField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            trainers.add(new Trainer(nameField.getText(), specializationField.getText()));
            JOptionPane.showMessageDialog(frame, "Trainer added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showTrainerManagement();
        });

        backButton.addActionListener(e -> showTrainerManagement());

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showUpdateTrainerForm() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Update Trainer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);

        if (trainers.isEmpty()) {
            JLabel noTrainersLabel = new JLabel("No trainers available to update.");
            noTrainersLabel.setForeground(TEXT_COLOR);
            JButton backButton = createStyledButton("Back");

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            panel.add(noTrainersLabel, gbc);

            gbc.gridy = 2;
            panel.add(backButton, gbc);

            backButton.addActionListener(e -> showTrainerManagement());
        } else {
            JComboBox<String> trainerCombo = new JComboBox<>(trainers.stream().map(t -> t.name).toArray(String[]::new));
            JTextField nameField = new JTextField(20);
            JTextField specializationField = new JTextField(20);
            JButton submitButton = createStyledButton("Update Trainer");
            JButton backButton = createStyledButton("Back");

            // Load selected trainer data
            trainerCombo.addActionListener(e -> {
                int index = trainerCombo.getSelectedIndex();
                if (index >= 0) {
                    Trainer t = trainers.get(index);
                    nameField.setText(t.name);
                    specializationField.setText(t.specialization);
                }
            });

            // Load first trainer's data initially
            if (!trainers.isEmpty()) {
                Trainer first = trainers.get(0);
                nameField.setText(first.name);
                specializationField.setText(first.specialization);
            }

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 1;
            panel.add(new JLabel("Select Trainer:"), gbc);
            gbc.gridx = 1;
            panel.add(trainerCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(new JLabel("Name:"), gbc);
            gbc.gridx = 1;
            panel.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(new JLabel("Specialization:"), gbc);
            gbc.gridx = 1;
            panel.add(specializationField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 1;
            panel.add(submitButton, gbc);
            gbc.gridx = 1;
            panel.add(backButton, gbc);

            submitButton.addActionListener(e -> {
                int index = trainerCombo.getSelectedIndex();
                if (index >= 0) {
                    trainers.set(index, new Trainer(
                        nameField.getText(),
                        specializationField.getText()
                    ));
                    JOptionPane.showMessageDialog(frame, "Trainer updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    showTrainerManagement();
                }
            });
            backButton.addActionListener(e -> showTrainerManagement());
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showViewTrainers() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("Trainer List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        if (trainers.isEmpty()) {
            textArea.append("No trainers registered yet.");
        } else {
            for (Trainer t : trainers) {
                textArea.append(t.toString() + "\n\n");
            }
        }

        JButton backButton = createStyledButton("Back");
        backButton.addActionListener(e -> showTrainerManagement());

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showDeleteTrainerForm() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Delete Trainer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);

        if (trainers.isEmpty()) {
            JLabel noTrainersLabel = new JLabel("No trainers available to delete.");
            noTrainersLabel.setForeground(TEXT_COLOR);
            JButton backButton = createStyledButton("Back");

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            panel.add(noTrainersLabel, gbc);

            gbc.gridy = 2;
            panel.add(backButton, gbc);

            backButton.addActionListener(e -> showTrainerManagement());
        } else {
            JComboBox<String> trainerCombo = new JComboBox<>(trainers.stream().map(t -> t.name).toArray(String[]::new));
            JButton deleteButton = createStyledButton("Delete Trainer");
            JButton backButton = createStyledButton("Back");

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 1;
            panel.add(new JLabel("Select Trainer:"), gbc);
            gbc.gridx = 1;
            panel.add(trainerCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            panel.add(deleteButton, gbc);

            gbc.gridy = 3;
            panel.add(backButton, gbc);

            deleteButton.addActionListener(e -> {
                int index = trainerCombo.getSelectedIndex();
                if (index >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to delete this trainer?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        trainers.remove(index);
                        JOptionPane.showMessageDialog(frame, "Trainer deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        showTrainerManagement();
                    }
                }
            });
            backButton.addActionListener(e -> showTrainerManagement());
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    // Membership Plan Management
    private void showPlanManagement() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel titleLabel = new JLabel("Membership Plan Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);

        JButton addButton = createStyledButton("Add Plan");
        JButton viewButton = createStyledButton("View Plans");
        JButton updateButton = createStyledButton("Update Plan");
        JButton deleteButton = createStyledButton("Delete Plan");
        JButton backButton = createStyledButton("Back to Dashboard");

        addButton.addActionListener(e -> showAddPlanForm());
        viewButton.addActionListener(e -> showViewPlans());
        updateButton.addActionListener(e -> showUpdatePlanForm());
        deleteButton.addActionListener(e -> showDeletePlanForm());
        backButton.addActionListener(e -> showAdminMainMenu());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(addButton, gbc);
        gbc.gridx = 1;
        panel.add(viewButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(updateButton, gbc);
        gbc.gridx = 1;
        panel.add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showAddPlanForm() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Add New Membership Plan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);

        JTextField nameField = new JTextField(20);
        JTextField durationField = new JTextField(20);
        JTextField priceField = new JTextField(20);
        JButton submitButton = createStyledButton("Add Plan");
        JButton backButton = createStyledButton("Back");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Plan Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Duration (months):"), gbc);
        gbc.gridx = 1;
        panel.add(durationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Price ($):"), gbc);
        gbc.gridx = 1;
        panel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(submitButton, gbc);
        gbc.gridx = 1;
        panel.add(backButton, gbc);

        submitButton.addActionListener(e -> {
            if (nameField.getText().isEmpty() || durationField.getText().isEmpty() || priceField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Integer.parseInt(durationField.getText());
                Double.parseDouble(priceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Duration must be a number and Price must be a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            plans.add(new MembershipPlan(nameField.getText(), durationField.getText(), priceField.getText()));
            JOptionPane.showMessageDialog(frame, "Plan added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showPlanManagement();
        });

        backButton.addActionListener(e -> showPlanManagement());

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showUpdatePlanForm() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Update Membership Plan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);

        if (plans.isEmpty()) {
            JLabel noPlansLabel = new JLabel("No plans available to update.");
            noPlansLabel.setForeground(TEXT_COLOR);
            JButton backButton = createStyledButton("Back");

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            panel.add(noPlansLabel, gbc);

            gbc.gridy = 2;
            panel.add(backButton, gbc);

            backButton.addActionListener(e -> showPlanManagement());
        } else {
            JComboBox<String> planCombo = new JComboBox<>(plans.stream().map(p -> p.name).toArray(String[]::new));
            JTextField nameField = new JTextField(20);
            JTextField durationField = new JTextField(20);
            JTextField priceField = new JTextField(20);
            JButton submitButton = createStyledButton("Update Plan");
            JButton backButton = createStyledButton("Back");

            // Load selected plan data
            planCombo.addActionListener(e -> {
                int index = planCombo.getSelectedIndex();
                if (index >= 0) {
                    MembershipPlan p = plans.get(index);
                    nameField.setText(p.name);
                    durationField.setText(p.duration);
                    priceField.setText(p.price);
                }
            });

            // Load first plan's data initially
            if (!plans.isEmpty()) {
                MembershipPlan first = plans.get(0);
                nameField.setText(first.name);
                durationField.setText(first.duration);
                priceField.setText(first.price);
            }

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 1;
            panel.add(new JLabel("Select Plan:"), gbc);
            gbc.gridx = 1;
            panel.add(planCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(new JLabel("Plan Name:"), gbc);
            gbc.gridx = 1;
            panel.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(new JLabel("Duration (months):"), gbc);
            gbc.gridx = 1;
            panel.add(durationField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            panel.add(new JLabel("Price ($):"), gbc);
            gbc.gridx = 1;
            panel.add(priceField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 1;
            panel.add(submitButton, gbc);
            gbc.gridx = 1;
            panel.add(backButton, gbc);

            submitButton.addActionListener(e -> {
                int index = planCombo.getSelectedIndex();
                if (index >= 0) {
                    try {
                        Integer.parseInt(durationField.getText());
                        Double.parseDouble(priceField.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Duration must be a number and Price must be a valid amount!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    plans.set(index, new MembershipPlan(
                        nameField.getText(),
                        durationField.getText(),
                        priceField.getText()
                    ));
                    JOptionPane.showMessageDialog(frame, "Plan updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    showPlanManagement();
                }
            });
            backButton.addActionListener(e -> showPlanManagement());
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showViewPlans() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("Membership Plans");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        if (plans.isEmpty()) {
            textArea.append("No membership plans available yet.");
        } else {
            for (MembershipPlan p : plans) {
                textArea.append(p.toString() + "\n\n");
            }
        }

        JButton backButton = createStyledButton("Back");
        backButton.addActionListener(e -> showPlanManagement());

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showDeletePlanForm() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Delete Membership Plan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);

        if (plans.isEmpty()) {
            JLabel noPlansLabel = new JLabel("No plans available to delete.");
            noPlansLabel.setForeground(TEXT_COLOR);
            JButton backButton = createStyledButton("Back");

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            panel.add(noPlansLabel, gbc);

            gbc.gridy = 2;
            panel.add(backButton, gbc);

            backButton.addActionListener(e -> showPlanManagement());
        } else {
            JComboBox<String> planCombo = new JComboBox<>(plans.stream().map(p -> p.name).toArray(String[]::new));
            JButton deleteButton = createStyledButton("Delete Plan");
            JButton backButton = createStyledButton("Back");

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 1;
            panel.add(new JLabel("Select Plan:"), gbc);
            gbc.gridx = 1;
            panel.add(planCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            panel.add(deleteButton, gbc);

            gbc.gridy = 3;
            panel.add(backButton, gbc);

            deleteButton.addActionListener(e -> {
                int index = planCombo.getSelectedIndex();
                if (index >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to delete this plan?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        plans.remove(index);
                        JOptionPane.showMessageDialog(frame, "Plan deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        showPlanManagement();
                    }
                }
            });
            backButton.addActionListener(e -> showPlanManagement());
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    // Product Management
    private void showProductManagement() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel titleLabel = new JLabel("Product Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);

        JButton addButton = createStyledButton("Add Product");
        JButton viewButton = createStyledButton("View Products");
        JButton updateButton = createStyledButton("Update Product");
        JButton deleteButton = createStyledButton("Delete Product");
        JButton backButton = createStyledButton("Back to Dashboard");

        addButton.addActionListener(e -> showAddProductForm());
        viewButton.addActionListener(e -> showViewProducts());
        updateButton.addActionListener(e -> showUpdateProductForm());
        deleteButton.addActionListener(e -> showDeleteProductForm());
        backButton.addActionListener(e -> showAdminMainMenu());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(addButton, gbc);
        gbc.gridx = 1;
        panel.add(viewButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(updateButton, gbc);
        gbc.gridx = 1;
        panel.add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showAddProductForm() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Add New Product");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);

        JTextField nameField = new JTextField(20);
        JTextField priceField = new JTextField(20);
        JTextArea descriptionArea = new JTextArea(3, 20);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        JButton submitButton = createStyledButton("Add Product");
        JButton backButton = createStyledButton("Back");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Product Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Price ($):"), gbc);
        gbc.gridx = 1;
        panel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        panel.add(descriptionScroll, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(submitButton, gbc);
        gbc.gridx = 1;
        panel.add(backButton, gbc);

        submitButton.addActionListener(e -> {
            if (nameField.getText().isEmpty() || priceField.getText().isEmpty() || descriptionArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Double.parseDouble(priceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Price must be a valid amount!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            products.add(new Product(nameField.getText(), priceField.getText(), descriptionArea.getText()));
            JOptionPane.showMessageDialog(frame, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showProductManagement();
        });

        backButton.addActionListener(e -> showProductManagement());

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showUpdateProductForm() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Update Product");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);

        if (products.isEmpty()) {
            JLabel noProductsLabel = new JLabel("No products available to update.");
            noProductsLabel.setForeground(TEXT_COLOR);
            JButton backButton = createStyledButton("Back");

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            panel.add(noProductsLabel, gbc);

            gbc.gridy = 2;
            panel.add(backButton, gbc);

            backButton.addActionListener(e -> showProductManagement());
        } else {
            JComboBox<String> productCombo = new JComboBox<>(products.stream().map(p -> p.name).toArray(String[]::new));
            JTextField nameField = new JTextField(20);
            JTextField priceField = new JTextField(20);
            JTextArea descriptionArea = new JTextArea(3, 20);
            JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
            JButton submitButton = createStyledButton("Update Product");
            JButton backButton = createStyledButton("Back");

            // Load selected product data
            productCombo.addActionListener(e -> {
                int index = productCombo.getSelectedIndex();
                if (index >= 0) {
                    Product p = products.get(index);
                    nameField.setText(p.name);
                    priceField.setText(p.price);
                    descriptionArea.setText(p.description);
                }
            });

            // Load first product's data initially
            if (!products.isEmpty()) {
                Product first = products.get(0);
                nameField.setText(first.name);
                priceField.setText(first.price);
                descriptionArea.setText(first.description);
            }

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 1;
            panel.add(new JLabel("Select Product:"), gbc);
            gbc.gridx = 1;
            panel.add(productCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(new JLabel("Product Name:"), gbc);
            gbc.gridx = 1;
            panel.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(new JLabel("Price ($):"), gbc);
            gbc.gridx = 1;
            panel.add(priceField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            panel.add(new JLabel("Description:"), gbc);
            gbc.gridx = 1;
            panel.add(descriptionScroll, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 1;
            panel.add(submitButton, gbc);
            gbc.gridx = 1;
            panel.add(backButton, gbc);

            submitButton.addActionListener(e -> {
                int index = productCombo.getSelectedIndex();
                if (index >= 0) {
                    try {
                        Double.parseDouble(priceField.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Price must be a valid amount!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    products.set(index, new Product(
                        nameField.getText(),
                        priceField.getText(),
                        descriptionArea.getText()
                    ));
                    JOptionPane.showMessageDialog(frame, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    showProductManagement();
                }
            });
            backButton.addActionListener(e -> showProductManagement());
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showViewProducts() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("Product List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        if (products.isEmpty()) {
            textArea.append("No products available yet.");
        } else {
            for (Product p : products) {
                textArea.append(p.toString() + "\n\n");
            }
        }

        JButton backButton = createStyledButton("Back");
        backButton.addActionListener(e -> showProductManagement());

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showDeleteProductForm() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Delete Product");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);

        if (products.isEmpty()) {
            JLabel noProductsLabel = new JLabel("No products available to delete.");
            noProductsLabel.setForeground(TEXT_COLOR);
            JButton backButton = createStyledButton("Back");

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            panel.add(noProductsLabel, gbc);

            gbc.gridy = 2;
            panel.add(backButton, gbc);

            backButton.addActionListener(e -> showProductManagement());
        } else {
            JComboBox<String> productCombo = new JComboBox<>(products.stream().map(p -> p.name).toArray(String[]::new));
            JButton deleteButton = createStyledButton("Delete Product");
            JButton backButton = createStyledButton("Back");

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 1;
            panel.add(new JLabel("Select Product:"), gbc);
            gbc.gridx = 1;
            panel.add(productCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            panel.add(deleteButton, gbc);

            gbc.gridy = 3;
            panel.add(backButton, gbc);

            deleteButton.addActionListener(e -> {
                int index = productCombo.getSelectedIndex();
                if (index >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to delete this product?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        products.remove(index);
                        JOptionPane.showMessageDialog(frame, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        showProductManagement();
                    }
                }
            });
            backButton.addActionListener(e -> showProductManagement());
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    // User Admission Form
    private void showUserAdmissionForm() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("New Member Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);

        JTextField nameField = new JTextField(20);
        JTextField ageField = new JTextField(20);
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JComboBox<String> planCombo = new JComboBox<>(plans.stream().map(p -> p.name).toArray(String[]::new));
        JComboBox<String> trainerCombo = new JComboBox<>(trainers.stream().map(t -> t.name).toArray(String[]::new));
        JButton registerButton = createStyledButton("Register & Pay");
        JButton backButton = createStyledButton("Back");

        // Add plan details display
        JLabel planDetailsLabel = new JLabel(" ");
        planDetailsLabel.setForeground(SECONDARY_COLOR);

        planCombo.addActionListener(e -> {
            int index = planCombo.getSelectedIndex();
            if (index >= 0) {
                MembershipPlan selectedPlan = plans.get(index);
                planDetailsLabel.setText("Plan Details: " + selectedPlan.toString());
            }
        });

        // Add trainer details display
        JLabel trainerDetailsLabel = new JLabel(" ");
        trainerDetailsLabel.setForeground(SECONDARY_COLOR);

        trainerCombo.addActionListener(e -> {
            int index = trainerCombo.getSelectedIndex();
            if (index >= 0) {
                Trainer selectedTrainer = trainers.get(index);
                trainerDetailsLabel.setText("Trainer Details: " + selectedTrainer.toString());
            }
        });

        // Initialize with first item's details
        if (!plans.isEmpty()) {
            planDetailsLabel.setText("Plan Details: " + plans.get(0).toString());
        }

        if (!trainers.isEmpty()) {
            trainerDetailsLabel.setText("Trainer Details: " + trainers.get(0).toString());
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        panel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        panel.add(genderCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Membership Plan:"), gbc);
        gbc.gridx = 1;
        panel.add(planCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel(""), gbc); // Empty label for spacing
        gbc.gridx = 1;
        panel.add(planDetailsLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Preferred Trainer:"), gbc);
        gbc.gridx = 1;
        panel.add(trainerCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel(""), gbc); // Empty label for spacing
        gbc.gridx = 1;
        panel.add(trainerDetailsLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        panel.add(registerButton, gbc);
        gbc.gridx = 1;
        panel.add(backButton, gbc);

        registerButton.addActionListener(e -> {
            if (nameField.getText().isEmpty() || ageField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String name = nameField.getText();
            String age = ageField.getText();
            String gender = (String) genderCombo.getSelectedItem();
            String plan = (String) planCombo.getSelectedItem();
            String trainer = (String) trainerCombo.getSelectedItem();

            // Find the selected plan to get price
            MembershipPlan selectedPlan = plans.stream()
                .filter(p -> p.name.equals(plan))
                .findFirst()
                .orElse(null);

            if (selectedPlan != null) {
                showPaymentOptions(name, age, gender, plan, trainer, Double.parseDouble(selectedPlan.price));
            }
        });

        backButton.addActionListener(e -> showWelcomePage());

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showPaymentOptions(String name, String age, String gender, String plan, String trainer, double amount) {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Payment Options");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);

        JLabel amountLabel = new JLabel("Amount to Pay: $" + amount);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        amountLabel.setForeground(SECONDARY_COLOR);

        JButton upiButton = createStyledButton("Pay via UPI");
        JButton cardButton = createStyledButton("Pay via Credit/Debit Card");
        JButton backButton = createStyledButton("Back");

        upiButton.addActionListener(e -> showUPIPayment(name, age, gender, plan, trainer, amount));
        cardButton.addActionListener(e -> showCardPayment(name, age, gender, plan, trainer, amount));
        backButton.addActionListener(e -> showUserAdmissionForm());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        panel.add(amountLabel, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(upiButton, gbc);
        gbc.gridx = 1;
        panel.add(cardButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showUPIPayment(String name, String age, String gender, String plan, String trainer, double amount) {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("UPI Payment - $" + amount);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // UPI QR code image (placeholder - in a real app, you would use a real QR code)
        JLabel qrCodeLabel = new JLabel("", SwingConstants.CENTER);
        qrCodeLabel.setIcon(new ImageIcon("Scanner.jpg")); // Replace with actual QR code image
        qrCodeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        qrCodeLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        qrCodeLabel.setText("Scan this QR code to pay");
        qrCodeLabel.setForeground(SECONDARY_COLOR);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(BACKGROUND_COLOR);

        JButton paidButton = createStyledButton("I've Paid");
        JButton backButton = createStyledButton("Back");

        paidButton.addActionListener(e -> {
            members.add(new Member(name, age, gender, plan, trainer));
            showThankYouPage();
        });

        backButton.addActionListener(e -> showPaymentOptions(name, age, gender, plan, trainer, amount));

        buttonPanel.add(paidButton);
        buttonPanel.add(backButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(qrCodeLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showCardPayment(String name, String age, String gender, String plan, String trainer, double amount) {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Card Payment - $" + amount);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);

        JTextField cardNumberField = new JTextField(20);
        JTextField cardHolderField = new JTextField(20);
        JTextField expiryField = new JTextField(20);
        JTextField cvvField = new JTextField(20);
        JButton payButton = createStyledButton("Pay Now");
        JButton backButton = createStyledButton("Back");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Card Number:"), gbc);
        gbc.gridx = 1;
        panel.add(cardNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Card Holder Name:"), gbc);
        gbc.gridx = 1;
        panel.add(cardHolderField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Expiry Date (MM/YY):"), gbc);
        gbc.gridx = 1;
        panel.add(expiryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("CVV:"), gbc);
        gbc.gridx = 1;
        panel.add(cvvField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(payButton, gbc);
        gbc.gridx = 1;
        panel.add(backButton, gbc);

        payButton.addActionListener(e -> {
            if (cardNumberField.getText().isEmpty() || cardHolderField.getText().isEmpty() || 
                expiryField.getText().isEmpty() || cvvField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all card details!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Simple validation for demo purposes
            if (cardNumberField.getText().length() < 16) {
                JOptionPane.showMessageDialog(frame, "Card number must be 16 digits!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cvvField.getText().length() < 3) {
                JOptionPane.showMessageDialog(frame, "CVV must be 3 digits!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Process payment (in a real app, this would connect to a payment gateway)
            members.add(new Member(name, age, gender, plan, trainer));
            showThankYouPage();
        });

        backButton.addActionListener(e -> showPaymentOptions(name, age, gender, plan, trainer, amount));

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showThankYouPage() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel thankYouLabel = new JLabel("Thank You for Your Registration!");
        thankYouLabel.setFont(new Font("Arial", Font.BOLD, 24));
        thankYouLabel.setForeground(PRIMARY_COLOR);

        JLabel detailsLabel = new JLabel("Your membership details have been saved.");
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        detailsLabel.setForeground(SECONDARY_COLOR);

        JButton productsButton = createStyledButton("Browse Our Products");
        JButton homeButton = createStyledButton("Back to Home");

        productsButton.addActionListener(e -> showProductPurchasePage());
        homeButton.addActionListener(e -> showWelcomePage());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(thankYouLabel, gbc);

        gbc.gridy = 1;
        panel.add(detailsLabel, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(productsButton, gbc);
        gbc.gridx = 1;
        panel.add(homeButton, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showProductPurchasePage() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("Our Products");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel productsPanel = new JPanel();
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
        productsPanel.setBackground(BACKGROUND_COLOR);

        List<Product> cart = new ArrayList<>();

        for (Product p : products) {
            JPanel productPanel = new JPanel(new BorderLayout());
            productPanel.setBackground(new Color(40, 40, 40));
            productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel nameLabel = new JLabel(p.name);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            nameLabel.setForeground(PRIMARY_COLOR);

            JLabel priceLabel = new JLabel("$" + p.price);
            priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
            priceLabel.setForeground(SECONDARY_COLOR);

            JLabel descLabel = new JLabel(p.description);
            descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            descLabel.setForeground(SECONDARY_COLOR);

            JButton addButton = createStyledButton("Add to Cart");
            addButton.setPreferredSize(new Dimension(120, 30));
            addButton.addActionListener(e -> {
                cart.add(p);
                JOptionPane.showMessageDialog(frame, p.name + " added to cart!", "Cart", JOptionPane.INFORMATION_MESSAGE);
            });

            JPanel infoPanel = new JPanel(new GridLayout(3, 1));
            infoPanel.setBackground(new Color(40, 40, 40));
            infoPanel.add(nameLabel);
            infoPanel.add(priceLabel);
            infoPanel.add(descLabel);

            productPanel.add(infoPanel, BorderLayout.CENTER);
            productPanel.add(addButton, BorderLayout.EAST);

            productsPanel.add(productPanel);
            productsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(BACKGROUND_COLOR);

        JButton checkoutButton = createStyledButton("Proceed to Checkout");
        JButton backButton = createStyledButton("Back");

        checkoutButton.addActionListener(e -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Your cart is empty!", "Cart", JOptionPane.WARNING_MESSAGE);
            } else {
                showCheckoutPage(cart);
            }
        });

        backButton.addActionListener(e -> showWelcomePage());

        bottomPanel.add(backButton);
        bottomPanel.add(checkoutButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showCheckoutPage(List<Product> cart) {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("Checkout");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea cartDetails = new JTextArea();
        cartDetails.setEditable(false);
        cartDetails.setBackground(new Color(40, 40, 40));
        cartDetails.setForeground(SECONDARY_COLOR);
        cartDetails.setFont(new Font("Monospaced", Font.PLAIN, 14));

        double total = 0;
        cartDetails.append(String.format("%-30s %10s\n", "Product", "Price"));
        cartDetails.append("---\n");

        for (Product p : cart) {
            double price = Double.parseDouble(p.price);
            total += price;
            cartDetails.append(String.format("%-30s $%9.2f\n", p.name, price));
        }

        cartDetails.append("---\n");
        cartDetails.append(String.format("%-30s $%9.2f\n", "TOTAL", total));

        JScrollPane scrollPane = new JScrollPane(cartDetails);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel paymentPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        paymentPanel.setBackground(BACKGROUND_COLOR);

        JButton payButton = createStyledButton("Pay Now");
        JButton backButton = createStyledButton("Back to Products");

        // Store total in a final variable for the lambda
        final double finalTotal = total;

        payButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                "Payment successful!\nTotal: $" + String.format("%.2f", finalTotal),
                "Thank You",
                JOptionPane.INFORMATION_MESSAGE);
            showWelcomePage();
        });

        backButton.addActionListener(e -> showProductPurchasePage());

        paymentPanel.add(backButton);
        paymentPanel.add(payButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(paymentPanel, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel for better appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new GymManagementSystem();
        });
    }
}