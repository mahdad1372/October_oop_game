import java.awt.*;

public class Weapon {
    private Image weapon;

    public Weapon(Image img){
        this.weapon = img;
    }
    public void set_image(Image img){
        this.weapon = img;
    }

    public Image getWeapon() {
        return weapon;
    }
}
