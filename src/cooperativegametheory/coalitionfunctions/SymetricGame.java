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
import java.util.Map;

/**
 *
 * @author jonas
 */
public class SymetricGame implements CoalitionFunction{

    protected Map<Integer,Integer> map;
    PlayerSet player;

    public SymetricGame(Map<Integer,Integer> newMap) {
        map = newMap;
        player = new PlayerSet(this.getNumPlayers());
    }

    public int getNumPlayers() {
        int max = 0;
        for(int i : map.keySet()){
            if(i > max){
                max = i;
            }
        }
        return max;
    }

    public float getValue(Coalition coalition) {
        return map.get(coalition.size());
    }

    public boolean isMonotonic() {
        return false;
    }

    @Override
    public PlayerSet getPlayers() {
        return player;
    }

    @Override
    public Partition getSymetricPlayers() {
        Partition ret = new Partition(player);

//        if all players are non-symetric
//        Iterator<Integer> it = player.iterator();
//        while(it.hasNext()){
//            ret.add( new Coalition(it.next(), player));
//        }

        //all players are symetric to all others
        ret.add(new Coalition(player,player));
        return ret;
    }

}
