package rental.form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;

public class RentalMobil extends JFrame {
    private String username;
    private Stok_mobil stokMobil = new Stok_mobil();

    JLabel lblNama = new JLabel("Nama Penyewa:");
    JLabel lblMobil = new JLabel("Nama Mobil:");
    JLabel lblHari = new JLabel("Lama Sewa (hari):");
    JLabel lblDiscount = new JLabel("Diskon (%):");
    JLabel lblNIK = new JLabel("NIK:");
    JLabel lblAlamat = new JLabel("Alamat:");
    JLabel lblFileKTP = new JLabel("Belum ada file dipilih");
    JLabel lblFileSIM = new JLabel("Belum ada file dipilih");

    JTextField txtNama = new JTextField();
    JTextField txtNIK = new JTextField();
    JTextField txtAlamat = new JTextField();
    JTextField txtHari = new JTextField();
    JTextField txtDiscount = new JTextField();

    String[] daftarMobil = {
        "Toyota Alphard = Rp.1.800.000/hari",
        "Toyota Avanza = Rp.1.000.000/hari",
        "BMW M4 = Rp.3.500.000/hari",
        "Mitsubishi Xpander = Rp.1.000.000/hari",
        "Nissan ALL New Grand Livina = Rp.1.500.000/hari",
        "Honda Brio = Rp.800.000/hari",
        "Honda Jazz = Rp.1.200.000/hari",
        "Honda BR-V = RP.750.000/hari",
        "Daihatsu Xenia = RP.1.000.000/hari",
        "Tesla Cybertruck = Rp.10.000.000/hari"
    };
    JComboBox<String> cmbMobil = new JComboBox<>(daftarMobil);

    JButton btnHitung = new JButton("Hitung Total");
    JButton btnSimpan = new JButton("Simpan Data");
    JButton btnBersih = new JButton("Bersih");
    JButton btnLoadData = new JButton("Load Data");
    JButton btnReturn = new JButton("Kembali");
    JButton btnUploadKTP = new JButton("Upload KTP");
    JButton btnUploadSIM = new JButton("Upload SIM");

    JTextArea areaHasil = new JTextArea();
    JScrollPane scrollArea = new JScrollPane(areaHasil);

    DefaultTableModel model = new DefaultTableModel(new String[]{
        "Nama", "NIK", "Alamat", "Mobil", "Hari", "Diskon", "Total", "File KTP / SIM"
    }, 0);
    JTable table = new JTable(model);
    JScrollPane scrollTable = new JScrollPane(table);

    int totalBiaya = 0;
    File fileKTP = null;
    File fileSIM = null;

