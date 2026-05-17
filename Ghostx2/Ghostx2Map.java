import java.util.ArrayList;
import java.util.List;

public class Ghostx2Map {
    private List<Ghostx2Data> ghosts;
    private int scoreThreshold = 100; // Cứ mỗi 100 điểm thì tăng độ khó
    private int lastDifficultyLevel = 0;

    public Ghostx2Map() {
        ghosts = new ArrayList<>();
        // Khởi tạo một vài Ghost mẫu
        ghosts.add(new Ghostx2Data("Blinky", 10, 10, 1.5));
        ghosts.add(new Ghostx2Data("Pinky", 15, 15, 1.5));
        ghosts.add(new Ghostx2Data("Inky", 20, 20, 1.5));
    }

    // Kiểm tra điểm số để cập nhật tốc độ Ghost
    public void updateDifficulty(int currentScore) {
        int currentLevel = currentScore / scoreThreshold;

        if (currentLevel > lastDifficultyLevel) {
            lastDifficultyLevel = currentLevel;
            System.out.println("--- UP LEVEL: " + lastDifficultyLevel + " ---");
            
            // Duyệt danh sách và x2 tốc độ từng con ghost
            for (Ghostx2Data g : ghosts) {
                g.doubleSpeed();
            }
        }
    }

    public List<Ghostx2Data> getGhosts() {
        return ghosts;
    }
}