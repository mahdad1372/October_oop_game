public class Wall {
    private int Wall_x;
    private int  Wall_y;
    private int  width ;
    private int  height ;
    public Wall ( int x, int y,int w , int h){
        this.Wall_x = x;
        this.Wall_y = y;
        this.width = w;
        this.height = h;
    }
    public int getPosition_Wall_x() {
        return Wall_x;
    }


    public int getPosition_Wall_y() {
        return Wall_y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
