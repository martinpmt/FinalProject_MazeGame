/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.swing.JFileChooser;
import Model.Tempat;

/**
 *
 * @author admin
 */
public class TestTempat {

    public static void main(String[] args) {
        JFileChooser jf = new JFileChooser();
        int returnVal = jf.showOpenDialog(null);
        Tempat tempat = new Tempat();
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            tempat.bacaKonfigurasiPeta(jf.getSelectedFile());
            // menampilkan atribut 'isi' dari kelas Tempat
            System.out.println("Isi peta = ");
            System.out.println(tempat.getIsi());
            if(tempat.getDaftarSel()!=null){
                for (int i = 0; i < tempat.getDaftarSel().size(); i++) {
                    // menampilkan nilai posisiX,posisiY dan nilai
                    System.out.println(
                            tempat.getDaftarSel().get(i).getBaris()+","
                            +tempat.getDaftarSel().get(i).getKolom()+","
                            +tempat.getDaftarSel().get(i).getNilai());
                    
                }
            }
        }
    }
}
