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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * this is a wrapper class for PlayerSet, to describe subsets of the playerset
 * @author jonas
 */
public class Coalition extends ExtendedTreeSet<Integer> implements Comparable<Coalition> {

    /**
     * the playerset - an superset of this set
     */
    PlayerSet definedOn;

    /**
     * to construct an one-man coalition
     * @param i
     * @param players
     */
    public Coalition(Integer i, PlayerSet players) {
        super();
        this.definedOn = players;
        if(!players.contains(i)){
            throw new IllegalArgumentException("agument 1 is not a proper coalition (members must be from the PlayerSet)");
        }
        this.add(i);
    }

    /**
     * to construct empty coalition
     * @param players on which set of players this empty coalition is defined
     */
    public Coalition(PlayerSet players) {
        super();
        this.definedOn = players;
    }

    /**
     * to construct coalition from another coalition
     * @param coalition
     */
    public Coalition(Coalition coalition) {
        super();
        this.definedOn = coalition.getPlayer();
        for(int i : coalition){
            add(i);
        }
    }

    /**
     * create the grant coalition (that contains all players of the playerset)
     * @param players
     * @param member
     */
    public Coalition(PlayerSet players, PlayerSet member) {
        super();
        definedOn = players;
        addAll(member);
    }

    /**
     * add a player to a coalition
     * @param e
     * @return
     */
    @Override
    public final boolean add(Integer e) {
        if(definedOn.contains(e)){
            return super.add(e);
        } else {
            return false;
        }
    }

    /**
     * add a collection of players to a coalition
     * @param clctn
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends Integer> clctn) {
        for(int i : clctn){
            if(!definedOn.contains(i)){
                return false;
            }
        }
       
        return super.addAll(clctn);
    }

    /**
     * create a coalition from a set of players
     * @param coalition set of players
     * @param players on which playerset the coalition is defined on (superset of coalition)
     */
    public Coalition(Set<Integer> coalition, PlayerSet players) {
        super();
        this.definedOn = players;
        Iterator<Integer> it = coalition.iterator();
        int element;
        while(it.hasNext()){
            element = it.next();
            if(!players.contains(element)){
                throw new IllegalArgumentException("agument 1 is not a proper coalition (members must be from the PlayerSet)");
            }
            this.add(element);
        }
    }

    /**
     * create a coalition from a array of players. checks the array for set-ishnes
     * @param coalition array of players
     * @param players on which playerset the coalition is defined on (superset of coalition)
     */
    public Coalition(int[] coalition, PlayerSet players) {
        super();
        TreeSet<Integer> seen = new TreeSet<Integer>();
        this.definedOn = players;

        for(int i = 0; i < coalition.length; i++){
            if(!seen.add(coalition[i])){
                throw new IllegalArgumentException("agument 1 is not a proper coalition (must be a set)");
            }
            if(!players.contains(coalition[i])){
                throw new IllegalArgumentException("agument 1 is not a proper coalition (members must be from the PlayerSet)");
            }
            this.add(coalition[i]);
        }
    }

    /**
     * get the playerset this coalition is defined on
     * @return
     */
    public PlayerSet getPlayer(){
        return definedOn;
    }

     @Override
    public Set<Coalition> getAllSubsets() {
        return convertToCoalitions(super.getAllSubsets());
    }

    @Override
    public Set<Coalition> getAllCombinations() {
        return convertToCoalitions(super.getAllCombinations());
    }

    @Override
    public Set<Partition> getAllPartitions() {
        return convertToCoalitionPartition(super.getAllPartitions());
    }

    protected Set<Partition> convertToCoalitionPartition(Set<? extends Set<? extends ExtendedTreeSet<Integer>>> partitions) {
        HashSet<Partition>  out = new HashSet<Partition>();
        for(Set<? extends Set<Integer>> partition : partitions){
            Partition p = new Partition(getPlayer());
            for(Set<Integer> component : partition){
                p.add(new Coalition(component, getPlayer()));
            }
            out.add(p);
        }
        return out;
    }

    protected Set<Coalition> convertToCoalitions(Set<? extends ExtendedTreeSet<Integer>> in) {
        Set<Coalition>  out = new HashSet<Coalition>();
        for(Set<Integer> subset : in){
            out.add(new Coalition(subset, getPlayer()));
        }
        return out;
    }



    @Override
    public Coalition addFluid(Integer e) {
        add(e);
        return this;
    }

    @Override
    public Coalition removeFluid(Integer e) {
        remove(e);
        return this;
    }

    @Override
    public Coalition addAllFluid(Collection<? extends Integer> clctn) {
        addAll(clctn);
        return this;
    }

    @Override
    public Coalition removeAllFluid(Collection<? extends Integer> clctn) {
        removeAll(clctn);
        return this;
    }

    @Override
    public Coalition addFluidCopy(Integer e) {
        Coalition copy = new Coalition(this);
        copy.add(e);
        return copy;
    }

    @Override
    public Coalition removeFluidCopy(Integer e) {
        Coalition copy = new Coalition(this);
        copy.remove(e);
        return copy;
    }

    @Override
    public Coalition addAllFluidCopy(Collection<? extends Integer> clctn) {
        Coalition copy = new Coalition(this);
        copy.addAll(clctn);
        return copy;
    }

    @Override
    public Coalition removeAllFluidCopy(Collection<? extends Integer> clctn) {
        Coalition copy = new Coalition(this);
        copy.removeAll(clctn);
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        try {
            Coalition other = (Coalition) o;
            if(other.containsAll(this) && this.containsAll(other) && getPlayer().equals(other.getPlayer())){
                return true;
            }
        } catch(Exception e){

        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for(int i : this){
            hash += i;
        }
        hash += definedOn.hashCode();
        return hash;
    }

    @Override
    public boolean contains(Object o) {
        for(int i : this){
            if(i == o){
                return true;
            }
        }
        return false;
    }

    //TODO fix
    public int compareTo(Coalition t) {
        return equals(t) ? 0 : 1;
    }


}
