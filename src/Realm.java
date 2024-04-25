import java.io.BufferedReader;

public class Realm {

    public static BufferedReader br;
    private static FantasyCharacter player = null;
    private static BattleScene battleScene = null;

    public class FightCallback {
        public void fightLost() {
        }

        public void fightWin() {
        }
    }
}
