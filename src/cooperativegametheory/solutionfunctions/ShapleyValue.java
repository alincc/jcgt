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
import org.apache.commons.math.util.MathUtils;

/**
 * The shapley value obeys the following axioms
 * * additivity
 * * efficiency
 * * symmetry
 * * null-player
 * this version of the shapley value sums the average marginal contribution over all coalitions. it is much faster than the alternative implementation
 * @see ShapleyValueAlt
 * @author jonas
 */
public class ShapleyValue extends SolutionFunction {

    protected CoalitionFunction cf;
    protected Map<Integer, Float> payoffs;

    public ShapleyValue(CoalitionFunction c) {
        cf = c;
        int n = cf.getPlayers().size();
        long nFac = MathUtils.factorial(n);
        payoffs = new HashMap<Integer, Float>();
        Set<Coalition> coalitions = cf.getPlayers().getAllSubsets();
        for(int i : cf.getPlayers()){
            float payoff = 0;
            for(Coalition coalition : coalitions){
                payoff += ((((double)MathUtils.factorial(n-coalition.size())*MathUtils.factorial(coalition.size()-1))/nFac)*(c.getValue(coalition)-c.getValue(coalition.removeFluidCopy(i))));
            }
            payoffs.put(i, payoff);
        }
    }

    public CoalitionFunction getCoalitionFunction() {
        return cf;
    }

    public float getValue(int i) {
        if(payoffs.containsKey(i)){
            return payoffs.get(i);
        } else {
            throw new IllegalArgumentException("this player is not defined");
        }
    }
}
