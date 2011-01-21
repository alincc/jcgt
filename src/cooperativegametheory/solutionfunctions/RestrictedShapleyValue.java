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
import cooperativegametheory.PermutationGenerator;
import cooperativegametheory.coalitionfunctions.CoalitionFunction;

/**
 *
 * @author Jonas Brekle <jonas.brekle@gmail.com>
 */
public class RestrictedShapleyValue extends ShapleyValueAlt {
    protected Coalition rest;

    public RestrictedShapleyValue(CoalitionFunction c, Coalition r) {
        super(c); // ?
        rest = r;
        int num = r.size();
        rankOrders = PermutationGenerator.getAllPermutations(num);
        marginalContributions = new float[rankOrders.length][num];
        for (int i = 0; i < rankOrders.length; i++) {
            for (int j = 0; j < rankOrders[i].length; j++) {
                marginalContributions[i][rankOrders[i][j]] = marginalContribution(cf, rankOrders[i], rankOrders[i][j]);
            }
        }

        averageMarginalContributions = averageMarginalContribution(c, marginalContributions);
    }

    @Override
    public float getValue(int i) {
        for(int j : rest){
            if(i == j){
                return averageMarginalContributions[j];
            }
        }
        return 0;
    }


}
