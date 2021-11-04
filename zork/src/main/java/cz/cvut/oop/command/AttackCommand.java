package cz.cvut.oop.command;

import java.util.concurrent.ThreadLocalRandom;
import cz.cvut.oop.game.GameData;
import cz.cvut.oop.game.enemy.Enemy;
import cz.cvut.oop.game.item.CommonItem;
import cz.cvut.oop.game.item.Item;


public final class AttackCommand  implements Command{

    private Enemy enemy;

    public AttackCommand(){

    }

    @Override
    public String getName() {
        return "útok";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {

        if(gameData.getCurrentRoom().getEnemy() == null){
            return "Není na koho zaútočit a tak jsi jen epicky zamával zbraní";
        }
        else if(gameData.getHero().getWeapon() == null){
            return "Nemáš zbraň a " + gameData.getCurrentRoom().getEnemy().getEnemyFlyweight().getName() + " tě zranil";

        }
        else {
            int dmg = ThreadLocalRandom.current().nextInt(gameData.getHero().getWeapon().statsMin(), gameData.getHero().getWeapon().statsMax() + 1);
            gameData.getCurrentRoom().getEnemy().getEnemyFlyweight().setHP(gameData.getCurrentRoom().getEnemy().getEnemyFlyweight().getHP() - dmg);
            if (gameData.getCurrentRoom().getEnemy().getEnemyFlyweight().getHP() <= 0) {

                if(gameData.getCurrentRoom().getEnemy().getType().equals("boss")){
                    gameData.setFinished(true);
                }

                if(gameData.getCurrentRoom().getEnemy().getLoot()==null){
                    gameData.getCurrentRoom().setEnemy(null);
                    return "Zasáhl jsi nepřítele za " + dmg +" poškození, nepřítel je mrtvý";
                }
                else {
                    Item item = gameData.getCurrentRoom().getEnemy().getLoot();
                    gameData.getCurrentRoom().setItem(item);
                    gameData.getCurrentRoom().setEnemy(null);
                    return "Zasáhl jsi nepřítele za " + dmg +" poškození, nepřítel je mrtvý a v jeho útrobách jsi našel: " + item.getName();
                }
            }
            else {
                return "Zasáhl jsi nepřítele za " + dmg + " poškození a zbývá mu ještě " + gameData.getCurrentRoom().getEnemy().getEnemyFlyweight().getHP() + " životů";
            }
        }

        //return "Zasáhl jsi nepřítele";
    }
}
