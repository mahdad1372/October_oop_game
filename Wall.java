public class Wall {
    private int Wall_coordinate_x;
    private int  Wall_coordinate_y;
    private int  width ;
    private int  height ;
    public Wall ( int coordinate_x, int coordinate_y,int width , int height){
        this.Wall_coordinate_x = coordinate_x;
        this.Wall_coordinate_y = coordinate_y;
        this.width = width;
        this.height = height;
    }
    public int getPosition_Wall_x() {
        return Wall_coordinate_x;
    }


    public int getPosition_Wall_y() {
        return Wall_coordinate_y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
