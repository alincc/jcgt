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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * this generic class extends TreeSet (so its order is fix) and offers some methods to
 * do some set theory operations.
 * @author based on code by JosAH 2007 http://bytes.com/topic/java/insights/709126-generics-sets
 */
public class ExtendedTreeSet<T> extends TreeSet<T>{

    /**
     * create empty set
     */
    public ExtendedTreeSet() {
    }

    /**
     * convert a collection to ExtendedTreeSet
     * @param collection
     */
    public ExtendedTreeSet(Collection<T> collection) {
        super(collection);
    }

    /**
     * create a set containing one element
     * @param arg
     */
    public ExtendedTreeSet(T arg) {
        add(arg);
    }

    /**
     *
     * @return
     */
    public Set<? extends ExtendedTreeSet<T>> getAllCombinations() {

        ExtendedTreeSet<ExtendedTreeSet<T>> result = new ExtendedTreeSet<ExtendedTreeSet<T>>(new ExtendedTreeSet<T>());

        for (T current : this) {
            Set<? extends ExtendedTreeSet<T>> comb = removeFluidCopy(current).getAllCombinations();
            result.addAll(comb);
            for (ExtendedTreeSet<T> set : comb) {
                result.add(set.addFluidCopy(current));
            }
        }

        return result;
    }

    //TODO the following 2 methods are propably bad style
    /**
     *
     * @return
     */
    public Set<? extends Set<? extends ExtendedTreeSet<T>>> getAllPartitions() {
        ExtendedTreeSet<ExtendedTreeSet<ExtendedTreeSet<T>>> result = getAllPartitionsStrict();
        HashSet<HashSet<ExtendedTreeSet<T>>> ret = new HashSet<HashSet<ExtendedTreeSet<T>>>();
        for(ExtendedTreeSet<ExtendedTreeSet<T>> partition : result){
            HashSet<ExtendedTreeSet<T>> np = new HashSet<ExtendedTreeSet<T>>();
            for(ExtendedTreeSet<T> component : partition){
                np.add(component);
            }
            ret.add(np);
        }
        return ret;
    }

    public ExtendedTreeSet<ExtendedTreeSet<ExtendedTreeSet<T>>> getAllPartitionsStrict() {

        ExtendedTreeSet<ExtendedTreeSet<ExtendedTreeSet<T>>> result = new ExtendedTreeSet<ExtendedTreeSet<ExtendedTreeSet<T>>>();

        if (size() < 2) {
            return result.addFluid(new ExtendedTreeSet<ExtendedTreeSet<T>>(this));
        }

        T current = iterator().next();
        ExtendedTreeSet<ExtendedTreeSet<ExtendedTreeSet<T>>> part = removeFluidCopy(current).getAllPartitionsStrict();

        for (ExtendedTreeSet<ExtendedTreeSet<T>> set : part) {
            result.addFluid(set.addFluidCopy(new ExtendedTreeSet<T>(current)));

            for (ExtendedTreeSet<T> elem : set) {
                ExtendedTreeSet<ExtendedTreeSet<T>> copy = set.removeFluidCopy(elem);
                result.addFluid(copy.addFluid(elem.addFluidCopy(current)));
            }
        }

        return result;
    }
    
    public Set<? extends List<Integer>> getAllPermutations(){
        Set<ArrayList<Integer>> permutations = new HashSet<ArrayList<Integer>>();
        Map<Integer, T> map = new HashMap<Integer, T>();
        int i = 0;
        for(T e : this){
            map.put(i, e);
            i++;
        }
        int[][] allPermutations = PermutationGenerator.getAllPermutations(size());
        for(i = 0; i < allPermutations.length; i++){
            ArrayList<Integer> rankorder = new ArrayList<Integer>();
            for(int j : allPermutations[i]){
                rankorder.add((Integer) map.get(j));
            }
            permutations.add(rankorder);
        }
        return permutations;
    }

