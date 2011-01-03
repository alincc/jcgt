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
import cooperativegametheory.coalitionfunctions.CoalitionFunction;
import java.util.List;
import java.util.Set;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 *
 * @author jonas
 */
public abstract class NetworkGame implements CoalitionFunction{
    UndirectedGraph<Integer, DefaultEdge> network;
    
    public UndirectedGraph<Integer, DefaultEdge> getNetwork(){
        return network;
    }

    public Partition getPartition(){
        Partition p = new Partition(getPlayers());
        ConnectivityInspector<Integer, DefaultEdge> ci = new ConnectivityInspector<Integer, DefaultEdge>(network);
        List<Set<Integer>> connectedSets = ci.connectedSets();
        for(Set<Integer> component : connectedSets){
            p.add(new Coalition(component, getPlayers()));
        }
        return p;
    }

    public Partition getPartition(Coalition c){
        Partition p = new Partition(getPlayers());
        UndirectedGraph<Integer, DefaultEdge> copy = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
        
        for(int i : c){
            copy.addVertex(i);
        }

        //make subgraph
        for(DefaultEdge edge : network.edgeSet()){
            if((c.contains(network.getEdgeSource(edge)) && c.contains(network.getEdgeTarget(edge))) && !copy.containsEdge(network.getEdgeSource(edge), network.getEdgeTarget(edge)) ){
                copy.addEdge(network.getEdgeSource(edge), network.getEdgeTarget(edge));
            }
        }
        ConnectivityInspector<Integer, DefaultEdge> ci = new ConnectivityInspector<Integer, DefaultEdge>(copy);
        List<Set<Integer>> connectedSets = ci.connectedSets();
        for(Set<Integer> component : connectedSets){
            //System.out.println("add: " + component);
            Coalition coal = new Coalition(component, getPlayers());
            //System.out.println("contained: " + p.contains(coal));
            p.add(coal);
        }
        //System.out.println(p);
        return p;
    }
}
