public class Durian extends Fruit{
    public Durian(int x, int y, String name){
        super(x, y, "Durian");
    }
    @Override
    public void onCollected(PacMan player){
                player.setHasThorns(true); 
                //tạo thêm một method nữa trong con pacman là setHasThorn xong rồi cài logic vô map nhé  
            }
        }