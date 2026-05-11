package game;
import java.io.*;
public class ScoreManager {
    private static final String FILE_NAME = "iuers_pacman_data.txt";
    private int highscore = 0;
    private long totalAccumulatedScore = 0;
    public ScoreManager() {
        loadData();
    }
    private void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Chưa có file lưu, bắt đầu với điểm 0.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line1 = br.readLine();
            if (line1 != null && !line1.isEmpty()) {
                this.highscore = Integer.parseInt(line1.trim());
            }
            
            String line2 = br.readLine();
            if (line2 != null && !line2.isEmpty()) {
                this.totalAccumulatedScore = Long.parseLong(line2.trim());
            }
            System.out.println("Load thành công! Highscore: " + highscore + " | Total: " + totalAccumulatedScore);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Lỗi khi load file: " + e.getMessage());
        }
    }
    public void saveData(int currentScore) {
        if (currentScore > highscore) {
            highscore = currentScore;
        }
        totalAccumulatedScore += currentScore;
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
            pw.println(highscore);
            pw.println(totalAccumulatedScore);
            pw.flush(); 
        } catch (IOException e) {
            System.err.println("Lỗi ghi file: " + e.getMessage());
        }
    }
    public int getHighscore() { return highscore; }
    public long getCumulativeScore() { return totalAccumulatedScore; }
}