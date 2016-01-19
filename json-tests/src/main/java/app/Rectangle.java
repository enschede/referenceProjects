package app;

/**
 * Created by marc on 18/01/16.
 */
public class Rectangle {

    private int w, h;

    public Rectangle() {
    }

    public Rectangle(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getSize() {
        return w * h;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "w=" + w +
                ", h=" + h +
                '}';
    }
}
