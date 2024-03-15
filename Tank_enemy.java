import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;

public class Tank_enemy extends Army_enemy{
    public Tank_enemy(Image img, int x, int y, int w , int h, String direction, int final_position ,Tank_rocket tank_rocket){
        super(img, x, y, w, h, direction, final_position);
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
                Tank_rocket_list.add(new Tank_rocket(tank_rocket.getPosition_x(),tank_rocket.getPosition_y(),
                        tank_rocket.getRocket_image(),tank_rocket.getPositionFinal_x(),tank_rocket.getPositionFinal_y()));
            }
        };
        timer.schedule(task, 0, 7000);

    }

    public void Editing_Rocket_List(){
        for (int i=0;i< Tank_rocket_list.size();i++){
            if (Tank_rocket_list.get(i).getPosition_x() ==Tank_rocket_list.get(i).getPositionFinal_x() &&
                    Tank_rocket_list.get(i).getPosition_y() == Tank_rocket_list.get(i).getPositionFinal_y()){
                Tank_rocket_list.remove(i);
            }
        }
    }
}
