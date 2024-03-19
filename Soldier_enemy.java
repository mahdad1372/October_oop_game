import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;

public class Soldier_enemy extends Army_enemy{


    public Soldier_enemy(Image img, int position_x, int position_y, int w , int h,
                          Soldier_rocket rocket){
        super(img, position_x, position_y, w, h);
        this.Soldier_Rocket = rocket;
    }
    private Soldier_rocket Soldier_Rocket;
    ArrayList<Soldier_rocket> Soldier_rocket_list = new ArrayList<>();
    public ArrayList<Soldier_rocket> getSoldier_Rocket(){
        return  this.Soldier_rocket_list;
    }
    public void Shooting_Rocket_Soldier(){
        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Soldier_rocket_list.add(new Soldier_rocket(Soldier_Rocket.getPosition_coordinate_x(),Soldier_Rocket.getPosition_coordinate_y(),
                       Soldier_Rocket.getRocket_image(),Soldier_Rocket.getPositionFinal_x(),Soldier_Rocket.getPositionFinal_y()));
            }
        };
        timer.schedule(task, 0, 4000);

    }

    public void Editing_Rocket_List(){
        for (int i=0;i< Soldier_rocket_list.size();i++){
            if (Soldier_rocket_list.get(i).getPosition_coordinate_x() ==Soldier_rocket_list.get(i).getPositionFinal_x() &&
                    Soldier_rocket_list.get(i).getPosition_coordinate_y() == Soldier_rocket_list.get(i).getPositionFinal_y()){
                Soldier_rocket_list.remove(i);
            }
        }
    }
}
