package cz.cvut.oop.game;


import cz.cvut.oop.command.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import cz.cvut.oop.ui.CommandLineUi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *  Class represents running game instance
 *
 */
public class GameImpl implements Game {

    private static Map<String, Command> commands = new HashMap<>();
    private GameData gameData;
    private static final Logger log = LogManager.getLogger(GameDataImpl.class);

    public GameImpl(){
        this.gameData = new GameDataImpl();
        //this.registerCommands();
    }

    /**
     *  Method registering immutable Command instances
     *
     */
    public static void registerCommands(){
        Command help = new HelpCommand(commands);
        Command move = new MoveCommand();
        Command attack = new AttackCommand();
        Command end = new EndCommand();
        Command equip = new EquipCommand();
        Command put = new PutCommand();
        Command restart = new RestartCommand();
        Command take= new TakeCommand();
        commands.put(help.getName(), help);
        commands.put(move.getName(), move);
        commands.put(attack.getName(), attack);
        commands.put(end.getName(),end);
        commands.put(equip.getName(), equip);
        commands.put(put.getName(),put);
        commands.put(restart.getName(),restart);
        commands.put(take.getName(),take);
    }

    /**
     *  Method returns welcome message which should be printed right after
     *  game is started
     */
    @Override
    public String welcomeMessage() {
        //TODO doplnit pořádnou uvítací hlášku
        return "Jsi královským kuchařem a jako každou neděli \n" +
                "vyrážíš za úsvitu slunce na výpravu abys králi k snídani připravil jeho oblíbený pokrm, \n" +
                "Gryfí vejce naměkko. \n" +
                "Problém je, že tyhle bestie žijí až na vrcholcích hor a většinou své mladé nedávají zadarmo.\n" +
                "Pokud nevíte co a jak, použijte příkaz 'nápověda' \n"
                + gameData.getCurrentRoom().getDescriptionWithExits();

    }

    /**
     *  Method returns end message which should be printed right after
     *  game is finished
     */
    @Override
    public String endMessage() {
        //TODO doplnit pořádnou koncovou hlášku
        return "Konec hry";
    }

    /**
     *  Method parses input line and should divide its parts to command name
     *  and array of input arguments (0-N). Based on parsed command name,
     *  specific command should be looked up and executed. If none is found,
     *  default message should be returned
     */
    @Override
    public String processTextCommand(String line) {
        //TODO zpracovat z řádku příkaz a argumenty a naplnit kde je to potřeba

        String[] words = line.split("\\s+");
        String commandStr;
        String[] arguments = new String[1];
        log.info(line);

        if(words.length == 1){
            commandStr = words[0];
        }
        else if(words.length > 2){
            commandStr = words[0];
            arguments[0] = "";
            for (int i = 1; i < words.length; i++)
                arguments[0] += words[i] + " ";
        }
        else{
            commandStr = words[0];
            arguments[0] = words[1];
        }

        String result;
        Command command = commands.getOrDefault(commandStr, null);

        if(command != null){
            result = command.execute(arguments, this.gameData);
            if(this.gameData.getCurrentRoom().getEnemy() != null && !commandStr.equals("jdi")){
                int dmg = ThreadLocalRandom.current().nextInt(this.gameData.getCurrentRoom().getEnemy().getEnemyFlyweight().getAttackMin(), this.gameData.getCurrentRoom().getEnemy().getEnemyFlyweight().getAttackMax() + 1);
                this.gameData.getHero().setHealth(this.gameData.getHero().getHealth()-dmg);
                result += "\n" + gameData.getCurrentRoom().getEnemy().getEnemyFlyweight().getName() + " tě zasáhl za " +dmg+" poškození.\nZbývá ti: "+this.gameData.getHero().getHealth()+" životů";
                if (this.gameData.getHero().getHealth() <=0){
                    result = endMessage();
                    gameData.setFinished(true);
                }
            }

        }
        else{
            result = "Neznámý příkaz, zkuste jiný nebo vyzkoušejte příkaz 'nápověda'";
        }
        log.info(result);
        return result;
    }

    /**
     *  Method delegates its call to mutable GameData instance, which hold current game state. This
     *  state should be checked after each command evaluation a possibly terminate whole app if
     *  true is returned
     */
    @Override
    public boolean isFinished() {
        return gameData.isFinished();
    }
}
