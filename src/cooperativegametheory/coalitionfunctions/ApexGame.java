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
import java.util.Iterator;

/**
 *
 * @author Jonas Brekle <jonas.brekle@gmail.com>
 */
public class ApexGame implements CoalitionFunction {

    protected PlayerSet player;
    protected int apexPlayer;

    public ApexGame(PlayerSet players, int apex) {
        player = players;
        if(!players.contains(apex)){
            throw new IllegalArgumentException("agument 2 is not a proper apex player (must be from the PlayerSet)");
        }
        apexPlayer = apex;
    }

    public int getNumPlayers() {
        return player.size();
    }

    public float getValue(Coalition coalition) {
        if((coalition.contains(apexPlayer) && coalition.size() > 1) || (!coalition.contains(apexPlayer) && coalition.size() == player.size() -1)){
            return 1;
        }
        return 0;
    }

    public boolean isMonotonic() {
        return false;
    }

    @Override
    public String toString(){
        return "Apex Game: "+player+" players, "+apexPlayer+" is apex";
    }


    /**
     *
     * @return a set of sets of symetric players {{1,2},{3}} means that 1 and 2 are symetric, 3 only to itself
     */
    public Partition getSymetricPlayers() {
        Partition ret = new Partition(player);
        Coalition ap = new Coalition(player);
        ap.add(apexPlayer);
        ret.add(ap);
        Coalition rest = new Coalition(player);
        Iterator<Integer> it = player.iterator();
        int i;
        while(it.hasNext()){
            i= it.next();
            if(i != apexPlayer){
                rest.add(i);
            }
        }
        ret.add(rest);
        return ret;
    }

    @Override
    public PlayerSet getPlayers() {
        return player;
    }
}
