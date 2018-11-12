/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import Model.*;

/**
 *
 * @author Martin Paramarta / 175314090
 * @author Martin Paramarta / 175314111
 */
public class GameFrame extends JFrame {

    private TempatPanel tempatPanel;
    private Sel sel;
    private Tempat tempat;

    private JLabel perintahlabel;
    private JTextField perintahText;
    private JButton okButton;

    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem exitMenuItem;
    private JMenuItem bacaKonfigurasiMenuItem;

    public GameFrame(String title) {
        this.setTitle(title);
        this.init();
    }

    public GameFrame(String title, TempatPanel tempatPanel) {
        sel = new Sel();
        tempat = new Tempat();
        setTitle(title);
        this.tempatPanel = tempatPanel;
        this.init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init() {
        // set ukuran dan layout
        this.setSize(460, 550);
        this.setLayout(new BorderLayout());

        // set menu Bar
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        exitMenuItem = new JMenuItem("Keluar");
        bacaKonfigurasiMenuItem = new JMenuItem("Baca");
        gameMenu.add(bacaKonfigurasiMenuItem);
        gameMenu.add(exitMenuItem);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);

        //action perform for exitMenuItem
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        );

        //action perform for bacaMenuItem
        bacaKonfigurasiMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TempatPanel tempatPanel = new TempatPanel();
                JFileChooser jf = new JFileChooser();
                int returnVal = jf.showOpenDialog(null);
                Tempat tempat = new Tempat();
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    tempat.bacaKonfigurasi(jf.getSelectedFile());
                    // menampilkan atribut 'isi' dari kelas Tempat
                    System.out.println("\nIsi peta Baru = ");
                    System.out.println(tempat.getIsi());
                    if (tempat.getDaftarSel() != null) {
                        for (int i = 0; i < tempat.getDaftarSel().size(); i++) {
                            // menampilkan nilai posisiX,posisiY dan nilai
                            System.out.println(
                                    tempat.getDaftarSel().get(i).getPosisiX() + ","
                                    + tempat.getDaftarSel().get(i).getPosisiY() + ","
                                    + tempat.getDaftarSel().get(i).getNilai());

                        }
                    }
                }
                // Set ukuran tempat
                Tempat.batasKanan = 500;
                Tempat.batasBawah = 300;
                // buat tempatPanel dan tambahkan tempat ke tempatPanel
                tempatPanel.setTempat(tempat);
            }
        });

        // panel selatan
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        this.perintahlabel = new JLabel("Perintah");
        southPanel.add(perintahlabel);

        this.perintahText = new JTextField(20);
        southPanel.add(perintahText);

        this.okButton = new JButton("OK");
        southPanel.add(okButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pindah();
            }
        });

        // set contentPane
        Container cp = this.getContentPane();
        if (tempatPanel != null) {
            cp.add(getTempatPanel(), BorderLayout.CENTER);
        }
        cp.add(southPanel, BorderLayout.SOUTH);

        // set visible= true
        this.setVisible(true);
    }

    /**
     * Fungsi untuk memindahkan sel dan menggambar ulang
     */
    public void pindah() {
        Sel sel = new Sel();
        boolean check = false;
        String perintah = perintahText.getText().substring(0, 1);
        String langkah = perintahText.getText().substring(1, 2);

        if (perintah.equalsIgnoreCase("U")) {
            for (int i = 0; i < Integer.parseInt(langkah); i++) {
                check = sel.isBatasAtas();
            }
            if (check == true) {
                for (int i = 0; i < Integer.parseInt(langkah); i++) {
                    sel.geserAtas();
                }
            }
        } else if (perintah.equalsIgnoreCase("D")) {
            for (int i = 0; i < Integer.parseInt(langkah); i++) {
                check = sel.isBatasBawah();
            }
            if (check == true) {
                for (int i = 0; i < Integer.parseInt(langkah); i++) {
                    sel.geserBawah();
                }
            }
        } else if (perintah.equalsIgnoreCase("R")) {
            for (int i = 0; i < Integer.parseInt(langkah); i++) {
                check = sel.isBatasKanan();
            }
            if (check == true) {
                for (int i = 0; i < Integer.parseInt(langkah); i++) {
                    sel.geserKanan();
                }
            }
        } else if (perintah.equalsIgnoreCase("L")) {
            for (int i = 0; i < Integer.parseInt(langkah); i++) {
                check = sel.isBatasKiri();
            }
            if (check == true) {
                for (int i = 0; i < Integer.parseInt(langkah); i++) {
                    sel.geserKiri();
                }
            }
        }

        // gambar ulang tempat Panel
        getTempatPanel().repaint();
    }

    /**
     * @return the tempatPanel
     */
    public TempatPanel getTempatPanel() {
        return tempatPanel;
    }

    /**
     * @param tempatPanel the tempatPanel to set
     */
    public void setTempatPanel(TempatPanel tempatPanel) {
        this.tempatPanel = tempatPanel;
    }

}
