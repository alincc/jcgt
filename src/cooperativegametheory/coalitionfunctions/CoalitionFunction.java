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

package cooperativegametheory.coalitionfunctions;

import cooperativegametheory.Coalition;
import cooperativegametheory.Partition;
import cooperativegametheory.PlayerSet;

/**
 *
 * @author Jonas Brekle <jonas.brekle@gmail.com>
 */
public interface CoalitionFunction {
    public int getNumPlayers();
    public PlayerSet getPlayers();
    public float getValue(Coalition coalition);
    public boolean isMonotonic();
    public Partition getSymetricPlayers();
}
