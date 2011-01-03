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

import cooperativegametheory.Partition;
import cooperativegametheory.coalitionfunctions.CoalitionFunction;

/**
 *
 * @author jonas
 */
public class AumanDrezeValue extends SolutionFunction{

    Partition partition;
    CoalitionFunction cf;
    public AumanDrezeValue(CoalitionFunction c, Partition p) {
        partition = p;
        cf = c;
    }

    @Override
    public float getValue(int c) {
        RestrictedShapleyValue shHelper = new RestrictedShapleyValue(cf, partition.getComponent(c));
        return shHelper.getValue(c);
    }

    @Override
    public String toString() {
        return "Aumann-Dreze with Partition " + partition + " and CF: "+cf;
    }

    @Override
    public CoalitionFunction getCoalitionFunction() {
        return cf;
    }


}
