/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Martin Paramarta / 175314090
 * @author Martin Paramarta / 175314111
 */
public class TempatPanel extends JPanel {

    private Tempat tempat;
    private Sel sel;

    public TempatPanel() {
        tempat = new Tempat();
        sel = new Sel();
        setFocusable(true);
    }

    public TempatPanel(Tempat tempat) {
        this.tempat = tempat;
    }

    /**
     * Fungsi untuk menggambar di panel
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.fillRect(0, 0, Tempat.batasKanan, Tempat.batasBawah);
        // proses gambar daftar sel
        // asumsi sel berbentuk lingkaran
        // gambar lingkaran dengan fillOval dengan diameter 20
        if (tempat != null) {
            for (int i = 0; i < tempat.getDaftarSel().size(); i++) {
                sel = tempat.getDaftarSel().get(i);
                if (sel.getNilai() == '#') {
                    g.setColor(sel.getWarna());
                    g.fillRect(sel.getPosisiX() * sel.getLebar(),
                            sel.getPosisiY() * sel.getTinggi(),
                            sel.getLebar(),
                            sel.getTinggi());
                } else if (sel.getNilai() == '.') {
                    g.setColor(sel.getWarna());
                    g.fillRect(sel.getPosisiX() * sel.getLebar(),
                            sel.getPosisiY() * sel.getTinggi(),
                            sel.getLebar(),
                            sel.getTinggi());
                } else if (sel.getNilai() == 'O') {
                    g.setColor(sel.getWarna());
                    g.fillRect(sel.getPosisiX() * sel.getLebar(),
                            sel.getPosisiY() * sel.getTinggi(),
                            sel.getLebar(),
                            sel.getTinggi());
                }
            }
            g.setColor(sel.getWarna());
            g.fillRect(sel.getPosisiXPemain() * sel.getLebar(),
                    sel.getPosisiYPemain() * sel.getTinggi(),
                    sel.getLebar(),
                    sel.getTinggi());
        }
    }

    /**
     * @return the tempat
     */
    public Tempat getTempat() {
        return tempat;
    }

    /**
     * @param tempat the tempat to set
     */
    public void setTempat(Tempat tempat) {
        this.tempat = tempat;
    }
}
