/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cooperativegametheory.coalitionfunctions;

import cooperativegametheory.Coalition;
import cooperativegametheory.Partition;
import cooperativegametheory.PlayerSet;

/**
 * Example Game from the lecture about about a tricky relationship :)
 * Onno: 0
 * Max: 1
 * Anna: 2
 * @author jonas
 */
public class ExampleGame implements CoalitionFunction{
    PlayerSet player;
    public ExampleGame() {
        player = new PlayerSet(3);
    }


    public int getNumPlayers() {
        return 3;
    }

    public PlayerSet getPlayers() {
        return player;
    }

    public float getValue(Coalition coalition) {
        if(coalition.size() <= 1){
            return 0;
        } else if(coalition.size() == 3){
            return 2;
        } else if(coalition.contains(1) && coalition.contains(2)){
            return 6;
        } else if(coalition.contains(0) && coalition.contains(2)){
            return 4;
        } else if(coalition.contains(1) && coalition.contains(0)){
            return 1;
        }
        throw new IllegalArgumentException("should not happen");
    }

    public boolean isMonotonic() {
        return false;
    }

    public Partition getSymetricPlayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
