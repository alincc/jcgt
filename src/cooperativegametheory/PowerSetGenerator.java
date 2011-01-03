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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator over the power set of a collection. The power set of a collection
 * is the set of all subsets of that collection.
 *
 * To use this class, one passes a collection to one of the constructors. The
 * PowerSet object produced is an Iterator, and one may use its hasNext and next
 * methods to produce all nonempty subsets of the given collection. Strictly
 * speaking, the power set of a collection includes the null or empty set, but
 * for practical reasons this class does not produce this set. Each application
 * of the next method produces an instance of the Vector class; this instance
 * contains a subset of the given collection.
 *
 * @author  Kerry Michael Soileau
 * ksoileau@yahoo.com>
 * http://www.kerrysoileau.com/
 * 2004 Positronic Software
 */
public class PowerSetGenerator<T> implements Iterator {

    private boolean[] memberBitmask;
    private ExtendedTreeSet<T> elements;

    public PowerSetGenerator(ExtendedTreeSet<T> set) {
        elements = set;
        memberBitmask = new boolean[elements.size()];
    }

    /**
     * Returns the next subset in the PowerSet.
     *
     * @return the next subset in the PowerSet.
     * @exception NoSuchElementException PowerSet has no more subsets.
     */
    public ExtendedTreeSet<T> next() {
        if (!hasNext()) {
            throw (new NoSuchElementException("The next method was called when no more objects remained."));
        } else {
            int n = 0;
            memberBitmask[0] = !memberBitmask[0];
            boolean carry = !memberBitmask[0];
            while (n + 1 < memberBitmask.length) {
                n++;
                if (carry) {
                    memberBitmask[n] = !memberBitmask[n];
                    carry = !memberBitmask[n];
                } else {
                    break;
                }
            }

            ExtendedTreeSet<T> ret = new ExtendedTreeSet<T>();
            T curr;
            Iterator<T> it = elements.descendingIterator();

            for (int i = 0; i < memberBitmask.length && it.hasNext(); i++) {
                curr = it.next();
                if (memberBitmask[i]) {
                    ret.add(curr);
                }
            }
            return ret;
        }
    }

    /**
     *
     * Not supported by this class.
     *
     * @exception UnsupportedOperationException because the <tt>remove</tt>
     *		  operation is not supported by this Iterator.
     */
    public void remove() {
        throw new UnsupportedOperationException("The PowerSet class does not support the remove method.");
    }

    /**
     * Returns <tt>true</tt> if the PowerSet has more subsets. (In other
     * words, returns <tt>true</tt> if <tt>next</tt> would return a subset
     * rather than throwing an exception.)
     *
     * @return <tt>true</tt> if the PowerSet has more subsets.
     */
    public boolean hasNext() {
        for (int i = 0; i < memberBitmask.length; i++) {
            if (!memberBitmask[i]) {
                return true;
            }
        }
        return false;
    }
}
