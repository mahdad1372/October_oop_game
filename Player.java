import java.awt.*;

public class Player {
    public Player(int x , int y, Image icon){
        this.position_x =x;
        this.position_y = y;
        this.Icon_player = icon;
    }
    private int position_x;
    private int position_y;
    private Image Icon_player;



    public int getPosition_x(){
        return this.position_x;
    }
    public int getPosition_y(){
        return this.position_y;
    }
    public Image getPlayerIcon(){
        return  this.Icon_player;
    }

    public void setPlayerIcon(Image img){
        this.Icon_player = img;
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
