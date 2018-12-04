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
import view.TempatPanel;

/**
 *
 * @author user only
 */
public class Tempat {

    private ArrayList tembok = new ArrayList();//menyimpan data tembok
    private ArrayList selesai = new ArrayList();//menyimpan data bola
    private ArrayList<Sel> map;//menyimpan data tembok,gawang,bola,soko
    private Sel pemain;

    private TempatPanel tempatPanel = new TempatPanel();

    private String isi; // isi file konfigurasi

    //untuk menentukan besarkan sel didalam panel.
    private int lebarSel = 25;
    private int tinggiSel = 25;

    public static int batasKanan;
    public static int batasBawah;

    public Tempat() {
        map = new ArrayList<Sel>();
    }

    /**
     * Fungsi penambah daftar sel.
     *
     * @param sel
     */
    public void tambahSel(Sel sel) {
        map.add(sel);
    }

    /**
     * @return the daftarSel
     */
    public ArrayList<Sel> getDaftarSel() {
        return map;
    }

    /**
     * @param daftarSel the daftarSel to set
     */
    public void setDaftarSel(ArrayList<Sel> daftarSel) {
        this.map = daftarSel;
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

    /**
     * Fungsi pembaca file konfigurasi. Hasil pembacaan file akan disimpan di
     * atribut 'isi' dan juga di atribut daftarSel
     *
     * @param file
     */
    public void bacaKonfigurasiPeta(File file) {
        if (file != null) {
            FileInputStream fis = null;
            String hasil = "";
            int baris = 0;
            int kolom = 0;
            int dataInt;
            try {
                fis = new FileInputStream(file);
                while ((dataInt = fis.read()) != -1) {
                    hasil = hasil + (char) dataInt;
                    if ((char) dataInt != '\n') {
                        if ((char) dataInt == '#') {
                            Sel wall = new Sel(baris, kolom, (char) dataInt);
                            wall.setWarna(Color.BLACK);
                            tembok.add(wall);
                        } else if ((char) dataInt == '.') {
                            Sel route = new Sel(baris, kolom, (char) dataInt);
                            route.setWarna(Color.white);
                        } else if ((char) dataInt == 'O') {
                            Sel finish = new Sel(baris, kolom, (char) dataInt);
                            finish.setWarna(Color.yellow);
                            selesai.add(finish);
                        } else if ((char) dataInt == '@') {
                            pemain = new Sel(baris, kolom, (char) dataInt);
                            pemain.setWarna(Color.RED);
                        }
                        map.addAll(tembok);
                        map.addAll(selesai);
                        map.add(pemain);
                        kolom++;
                    } else {
                        kolom = 0;
                        baris++;
                    }
                }
                this.setIsi(hasil);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void PerintahGerak(String input) throws Exception {
        String perintah[] = input.split("");
        if (perintah.length > 2) {
            throw new Exception("Jumlah karakter lebih dari 2");
        } else if (perintah.length == 2) {
            if (perintah[0].matches("[udrl]")) {
                if (perintah[0].equalsIgnoreCase("u")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(perintah[1])); i++) {
                        if (cekPemainNabrakTembok(pemain, "u")) {
                            return;
                        } else {
                            pemain.geserAtas(0, -tinggiSel);
                            tempatPanel.repaint();
                        }
                    }
                } else if (perintah[0].equalsIgnoreCase("d")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(perintah[1])); i++) {
                        if (cekPemainNabrakTembok(pemain, "d")) {
                            return;
                        } else {
                            pemain.geserBawah(0, tinggiSel);
                            tempatPanel.repaint();
                        }
                    }
                } else if (perintah[0].equalsIgnoreCase("r")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(perintah[1])); i++) {
                        if (cekPemainNabrakTembok(pemain, "r")) {
                            return;
                        } else {
                            pemain.geserKanan(lebarSel, 0);
                            tempatPanel.repaint();
                        }
                    }
                } else if (perintah[0].equalsIgnoreCase("l")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(perintah[1])); i++) {
                        if (cekPemainNabrakTembok(pemain, "l")) {
                            return;
                        } else {
                            pemain.geserKiri(-lebarSel, 0);
                            tempatPanel.repaint();
                        }
                    }
                }
            } else {
                throw new Exception("Kata Tidak Dikenal");
            }
        } else {
            throw new Exception("Jumlah kata hanya satu");
        }
    }

    private boolean cekPemainNabrakTembok(Sel pemain, String input) {
        boolean bantu = false;
        if (input.equalsIgnoreCase("l")) {
            for (int i = 0; i < tembok.size(); i++) {
                Sel wall = (Sel) tembok.get(i);//ambil posisi tembok
                if (pemain.isBatasKiri(wall)) {
                    bantu = true;
                    break;
                }
            }

        } else if (input.equalsIgnoreCase("r")) {
            for (int i = 0; i < tembok.size(); i++) {
                Sel wall = (Sel) tembok.get(i);//ambil posisi tembok
                if (pemain.isBatasKanan(wall)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equalsIgnoreCase("u")) {
            for (int i = 0; i < tembok.size(); i++) {
                Sel wall = (Sel) tembok.get(i);//ambil posisi tembok
                if (pemain.isBatasBawah(wall)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equalsIgnoreCase("d")) {
            for (int i = 0; i < tembok.size(); i++) {
                Sel wall = (Sel) tembok.get(i);//ambil posisi tembok
                if (pemain.isBatasBawah(wall)) {
                    bantu = true;
                    break;
                }
            }
        }
        return bantu;//default return false
    }
}
