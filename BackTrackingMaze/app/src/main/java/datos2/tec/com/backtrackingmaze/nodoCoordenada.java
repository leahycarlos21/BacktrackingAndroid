package datos2.tec.com.backtrackingmaze;

import android.graphics.Point;

public class nodoCoordenada {
    private boolean state;//True para poner, False para quitar
    private Point posXY;

    public nodoCoordenada(boolean state, Point posXY) {
        this.state = state;
        this.posXY = posXY;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Point getPosXY() {
        return posXY;
    }

    public void setPosXY(Point posXY) {
        this.posXY = posXY;
    }
}
