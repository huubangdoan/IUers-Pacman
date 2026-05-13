public class Ghostx2Run {
    public static void main(String[] args) {
        Ghostx2Map gameMap = new Ghostx2Map();
        int myScore = 0;

        System.out.println("Bắt đầu trò chơi. Tốc độ Ghost hiện tại: 1.5");

        // Giả lập quá trình chơi game
        for (int i = 1; i <= 300; i++) {
            myScore++; // Giả sử mỗi bước ăn 1 điểm
            
            // Gọi hàm kiểm tra độ khó liên tục trong Game Loop
            gameMap.updateDifficulty(myScore);

            // In ra thông báo tại các mốc quan trọng để kiểm tra
            if (myScore % 100 == 0) {
                System.out.println("Điểm hiện tại: " + myScore);
            }
        }
        
        System.out.println("Kết thúc mô phỏng.");
    }
}