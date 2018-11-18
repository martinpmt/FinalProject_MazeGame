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

   private int baris;
    private int kolom;
    private int lebar;
    private int tinggi;

    private char nilai;

    private Color warna;

    public Sel() {
    }

    public Sel(int baris, int kolom, char nilai) {
        this.baris = baris;
        this.kolom = kolom;
        this.nilai = nilai;
    }

    public Sel(int baris, int kolom, char nilai, Color warna) {
        this.baris = baris;
        this.kolom = kolom;
        this.nilai = nilai;
        this.warna = warna;
    }

    public Sel(int baris, int kolom, int lebar, int tinggi, char nilai, Color warna) {
        this.baris = baris;
        this.kolom = kolom;
        this.lebar = lebar;
        this.tinggi = tinggi;
        this.nilai = nilai;
        this.warna = warna;
    }

    /**
     * Fungsi mengecek sel ada di batas kiri
     *
     * @return
     */
    public boolean isBatasKiri() {
        if (kolom * lebar <= 0) {
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
    public boolean isBatasKanan() {
        if (kolom * lebar + lebar < Tempat.batasKanan) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Fungsi untuk menggeser sel ke kanan
     */
    public void geserKanan() {
        if (isBatasKanan() == false) {
            kolom++;
        }else{
            kolom--;
        }
    }

    /**
     * Fungsi untuk menggeser sel ke kanan
     */
    public void geserKiri() {
        if (isBatasKiri() == false) {
            kolom--;
        }else{
            kolom++;
        }
    }

    /**
     * Fungsi untuk mengecek sel ada di batas atas
     */
    public boolean isBatasAtas() {
        if (baris * tinggi <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Fungsi untuk mengecek sel ada di batas bawah
     */
    public boolean isBatasBawah() {
        if (baris * tinggi + tinggi < Tempat.batasBawah) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Fungsi untuk geser atas
     */
    public void geserAtas() {
        if (isBatasAtas() == false) {
            baris--;
        }else{
            baris++;
        }
    }

    /**
     * Fungsi untuk geser bawah
     */
    public void geserBawah() {
        if (isBatasBawah()== false) {
            baris++;
        }else{
            baris--;
        }
    }

    /**
     * @return the baris
     */
    public int getBaris() {
        return baris;
    }

    /**
     * @param baris the baris to set
     */
    public void setBaris(int baris) {
        this.baris = baris;
    }

    /**
     * @return the kolom
     */
    public int getKolom() {
        return kolom;
    }

    /**
     * @param kolom the kolom to set
     */
    public void setKolom(int kolom) {
        this.kolom = kolom;
    }

    /**
     * @return the nilai
     */
    public char getNilai() {
        return nilai;
    }

    /**
     * @param nilai the nilai to set
     */
    public void setNilai(char nilai) {
        this.nilai = nilai;
    }

    /**
     * @return the warna
     */
    public Color getWarna() {
        return warna;
    }

    /**
     * @param warna the warna to set
     */
    public void setWarna(Color warna) {
        this.warna = warna;
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
}
