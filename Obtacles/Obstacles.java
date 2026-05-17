package Obtacles;

public abstract class Obstacles implements obstecles_measurement {
    public Obstacles(int coordinate_x,int coordinate_y,int length,int height){
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
        this.length = length;
        this.height = height;
    }
      private final int coordinate_x;
      private final int coordinate_y;
      private final int length;
      private final int height;

    public int getCoordinate_x() {
        return coordinate_x;
    }

    public int getCoordinate_y() {
        return coordinate_y;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }
}

