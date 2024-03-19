import java.awt.*;

public class thief extends Enemy{
    public thief(Image img, int x, int y, int w, int h, String direction, int final_position) {
        super(img, x, y, w, h);
        this.Direction = direction;
        this.final_position = final_position;
        if (direction == "Y"){
            this.curr_position = y;
        }else {
            this.curr_position = x;
        }
    }
    private String Direction;
    private int curr_position;
    private int final_position;

    private boolean increase_x = true;
    private boolean increase_y = true;

    public String get_direction(){
        return this.Direction;
    }
    public int getCurr_position() {
        return curr_position;
    }
    public int getFinal_position() {
        return this.final_position;
    }

    public void thief_movement(int curr_position,int final_position ,String direction){

        if (direction == "Y"){
            if (super.getPosition_enemy_y() > final_position && increase_y == true){
                curr_position = super.getPosition_enemy_y();
                super.setPosition_enemy_y(curr_position-=1);
            }
            else if (super.getPosition_enemy_y() == final_position && increase_y == true && super.getPosition_enemy_y() < curr_position){
                this.increase_y = false;
                curr_position = super.getPosition_enemy_y();
                super.setPosition_enemy_y(curr_position+=1);
            }
            else if (super.getPosition_enemy_y() > final_position && increase_y == false && super.getPosition_enemy_y() < curr_position){
                curr_position = super.getPosition_enemy_y();
                super.setPosition_enemy_y(curr_position+=1);
            }
            else if  (super.getPosition_enemy_y() < final_position && increase_y == true){
                curr_position = super.getPosition_enemy_y();
                super.setPosition_enemy_y(curr_position+=1);
            } else if (super.getPosition_enemy_y() == final_position) {
                this.increase_y = false;
                curr_position = super.getPosition_enemy_y();
                super.setPosition_enemy_y(curr_position-=1);
            }else if (super.getPosition_enemy_y() > curr_position && this.increase_y == false) {
                curr_position = super.getPosition_enemy_y();
                super.setPosition_enemy_y(curr_position-=1);
            }else if (super.getPosition_enemy_y() == curr_position && this.increase_y == false) {
                this.increase_y = true;
            }
        } else if (direction == "X") {
            if (super.getPosition_enemy_x() > final_position && this.increase_x == true){
                curr_position = super.getPosition_enemy_x();
                super.setPosition_enemy_x(curr_position-=1);
            } else if (super.getPosition_enemy_x() == final_position && super.getPosition_enemy_x() < curr_position && this.increase_x == true){
                this.increase_x = false;
                curr_position = super.getPosition_enemy_x();
                super.setPosition_enemy_x(curr_position+=1);
            }else if (this.increase_x == false && super.getPosition_enemy_x() < curr_position ){
                curr_position = super.getPosition_enemy_x();
                super.setPosition_enemy_x(curr_position+=1);
            }
            else if (this.increase_x == false && super.getPosition_enemy_x() == curr_position && super.getPosition_enemy_x() > final_position ){
                curr_position = super.getPosition_enemy_x();
                super.setPosition_enemy_x(curr_position-=1);
                this.increase_x = true;
            }


        }
    }
}
