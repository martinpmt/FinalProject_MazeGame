/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Aweng
 */
public class Peta extends JPanel {

    private ArrayList<Tembok> tembok = new ArrayList<>();//menyimpan data tembok
    private ArrayList<Finish> finish = new ArrayList<>();//menyimpan data gawang
    private ArrayList<Sel> sel = new ArrayList<>();//menyimpan data tembok,finish,pemain
    private Pemain pemain;
    private final char TEMBOK = '#';
    private final char PEMAIN = '@';
    private final char KOSONG = '.';
    private final char FINISH = 'O';
    private int lebar = 0;
    private int tinggi = 0;
    private int jarak = 20;//untuk menentukan besarkan pixel/jarak space gambar didalam panel
    private String isi;
    public static int batasKanan;
    public static int batasBawah;

    private File Alamatpeta;//digunakan untuk merestart level
    private ArrayList Allperintah = new ArrayList();//menyimpan semua perintah yang dimasukkan

    public Peta() {
        setFocusable(true);
    }

    public Peta(File file) {
        bacaKonfigurasi(file);
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public ArrayList<Tembok> getTembok() {
        return tembok;
    }

    public void setTembok(Tembok tembok) {
        this.tembok.add(tembok);
    }

    public ArrayList<Finish> getFinish() {
        return finish;
    }

    public void setFinish(Finish finish) {
        this.finish.add(finish);
    }

    public ArrayList<Sel> getSel() {
        return sel;
    }

    public void setSel(Pemain pemain, ArrayList<Tembok> tembok, ArrayList<Finish> finish) {
        this.sel.add(pemain);
        this.sel.addAll(tembok);
        this.sel.addAll(finish);
    }

    private void bacaKonfigurasi(File file) {
        try {
            if (file != null) {
                FileInputStream input = new FileInputStream(file);
                Alamatpeta = file;
                int posisiX = 0;
                int posisiY = 0;
                Tembok wall;
                Finish f;
                String isi = "";
                int data;
                while ((data = input.read()) != -1) {
                    isi = isi + (char) data;
                    if ((char) data != '\n') {
                        if ((char) data == TEMBOK) {
                            wall = new Tembok(posisiX, posisiY, (char) data);
                            setTembok(wall);
                            posisiX += jarak;
                        } else if ((char) data == PEMAIN) {
                            pemain = new Pemain(posisiX, posisiY, (char) data);
                            posisiX += jarak;
                        } else if ((char) data == KOSONG) {
                            posisiX += jarak;
                        } else if ((char) data == FINISH) {
                            f = new Finish(posisiX, posisiY, (char) data);
                            setFinish(f);
                            posisiX += jarak;
                        }
                    } else {
                        posisiY += jarak;
                        lebar = posisiX;
                        posisiX = 0;
                    }
                    tinggi = posisiY;
                }
                setIsi(isi);
            }

        } catch (IOException ex) {
            Logger.getLogger(Peta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);	   // Hapus background
        // Tempat Gambar:
        g.setColor(new Color(255, 255, 255));//set panel warna putih
        g.fillRect(0, 0, this.getLebar(), this.getTinggi());// set tinggi lebar sesuai konfigurasi
        setSel(pemain, tembok, finish);
        for (int i = 0; i < sel.size(); i++) {
            Finish fin = (Finish) finish.get(0);
            if (pemain.getPosisiX() != fin.getPosisiX() && pemain.getPosisiY() != fin.getPosisiY()) {//jika semua gawang sudah terisi bola) {
                if (sel.get(i) != null) {
                    Sel item = (Sel) sel.get(i);//map diterjemahkan dalam kelas pixel.
                    g.drawImage(item.getImage(), item.getPosisiX(), item.getPosisiY(), this);//proses gambar di panel
                }

            } else {
                g.setColor(Color.ORANGE);
                g.setFont(new Font("Serif", Font.BOLD, 48));
                g.drawString("Winner", 150, 300);
            }
        }
    }

    public int getLebar() {
        return this.lebar;
    }

    public int getTinggi() {
        return this.tinggi;
    }

    public void PerintahGerak(String input) {
        String in[] = input.split(" ");
        if (in.length > 2) {
            JOptionPane.showMessageDialog(null, "Jumlah kata lebih dari 2");
        } else if (in.length == 2) {
            if (in[0].matches("[udrl]")) {
                Allperintah.add(input);
                if (in[0].equalsIgnoreCase("u")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (cekObjekNabrakTembok(pemain, "u")) {
                            return;
                        } else {
                            pemain.Gerak(0, -jarak);
                            repaint();
                        }

                    }
                } else if (in[0].equalsIgnoreCase("d")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (cekObjekNabrakTembok(pemain, "d")) {
                            return;
                        } else {
                            pemain.Gerak(0, jarak);
                            repaint();
                        }
                    }
                } else if (in[0].equalsIgnoreCase("r")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (cekObjekNabrakTembok(pemain, "r")) {
                            return;
                        } else {
                            pemain.Gerak(jarak, 0);
                            repaint();
                        }
                    }
                } else if (in[0].equalsIgnoreCase("l")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (cekObjekNabrakTembok(pemain, "l")) {
                            return;
                        } else {
                            pemain.Gerak(-jarak, 0);
                            repaint();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Kata Tidak Dikenal");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Kata Tidak Dikenal");
            }
        }
    }

    private boolean cekObjekNabrakTembok(Sel pemain, String input) {
        boolean bantu = false;
        if (input.equalsIgnoreCase("l")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//ambil posisi tembok
                if (pemain.PosisiKiriObjek(wall)) {
                    bantu = true;
                    break;
                }
            }

        } else if (input.equalsIgnoreCase("r")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//ambil posisi tembok
                if (pemain.PosisiKananObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equalsIgnoreCase("u")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//ambil posisi tembok
                if (pemain.PosisiAtasObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equalsIgnoreCase("d")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//ambil posisi tembok
                if (pemain.PosisiBawahObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        }
        return bantu;//default return false
    }

    public void restartLevel() {
        Allperintah.clear();//hapus semua perintah yang tersimpan
        finish.clear();//hapus gawang
        tembok.clear();//hapus tembok
        sel.clear();//hapus map
        bacaKonfigurasi(Alamatpeta);//set ulang gambar peta
        repaint();//gambar ulang
    }

    public String getTeksPerintah() {
        String bantu = "";
        for (int i = 0; i < Allperintah.size(); i++) {
            bantu = bantu + Allperintah.get(i) + " ";
        }
        return bantu;
    }

    public int getPoin() {
        int bantu = Allperintah.size();
        if (bantu < 20) {
            JOptionPane.showMessageDialog(this, sel);
        }
        return bantu;//085245843743
    }
}
