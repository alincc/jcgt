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
import java.util.Set;

/**
 * 
 * @author jonas
 */
public class PlayerSet extends ExtendedTreeSet<Integer>{
    /**
     * create a playerset with a given number of players. the player will be created internally
     * @param numPlayer
     */
    public PlayerSet(int numPlayer) {
        for(int i = 0; i < numPlayer; i++){
            super.add(i);
        }
    }

    public PlayerSet(PlayerSet ps) {
        for(int i : ps){
            super.add(i);
        }
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
            Partition p = new Partition(this);
            for(Set<Integer> component : partition){
                p.add(new Coalition(component, this));
            }
            out.add(p);
        }
        return out;
    }

    protected Set<Coalition> convertToCoalitions(Set<? extends ExtendedTreeSet<Integer>> in) {
        Set<Coalition>  out = new HashSet<Coalition>();
        for(Set<Integer> subset : in){
            out.add(new Coalition(subset, this));
        }
        return out;
    }
    
    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param e
     * @return
     */
    @Override
    public boolean add(Integer e) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param clctn
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends Integer> clctn) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param clctn
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param clctn
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param e
     * @return
     */
    @Override
    public PlayerSet addFluid(Integer e) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param e
     * @return
     */
    @Override
    public PlayerSet removeFluid(Integer e) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param clctn
     * @return
     */
    @Override
    public PlayerSet addAllFluid(Collection<? extends Integer> clctn) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param collection
     * @return
     */
    @Override
    public ExtendedTreeSet<Integer> retainAllFluid(Collection<Integer> collection) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param clctn
     * @return
     */
    @Override
    public PlayerSet removeAllFluid(Collection<? extends Integer> clctn) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param e
     * @return
     */
    @Override
    public PlayerSet addFluidCopy(Integer e) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param e
     * @return
     */
    @Override
    public PlayerSet removeFluidCopy(Integer e) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param clctn
     * @return
     */
    @Override
    public PlayerSet addAllFluidCopy(Collection<? extends Integer> clctn) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param clctn
     * @return
     */
    @Override
    public PlayerSet removeAllFluidCopy(Collection<? extends Integer> clctn) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    /**
     * do not use this method it will throw an UnsupportedOperationException because a playerset is meant to be fix.
     * @param collection
     * @return
     */
    @Override
    public ExtendedTreeSet<Integer> retainAllFluidCopy(Collection<Integer> collection) {
        throw new UnsupportedOperationException("a PlayerSet can not be modified");
    }

    @Override
    public boolean equals(Object o) {
        try {
            PlayerSet other = (PlayerSet) o;
            if(other.containsAll(this) && this.containsAll(other)){
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
        return hash;
    }
}
