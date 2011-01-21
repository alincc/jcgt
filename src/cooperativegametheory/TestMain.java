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

package cooperativegametheory;

import cooperativegametheory.coalitionfunctions.*;
import cooperativegametheory.coalitionfunctions.networks.MyersonGame;
import cooperativegametheory.coalitionfunctions.networks.PermissionGame;
import cooperativegametheory.solutionfunctions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 *
 * @author Jonas Brekle <jonas.brekle@gmail.com>
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PlayerSet N = new PlayerSet(5);
        ExampleGame exampleGame = new ExampleGame();
        OwenValue ow = new OwenValue(exampleGame, new Partition(new int[][]{new int[]{1,0}, new int[]{2}}, exampleGame.getPlayers()));
        System.out.println("ow(0) = " + ow.getValue(0));
        System.out.println("ow(1) = " + ow.getValue(1));
        System.out.println("ow(2) = " + ow.getValue(2));
//        UndirectedGraph<Integer, DefaultEdge> network = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
//        for(int player : N){
//            network.addVertex(player);
//        }
//        network.addEdge(0, 1);
//        network.addEdge(1, 2);
//
//        MyersonGame my = new MyersonGame(network, new MaschlerGame());
//        for(Coalition c : N.getAllSubsets()){
//            System.out.print("v("+c+") = ");System.out.println(my.getValue(c));
//        }
//        DirectedGraph<Integer, DefaultEdge> network = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
//        for(int player : N){
//            network.addVertex(player);
//        }
//        network.addEdge(0, 2);
//        network.addEdge(2, 3);
//        network.addEdge(3, 0);
//        network.addEdge(4, 2);
//
//        PermissionGame p = new PermissionGame(network, new UnanimityGame(N, new Coalition(new int[]{0,1}, N)));
        //System.out.println(p.getSubordinatesCoalition(0, true, new Coalition(N)));
        //System.out.println(p.getSuperiorsCoalition(0, true, new Coalition(N)));
//        System.out.println(p.getLargestEffectiveSubset(new Coalition(N, N)));
//        BanzhafValue b = new BanzhafValue(new UnanimityGame(N, new Coalition(new int[]{0,1}, N)));
//        System.out.println("b(0) = " + b.getValue(0));
//        System.out.println("b(1) = " + b.getValue(1));
//        System.out.println("b(2) = " + b.getValue(2));

//        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//        map.put(0, 0);
//        map.put(1, 0);
//        map.put(2, 8);
//        map.put(3, 9);
//        SymetricGame v = new SymetricGame(map);
//        HarsanyiDividend hd = new HarsanyiDividend(v);
//        Set<? extends Set<Integer>>  subsets = v.getPlayers().getAllSubsets();
//        for(Set<Integer> subset : subsets){
//            System.out.println("hd("+subset+")="+hd.getValue(new Coalition(subset, v.getPlayers())));
//        }

//        ShapleyValue sh = new ShapleyValue(new UnanimityGame(N, new Coalition(new int[]{0}, N)));
//        System.out.println("sh(0) = " + sh.getValue(0));
//        System.out.println("sh(1) = " + sh.getValue(1));
//        System.out.println("sh(2) = " + sh.getValue(2));
//
//        ShapleyValue sh = new ShapleyValue(new ApexGame(N, 0));
//        System.out.println("sh(0) = " + sh.getValue(0));
//        System.out.println("sh(1) = " + sh.getValue(1));
//        System.out.println("sh(2) = " + sh.getValue(2));
//        System.out.println("sh(3) = " + sh.getValue(3));

//        ShapleyValue sh2 = new ShapleyValue(new GlovesGame(3, new int[]{0}, new int[]{1,2}));
//        System.out.println("sh(0) = " + sh2.getValue(0));
//        System.out.println("sh(1) = " + sh2.getValue(1));
//        System.out.println("sh(2) = " + sh2.getValue(2));

//        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
//        m.put(0, 0); //numPlayer => value
//        m.put(1, 2);
//        m.put(2, 5);
//        ShapleyValue sh3 = new ShapleyValue(new SomeSymetricGame(m));
//        System.out.println("sh(0) = " + sh3.getValue(0));
//        System.out.println("sh(1) = " + sh3.getValue(1));

//        int[][] p = new int[][]{new int[]{0,1}, new int[]{2}};
//        WieseValue w = new WieseValue(new GlovesGame(3, new int[]{0}, new int[]{1,2}), p);
//        System.out.println("w(0) = " + w.getValue(0));
//        System.out.println("w(1) = " + w.getValue(1));
//        System.out.println("w(2) = " + w.getValue(2));

//        int[][] p = new int[][]{new int[]{0,1}, new int[]{2}};
//        CasajusValue ad = new CasajusValue(new GlovesGame(3, new int[]{0}, new int[]{1,2}), p);
//        System.out.println("w(0) = " + ad.getValue(0));
//        System.out.println("w(1) = " + ad.getValue(1));
//        System.out.println("w(2) = " + ad.getValue(2));

//        int[][] p = new int[][]{new int[]{0,1}, new int[]{2,3,4,5}};
//        HashMap<Integer, Float> vote = new HashMap<Integer, Float>();
//        vote.put(0, 0.23F); //SPD
//        vote.put(1, 0.338F); //CDU
//        vote.put(2, 0.146F); //FDP
//        vote.put(3, 0.119F); //Linke
//        vote.put(4, 0.107F); //Grüne
//        vote.put(5, 0.06F); //Sonstige
//
//        WieseValue w = new WieseValue(new VotingGame(vote), p);
//        System.out.println("w(0) = " + ad.getValue(0));
//        System.out.println("w(1) = " + ad.getValue(1));
//        System.out.println("w(2) = " + ad.getValue(2));
//        System.out.println("w(3) = " + ad.getValue(3));
    }
}
