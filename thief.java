import java.awt.*;

public class thief extends Enemy{
    public thief(Image img, int x, int y, int w, int h, String direction, int final_position) {
        super(img, x, y, w, h, direction, final_position);
    }

    @Override
    public void enemy_movement(int curr_position, int final_position, String direction) {
        super.enemy_movement(curr_position, final_position, direction);
    }

    @Override
    public int getFinal_position() {
        return super.getFinal_position();
    }

    @Override
    public String getDirection() {
        return super.getDirection();
    }

    @Override
    public int getPosition_enemy_x() {
        return super.getPosition_enemy_x();
    }

    @Override
    public int getPosition_enemy_y() {
        return super.getPosition_enemy_y();
    }
    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public int getCurr_position() {
        return super.getCurr_position();
    }



    @Override
    public Image getImage_enemy() {
        return super.getImage_enemy();
    }

    @Override
    public void set_Position_enemy_y(int y) {
        super.set_Position_enemy_y(y);
    }
}
