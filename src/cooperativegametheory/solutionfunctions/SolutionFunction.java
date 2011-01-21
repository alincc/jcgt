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

package cooperativegametheory.solutionfunctions;

import cooperativegametheory.Coalition;
import cooperativegametheory.coalitionfunctions.CoalitionFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonas Brekle <jonas.brekle@gmail.com>
 */
public abstract class  SolutionFunction {
    public abstract CoalitionFunction getCoalitionFunction();

    public abstract float getValue(int c);

    public static float marginalContribution(CoalitionFunction c, int[] rankOrder, int player) {
        Coalition playersInTheRoom = new Coalition(c.getPlayers());

        //add all player tat are BEFORE him in the rankorder
        for (int i = 0; i < rankOrder.length -1  && rankOrder[i] != player; i++) {
            playersInTheRoom.add(new Integer(rankOrder[i]));
        }
        float valBefore = c.getValue(playersInTheRoom);

        playersInTheRoom.add(player);

        float valAfter = c.getValue(playersInTheRoom);

        return valAfter - valBefore;
    }

    public static float marginalContribution(CoalitionFunction c, List<Integer> rankOrder, int player) {
        Coalition playersInTheRoom = new Coalition(c.getPlayers());

        //add all player tat are BEFORE him in the rankorder
        for (int i = 0; i < rankOrder.size() -1  && rankOrder.get(i) != player; i++) {
            playersInTheRoom.add(new Integer(rankOrder.get(i)));
        }
        float valBefore = c.getValue(playersInTheRoom);

        playersInTheRoom.add(player);

        float valAfter = c.getValue(playersInTheRoom);

        return valAfter - valBefore;
    }

    public static float[] averageMarginalContribution(CoalitionFunction c, float[][] marginalContributions){
        float sum = 0;
        if(marginalContributions.length == 0){
            return new float[]{};
        }
        int numPlayer = marginalContributions[0].length;
        float[] ret = new float[numPlayer];
        for (int i = 0; i < numPlayer; i++) {
            sum = 0;
            for (int j = 0; j < marginalContributions.length; j++) {
                sum += marginalContributions[j][i];
            }
            ret[i] = sum / marginalContributions.length;
        }
        return ret;
    }
}
