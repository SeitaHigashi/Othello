package othello.utils;

public enum Arrow implements Location {
    Up(0, 1),
    UpperRight(1, 1),
    Right(1, 0),
    LowerRight(1, -1),
    Lower(0, -1),
    LowerLeft(-1, -1),
    Left(-1, 0),
    UpperLeft(-1, 1);

    int x;
    int y;

    Arrow(int x, int y){
        this.x = x;
        this.y = y;
    }
}
