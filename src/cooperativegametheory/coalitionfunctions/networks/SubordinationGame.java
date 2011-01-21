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
import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

/**
 *
 * @author Jonas Brekle <jonas.brekle@gmail.com>
 */
public abstract class SubordinationGame implements CoalitionFunction{
    DirectedGraph<Integer, DefaultEdge> network;

    public SubordinationGame(DirectedGraph<Integer, DefaultEdge> network) {
        this.network = network;
    }
    
    public DirectedGraph<Integer, DefaultEdge> getNetwork(){
        return network;
    }

    public Coalition getSubordinatesCoalition(int i, boolean transitive, Coalition c){
        Set<DefaultEdge> edges = network.edgesOf(i);
        for(DefaultEdge edge : edges){
            if(network.getEdgeSource(edge) == i){
                if(!c.contains(network.getEdgeTarget(edge))){
                    c.add(network.getEdgeTarget(edge));
                    if(transitive){
                        c.addAll(getSubordinatesCoalition(network.getEdgeTarget(edge), transitive, c));
                    }
                }
            }
        }
        return c;
    }

    public Coalition getSuperiorsCoalition(int i, boolean transitive, Coalition c){
        Set<DefaultEdge> edges = network.edgesOf(i);
        for(DefaultEdge edge : edges){
            if(network.getEdgeTarget(edge) == i){
                if(!c.contains(network.getEdgeSource(edge))){
                    c.add(network.getEdgeSource(edge));
                
                    if(transitive){
                        c.addAll(getSuperiorsCoalition(network.getEdgeSource(edge), transitive, c));
                    }
                }
            }
        }
        return c;
    }

    protected Partition getPartition(){
        Partition p = new Partition(getPlayers());
        ConnectivityInspector<Integer, DefaultEdge> ci = new ConnectivityInspector<Integer, DefaultEdge>(network);
        List<Set<Integer>> connectedSets = ci.connectedSets();
        for(Set<Integer> component : connectedSets){
            p.add(new Coalition(component, getPlayers()));
        }
        return p;
    }

    protected Partition getPartition(Coalition c){
        Partition p = new Partition(getPlayers());
        DirectedGraph<Integer, DefaultEdge> copy = new SimpleDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);

        //make subgraph
        for(DefaultEdge edge : network.edgeSet()){
            if((c.contains(network.getEdgeSource(edge)) || c.contains(network.getEdgeTarget(edge))) && !copy.containsEdge(network.getEdgeSource(edge), network.getEdgeTarget(edge)) ){
                if(!copy.containsVertex(network.getEdgeSource(edge))){
                    copy.addVertex(network.getEdgeSource(edge));
                }
                if(!copy.containsVertex( network.getEdgeTarget(edge))){
                    copy.addVertex( network.getEdgeTarget(edge));
                }
                copy.addEdge(network.getEdgeSource(edge), network.getEdgeTarget(edge));
            }
        }
        ConnectivityInspector<Integer, DefaultEdge> ci = new ConnectivityInspector<Integer, DefaultEdge>(copy);
        List<Set<Integer>> connectedSets = ci.connectedSets();
        for(Set<Integer> component : connectedSets){
            p.add(new Coalition(component, getPlayers()));
        }
        return p;
    }

    public boolean isAutonomous(Coalition c){
        for(int i : c){
            if(!c.containsAll(getSuperiorsCoalition(i, true, new Coalition(getPlayers())))){
                return false;
            }
        }
        return true;
    }
    public boolean isEffective(Coalition c){
        for(int i : c){
            if(!c.containsAll(getSubordinatesCoalition(i, true, new Coalition(getPlayers())))){
                return false;
            }
        }
        return true;
    }

    public Coalition getLargestEffectiveSubset(Coalition coalition) {
        Set<Coalition> allSubsets = coalition.getAllSubsets();
        Coalition max = null;
        for(Coalition curr : allSubsets){
            if(max == null || (curr.size() > max.size() && isEffective(curr))){
                max = curr;
            }
        }
        return max;
    }

    public Coalition getLargestAutonomousSubset(Coalition coalition) {
        Set<Coalition> allSubsets = coalition.getAllSubsets();
        Coalition max = null;
        for(Coalition curr : allSubsets){
            if(max == null || (curr.size() > max.size() && isAutonomous(curr))){
                max = curr;
            }
        }
        return max;
    }
}
