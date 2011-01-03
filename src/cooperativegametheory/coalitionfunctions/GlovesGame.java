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
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jonas
 */
public class GlovesGame implements CoalitionFunction{

    PlayerSet players;
    ArrayList<Integer> rightOwners;
    ArrayList<Integer> leftOwners;

    public GlovesGame(PlayerSet newPlayers, int[] newRightOwners, int[] newLeftOwners) {
        players = newPlayers;
        for(int i : rightOwners){
            if(!players.contains(i)){
                throw new IllegalArgumentException("agument 2 is not a proper coalition (members must be from the PlayerSet)");
            }
        }
        rightOwners = asIntegerList(newRightOwners);
        for(int i : rightOwners){
            if(!players.contains(i)){
                throw new IllegalArgumentException("agument 3 is not a proper coalition (members must be from the PlayerSet)");
            }
        }
        leftOwners = asIntegerList(newLeftOwners);
    }


    public int getNumPlayers() {
        return players.size();
    }

    public float getValue(Coalition coalition) {
        int numLeft = 0;
        int numRight = 0;
        //how many gloves does each of the players bring
        int i;
        Iterator<Integer> it = coalition.iterator();
        while(it.hasNext()){
            i = it.next();
            for(int j = 0; j < rightOwners.size(); j++){
                if(i == rightOwners.get(j)){
                    numRight++;
                }
            }
            for(int j = 0; j < leftOwners.size(); j++){
                if(i == leftOwners.get(j)){
                    numLeft++;
                }
            }
        }
        return Math.min(numLeft, numRight);
    }

    public boolean isMonotonic() {
        return true;
    }

    private static ArrayList<Integer> asIntegerList(int[] intArr){
        ArrayList<Integer>ret = new ArrayList<Integer>(intArr.length);
        for(int r : intArr){
            ret.add(new Integer(r));
        }
        return ret;
    }

    @Override
    public PlayerSet getPlayers() {
        return players;
    }

    @Override
    public Partition getSymetricPlayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
