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

/**
 *
 * @author jonas
 */
public class HarsanyiDividend implements CoalitionFunction{

    CoalitionFunction cf;
    public HarsanyiDividend(CoalitionFunction c) {
        cf = c;
    }

    @Override
    public int getNumPlayers() {
        return cf.getNumPlayers();
    }

    @Override
    public PlayerSet getPlayers() {
        return cf.getPlayers();
    }

    @Override
    public float getValue(Coalition coalition) {
    
        float value = 0;
        for(Coalition c : coalition.getAllSubsets()){
            //System.out.println("+= "+Math.pow(-1, coalition.size() - curr.size())+" * "+cf.getValue(new Coalition(curr, cf.getPlayers())));
            value += Math.pow(-1, coalition.size() - c.size()) * cf.getValue(c);
        }
        return value;
    }

    @Override
    public boolean isMonotonic() {
        return false;
    }

    @Override
    public Partition getSymetricPlayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
