import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;

public class Missile_launcher extends Army_enemy{
    public Missile_launcher(Image img, int coordinate_x, int coordinate_y, int width, int height, Missile Missile){
        super(img, coordinate_x, coordinate_y, width, height);
        this.Missile = Missile;
    }
    private Missile Missile;
    private ArrayList<Missile> Missile_List = new ArrayList<Missile>();
    public ArrayList<Missile> get_Missiles(){
        return this.Missile_List;
    }
    public void Adding_Missiles(){
        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Missile_List.add(new Missile(Missile.getPosition_coordinate_x(),Missile.getPosition_coordinate_y(),
                        Missile.getPositionFinal_x(),Missile.getPositionFinal_y(), Missile.getMissileImage()));
            }
        };
        timer.schedule(task, 0, 7000);
    }
    public void Editing_Missile_List(){
        for (int i=0;i< Missile_List.size();i++){
            if (Missile_List.get(i).getPosition_coordinate_x() ==Missile_List.get(i).getPositionFinal_x() &&
                    Missile_List.get(i).getPosition_coordinate_y() ==Missile_List.get(i).getPositionFinal_y()){
                Missile_List.remove(i);
            }
        }
    }
}
