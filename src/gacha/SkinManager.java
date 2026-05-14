package gacha;

import game.ScoreManager;
import java.io.*;
import java.util.*;

public class SkinManager {
    private static final String FILE_NAME = "iuers_pacman_skins.txt";
    public static final int ROLL_COST    = 1600;
    public static final int REFUND_COST  = 800;
    public static final String DEFAULT_SKIN_FOLDER = "Default Skin";
    private Set<String> unlockedFolderNames = new HashSet<>();
    private String selectedSkinFolder;
    private ScoreManager scoreManager;
    private static final List<Skin> ALL_SKINS = new ArrayList<>();
    static {
        // RARE skins
        ALL_SKINS.add(new Skin("Fire",       "Fire PacMan",       Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Ice",        "Ice PacMan",        Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Devil",      "Devil PacMan",      Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("LGBT",       "LGBT PacMan",       Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Basketball", "Basketball PacMan", Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Volleyball", "Volleyball PacMan", Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Tennis",     "Tennis PacMan",     Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Panda",      "Panda PacMan",      Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Polar Bear", "Polar PacMan",      Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Brown Bear", "Brown PacMan",      Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Koala",      "Koala PacMan",      Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Koala",      "Koala PacMan",      Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Koala",      "Koala PacMan",      Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Lime",      "Lime PacMan",      Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Saiyan",      "Saiyan PacMan",      Skin.Rarity.RARE));
        ALL_SKINS.add(new Skin("Samurai",      "Samurai PacMan",      Skin.Rarity.RARE));
        // EPIC skins
        ALL_SKINS.add(new Skin("Thanos",   "Thanos PacMan",   Skin.Rarity.EPIC));
        ALL_SKINS.add(new Skin("Batman",   "Bat-Man PacMan",  Skin.Rarity.EPIC));
        ALL_SKINS.add(new Skin("Iron Man", "Iron-Man PacMan", Skin.Rarity.EPIC));
        ALL_SKINS.add(new Skin("Joker",    "Joker PacMan",    Skin.Rarity.EPIC));
    }


    public SkinManager(ScoreManager scoreManager) {
        this.scoreManager      = scoreManager;
        this.selectedSkinFolder = DEFAULT_SKIN_FOLDER;
        loadData();
    }
    public RollResult rollOne() {
        if (scoreManager.getCumulativeScore() < ROLL_COST) return null;
        scoreManager.deductCumulativeScore(ROLL_COST);
        Random rand = new Random();
        List<Skin> pool = (rand.nextInt(100) < 20)
                ? getSkinsByRarity(Skin.Rarity.EPIC)
                : getSkinsByRarity(Skin.Rarity.RARE);
        Skin result = pool.get(rand.nextInt(pool.size()));

        boolean isDuplicate = unlockedFolderNames.contains(result.getFolderName());
        if (isDuplicate) {
            scoreManager.addCumulativeScore(REFUND_COST);
        } else {
            unlockedFolderNames.add(result.getFolderName());
        }
        saveData();
        return new RollResult(result, isDuplicate);
    }
    
    public List<RollResult> rollTen() {
        long totalCost = (long) ROLL_COST * 10;
        if (scoreManager.getCumulativeScore() < totalCost) return null;

        List<RollResult> results = new ArrayList<>();
        boolean hasEpic = false;
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            List<Skin> pool;
            if (i == 100 && !hasEpic) {
                pool = getSkinsByRarity(Skin.Rarity.EPIC);
            } else {
                pool = (rand.nextInt(100) < 2)
                        ? getSkinsByRarity(Skin.Rarity.EPIC)
                        : getSkinsByRarity(Skin.Rarity.RARE);
            }
            Skin skin = pool.get(rand.nextInt(pool.size()));
            if (skin.getRarity() == Skin.Rarity.EPIC) hasEpic = true;

            boolean isDuplicate = unlockedFolderNames.contains(skin.getFolderName());
            if (isDuplicate) {
                scoreManager.addCumulativeScore(REFUND_COST);
            } else {
                unlockedFolderNames.add(skin.getFolderName());
            }
            results.add(new RollResult(skin, isDuplicate));
        }

        scoreManager.deductCumulativeScore(totalCost);
        saveData();
        return results;
    }
    public List<RollResult> rollHundred() {
        long totalCost = (long) ROLL_COST * 100;
        if (scoreManager.getCumulativeScore() < totalCost) return null;
        scoreManager.deductCumulativeScore(totalCost);

        List<RollResult> results = new ArrayList<>();
        Random rand = new Random();
        boolean hasEpic = false;

        for (int i = 0; i < 100; i++) {
            List<Skin> pool;
            if (i == 99 && !hasEpic) {
                pool = getSkinsByRarity(Skin.Rarity.EPIC);
            } else {
                pool = (rand.nextInt(100) < 20)
                        ? getSkinsByRarity(Skin.Rarity.EPIC)
                        : getSkinsByRarity(Skin.Rarity.RARE);
            }
            Skin skin = pool.get(rand.nextInt(pool.size()));
            if (skin.getRarity() == Skin.Rarity.EPIC) hasEpic = true;

            boolean isDuplicate = unlockedFolderNames.contains(skin.getFolderName());
            if (isDuplicate) {
                scoreManager.addCumulativeScore(REFUND_COST);
            } else {
                unlockedFolderNames.add(skin.getFolderName());
            }
            results.add(new RollResult(skin, isDuplicate));
        }
        saveData();
        return results;
    }


    public void selectSkin(String folderName) {
        this.selectedSkinFolder = folderName;
        saveData();
    }

    public String getSelectedSkinFolder() { return selectedSkinFolder; }

    public List<Skin> getUnlockedSkins() {
        List<Skin> list = new ArrayList<>();
        for (Skin s : ALL_SKINS)
            if (unlockedFolderNames.contains(s.getFolderName())) list.add(s);
        return list;
    }

    public static List<Skin> getAllSkins() { return ALL_SKINS; }

    public ScoreManager getScoreManager() { return scoreManager; }

    public void saveData() {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
            pw.println(selectedSkinFolder);
            for (String folder : unlockedFolderNames) pw.println(folder);
            pw.flush();
        } catch (IOException e) {
            System.err.println("Lỗi lưu skin: " + e.getMessage());
        }
    }

    private void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line != null && !line.isBlank()) selectedSkinFolder = line.trim();
            while ((line = br.readLine()) != null)
                if (!line.isBlank()) unlockedFolderNames.add(line.trim());
        } catch (IOException e) {
            System.err.println("Lỗi load skin: " + e.getMessage());
        }
    }

    private List<Skin> getSkinsByRarity(Skin.Rarity rarity) {
        List<Skin> list = new ArrayList<>();
        for (Skin s : ALL_SKINS) if (s.getRarity() == rarity) list.add(s);
        return list;
    }

    public static class RollResult {
        public final Skin skin;
        public final boolean isDuplicate;

        public RollResult(Skin skin, boolean isDuplicate) {
            this.skin        = skin;
            this.isDuplicate = isDuplicate;
        }
    }
}