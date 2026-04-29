public class Ghost extends MoveSystem{
    private String ghostType;
    private boolean isFrighted;//con ma có trong trạng thái có thể ăn được hay không
    int speed; 
    public Ghost(int x, int y, int speed, String ghostType){
        super(x,y, speed);
        this.ghostType=ghostType;
    }
    @Override
    public void move(Map map){
        if (!map.isWall(x + dx*speed, y + dy*speed)){
            generateRandomDirection(map);
        }}
    public void generateRandomDirection(Map map){
    int[] directions = {-1, 0, 1}; 
    java.util.Random rand = new java.util.Random();
    while (true) {
        int newDx = directions[rand.nextInt(3)]; 
        int newDy = (newDx == 0) ? directions[rand.nextInt(3)] : 0; 
        if ((newDx != 0 || newDy != 0) && !map.isWall(x + newDx * speed, y + newDy * speed)) {
            this.dx = newDx;
            this.dy = newDy;
            break; 
        }
    }}
    public String getGhostType(){return ghostType;}
    public Boolean getIsFrighted(){return isFrighted;}
}