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
 * @author Jonas Brekle <jonas.brekle@gmail.com>
 */
public class VotingGame implements CoalitionFunction{

    Map<Integer, Float> vote;
    PlayerSet player;

    public VotingGame(Map<Integer, Float> newVote) {
        vote = newVote;
        
        float sum = 0;
        for(int i : newVote.keySet()){
            sum += newVote.get(i);
        }
        if(sum <= 0.5 || sum > 1){
            throw new IllegalArgumentException("your vote distribution seems odd. sum = "+sum+" (should be between 0.5 and 1)");
        }
        player = new PlayerSet(getNumPlayers());
    }

    public final int getNumPlayers() {
        return vote.keySet().size();
    }

    public float getValue(Coalition coalition) {
        float sum = 0;
        for(int i : coalition){
            sum += vote.get(i);
        }
        return (sum > 0.5 ? 1 : 0) ;
    }

    public boolean isMonotonic() {
        return true;
    }

    @Override
    public PlayerSet getPlayers() {
        return player;
    }

    @Override
    public Partition getSymetricPlayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
