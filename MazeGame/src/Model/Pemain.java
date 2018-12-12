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

    private char nilai;

    public Pemain(int x, int y, char nilai) {
        super(x, y);//Mengakses constructor superclass (pixel) oleh subclass (Pemain) dan lsg di set nilai xy Pemain 
        URL loc = this.getClass().getResource("/image/pemain.gif");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
        this.setNilai(nilai);
    }

    public void Gerak(int x, int y) {
        int nx = this.getPosisiX() + x;//bergerak kiri atau kanan, tergantung nilai x jika negative maka ke kiri, positive maka ke kanan
        int ny = this.getPosisiY() + y;//bergerak atas atau bawah, tergantung nilai y jika negative maka ke atas, positive maka ke bawah
        this.setPosisiX(nx);
        this.setPosisiY(ny);
    }

    public char getNilai() {
        return nilai;
    }

    public void setNilai(char nilai) {
        this.nilai = nilai;
    }

}
