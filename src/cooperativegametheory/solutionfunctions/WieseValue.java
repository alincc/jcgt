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

/**
 *
 * @author jonas
 */
public class WieseValue extends SolutionFunction{

    CoalitionFunction cf;
    Partition partition;
    float[][] marginalContributions;
    float[] averageMarginalContributions;

    public WieseValue(CoalitionFunction c, Partition p) {
        cf = c;
        partition = p;
        int[][] ro = PermutationGenerator.getAllPermutations(cf.getNumPlayers());
        marginalContributions = new float[ro.length][cf.getNumPlayers()];
        for(int i = 0; i < ro.length; i++){
            for(int j = 0; j < ro[i].length; j++){
                Coalition component = p.getComponent(ro[i][j]);
                if(p.completesComponent(ro[i][j], ro[i])){

                    float v = cf.getValue(component);
                    for(int componentMember : component){
                        if(componentMember != ro[i][j]){
                            v -= marginalContribution(cf, ro[i], componentMember);
                        }
                    }
                    marginalContributions[i][ro[i][j]] = v;
                } else {
                    marginalContributions[i][ro[i][j]] = marginalContribution(c, ro[i], ro[i][j]);
                }
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
            return 0;
        }
    }
}
