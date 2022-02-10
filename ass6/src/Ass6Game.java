import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.gameenvironment.GameFlow;

import game.gameenvironment.LevelInformation;
import game.gameenvironment.levels.LevelFour;
import game.gameenvironment.levels.LevelOne;
import game.gameenvironment.levels.LevelThree;
import game.gameenvironment.levels.LevelTwo;

import java.util.LinkedList;
import java.util.List;

/**
 * Ass class to run a game example.
 */
public class Ass6Game {
    /**
     * main method to run.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Ass6", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        LevelInformation levelOne = new LevelOne();
        LevelInformation levelTwo = new LevelTwo();
        LevelInformation levelThree = new LevelThree();
        LevelInformation levelFour = new LevelFour();
        AnimationRunner ar = new AnimationRunner(gui);
        GameFlow gameflow = new GameFlow(ar, ks, gui, 800, 600);
        List<LevelInformation> levelInfoList = new LinkedList<>();
        if (args.length == 0) {
            levelInfoList.add(levelOne);
            levelInfoList.add(levelTwo);
            levelInfoList.add(levelThree);
            levelInfoList.add(levelFour);
            gameflow.runLevels(levelInfoList);
        } else {
            for (String arg : args) {
                try {
                    int num = Integer.parseInt(arg);
                    switch (num) {
                        case 1:
                            levelInfoList.add(levelOne);
                            break;
                        case 2:
                            levelInfoList.add(levelTwo);
                            break;
                        case 3:
                            levelInfoList.add(levelThree);
                            break;
                        case 4:
                            levelInfoList.add(levelFour);
                            break;
                        default:
                            break;
                    }
                } catch (NumberFormatException ex) {
                    continue;
                }
            }
            gameflow.runLevels(levelInfoList);
        }
    }
}
