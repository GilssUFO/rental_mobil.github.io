package rental.form;

import javax.swing.*;
import rental.form.BerandaForm;

public class LoginForm extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public LoginForm() {
        setTitle("Login");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblUser = new JLabel("Username:");
        JLabel lblPass = new JLabel("Password:");
        txtUser = new JTextField();
        txtPass = new JPasswordField();
        btnLogin = new JButton("Login");

        lblUser.setBounds(30, 30, 80, 25);
        txtUser.setBounds(120, 30, 120, 25);
        lblPass.setBounds(30, 70, 80, 25);
        txtPass.setBounds(120, 70, 120, 25);
        btnLogin.setBounds(90, 110, 100, 30);

        add(lblUser);
        add(txtUser);
        add(lblPass);
        add(txtPass);
        add(btnLogin);

        btnLogin.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {
        String username = txtUser.getText();
        String password = new String(txtPass.getPassword());

        if (((username.equals("admin") && password.equals("123")) ||
            (username.equals("admin2") && password.equals("456")))||
            (username.equals("admin3") && password.equals("789"))){
            JOptionPane.showMessageDialog(this, "Login berhasil!");
            dispose(); // tutup form login
            new BerandaForm(username); // lanjut ke beranda
        } else {
            JOptionPane.showMessageDialog(this, "Login gagal! Username/password salah.");
        }
    }

    public static void main(String[] args) {
        new LoginForm(); // mulai dari login
    }
}