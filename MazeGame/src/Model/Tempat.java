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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Aweng
 */
public class Tempat extends JPanel {

    private ArrayList<Tembok> tembok = new ArrayList<>();//menyimpan data tembok
    private Finish finish;
    private ArrayList<Sel> sel = new ArrayList<>();//menyimpan data tembok,finish,pemain
    private Pemain pemain;
    private LinkedList<String> undo = new LinkedList<>();
    private final char TEMBOK = '#';
    private final char PEMAIN = '@';
    private final char KOSONG = '.';
    private final char FINISH = 'O';
    private int lebar = 0;
    private int tinggi = 0;
    private int jarak = 30;//untuk menentukan besarkan pixel/jarak space gambar didalam panel
    private String isi;
    private boolean completed = false;

    private File Alamatpeta;//digunakan untuk merestart level
    private ArrayList Allperintah = new ArrayList();//menyimpan semua perintah yang dimasukkan

    public Tempat() {
        setFocusable(true);
    }

    public Tempat(File file) {
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

    public ArrayList<Sel> getSel() {
        return sel;
    }

    public void setSel(Pemain pemain, ArrayList<Tembok> tembok, Finish finish) {
        this.sel.add(pemain);
        this.sel.addAll(tembok);
        this.sel.add(finish);
    }

    public void simpanObjekKonfigurasi(File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(isi.getBytes());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bacaObjekKonfigurasi(File file) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            Tempat peta = (Tempat) ois.readObject();
            this.setIsi(peta.getIsi());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void bacaKonfigurasi(File file) {
        try {
            if (file != null) {
                FileInputStream fis = new FileInputStream(file);
                Alamatpeta = file;
                int posisiX = 0;
                int posisiY = 0;
                Tembok wall;
                String isi = "";
                int data;
                while ((data = fis.read()) != -1) {
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
                            finish = new Finish(posisiX, posisiY, (char) data);
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
                setSel(pemain, tembok, finish);
            }

        } catch (IOException ex) {
            Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);	   // Hapus background
        // Tempat Gambar:
        g.setColor(new Color(255, 255, 255));//set panel warna putih
        g.fillRect(0, 0, this.getLebar(), this.getTinggi());// set tinggi lebar sesuai konfigurasi
        if (!completed) {
            for (int i = 0; i < sel.size(); i++) {
                if (sel.get(i) != null) {
                    Sel item = (Sel) sel.get(i);//map diterjemahkan dalam kelas pixel.
                    g.drawImage(item.getImage(), item.getPosisiX(), item.getPosisiY(), this);//proses gambar di panel
                }
            }
        }
        if (completed) {
            g.setColor(Color.ORANGE);
            g.setFont(new Font("Serif", Font.BOLD, 48));
            g.drawString("Winner", 50, 80);
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
        if (in[0].equalsIgnoreCase("UNDO") && in[1].matches("[123456789]")) {
            Allperintah.add(input);
            if (!undo.isEmpty()) {
                for (int index = Integer.parseInt(String.valueOf(in[1])); index > 0; index--) {
                    String x = undo.removeLast();
                    String un[] = x.split(" ");
                    if (un[0].equalsIgnoreCase("u")) {
                        for (int i = 0; i < Integer.parseInt(String.valueOf(un[1])); i++) {
                            if (cekPemainNabrakTembok(pemain, "u")) {
                                return;
                            } else {
                                pemain.Gerak(0, jarak);
                                repaint();
                            }

                        }
                    } else if (un[0].equalsIgnoreCase("d")) {
                        for (int i = 0; i < Integer.parseInt(String.valueOf(un[1])); i++) {
                            if (cekPemainNabrakTembok(pemain, "d")) {
                                return;
                            } else {
                                pemain.Gerak(0, -jarak);
                                repaint();
                            }
                        }
                    } else if (un[0].equalsIgnoreCase("r")) {
                        for (int i = 0; i < Integer.parseInt(String.valueOf(un[1])); i++) {
                            if (cekPemainNabrakTembok(pemain, "r")) {
                                return;
                            } else {
                                pemain.Gerak(-jarak, 0);
                                repaint();
                            }
                        }
                    } else if (un[0].equalsIgnoreCase("l")) {
                        for (int i = 0; i < Integer.parseInt(String.valueOf(un[1])); i++) {
                            if (cekPemainNabrakTembok(pemain, "l")) {
                                return;
                            } else {
                                pemain.Gerak(jarak, 0);
                                repaint();
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sudah Tidak Ada Yang Bisa di Undo, Silahkan Masukan Perintah Terlebih Dahulu");
            }
        } else if (in[0].matches("[udrl]") && in[1].matches("[123456789]") && in.length == 2) {
            undo.addLast(input);
            Allperintah.add(input);
            if (in[0].equalsIgnoreCase("u")) {
                for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                    if (cekPemainNabrakTembok(pemain, "u")) {
                        return;
                    } else {
                        pemain.Gerak(0, -jarak);
                        isCompleted();
                        repaint();
                    }

                }
            } else if (in[0].equalsIgnoreCase("d")) {
                for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                    if (cekPemainNabrakTembok(pemain, "d")) {
                        return;
                    } else {
                        pemain.Gerak(0, jarak);
                        isCompleted();
                        repaint();
                    }
                }
            } else if (in[0].equalsIgnoreCase("r")) {
                for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                    if (cekPemainNabrakTembok(pemain, "r")) {
                        return;
                    } else {
                        pemain.Gerak(jarak, 0);
                        isCompleted();
                        repaint();
                    }
                }
            } else if (in[0].equalsIgnoreCase("l")) {
                for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                    if (cekPemainNabrakTembok(pemain, "l")) {
                        return;
                    } else {
                        pemain.Gerak(-jarak, 0);
                        isCompleted();
                        repaint();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Input Tidak Valid, Silahkan Lihat Keterangan");
        }
    }

    private boolean cekPemainNabrakTembok(Sel pemain, String input) {
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

    public void isCompleted() {
        if (pemain.getPosisiX() == finish.getPosisiX() && pemain.getPosisiY() == finish.getPosisiY()) {
            completed = true;
        }
    }

    public void restartLevel() {
        Allperintah.clear();//hapus semua perintah yang tersimpan
        tembok.clear();//hapus tembok
        sel.clear();//hapus map
        completed = false;
        bacaKonfigurasi(Alamatpeta);//set ulang gambar peta
        repaint();//gambar ulang
    }

    public String getTeksPerintah() {
        String bantu = "";
        for (int i = 0; i < Allperintah.size(); i++) {
            bantu = bantu + Allperintah.get(i) + "\n";
        }
        return bantu;
    }

}
