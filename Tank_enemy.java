import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;

public class Tank_enemy extends Army_enemy{
    public Tank_enemy(Image img, int tank_coordinate_x, int tank_coordinate_y, int width , int height ,Tank_rocket tank_rocket){
        super(img, tank_coordinate_x, tank_coordinate_y, width, height);
        this.tank_rocket = tank_rocket;
    }
    private Tank_rocket tank_rocket;
    ArrayList<Tank_rocket> Tank_rocket_list = new ArrayList<>();
    public ArrayList<Tank_rocket> getTank_Rocket(){
        return this.Tank_rocket_list;
    }
    public void Shooting_Rocket_Tank(){
        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Tank_rocket_list.add(new Tank_rocket(tank_rocket.getPosition_coordinate_x(),tank_rocket.getPosition_coordinate_y(),
                        tank_rocket.getRocket_image(),tank_rocket.getPositionFinal_x(),tank_rocket.getPositionFinal_y()));
            }
        };
        timer.schedule(task, 0, 7000);

    }

    public void Editing_Rocket_List(){
        for (int i=0;i< Tank_rocket_list.size();i++){
            if (Tank_rocket_list.get(i).getPosition_coordinate_x() ==Tank_rocket_list.get(i).getPositionFinal_x() &&
                    Tank_rocket_list.get(i).getPosition_coordinate_y() == Tank_rocket_list.get(i).getPositionFinal_y()){
                Tank_rocket_list.remove(i);
            }
        }
    }
}
