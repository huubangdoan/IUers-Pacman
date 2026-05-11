package gacha;

public class Skin {
    public enum Rarity { RARE, EPIC , COMMON}

    private final String name;
    private final String folderName;
    private final Rarity rarity;

    public Skin(String name, String folderName, Rarity rarity) {
        this.name       = name;
        this.folderName = folderName;
        this.rarity     = rarity;
    }

    public String getName()       { return name; }
    public String getFolderName() { return folderName; }
    public Rarity getRarity()     { return rarity; }

    private String rarityFolder() {
        return switch (rarity) {
            case EPIC -> "Epic Skin";
            case RARE -> "Rare Skin";
            case COMMON -> "Common Skin";
        };
    }

    public String[] getFrames(String direction) {
        String base = "src/assets/Gacha Skin/" + rarityFolder() + "/" + folderName + "/" + direction + "/";
        return new String[]{ base + "1.png", base + "2.png", base + "3.png" };
    }
    public String getPreviewPath() {
        return "src/assets/Gacha Skin/" + rarityFolder() + "/" + folderName + "/right/1.png";
    }
}