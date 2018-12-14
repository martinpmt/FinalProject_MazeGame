/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Aweng
 */
public class Pemain extends Sel {

    private int lebar;
    private int tinggi;
    private char nilai;

    public Pemain(int x, int y, int lebar, int tinggi, char nilai) {
        super(x, y);//Mengakses constructor superclass (pixel) oleh subclass (Pemain) dan lsg di set nilai xy Pemain 
        URL loc = this.getClass().getResource("/image/pemainx.gif");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
        this.lebar = lebar;
        this.tinggi = tinggi;
        this.setNilai(nilai);
    }

    public void Gerak(int x, int y) {
        int nx = this.getLebar() + x;//bergerak kiri atau kanan, tergantung nilai x jika negative maka ke kiri, positive maka ke kanan
        int ny = this.getTinggi() + y;//bergerak atas atau bawah, tergantung nilai y jika negative maka ke atas, positive maka ke bawah
        this.setLebar(nx);
        this.setTinggi(ny);
    }

    @Override
    public int getLebar() {
        return lebar;
    }

    @Override
    public void setLebar(int lebar) {
        this.lebar = lebar;
    }

    @Override
    public int getTinggi() {
        return tinggi;
    }

    @Override
    public void setTinggi(int tinggi) {
        this.tinggi = tinggi;
    }

    @Override
    public char getNilai() {
        return nilai;
    }

    @Override
    public void setNilai(char nilai) {
        this.nilai = nilai;
    }

}
