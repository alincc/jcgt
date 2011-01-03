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

package cooperativegametheory.coalitionfunctions.networks;

import cooperativegametheory.Coalition;
import cooperativegametheory.Partition;
import cooperativegametheory.PlayerSet;
import cooperativegametheory.coalitionfunctions.CoalitionFunction;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 *
 * @author jonas
 */
public class MyersonGame extends NetworkGame {
    CoalitionFunction cf;
    public MyersonGame(UndirectedGraph<Integer, DefaultEdge> network, CoalitionFunction cf) {
        this.cf = cf;
        this.network = network;
    }

    public int getNumPlayers() {
        return cf.getNumPlayers();
    }

    public PlayerSet getPlayers() {
        return cf.getPlayers();
    }

    public float getValue(Coalition coalition) {
        float value = 0;
        Partition np = getPartition(coalition);
        for(Coalition c : np){
            value += cf.getValue(c);
        }
        return value;
    }

    public boolean isMonotonic() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Partition getSymetricPlayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
