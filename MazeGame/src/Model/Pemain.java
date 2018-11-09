/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author jarkom
 */
public class Pemain {

    private int x, y, tileX, tileY;

    public Pemain() {
        x = 32;
        y = 32;

        tileX = 1;
        tileY = 1;
    }

    public void move(int dx, int dy, int tx, int ty) {
        x += dx;
        y += dy;

        tileX += tx;
        tileY += ty;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }
    
}
