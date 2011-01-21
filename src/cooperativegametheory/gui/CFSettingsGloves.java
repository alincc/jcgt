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

package cooperativegametheory.gui;

import cooperativegametheory.coalitionfunctions.CoalitionFunction;
import cooperativegametheory.coalitionfunctions.GlovesGame;
import cooperativegametheory.PlayerSet;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JSpinner;
/**
 *
 * @author Jonas Brekle <jonas.brekle@gmail.com>
 */
public class CFSettingsGloves extends CFSettingsPanel {

    int oldPlayerNumber = 1;

    /** Creates new form CFSettingsGloves */
    public CFSettingsGloves() {
        initComponents();
        playerSettingsContainer.setLayout(new BoxLayout(playerSettingsContainer, BoxLayout.Y_AXIS));
        playerSettingsContainer.setAlignmentX(LEFT_ALIGNMENT);
        playerSettingsContainer.setAlignmentY(TOP_ALIGNMENT);
        playerSettingsContainer.add(new CFSettingsGlovesPlayer(1), BorderLayout.SOUTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        numPlayers = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playerScrollPane = new javax.swing.JScrollPane();
        playerSettingsContainer = new javax.swing.JPanel();

        numPlayers.setModel(new javax.swing.SpinnerNumberModel(1, 1, 19, 1));
        numPlayers.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                numPlayersStateChanged(evt);
            }
        });

        jLabel1.setText("player");

        jLabel2.setText("left");

        jLabel3.setText("right");

        jScrollPane1.setBorder(null);

        playerScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        playerScrollPane.setOpaque(false);

        playerSettingsContainer.setOpaque(false);

        javax.swing.GroupLayout playerSettingsContainerLayout = new javax.swing.GroupLayout(playerSettingsContainer);
        playerSettingsContainer.setLayout(playerSettingsContainerLayout);
        playerSettingsContainerLayout.setHorizontalGroup(
            playerSettingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
        playerSettingsContainerLayout.setVerticalGroup(
            playerSettingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );

        playerScrollPane.setViewportView(playerSettingsContainer);

        jScrollPane1.setViewportView(playerScrollPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(numPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addComponent(jLabel1))
                        .addGap(149, 149, 149)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void numPlayersStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_numPlayersStateChanged
        int newNum = (Integer) (((JSpinner)evt.getSource()).getValue());
        if(newNum > oldPlayerNumber){
            for(int i = oldPlayerNumber + 1 ; i <= newNum; i++){
                playerSettingsContainer.add(new CFSettingsGlovesPlayer(i), BorderLayout.SOUTH);
            }
        } else {
            System.out.println("try to remove from "+ (oldPlayerNumber - 1) + " to "  +(newNum));
            for(int i = oldPlayerNumber - 1; i > newNum -1; i--){
                System.out.println("remove "+i +((CFSettingsGlovesPlayer)playerSettingsContainer.getComponent(i)).getNum());
                playerSettingsContainer.remove(i);
                playerSettingsContainer.repaint();
            }
        }
        validate();
        oldPlayerNumber = newNum;
    }//GEN-LAST:event_numPlayersStateChanged


    @Override
    CoalitionFunction getCF() {
        int[] l = getLeftGloveOwners();
        int[] r = getRightGloveOwners();
        return new GlovesGame(new PlayerSet((Integer) (numPlayers.getValue())), l, r);
    }

    protected int[] getLeftGloveOwners(){
        ArrayList<Integer> lp = new ArrayList<Integer>();
        int sum = 0;
        for(int i = 0; i < playerSettingsContainer.getComponentCount(); i++){
            lp.add(((CFSettingsGlovesPlayer) playerSettingsContainer.getComponent(i)).getLeft());
            sum += ((CFSettingsGlovesPlayer) playerSettingsContainer.getComponent(i)).getLeft();
        }
        int[] l = new int[sum];
        for(int k = 0; k < lp.size(); k++){
            for(int i = 0; i < lp.get(k); i++){
                l[--sum] = k;
            }
        }
        return l;
    }

    protected int[] getRightGloveOwners(){
        ArrayList<Integer> lp = new ArrayList<Integer>();
        int sum = 0;
        for(int i = 0; i < playerSettingsContainer.getComponentCount(); i++){
            lp.add(((CFSettingsGlovesPlayer) playerSettingsContainer.getComponent(i)).getRight());
            sum += ((CFSettingsGlovesPlayer) playerSettingsContainer.getComponent(i)).getRight();
        }
        int[] l = new int[sum];
        for(int k = 0; k < lp.size(); k++){
            for(int i = 0; i < lp.get(k); i++){
                l[--sum] = k;
            }
        }
        return l;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner numPlayers;
    private javax.swing.JScrollPane playerScrollPane;
    private javax.swing.JPanel playerSettingsContainer;
    // End of variables declaration//GEN-END:variables

}
