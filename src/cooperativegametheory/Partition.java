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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.collections.ListUtils;

/**
 *
 * @author jonas
 */
public class Partition extends TreeSet<Coalition>{
    PlayerSet players;

    public Partition(PlayerSet onPlayerSet) {
        this.players = onPlayerSet;
    }
    
    public Partition(int[][] partition, PlayerSet onPlayerSet) {
        //TODO cleanup
        this.players = onPlayerSet;
        TreeSet<Integer> seen = new TreeSet<Integer>();
        for(int i = 0; i < partition.length; i++){
            Coalition component = new Coalition(players);
            for(int j = 0; j < partition[i].length; j++){
                if(!players.contains(partition[i][j])){
                    throw new IllegalArgumentException("agument 1 is not a proper partition (component members must be from the PlayerSet)");
                } else {
                    if(!component.add(partition[i][j])){
                        throw new IllegalArgumentException("agument 1 is not a proper partition (components must be a set)");
                    }
                }
                if(!seen.add(partition[i][j])){
                    throw new IllegalArgumentException("agument 1 is not a proper partition (components must be disjoint)");
                }
            }
            if(!this.add(component)){
                throw new IllegalArgumentException("agument 1 is not a proper partition (this error should never appear ?!)");
            }
        }
        for(int i = 0; i < players.size(); i++){
            if(!seen.contains(i)){
                throw new IllegalArgumentException("agument 1 is not a proper partition (not complete)");
            }
        }
    }

    public Partition(Set<TreeSet<Integer>> partition, PlayerSet players) {
        Set<Integer> seen = new TreeSet<Integer>();
        Iterator<TreeSet<Integer>> componentIterator = partition.iterator();
        Set<Integer> currComponent;
        while(componentIterator.hasNext()){
            currComponent = componentIterator.next();
            Iterator<Integer> elementIterator = currComponent.iterator();
            int element;
            while(elementIterator.hasNext()){
                element = elementIterator.next();
                if(!players.contains(element)){
                    throw new IllegalArgumentException("agument 1 is not a proper coalition (component members must be from the PlayerSet)");
                }
                if(!seen.add(element)){
                    throw new IllegalArgumentException("agument 1 is not a proper partition (components must be disjoint)");
                }
            }
            this.add(new Coalition(currComponent, players));
        }
        for(int i = 0; i < players.size(); i++){
            if(!seen.contains(i)){
                throw new IllegalArgumentException("agument 1 is not a proper partition (not complete)");
            }
        }
    }
    
    public Coalition getComponent(Integer i){
        for(Coalition component : this){
            if(component.contains(i)){
                return component;
            }
        }
        return null;
    }

    public boolean completesComponent(int player, int[] rankOrder){
        Coalition component = getComponent(player);

        List<Integer> roList = new ArrayList<Integer>();
        for(int i : rankOrder){
            roList.add(i);
        }
        List<Integer> coList = new ArrayList<Integer>();
        for(int i : component){
            coList.add(i);
        }
        List<Integer> coRo = ListUtils.intersection(coList, roList);
        return coRo.get(coRo.size() -1) == player;
    }

    @Override
    public final boolean add(Coalition c){
        //TODO check more?
        if(!c.getPlayer().equals(this.players)){
            System.out.println("playerset not equal");
            return false;
        }
        return super.add(c);
    }

    @Override
    public boolean contains(Object o) {
        for(Coalition c : this){
            if((o == null ? c == null : c.equals(o))){
                return true;
            }
        }
        return false;
    }


}
