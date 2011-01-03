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

package cooperativegametheory.solutionfunctions;

import cooperativegametheory.Coalition;
import cooperativegametheory.Partition;
import cooperativegametheory.PermutationGenerator;
import cooperativegametheory.coalitionfunctions.CoalitionFunction;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TODO implement
 * @author jonas
 */
public class OwenValue extends SolutionFunction {
    Map<Integer, Float> payoffs = new HashMap<Integer, Float>();
    CoalitionFunction cf;
    protected float[][] marginalContributions;
    protected float[] averageMarginalContributions;

    public OwenValue(CoalitionFunction cf, Partition p) {
        this.cf = cf;
        Set<? extends List<Integer>> rankOrders = cf.getPlayers().getAllPermutations();
        Set<List<Integer>> toDelete = new HashSet<List<Integer>>();
        for(List<Integer> curr : rankOrders){
            if(!isConsistentWithPartition(curr, p)){
                toDelete.add(curr);
            }
        }
        rankOrders.removeAll(toDelete);
        marginalContributions = new float[rankOrders.size()][cf.getNumPlayers()];
        int i = 0;
        for(List<Integer> curr : rankOrders){
            for (int j = 0; j < curr.size(); j++) {
                marginalContributions[i][curr.get(j)] = marginalContribution(cf, curr, curr.get(j));
            }
            i++;
        }

        averageMarginalContributions = averageMarginalContribution(cf, marginalContributions);
    }

    public float getValue(int i) {
        try {
            return averageMarginalContributions[i];
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("this player is not defined");
        }
    }

    @Override
    public CoalitionFunction getCoalitionFunction() {
        return cf;
    }

    protected final boolean isConsistentWithPartition(List<Integer> c, Partition p){
        Set<Coalition> seen = new HashSet<Coalition>();
        Coalition curr;
        Coalition last = null;
        for(int i : c){
             curr = p.getComponent(i);
             if(last != null && !last.equals(curr) && seen.contains(curr)){
                 return false;
             }
             seen.add(curr);
             last = curr;
        }
        return true;
    }
}
