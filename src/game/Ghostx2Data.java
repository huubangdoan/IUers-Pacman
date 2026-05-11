public class Ghostx2Data {
    private int x, y;
    private double speed;
    private String name;

    public Ghostx2Data(String name, int x, int y, double initialSpeed) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.speed = initialSpeed;
    }

    // Phương thức cốt lõi để tăng độ khó
    public void doubleSpeed() {
        this.speed *= 2; 
        System.out.println("Cảnh báo: " + name + " đã tăng tốc lên: " + this.speed);
    }

    // Getters và Setters
    public double getSpeed() { return speed; }
    public int getX() { return x; }
    public int getY() { return y; }
}