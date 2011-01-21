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
import cooperativegametheory.coalitionfunctions.CoalitionFunction;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jonas Brekle <jonas.brekle@gmail.com>
 */
public class BanzhafValue extends SolutionFunction{
    CoalitionFunction cf;
    Map<Integer, Float> payoffs;
    public BanzhafValue(CoalitionFunction c) {
        cf = c;
        payoffs = new HashMap<Integer, Float>();
        Set<Coalition> subsets = cf.getPlayers().getAllSubsets();
        for(int i : cf.getPlayers()){
            float payoff = 0;
            for(Coalition subset : subsets){
                if(!subset.contains(i)){
                    payoff -= cf.getValue(subset);
                    subset.add(i);
                    payoff += cf.getValue(subset);
                }
            }
            payoffs.put(i, (1/(float)Math.pow(2, cf.getPlayers().size() - 1))*payoff);
        }
    }


    @Override
    public CoalitionFunction getCoalitionFunction() {
        return cf;
    }

    @Override
    public float getValue(int c) {
        return payoffs.get(c);
    }

}
