/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import javax.swing.JFileChooser;
import Model.Sel;
import Model.Tempat;
import view.GameFrame;
import view.TempatPanel;

/**
 *
 * @author user only
 */
public class TestGame2 {
    public static void main(String[] args) {
        JFileChooser jf = new JFileChooser();
        int returnVal = jf.showOpenDialog(null);
        Tempat tempat = new Tempat();
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            tempat.bacaKonfigurasi(jf.getSelectedFile());
            // menampilkan atribut 'isi' dari kelas Tempat
            System.out.println("Isi peta = ");
            System.out.println(tempat.getIsi());
            if(tempat.getDaftarSel()!=null){
                for (int i = 0; i < tempat.getDaftarSel().size(); i++) {
                    // menampilkan nilai posisiX,posisiY dan nilai
                    System.out.println(
                            tempat.getDaftarSel().get(i).getPosisiX()+","
                            +tempat.getDaftarSel().get(i).getPosisiY()+","
                            +tempat.getDaftarSel().get(i).getNilai());
                    
                }
            }
        }
        // Set ukuran tempat
        Tempat.batasKanan=500;
        Tempat.batasBawah=300;
        // buat tempatPanel dan tambahkan tempat ke tempatPanel
        TempatPanel tempatPanel = new TempatPanel();
        tempatPanel.setTempat(tempat);
        // buat gameFrame dan tambahkan tempatPanel ke gameFrame
        GameFrame game = new GameFrame("My Game",tempatPanel);
    
    }
}
