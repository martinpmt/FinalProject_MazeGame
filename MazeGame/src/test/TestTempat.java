/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.swing.JFileChooser;
import Model.Peta;

/**
 *
 * @author admin
 */
public class TestTempat {

    public static void main(String[] args) {
        JFileChooser jf = new JFileChooser();
        int returnVal = jf.showOpenDialog(null);
       Peta peta;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
           peta=new Peta(jf.getSelectedFile());
            // menampilkan atribut 'isi' dari kelas Tempat
            System.out.println("Isi peta = ");
            System.out.println(peta.getIsi());
            if(peta.getSel()!=null){
                for (int i = 0; i < peta.getSel().size(); i++) {
                    // menampilkan nilai posisiX,posisiY dan nilai
                    System.out.println(
                            peta.getSel().get(i).getPosisiX()+","
                            +peta.getSel().get(i).getPosisiY()+",");
                    
                }
            }
        }
    }
}
