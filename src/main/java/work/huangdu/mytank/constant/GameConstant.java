package work.huangdu.mytank.constant;

/**
 * 保存游戏定义的一些常数
 * 2019/9/21 9:49
 *
 * @author 445951954@qq.com
 */
public interface GameConstant {
    /**
     * 游戏属性文件路径
     */
    String PROPERTIES_PATH = "/mytank.properties";

    /**
     * 游戏状态
     */
    interface GameState {
        int START = 0;
        int RUNNING = 1;
        int PAUSE = 2;
        int GAMEOVER = 3;
        int SETMAP = 4;
    }

    /**
     * 描述生存状态的常量,活，死，可以移出
     */
    interface ElementState {
        int LIVE = 0;
        int DEAD = 1;
        int REMOVE = 2;
    }

    /**
     * 描述方向状态的常量
     */
    interface DirectionState {
        int UP = 0;
        int DOWN = 1;
        int LEFT = 2;
        int RIGHT = 3;
    }

    /**
     * XML文件标签
     */
    interface XmlFileLabel {
        String ROOT = "mytank";
        String GAME_MAP = "gamemap";
        String LEVEL = "level";
        String HOME = "home";
        String WALL = "wall";
        String STEEL = "steel";
        String RIVER = "river";
        String GRASS = "grass";
        String ICE = "ice";
        String STYLE = "style";
        String X = "x";
        String Y = "y";
        String STYLE_UP = "上";
        String STYLE_DOWN = "下";
        String STYLE_LEFT = "左";
        String STYLE_RIGHT = "右";
        String STYLE_LEFT_UP = "左上";
        String STYLE_LEFT_DOWN = "左下";
        String STYLE_RIGHT_UP = "右上";
        String STYLE_RIGHT_DOWN = "右下";
    }

    /**
     * 坦克火力等级
     */
    interface FireState {
        int NORMAL_FIRE = 0;
        int MEDIUM_FIRE = 1;
        int SUPER_FIRE = 2;
    }
}
