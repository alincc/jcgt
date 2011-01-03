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

import java.math.BigInteger;
import java.util.Iterator;

/**
 * Class used to create permutations of a set of numbers from 0 to n-1
 * @author Michael Gilleland, Merriam Park Software {  http://www.merriampark.com/perm.htm }
 */
public class PermutationGenerator implements Iterator {

    /**
     * array of integers representing current permutation that is being sent back to requestor
     */
    private int[] a;
    /**
     * BigInteger of the number of permutations that have not been retrieved
     */
    private BigInteger numLeft;
    /**
     * BigInteger of the total number of permutations
     */
    private BigInteger total;

    /**
     * Constructor to create the initial permutation of the list of integers from
     * 0 to a given number.
     * WARNING: Don't make n too large.
     * Recall that the number of permutations is n!
     * which can be very large, even when n is as small as 20 --
     * 20! = 2,432,902,008,176,640,000 and
     * 21! is too big to fit into a Java long, which is
     * why we use BigInteger instead.
     *
     * @param n integer size of the array to permutate
     */
    public PermutationGenerator(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Min 1");
        }
        a = new int[n];
        total = getFactorial(n);
        reset();
    }

    /**
     * Method to reset the permutation list to the original array
     *
     */
    public void reset() {
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        numLeft = new BigInteger(total.toString());
    }

    /**
     * Method to return number of permutations not yet generated
     *
     * @return BigInteger value of the number of permutations left
     */
    public BigInteger getNumLeft() {
        return numLeft;
    }

    /**
     * Method to return total number of permutations
     *
     * @return BigInteger value of the total number of permutations
     */
    public BigInteger getTotal() {
        return total;
    }

    /**
     * Method to return whether any more permutations are left to use
     * @return Boolean value determining if <CODE>numLeft</CODE> is greater than zero
     */
    public boolean hasNext() {
        return numLeft.compareTo(BigInteger.ZERO) == 1;
    }

    /**
     * Method to compute the factorial of a given integer
     *
     * @param n integer to which compute the factorial
     * @return BigInteger factorial of the given value
     */
    private static BigInteger getFactorial(int n) {
        BigInteger fact = BigInteger.ONE;
        for (int i = n; i > 1; i--) {
            fact = fact.multiply(new BigInteger(Integer.toString(i)));
        }
        return fact;
    }

    /**
     * Method to generate next permutation (algorithm from Rosen p. 284)
     *
     * @return integer array containing next permutation of list of integers from 0 to n-1
     */
    public int[] next() {

        if (numLeft.equals(total)) {
            numLeft = numLeft.subtract(BigInteger.ONE);
            return a;
        }

        int temp;

        // Find largest index j with a[j] < a[j+1]

        int j = a.length - 2;
        while (a[j] > a[j + 1]) {
            j--;
        }

        // Find index k such that a[k] is smallest integer
        // greater than a[j] to the right of a[j]

        int k = a.length - 1;
        while (a[j] > a[k]) {
            k--;
        }

        // Interchange a[j] and a[k]

        temp = a[k];
        a[k] = a[j];
        a[j] = temp;

        // Put tail end of permutation after jth position in increasing order

        int r = a.length - 1;
        int s = j + 1;

        while (r > s) {
            temp = a[s];
            a[s] = a[r];
            a[r] = temp;
            r--;
            s++;
        }

        numLeft = numLeft.subtract(BigInteger.ONE);
        return a;

    }

    public static int[][] getAllPermutations(int numPlayers) {
        PermutationGenerator x = new PermutationGenerator(numPlayers); // is there "self"?
        int[][] ro = new int[x.getTotal().intValue()][numPlayers];

        for (int i = 0; x.hasNext(); i++) {
            ro[i] = x.next().clone();
        }
        return ro;
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
}