    public RentalMobil(String username) {
        this.username = username;
        setTitle("Aplikasi Rental Mobil");
        setSize(900, 800);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblNama.setBounds(20, 20, 120, 25);
        txtNama.setBounds(150, 20, 200, 25);
        lblNIK.setBounds(20, 60, 120, 25);
        txtNIK.setBounds(150, 60, 200, 25);
        lblAlamat.setBounds(20, 100, 120, 25);
        txtAlamat.setBounds(150, 100, 200, 25);
        lblMobil.setBounds(20, 140, 120, 25);
        cmbMobil.setBounds(150, 140, 200, 25);
        lblHari.setBounds(20, 180, 120, 25);
        txtHari.setBounds(150, 180, 200, 25);
        lblDiscount.setBounds(20, 220, 120, 25);
        txtDiscount.setBounds(150, 220, 120, 25);
        btnUploadKTP.setBounds(300, 220, 120, 30);
        lblFileKTP.setBounds(430, 220, 300, 25);
        btnUploadSIM.setBounds(300, 260, 120, 30);
        lblFileSIM.setBounds(430, 260, 300, 25);

        btnHitung.setBounds(20, 300, 120, 30);
        btnSimpan.setBounds(160, 300, 120, 30);
        btnBersih.setBounds(300, 300, 90, 30);
        btnLoadData.setBounds(410, 300, 120, 30);
        btnReturn.setBounds(20, 700, 200, 30);

        scrollArea.setBounds(20, 340, 800, 80);
        areaHasil.setEditable(false);

        scrollTable.setBounds(20, 430, 850, 280);

        add(lblNama); add(txtNama);
        add(lblNIK); add(txtNIK);
        add(lblAlamat); add(txtAlamat);
        add(lblMobil); add(cmbMobil);
        add(lblHari); add(txtHari);
        add(lblDiscount); add(txtDiscount);
        add(btnUploadKTP); add(lblFileKTP);
        add(btnUploadSIM); add(lblFileSIM);
        add(btnHitung); add(btnSimpan); add(btnBersih); add(btnLoadData); add(btnReturn);
        add(scrollArea); add(scrollTable);

        btnUploadKTP.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                fileKTP = fileChooser.getSelectedFile();
                lblFileKTP.setText(fileKTP.getName());
            } else {
                lblFileKTP.setText("Belum ada file dipilih");
            }
        });

        btnUploadSIM.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                fileSIM = fileChooser.getSelectedFile();
                lblFileSIM.setText(fileSIM.getName());
            } else {
                lblFileSIM.setText("Belum ada file dipilih");
            }
        });

        btnHitung.addActionListener(e -> {
            try {
                String nama = txtNama.getText();
                String alamat = txtAlamat.getText();
                int NIK = Integer.parseInt(txtNIK.getText());
                int hari = Integer.parseInt(txtHari.getText());
                int discount = Integer.parseInt(txtDiscount.getText());
                int hargaPerHari;

                  switch (cmbMobil.getSelectedIndex()) {
                    case 0:
                        hargaPerHari = 1800000;
                        break;
                    case 1:
                        hargaPerHari = 1000000;
                        break;
                    case 2:
                        hargaPerHari = 3500000;
                        break;
                    case 3:
                        hargaPerHari = 1000000;
                        break;
                    case 4:
                        hargaPerHari = 1500000;
                        break;
                    case 5:
                        hargaPerHari = 800000;
                        break;
                    case 6:
                        hargaPerHari = 1200000;
                        break;
                    case 7:
                        hargaPerHari = 750000;
                        break;
                    case 8:
                        hargaPerHari = 1000000;
                        break;
                    case 9:
                        hargaPerHari = 10000000;
                        break;
                    default:
                        hargaPerHari = 0;

                };

                totalBiaya = hargaPerHari * hari;
                if (discount > 0) {
                    totalBiaya -= (totalBiaya * discount / 100);
                }

                areaHasil.setText("Nama: " + nama +
                        "\nNIK: " + NIK +
                        "\nAlamat: " + alamat +
                        "\nMobil: " + cmbMobil.getSelectedItem() +
                        "\nLama Sewa: " + hari + " hari" +
                        "\nDiskon: " + discount + "%" +
                        "\nTotal Biaya: Rp" + totalBiaya);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Input tidak valid! Pastikan NIK, lama sewa, dan diskon adalah angka.");
            }
        });

        btnSimpan.addActionListener(e -> {
            String nama = txtNama.getText();
            String NIK = txtNIK.getText();
            String alamat = txtAlamat.getText();
            String mobilPilihan = (String) cmbMobil.getSelectedItem();
            String namaMobil = mobilPilihan.split("=")[0].trim();
            String hari = txtHari.getText();
            String discount = txtDiscount.getText();

            if (nama.isEmpty() || NIK.isEmpty() || alamat.isEmpty() || hari.isEmpty() || discount.isEmpty() || totalBiaya == 0 || fileKTP == null || fileSIM == null) {
                JOptionPane.showMessageDialog(null, "Lengkapi semua data dan upload file terlebih dahulu!");
                return;
            }

            int stokTersedia = stokMobil.getStok(namaMobil);
            if (stokTersedia <= 0) {
                JOptionPane.showMessageDialog(null, "Stok untuk mobil " + namaMobil + " sudah habis!");
                return;
            }

            stokMobil.kurangiStok(namaMobil);

            try (FileWriter fw = new FileWriter("data_rental.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw)) {

                String data = nama + "," + NIK + "," + alamat + "," + mobilPilihan + "," + hari + "," + discount + "," + totalBiaya + "," + fileKTP.getName() + "," + fileSIM.getName();
                bw.write(data);
                bw.newLine();

                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!\nStok " + namaMobil + " tersisa: " + stokMobil.getStok(namaMobil));
                model.addRow(new Object[]{nama, NIK, alamat, mobilPilihan, hari, discount + "%", "Rp" + totalBiaya, fileKTP.getName() + " / " + fileSIM.getName()});

                txtNama.setText(""); txtNIK.setText(""); txtAlamat.setText("");
                txtHari.setText(""); txtDiscount.setText("");
                cmbMobil.setSelectedIndex(0);
                areaHasil.setText("");
                lblFileKTP.setText("Belum ada file dipilih");
                lblFileSIM.setText("Belum ada file dipilih");
                fileKTP = null;
                fileSIM = null;
                totalBiaya = 0;
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Gagal menyimpan data! " + ex.getMessage());
            }
        });

        btnLoadData.addActionListener(e -> loadRentalData());

        btnBersih.addActionListener(e -> {
            txtNama.setText(""); txtNIK.setText(""); txtAlamat.setText("");
            txtHari.setText(""); txtDiscount.setText("");
            cmbMobil.setSelectedIndex(0);
            areaHasil.setText("");
            lblFileKTP.setText("Belum ada file dipilih");
            lblFileSIM.setText("Belum ada file dipilih");
            fileKTP = null;
            fileSIM = null;
            totalBiaya = 0;
        });

        btnReturn.addActionListener(e -> {
            dispose();
            new BerandaForm(username);
        });

        setVisible(true);
    }

    private void loadRentalData() {
        try (BufferedReader br = new BufferedReader(new FileReader("data_rental.txt"))) {
            String line;
            model.setRowCount(0);
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 9) {
                    model.addRow(new Object[]{data[0], data[1], data[2], data[3], data[4], data[5] + "%", "Rp" + data[6], data[7] + " / " + data[8]});
                }
            }
            JOptionPane.showMessageDialog(null, "Data berhasil dimuat!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data! " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new RentalMobil("admin");
    }
}
