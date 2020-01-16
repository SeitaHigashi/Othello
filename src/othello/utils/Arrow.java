package othello.utils;

public enum Arrow {
    Up(0, -1),
    UpperRight(1, -1),
    Right(1, 0),
    LowerRight(1, 1),
    Lower(0, 1),
    LowerLeft(-1, 1),
    Left(-1, 0),
    UpperLeft(-1, -1);

    public int x;
    public int y;

    Arrow(int x, int y){
        this.x = x;
        this.y = y;
    }
}
