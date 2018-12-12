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
 * @author user only
 */
public class GameFrame extends JFrame {

    private Peta peta;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public GameFrame(String title, Peta peta) {
        setTitle(title);
        this.peta = peta;
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
                JFileChooser jf = new JFileChooser();
                int returnVal = jf.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    Peta tempat = new Peta(jf.getSelectedFile());
                    // menampilkan atribut 'isi' dari kelas Tempat
                    System.out.println("\nIsi peta Baru = ");
                    System.out.println(tempat.getIsi());
                    if (tempat.getSel() != null) {
                        for (int i = 0; i < tempat.getSel().size(); i++) {
                            // menampilkan nilai posisiX,posisiY dan nilai
                            System.out.println(
                                    tempat.getSel().get(i).getPosisiX() + ","
                                    + tempat.getSel().get(i).getPosisiY() + ",");
                        }
                    }
                }
                // Set ukuran tempat
                Peta.batasKanan = 410;
                Peta.batasBawah = 410;
                // buat tempatPanel dan tambahkan tempat ke tempatPanel
                peta = new Peta();
                init();
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
                peta.PerintahGerak(perintahText.getText());
            }
        });

        // set contentPane
        Container cp = this.getContentPane();
        if (peta != null) {
            cp.add(peta, BorderLayout.CENTER);
        }
        cp.add(southPanel, BorderLayout.SOUTH);

        // set visible= true
        this.setVisible(true);
    }
}
