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

import cooperativegametheory.PermutationGenerator;
import cooperativegametheory.coalitionfunctions.CoalitionFunction;

/**
 * The shapley value obeys the following axioms
 * * additivity
 * * efficiency
 * * symmetry
 * * null-player
 * this version of the shapley value sums the average marginal contribution over all rank orders. it is (with large playersets) much slower that the other implementation
 * @see ShapleyValue
 * @author jonas
 */
public class ShapleyValueAlt extends SolutionFunction {

    protected CoalitionFunction cf;
    protected int[][] rankOrders;
    protected float[][] marginalContributions;
    protected float[] averageMarginalContributions;

    public ShapleyValueAlt(CoalitionFunction c) {
        cf = c;
        int players = cf.getNumPlayers();
        rankOrders = PermutationGenerator.getAllPermutations(players);
        marginalContributions = new float[rankOrders.length][players];
        for (int i = 0; i < rankOrders.length; i++) {
            for (int j = 0; j < rankOrders[i].length; j++) {
                marginalContributions[i][rankOrders[i][j]] = marginalContribution(cf, rankOrders[i], rankOrders[i][j]);
            }
        }

        averageMarginalContributions = averageMarginalContribution(c, marginalContributions);
    }

    public CoalitionFunction getCoalitionFunction() {
        return cf;
    }

    public float getValue(int i) {
        try {
            return averageMarginalContributions[i];
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("this player is not defined");
        }
    }
}
