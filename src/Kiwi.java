public class Kiwi extends Fruit{
    public Kiwi(int x, int y){
        super(x, y, "Kiwi");
    }
    @Override
    public void onCollected(PacMan player){
                player.activateKiwiDisguise();
                //tạo thêm một method nữa trong con pacman là setHasThorn xong rồi cài logic vô map nhé  
            }
        }