package rental.form;

import javax.swing.*;
import java.util.Map;

public class BerandaForm extends JFrame {
    private String username;

    public BerandaForm(String username) {
        this.username = username;
        setTitle("Beranda");
        setSize(600, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // === MENU BAR ===
        JMenuBar menuBar = new JMenuBar();
        JMenu menuAkun = new JMenu("Akun anda:  " + username);
        JMenuItem menuLogout = new JMenuItem("Logout");

        menuAkun.add(menuLogout);
        menuBar.add(menuAkun);
        setJMenuBar(menuBar);

        // === KOMPONEN UTAMA ===
        JLabel lblWelcome = new JLabel("Selamat Datang di Beranda");
        JLabel lblWelcome2 = new JLabel("Rental Mobil Abdul");
        JLabel lblWelcome3 = new JLabel("Jl. Jendral Rizky, Tangerang");
        JButton btnKeFormRental = new JButton("Rental Mobil");
        JButton btnLihatStok = new JButton("Lihat Stok Mobil"); // Tombol stok mobil baru

        lblWelcome.setBounds(225, 10, 200, 25);
        lblWelcome2.setBounds(250, 30, 200, 25);
        lblWelcome3.setBounds(223, 50, 200, 25);
        btnKeFormRental.setBounds(60, 100, 160, 30);
        btnLihatStok.setBounds(60, 140, 160, 30); // Tombol stok berada di bawah tombol rental

        add(lblWelcome);
        add(lblWelcome2);
        add(lblWelcome3);
        add(btnKeFormRental);
        add(btnLihatStok); // Tambahkan ke frame

        // === EVENT TOMBOL RENTAL MOBIL ===
        btnKeFormRental.addActionListener(e -> {
            dispose(); // Tutup beranda
            new RentalMobil(username); // Buka form rental
        });

        // === EVENT TOMBOL STOK MOBIL ===
        btnLihatStok.addActionListener(e -> {
            Stok_mobil stokMobil = new Stok_mobil();
            Map<String, Integer> daftarStok = stokMobil.getAllStok();

            StringBuilder pesan = new StringBuilder("STOK MOBIL SAAT INI:\n\n");
            for (Map.Entry<String, Integer> entry : daftarStok.entrySet()) {
                pesan.append(entry.getKey()).append(" : ").append(entry.getValue()).append(" unit\n");
            }

            JOptionPane.showMessageDialog(this, pesan.toString(), "Stok Mobil", JOptionPane.INFORMATION_MESSAGE);
        });

        // === EVENT MENU LOGOUT ===
        menuLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // Tutup beranda
                new LoginForm(); // Kembali ke form login
                JOptionPane.showMessageDialog(null, "Berhasil logout!");
            }
        });

        setVisible(true);
    }
}
