package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginMenu extends GameMenu {
    private JButton loginButton, createButton;
    private JTextField userNameField, passwordField;
    private JLabel welcomeLabel, usernameLabel, passwordLabel;
    private Actions actions;

    public LoginMenu() {
        // setting buttons:
        setBounds(GUIConsts.loginMenu.menuBounds);
        setLayout(null);
        initButtons();
        initLabels();
        initTextFields();

        requestFocus();

    }

    private void initButtons() {
        actions = new Actions();

        loginButton = new JButton("Login");
        loginButton.setBounds(GUIConsts.loginMenu.loginButton_bounds);
        loginButton.addActionListener(actions);
        add(loginButton);

        createButton = new JButton("Sign up");
        createButton.setBounds(GUIConsts.loginMenu.createButton_bounds);
        createButton.addActionListener(actions);
        add(createButton);

    }

    private void initLabels() {
        welcomeLabel = new JLabel("Welcome to HearthStone!!");
        welcomeLabel.setBounds(GUIConsts.loginMenu.welcomeLabel_bounds);
        welcomeLabel.setFont(GUIConsts.loginMenu.welcomeLabel_font);
        add(welcomeLabel);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(GUIConsts.loginMenu.usernameLabel_bounds);
        usernameLabel.setFont(GUIConsts.loginMenu.usernameLabel_font);
        add(usernameLabel);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(GUIConsts.loginMenu.passwordLabel_bounds);
        passwordLabel.setFont(GUIConsts.loginMenu.passwordLabel_font);
        add(passwordLabel);

    }

    private void initTextFields() {
        userNameField = new JTextField();
        userNameField.setBounds(GUIConsts.loginMenu.usernameField_bounds);
        add(userNameField);

        passwordField = new JTextField();
        passwordField.setBounds(GUIConsts.loginMenu.passwordField_bounds);
        add(passwordField);

    }

    private class Actions implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
// checking whether the fields are valid
            String username = userNameField.getText().trim(), password = passwordField.getText().trim();
            if (username.equals("") || password.equals("")) {
                Message.showErrorMessage("Empty Fields", "Please enter username and password");
            } else {
                if (e.getSource() == loginButton) { // asked for login
                    switch (admin.login(username, password)) {
                        case -1: {  // wrong password inserted
                            Message.showErrorMessage("Wrong Password", "Password is incorrect,please try again");
                            break;
                        }
                        case 0: { // account doesn't exist
                            Message.showErrorMessage("No Account", "There is no Account with this Username");
                            break;
                        }
                        case 1: { // login successful,going to the main menu
GameScreen.getInstance().initMenus();
                            GameScreen.getInstance().gotoMenu("main");
                            break;
                        }
                    }

                } else { // asked for sign up
                    switch (admin.signup(username, password)) {
                        case 0:{ // account already exists
                            Message.showErrorMessage("","Account already exists");
                            break;
                        } case 1:{ // sign up successfully
                            GameScreen.getInstance().initMenus();
                            GameScreen.getInstance().gotoMenu("main");
                            break;
                        }
                    }

                }
            }

        }
    }

}

