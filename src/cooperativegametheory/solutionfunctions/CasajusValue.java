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
import cooperativegametheory.coalitionfunctions.CoalitionFunction;

/**
 *
 * @author jonas
 */
public class CasajusValue extends SolutionFunction{
    
    Partition partition;
    CoalitionFunction cf;
    float[] values;
    
    public CasajusValue(CoalitionFunction c, Partition p) {
        partition = p;
        cf = c;
        ShapleyValueAlt sh = new ShapleyValueAlt(c);
        values = new float[c.getNumPlayers()];
        for(int i = 0; i < c.getNumPlayers(); i++){
            Coalition others = p.getComponent(i);
            float sumOthers = 0;
            for(int j : others){
                sumOthers += sh.getValue(j);
            }
            values[i] = sh.getValue(i) + ((cf.getValue(p.getComponent(i)) - sumOthers) / others.size());
        }
    }

    @Override
    public float getValue(int c) {
        return values[c];
    }

    @Override
    public CoalitionFunction getCoalitionFunction() {
        return cf;
    }

}
