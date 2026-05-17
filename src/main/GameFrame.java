package main;
import controller.*;
import gacha.SkinManager;
import game.*;
import java.awt.*;
import javax.swing.*;
import view.*;

public class GameFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainContainer;

    private MainMenuController    mainCtrl;
    private MapMenuController     mapCtrl;
    private GachaMenuController   gachaCtrl;
    private GachaResultController gachaResultCtrl;
    private SkinMenuController    skinCtrl;
    private Map1Controller        map1Ctrl;
    private Map2Controller        map2Ctrl;
    private Map3Controller        map3Ctrl;
    //private EndlessController     endlessCtrl;
    private SettingsController    settingsCtrl;
    private LoseController        loseCtrl;
    private WinController         winCtrl;

    public GameFrame() {
        setTitle("Pac-Man: Multiverse");
        getContentPane().setPreferredSize(new Dimension(672, 672));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout    = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // ── ScoreManager dùng chung (highscore + điểm tích lũy gacha) ───────
        ScoreManager scoreManager = new ScoreManager();

        // ── SkinManager nhận ScoreManager để gacha trừ/cộng điểm ────────────
        SkinManager skinManager = new SkinManager(scoreManager);
        GameAssets assets=new GameAssets(skinManager);
        GameRenderer renderer= new GameRenderer(assets);


        // ── Controllers ──────────────────────────────────────────────────────
        mainCtrl    = new MainMenuController(cardLayout, mainContainer);
        mapCtrl     = new MapMenuController(cardLayout, mainContainer);
        skinCtrl    = new SkinMenuController(cardLayout, mainContainer);
        map1Ctrl    = new Map1Controller(cardLayout, mainContainer);
        map2Ctrl    = new Map2Controller(cardLayout, mainContainer);
        map3Ctrl    = new Map3Controller(cardLayout, mainContainer);
        //endlessCtrl = new EndlessController(cardLayout, mainContainer);
        settingsCtrl= new SettingsController(cardLayout, mainContainer);
        loseCtrl    = new LoseController(cardLayout, mainContainer);
        winCtrl     = new WinController(cardLayout, mainContainer);
        
        // ── SkinMenuPanel tạo trước (GachaResultController cần nó) ──────────
        SkinMenuPanel skinMenuPanel = new SkinMenuPanel(skinCtrl, skinManager);
        skinCtrl.setSkinMenuPanel(skinMenuPanel);
        mainCtrl.setSkinMenuPanel(skinMenuPanel);

        // ── GachaResult ──────────────────────────────────────────────────────
        gachaResultCtrl = new GachaResultController(cardLayout, mainContainer, skinMenuPanel);
        GachaResultPanel gachaResultPanel = new GachaResultPanel(gachaResultCtrl);

        // ── GachaMenu ────────────────────────────────────────────────────────
        gachaCtrl = new GachaMenuController(cardLayout, mainContainer, skinManager, gachaResultPanel);
        GachaMenuPanel gachaMenuPanel = new GachaMenuPanel(gachaCtrl, skinManager);
        gachaCtrl.setGachaMenuPanel(gachaMenuPanel);

        // ── Map Panels (truyền skinManager để load đúng skin đã chọn) ────────
        Map1Panel map1Panel       = new Map1Panel(map1Ctrl, skinManager, renderer, MapData.GRID, GameAssets.wall2Img, GameAssets.backGround2Img);
        Map2Panel map2Panel       = new Map2Panel(map2Ctrl, skinManager, renderer, SnakeData.GRID, GameAssets.wall3Img, GameAssets.backGround1Img);
        Map3Panel map3Panel       = new Map3Panel(map3Ctrl, skinManager, renderer, ChaoData.GRID, GameAssets.wall1Img, GameAssets.backGround2Img);
        //EndlessPanel endlessPanel = new EndlessPanel(endlessCtrl, skinManager);

        // ── Thêm tất cả vào CardLayout ───────────────────────────────────────
        mainContainer.add(new MainMenuPanel(mainCtrl), "MainMenu");
        mainContainer.add(new SettingsPanel(settingsCtrl), "Settings");
        mainContainer.add(gachaMenuPanel,              "GachaMenu");
        mainContainer.add(gachaResultPanel,            "GachaResult");
        mainContainer.add(skinMenuPanel,               "SkinMenu");
        mainContainer.add(new MapMenuPanel(mapCtrl),   "MapMenu");
        mainContainer.add(map1Panel,                   "Map1");
        mainContainer.add(map2Panel,                   "Map2");
        mainContainer.add(map3Panel,                   "Map3");
        mainContainer.add(new WinPanel(winCtrl), "Win");
        mainContainer.add(new LosePanel(loseCtrl), "Lose");
        //mainContainer.add(endlessPanel,                "Endless");

        add(mainContainer);
        cardLayout.show(mainContainer, "MainMenu");
    }
}