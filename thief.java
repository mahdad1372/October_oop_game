import java.awt.*;

public class thief extends Enemy{
    public thief(Image img, int coordinate_x, int coordinate_y, int width, int height, String direction, int final_position) {
        super(img, coordinate_x, coordinate_y, width, height);
        this.Direction = direction;
        this.final_position = final_position;
        if (direction == "Y"){
            this.curr_position = coordinate_y;
        }else {
            this.curr_position = coordinate_x;
        }
    }
    private String Direction;
    private int curr_position;
    private int final_position;

    private boolean increase_coordinate_x = true;
    private boolean increase_coordinate_y = true;

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
            if (super.getPosition_enemy_y() > final_position && increase_coordinate_y == true){
                curr_position = super.getPosition_enemy_y();
                super.setPosition_enemy_y(curr_position-=1);
            }
            else if (super.getPosition_enemy_y() == final_position && increase_coordinate_y == true && super.getPosition_enemy_y() < curr_position){
                this.increase_coordinate_y = false;
                curr_position = super.getPosition_enemy_y();
                super.setPosition_enemy_y(curr_position+=1);
            }
            else if (super.getPosition_enemy_y() > final_position && increase_coordinate_y == false && super.getPosition_enemy_y() < curr_position){
                curr_position = super.getPosition_enemy_y();
                super.setPosition_enemy_y(curr_position+=1);
            }
            else if  (super.getPosition_enemy_y() < final_position && increase_coordinate_y == true){
                curr_position = super.getPosition_enemy_y();
                super.setPosition_enemy_y(curr_position+=1);
            } else if (super.getPosition_enemy_y() == final_position) {
                this.increase_coordinate_y = false;
                curr_position = super.getPosition_enemy_y();
                super.setPosition_enemy_y(curr_position-=1);
            }else if (super.getPosition_enemy_y() > curr_position && this.increase_coordinate_y == false) {
                curr_position = super.getPosition_enemy_y();
                super.setPosition_enemy_y(curr_position-=1);
            }else if (super.getPosition_enemy_y() == curr_position && this.increase_coordinate_y == false) {
                this.increase_coordinate_y = true;
            }
        } else if (direction == "X") {
            if (super.getPosition_enemy_x() > final_position && this.increase_coordinate_x == true){
                curr_position = super.getPosition_enemy_x();
                super.setPosition_enemy_x(curr_position-=1);
            } else if (super.getPosition_enemy_x() == final_position && super.getPosition_enemy_x() < curr_position && this.increase_coordinate_x == true){
                this.increase_coordinate_x = false;
                curr_position = super.getPosition_enemy_x();
                super.setPosition_enemy_x(curr_position+=1);
            }else if (this.increase_coordinate_x == false && super.getPosition_enemy_x() < curr_position ){
                curr_position = super.getPosition_enemy_x();
                super.setPosition_enemy_x(curr_position+=1);
            }
            else if (this.increase_coordinate_x == false && super.getPosition_enemy_x() == curr_position && super.getPosition_enemy_x() > final_position ){
                curr_position = super.getPosition_enemy_x();
                super.setPosition_enemy_x(curr_position-=1);
                this.increase_coordinate_x = true;
            }


        }
    }

    @Override
    int killing_enemy_score() {
        return 10;
    }
}
