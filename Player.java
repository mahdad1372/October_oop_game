import java.awt.*;

public class Player {
    public Player(int player_coordinate_x , int player_coordinate_y, Image player_image){
        this.position_x =player_coordinate_x;
        this.position_y = player_coordinate_y;
        this.Image_player = player_image;
    }
    private int position_x;
    private int position_y;
    private Image Image_player;



    public int getPosition_x(){
        return this.position_x;
    }
    public int getPosition_y(){
        return this.position_y;
    }
    public Image getPlayerIcon(){
        return  this.Image_player;
    }

    public void setPlayerIcon(Image img){
        this.Image_player = img;
    }
    public void setPosition_x(int x){
        this.position_x = x;
    }
    public void setPosition_y(int y){
        this.position_y = y;
    }
    public void player_movement(String direction){
        switch (direction){
            case "left":
                this.position_x-=5;
                break;
            case "down":
                this.position_y+=5;
                break;
            case "right":
                this.position_x+=5;
                break;
            case "up":
                this.position_y-=5;
                break;
        }
    }
}
