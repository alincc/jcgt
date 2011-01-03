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

import cooperativegametheory.coalitionfunctions.CoalitionFunction;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 * TODO implement
 * @author jonas
 */
public class WeightedHierarchyValue extends SolutionFunction{

    public WeightedHierarchyValue(CoalitionFunction cf, DefaultDirectedWeightedGraph<Integer, DefaultEdge> wg) {
    }

    @Override
    public CoalitionFunction getCoalitionFunction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getValue(int c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