    /**
     * get the set of all subsets of this set
     * @return
     */
    public Set<? extends ExtendedTreeSet<T>> getAllSubsets() {
        Set<ExtendedTreeSet<T>> setOfSubsets = new HashSet<ExtendedTreeSet<T>>();

        PowerSetGenerator powerSet = new PowerSetGenerator(this);
        while(powerSet.hasNext()){
            if(!setOfSubsets.add(powerSet.next())){
                throw new RuntimeException("could not build set of subsets");
            }
        }
        return setOfSubsets;
    }

//    public Set<? extends ExtendedTreeSet<T>> getAllPermutations(){
//        Set<ExtendedTreeSet<T>> setOfPermutations = new HashSet<ExtendedTreeSet<T>>();
//        PermutationGenerator pg = new PermutationGenerator(size());
//        while(pg.hasNext()){
//            if(!setOfPermutations.add(pg.next())){
//                throw new RuntimeException("could not build set of subsets");
//            }
//        }
//        return setOfPermutations;
//
//    }

    //TODO fix compare
    /**
     * compare to another instance. (the TreeSet needs to implement Comparable)
     * @param t
     * @return
     */
    public int compareTo(ExtendedTreeSet<T> t) {
//       Iterator<T> myIt = this.iterator();
//       Iterator<T> otherIt = t.iterator();
//       while(myIt.hasNext() && otherIt.hasNext()){
//           if(myIt.next() > otherIt.next()){
//                return 1;
//           }
//           if(myIt.next() < otherIt.next()){
//                return -1;
//           }
//       }
//       if(!myIt.hasNext() && otherIt.hasNext()){
//           return 1;
//       }
//       if(myIt.hasNext() && !otherIt.hasNext()){
//           return -1;
//       }
       return 0;
    }

    /**
     * add, but return always this set - so its a "blind" add, but you can write shorter code
     * @param e
     * @return
     */
    public ExtendedTreeSet<T> addFluid(T e) {
        add(e);
        return this;
    }

    /**
     * remove, but return always this set - so its a "blind" remove, but you can write shorter code
     * @param e
     * @return
     */
    public ExtendedTreeSet<T> removeFluid(T e) {
        remove(e);
        return this;
    }

    /**
     * addAll, but return always this set - so its a "blind" addAll, but you can write shorter code
     * @param clctn
     * @return
     */
    public ExtendedTreeSet<T> addAllFluid(Collection<? extends T> clctn) {
        addAll(clctn);
        return this;
    }

    /**
     * removeAll, but return always this set - so its a "blind" removeAll, but you can write shorter code
     * @param clctn
     * @return
     */
    public ExtendedTreeSet<T> removeAllFluid(Collection<? extends T> clctn) {
        removeAll(clctn);
        return this;
    }

    /**
     * retainAll, but return always this set - so its a "blind" retainAll, but you can write shorter code
     * @param clctn
     * @return
     */
    public ExtendedTreeSet<T> retainAllFluid(Collection<T> collection) {
        retainAll(collection);
        return this;
    }

    /**
     * like addFluid but the returned object is a copy of this
     * @see #addFluid(T e)
     * @param e
     * @return
     */
    public ExtendedTreeSet<T> addFluidCopy(T e) {
        ExtendedTreeSet<T> copy = new ExtendedTreeSet<T>(this);
        return copy.addFluid(e);
    }

    /**
     * like removeFluid but the returned object is a copy of this
     * @see #removeFluid(T e)
     * @param e
     * @return
     */
    public ExtendedTreeSet<T> removeFluidCopy(T e) {
        ExtendedTreeSet<T> copy = new ExtendedTreeSet<T>(this);
        return copy.removeFluid(e);
    }

    /**
     * like addAllFluid but the returned object is a copy of this
     * @see #addAllFluid(Collection<? extends T>  clctn)
     * @param e
     * @return
     */
    public ExtendedTreeSet<T> addAllFluidCopy(Collection<? extends T> clctn) {
        ExtendedTreeSet<T> copy = new ExtendedTreeSet<T>(this);
        return copy.addAllFluid(clctn);
    }

    /**
     * like removeAllFluid but the returned object is a copy of this
     * @see #removeAllFluid(T e)
     * @param e
     * @return
     */
    public ExtendedTreeSet<T> removeAllFluidCopy(Collection<? extends T> clctn) {
        ExtendedTreeSet<T> copy = new ExtendedTreeSet<T>(this);
        return copy.removeAllFluid(clctn);
    }

    /**
     * like retainAllFluid but the returned object is a copy of this
     * @see #retainAllFluid(T e)
     * @param e
     * @return
     */
    public ExtendedTreeSet<T> retainAllFluidCopy(Collection<T> collection) {
        ExtendedTreeSet<T> copy = new ExtendedTreeSet<T>(this);
        return copy.retainAllFluid(collection);
    }
}
