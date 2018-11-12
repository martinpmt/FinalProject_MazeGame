/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Paramarta / 175314090
 * @author Martin Paramarta / 175314111
 */
public class Tempat {

    private int tinggi; // tinggi tempat Game
    private int lebar;  // lebar tempat Game
    private ArrayList<Sel> daftarSel; // daftar sel
    private String isi; // isi file konfigurasi

    public static int batasKanan;
    public static int batasBawah;

    public Tempat() {
        daftarSel = new ArrayList<Sel>();
    }

    /**
     * Fungsi pembaca file konfigurasi. Hasil pembacaan file akan disimpan di
     * atribut 'isi' dan juga di atribut daftarSel
     *
     * @param file
     */
    public void bacaKonfigurasi(File file) {
        FileInputStream fis = null;
        Sel sel = new Sel();
        String hasil = "";
        int baris = 0;
        int kolom = 0;
        int dataInt;
        try {
            fis = new FileInputStream(file);
            while ((dataInt = fis.read()) != -1) {
                hasil = hasil + (char) dataInt;
                if ((char) dataInt != '\n') {
                    sel = new Sel(kolom, baris, (char) dataInt);
                    sel.setTinggi(32);
                    sel.setLebar(32);
                    if (sel.getNilai() == '#') {
                        sel.setWarna(Color.black);
                    } else if (sel.getNilai() == '.') {
                        sel.setWarna(Color.green);
                    } else if (sel.getNilai() == 'O') {
                        sel.setWarna(Color.yellow);
                    }
                    this.tambahSel(sel);
                    kolom++;
                } else {
                    kolom = 0;
                    baris++;
                }
            }
            sel.setWarna(Color.red);
            sel.setPosisiX(sel.getPosisiXPemain());
            sel.setPosisiY(sel.getPosisiYPemain());
            this.setIsi(hasil);
        
    }   catch (IOException ex) {
            Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Fungsi penambah daftar sel.
     *
     * @param sel
     */
    public void tambahSel(Sel sel) {
        daftarSel.add(sel);
    }

    /**
     * @return the tinggi
     */
    public int getTinggi() {
        return tinggi;
    }

    /**
     * @param tinggi the tinggi to set
     */
    public void setTinggi(int tinggi) {
        this.tinggi = tinggi;
    }

    /**
     * @return the lebar
     */
    public int getLebar() {
        return lebar;
    }

    /**
     * @param lebar the lebar to set
     */
    public void setLebar(int lebar) {
        this.lebar = lebar;
    }

    /**
     * @return the daftarSel
     */
    public ArrayList<Sel> getDaftarSel() {
        return daftarSel;
    }

    /**
     * @param daftarSel the daftarSel to set
     */
    public void setDaftarSel(ArrayList<Sel> daftarSel) {
        this.daftarSel = daftarSel;
    }

    /**
     * @return the isi
     */
    public String getIsi() {
        return isi;
    }

    /**
     * @param isi the isi to set
     */
    public void setIsi(String isi) {
        this.isi = isi;
    }
}
