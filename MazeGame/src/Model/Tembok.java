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
public class Tembok extends Sel {

    private char nilai;

    public Tembok(int x, int y, char nilai) {
        super(x, y);//Mengakses constructor superclass (pixel) oleh subclass (Tembok) dan lsg di set nilai xy Tembok 
        URL loc = this.getClass().getResource("/Image/tembok.jpg");
        ImageIcon wall = new ImageIcon(loc);
        Image image = wall.getImage();
        this.setImage(image);
        this.nilai = nilai;
    }

    public char getNilai() {
        return nilai;
    }

    public void setNilai(char nilai) {
        this.nilai = nilai;
    }

}
