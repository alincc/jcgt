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

import java.util.HashMap;

/**
 *
 * @author Jonas Brekle <jonas.brekle@gmail.com>
 */
public class MaschlerGame extends SymetricGame{

    public MaschlerGame() {
        super(getMap());
    }
    private static HashMap<Integer,Integer> getMap(){
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 0);
        map.put(1, 0);
        map.put(2, 60);
        map.put(3, 72);
        return map;
    }
}
