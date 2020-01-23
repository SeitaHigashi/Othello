package othello.utils;

import java.io.Serializable;

public class Coordinate implements Serializable {

    public int x;
    public int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coordinate shift(Arrow arrow){
        return new Coordinate(this.x + arrow.x, this.y + arrow.y);
    }

    @Override
    public String toString(){
        return "("+this.x+","+this.y+")";
    }
}
