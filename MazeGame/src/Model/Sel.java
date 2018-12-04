/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;

/**
 *
 * @author user only
 */
public class Sel {

    //baris sama dengan posisiY
    private int baris;
    //kolom sama dengan posisiX
    private int kolom;
    //untuk menentukan besarkan sel didalam panel.
    private int lebarSel = 25;
    private int tinggiSel = 25;

    private char nilai;

    private Color warna;

    /**
     *
     */
    public Sel() {
    }

    /**
     *
     * @param baris
     * @param kolom
     * @param nilai
     */
    public Sel(int baris, int kolom, char nilai) {
        this.baris = baris;
        this.kolom = kolom;
        this.nilai = nilai;
    }

    /**
     *
     * @param baris
     * @param kolom
     * @param nilai
     * @param warna
     */
    public Sel(int baris, int kolom, char nilai, Color warna) {
        this.baris = baris;
        this.kolom = kolom;
        this.nilai = nilai;
        this.warna = warna;
    }

    /**
     *
     * @param baris
     * @param kolom
     * @param lebarSel
     * @param tinggiSel
     * @param nilai
     * @param warna
     */
    public Sel(int baris, int kolom, int lebarSel, int tinggiSel, char nilai, Color warna) {
        this.baris = baris;
        this.kolom = kolom;
        this.lebarSel = lebarSel;
        this.tinggiSel = tinggiSel;
        this.nilai = nilai;
        this.warna = warna;
    }

    /**
     *
     * @return
     */
    public int getBaris() {
        return baris;
    }

    /**
     *
     * @param baris
     */
    public void setBaris(int baris) {
        this.baris = baris;
    }

    /**
     *
     * @return
     */
    public int getKolom() {
        return kolom;
    }

    /**
     *
     * @param kolom
     */
    public void setKolom(int kolom) {
        this.kolom = kolom;
    }

    /**
     *
     * @return
     */
    public int getLebarSel() {
        return lebarSel;
    }

    /**
     *
     * @param lebarSel
     */
    public void setLebarSel(int lebarSel) {
        this.lebarSel = lebarSel;
    }

    /**
     *
     * @return
     */
    public int getTinggiSel() {
        return tinggiSel;
    }

    /**
     *
     * @param tinggiSel
     */
    public void setTinggiSel(int tinggiSel) {
        this.tinggiSel = tinggiSel;
    }

    /**
     *
     * @return
     */
    public char getNilai() {
        return nilai;
    }

    /**
     *
     * @param nilai
     */
    public void setNilai(char nilai) {
        this.nilai = nilai;
    }

    /**
     *
     * @return
     */
    public Color getWarna() {
        return warna;
    }

    /**
     *
     * @param warna
     */
    public void setWarna(Color warna) {
        this.warna = warna;
    }

    /**
     * Fungsi mengecek sel ada di batas kiri
     *
     * @return
     */
    public boolean isBatasKiri(Sel posisi) {
        if (((this.getKolom() - lebarSel) == posisi.getKolom()) && (this.getBaris() == posisi.getBaris())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Fungsi ceking sel ada di batas kanan
     *
     * @return
     */
    public boolean isBatasKanan(Sel posisi) {
        if (((this.getKolom() + lebarSel) == posisi.getKolom()) && (this.getBaris() == posisi.getBaris())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Fungsi untuk mengecek sel ada di batas atas
     *
     * @return
     */
    public boolean isBatasAtas(Sel posisi) {
        if (((this.getBaris() - tinggiSel) == posisi.getBaris()) && (this.getKolom() == posisi.getBaris())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Fungsi untuk mengecek sel ada di batas bawah
     *
     * @return
     */
    public boolean isBatasBawah(Sel posisi) {
        if (((this.getBaris() + tinggiSel) == posisi.getBaris()) && (this.getKolom() == posisi.getBaris())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Fungsi untuk menggeser sel ke kanan
     */
    public void geserKiri(int kolom, int baris) {
        //bergerak kiri atau kanan, tergantung nilai x jika negative maka ke kiri, positive maka ke kanan
        int posisiKolom = this.getKolom() + kolom;
        //bergerak atas atau bawah, tergantung nilai y jika negative maka ke atas, positive maka ke bawah
        int posisiBaris = this.getBaris() + baris;

        this.setKolom(posisiKolom);
        this.setBaris(posisiBaris);
    }

    /**
     * Fungsi untuk menggeser sel ke kanan
     */
    public void geserKanan(int kolom, int baris) {
        //bergerak kiri atau kanan, tergantung nilai x jika negative maka ke kiri, positive maka ke kanan
        int posisiKolom = this.getKolom() + kolom;
        //bergerak atas atau bawah, tergantung nilai y jika negative maka ke atas, positive maka ke bawah
        int posisiBaris = this.getBaris() + baris;

        this.setKolom(posisiKolom);
        this.setBaris(posisiBaris);
    }

    /**
     * Fungsi untuk geser atas
     */
    public void geserAtas(int kolom, int baris) {
        //bergerak kiri atau kanan, tergantung nilai x jika negative maka ke kiri, positive maka ke kanan
        int posisiKolom = this.getKolom() + kolom;
        //bergerak atas atau bawah, tergantung nilai y jika negative maka ke atas, positive maka ke bawah
        int posisiBaris = this.getBaris() + baris;

        this.setKolom(posisiKolom);
        this.setBaris(posisiBaris);
    }

    /**
     * Fungsi untuk geser bawah
     */
    public void geserBawah(int kolom, int baris) {
        //bergerak kiri atau kanan, tergantung nilai x jika negative maka ke kiri, positive maka ke kanan
        int posisiKolom = this.getKolom() + kolom;
        //bergerak atas atau bawah, tergantung nilai y jika negative maka ke atas, positive maka ke bawah
        int posisiBaris = this.getBaris() + baris;

        this.setKolom(posisiKolom);
        this.setBaris(posisiBaris);
    }

}
