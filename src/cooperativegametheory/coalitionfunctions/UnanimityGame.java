/**
 * This file is part of jCGT (the Java Cooperative Game Theory library).
 *
 * jCGT is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jCGT is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public License
 * along with jCGT.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package cooperativegametheory.coalitionfunctions;

import cooperativegametheory.Coalition;
import cooperativegametheory.Partition;
import cooperativegametheory.PlayerSet;


/**
 *
 * @author jonas
 */
public class UnanimityGame implements CoalitionFunction{

    PlayerSet players;
    Coalition powerfullPlayers;
    
    public UnanimityGame(PlayerSet newPlayers, Coalition pP) {
        players = newPlayers;
        for(int i : pP){
            if(!players.contains(i)){
                throw new IllegalArgumentException("agument 2 is not a proper coalition (members must be from the PlayerSet)");
            }
        }
        powerfullPlayers = pP;
    }

    public int getNumPlayers() {
        return players.size();
    }

    public float getValue(Coalition coalition) {
        if(coalition.containsAll(powerfullPlayers)){
            return 1;
        } else {
            return 0;
        }
    }

    public boolean isMonotonic() {
        return true;
    }

    @Override
    public PlayerSet getPlayers() {
        return players;
    }

    @Override
    public Partition getSymetricPlayers() {
        Partition ret = new Partition(players);
        PlayerSet nonPowerfullPlayers = (PlayerSet) players.clone();
        nonPowerfullPlayers.removeAll(powerfullPlayers);
        ret.add(new Coalition(nonPowerfullPlayers,players));
        ret.add(powerfullPlayers);
        return ret;
    }

    @Override
    public String toString(){
        return "Unanimity Game: "+players+" players, "+powerfullPlayers+" are powerfull player";
    }
}
