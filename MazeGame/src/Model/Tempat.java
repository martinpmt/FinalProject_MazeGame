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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Gregorius Bryan Osaldi x Martin Paramarta
 */
public class Tempat extends JPanel implements Serializable {

    private ArrayList<Tembok> tembok = new ArrayList<>();//menyimpan data tembok
    private ArrayList<Jalan> jalan = new ArrayList<>();
    private ArrayList<Sel> sel = new ArrayList<>();//menyimpan data tembok,finish,pemain
    private Finish finish;
    private Pemain pemain;
    private LinkedList<String> undo = new LinkedList<>();
    private int lebarTempat = 0;
    private int tinggiTempat = 0;
    private int jarak = 30;//untuk menentukan besarkan pixel/jarak space gambar didalam panel
    private String isi;

    private File Alamatpeta;//digunakan untuk merestart level
    private ArrayList<String> Allperintah = new ArrayList();//menyimpan semua perintah yang dimasukkan

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

    public ArrayList<Jalan> getJalan() {
        return jalan;
    }

    public void setJalan(Jalan jalan) {
        this.jalan.add(jalan);
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

    public void setSel(ArrayList<Jalan> jalan, Pemain pemain, ArrayList<Tembok> tembok, Finish finish) {
        this.sel.addAll(jalan);
        this.sel.addAll(tembok);
        this.sel.add(finish);
        this.sel.add(pemain);
    }

    public void savePermainan(File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            for (int i = 0; i < this.Allperintah.size(); i++) {
                String data = this.Allperintah.get(i) + ",";
                fos.write(data.getBytes());
            }
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void bacaKonfigurasi(File file) {
        try {
            if (file != null) {
                FileInputStream fis = new FileInputStream(file);
                Alamatpeta = file;
                int lebar = 0;
                int tinggi = 0;
                int posisiX = 0;
                int posisiY = 0;
                Tembok wall;
                Jalan route;
                String isi = "";
                int data;
                while ((data = fis.read()) != -1) {
                    isi = isi + (char) data;
                    if ((char) data != '\n') {
                        if ((char) data == '#') {
                            wall = new Tembok(posisiX, posisiY, lebar, tinggi, (char) data);
                            setTembok(wall);
                            posisiX++;
                            lebar += jarak;
                        } else if ((char) data == '@') {
                            pemain = new Pemain(posisiX, posisiY, lebar, tinggi, (char) data);
                            posisiX++;
                            lebar += jarak;
                        } else if ((char) data == '.') {
                            route = new Jalan(posisiX, posisiY, lebar, tinggi, (char) data);
                            setJalan(route);
                            posisiX++;
                            lebar += jarak;
                        } else if ((char) data == 'O') {
                            finish = new Finish(posisiX, posisiY, lebar, tinggi, (char) data);
                            posisiX++;
                            lebar += jarak;
                        }
                    } else {
                        posisiY++;
                        tinggi += jarak;
                        lebarTempat = lebar;
                        posisiX = 0;
                        lebar = 0;
                    }
                    tinggiTempat = tinggi;
                }
                setIsi(isi);
                setSel(jalan, pemain, tembok, finish);
            }

        } catch (IOException ex) {
            Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);	   // Hapus background
        // Tempat Gambar:
        g.setColor(new Color(128, 128, 128));//set panel warna gray
        g.fillRect(0, 0, this.getLebarTempat(), this.getTinggiTempat());// set tinggi lebar sesuai konfigurasi
        for (int i = 0; i < sel.size(); i++) {
            if (sel.get(i) != null) {
                Sel item = (Sel) sel.get(i);//map diterjemahkan dalam kelas pixel.
                g.drawImage(item.getImage(), item.getLebar(), item.getTinggi(), this);//proses gambar di panel
            }
        }
    }

    public int getLebarTempat() {
        return lebarTempat;
    }

    public void setLebarTempat(int lebarTempat) {
        this.lebarTempat = lebarTempat;
    }

    public int getTinggiTempat() {
        return tinggiTempat;
    }

    public void setTinggiTempat(int tinggiTempat) {
        this.tinggiTempat = tinggiTempat;
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
                            if (!cekPemainNabrakTembok(pemain, "u")) {
                                pemain.Gerak(0, jarak);
                                repaint();
                            }

                        }
                    } else if (un[0].equalsIgnoreCase("d")) {
                        for (int i = 0; i < Integer.parseInt(String.valueOf(un[1])); i++) {
                            if (!cekPemainNabrakTembok(pemain, "d")) {
                                pemain.Gerak(0, -jarak);
                                repaint();
                            }
                        }
                    } else if (un[0].equalsIgnoreCase("r")) {
                        for (int i = 0; i < Integer.parseInt(String.valueOf(un[1])); i++) {
                            if (!cekPemainNabrakTembok(pemain, "r")) {
                                pemain.Gerak(-jarak, 0);
                                repaint();
                            }
                        }
                    } else if (un[0].equalsIgnoreCase("l")) {
                        for (int i = 0; i < Integer.parseInt(String.valueOf(un[1])); i++) {
                            if (!cekPemainNabrakTembok(pemain, "l")) {
                                pemain.Gerak(jarak, 0);
                                repaint();
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sudah Tidak Ada Yang Bisa di Undo, Silahkan Masukan Perintah Terlebih Dahulu");
            }
        } else if (in[0].matches("[udrl]") || in[0].matches("[UDRL]") && in[1].matches("[123456789]") && in.length == 2) {
            undo.addLast(input);
            Allperintah.add(input);
            if (in[0].equalsIgnoreCase("u")) {
                for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                    if (!cekPemainNabrakTembok(pemain, "u")) {
                        pemain.Gerak(0, -jarak);
                        repaint();
                    }

                }
            } else if (in[0].equalsIgnoreCase("d")) {
                for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                    if (!cekPemainNabrakTembok(pemain, "d")) {
                        pemain.Gerak(0, jarak);
                        repaint();
                    }
                }
            } else if (in[0].equalsIgnoreCase("r")) {
                for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                    if (!cekPemainNabrakTembok(pemain, "r")) {
                        pemain.Gerak(jarak, 0);
                        repaint();
                    }
                }
            } else if (in[0].equalsIgnoreCase("l")) {
                for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                    if (!cekPemainNabrakTembok(pemain, "l")) {
                        pemain.Gerak(-jarak, 0);
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
        if (input.equals("l")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//ambil posisi tembok
                if (pemain.PosisiKiriObjek(wall)) {
                    bantu = true;
                    break;
                }
            }

        } else if (input.equals("r")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//ambil posisi tembok
                if (pemain.PosisiKananObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equals("u")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//ambil posisi tembok
                if (pemain.PosisiAtasObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equals("d")) {
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

    public void pintas() {
        pemain.setLebar(finish.getLebar());
        pemain.setTinggi(finish.getTinggi());
        isCompleted();
        repaint();
    }

    public void save() {
        this.savePermainan(new File("save.dat"));
    }

    public void load() {
        try {
            this.Allperintah.clear();
            Tempat tempat = new Tempat(Alamatpeta);
            tempat.loadPermainan(new File("save.dat"));
            for (int i = 0; i < tempat.Allperintah.size(); i++) {
                PerintahGerak(tempat.Allperintah.get(i));
            }
            this.savePermainan(new File("save.dat"));
        } catch (IOException ex) {
            Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadPermainan(File file) throws IOException {
        FileInputStream fis = null;
        try {
            String hasilBaca = "";
            fis = new FileInputStream(file);
            int dataInt;

            while ((dataInt = fis.read()) != -1) {
                if ((char) dataInt == ',') {
                    this.Allperintah.add(hasilBaca);
                    hasilBaca = "";
                } else {
                    hasilBaca = hasilBaca + (char) dataInt;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void isCompleted() {
        if (pemain.getLebar() == finish.getLebar() && pemain.getTinggi() == finish.getTinggi()) {
            JOptionPane.showMessageDialog(null, "Selamat anda berhasil!");
        }
    }

    public void restartLevel() {
        Allperintah.clear();//hapus semua perintah yang tersimpan
        tembok.clear();//hapus tembok
        jalan.clear();
        sel.clear();//hapus map
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
