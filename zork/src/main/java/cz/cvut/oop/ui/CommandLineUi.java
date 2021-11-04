package cz.cvut.oop.ui;

import cz.cvut.oop.command.Command;
import cz.cvut.oop.game.Game;

import java.util.Scanner;


/**
 *  Represents command line ui view
 */
public class  CommandLineUi {

    private Game game;
    private static CommandLineUi single_instance = null;

    private CommandLineUi(Game game){
        this.game = game;
    }
    /**
     *  Ffter ui is started, application prints welcome messages and waits for user input
     *  then proceeds to process user input and return messages back here
     *  -> to be represented on this view.
     */

    public static CommandLineUi getInstance(Game game){

        if (single_instance == null)
            single_instance = new CommandLineUi(game);

        return single_instance;
    }

    public void start(){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println(this.game.welcomeMessage());
            while(!this.game.isFinished()){
                System.out.print("> ");
                System.out.println(this.game.processTextCommand(scanner.nextLine()));
            }
            System.out.println(this.game.endMessage());
        }
    }

}
