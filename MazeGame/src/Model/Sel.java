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

    private int posisiX = 0; // nomor kolom, dimulai dari nol (0)
    private int posisiY = 0; // nomor baris, dimulai dari nol (0)
    private int lebar;
    private int tinggi;
    private int posisiXPemain = 1; //nomor kolom, dimulai dari (1)
    private int posisiYPemain = 1; //nomor baris, dimukai dari (1)

    private char nilai;
    private Tempat tempat;
    private Color warna;

    public Sel() {
    }

    public Sel(int posisiX, int posisiY, char nilai) {
        this.posisiX = posisiX;
        this.posisiY = posisiY;
        this.nilai = nilai;
    }

    public Sel(int posisiX, int posisiY, char nilai, Color warna) {
        this.posisiX = posisiX;
        this.posisiY = posisiY;
        this.nilai = nilai;
        this.warna = warna;
    }

    public Sel(int posisiX, int posisiY, int lebar, int tinggi, char nilai, Color warna) {
        this.posisiX = posisiX;
        this.posisiY = posisiY;
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
        return this.getNilai() != '#';
    }

    public void geserKiri() {
        if (isBatasKiri() == true) {
            move(1, 0);
        }
    }

    /**
     * Fungsi ceking sel ada di batas kanan
     *
     * @return
     */
    public boolean isBatasKanan() {
        return this.getNilai() != '#';
    }

    /**
     * Fungsi untuk menggeser sel ke kanan
     */
    public void geserKanan() {
        if (isBatasKanan() == true) {
            move(-1, 0);
        }
    }

    /**
     * Fungsi untuk mengecek sel ada di batas atas
     *
     * @return
     */
    public boolean isBatasAtas() {
        return this.getNilai() != '#';
    }

    public void geserAtas() {
        if (isBatasAtas() == true) {
            move(0, -1);
        }
    }

    /**
     * Fungsi untuk mengecek sel ada di batas bawah
     *
     * @return
     */
    public boolean isBatasBawah() {
        return this.getNilai() != '#';
    }

    public void geserBawah() {
        if (isBatasBawah() == true) {
            move(0, 1);
        }
    }

    /**
     * @return the posisiX
     */
    public int getPosisiX() {
        return posisiX;
    }

    /**
     * @param posisiX the posisiX to set
     */
    public void setPosisiX(int posisiX) {
        this.posisiX = posisiX;
    }

    /**
     * @return the posisiY
     */
    public int getPosisiY() {
        return posisiY;
    }

    /**
     * @param posisiY the posisiY to set
     */
    public void setPosisiY(int posisiY) {
        this.posisiY = posisiY;
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

    public int getPosisiXPemain() {
        return posisiXPemain;
    }

    public int getPosisiYPemain() {
        return posisiYPemain;
    }

    /**
     * menggeser pemain
     *
     * @param dx
     * @param dy
     */
    public void move(int dx, int dy) {
        posisiX += dx;
        posisiY += dy;

    }
}
