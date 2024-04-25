import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static Hero player;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BattleScene battleScene = new BattleScene();
        System.out.println("Введите имя персонажа:");
        try {
            command(Realm.br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(
                    string,
                    100,
                    20,
                    20,
                    0,
                    0
            );
            System.out.println(String.format("Спасти наш мир от драконов вызвался %s! Да будет его броня крепка и бицепс кругл!", player.getName()));
            printNavigation();
        }
        switch (string) {
            case "1": {
                System.out.println("Торговец еще не приехал");
                command(Realm.br.readLine());
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет": {
                printNavigation();
                command(Realm.br.readLine());
            }
        }
        command(Realm.br.readLine());

        System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
        try {
            command(Realm.br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
    }

    private static void commitFight() {
        Object player = null;
        BattleScene.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                FantasyCharacter player;
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d едениц здоровья.", player.getName(), player.getXp(), player.getGold(), player.getHealthPoints()));
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                try {
                    command(Realm.br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }

    private static FantasyCharacter createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin(
                "Гоблин",
                50,
                10,
                10,
                100,
                20
        );
        else return new Skeleton(
                "Скелет",
                25,
                20,
                20,
                100,
                10
        );
    }



}