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

/**
 *
 * @author jonas
 */
import java.util.*;
import java.io.*;
import java.awt.datatransfer.*;
import javax.swing.*;

public class ReportingListTransferHandler extends TransferHandler {
    DataFlavor localArrayListFlavor, serialArrayListFlavor;
    String localArrayListType = DataFlavor.javaJVMLocalObjectMimeType +
                                ";class=java.util.ArrayList";
    JList source = null;
    int[] indices = null;
    int addIndex = -1; //Location where items were added
    int addCount = 0;  //Number of items added

    public ReportingListTransferHandler() {
        try {
            localArrayListFlavor = new DataFlavor(localArrayListType);
        } catch (ClassNotFoundException e) {
            System.out.println(
             "ReportingListTransferHandler: unable to create data flavor");
        }
        serialArrayListFlavor = new DataFlavor(ArrayList.class,
                                              "ArrayList");
    }

    @Override
    public boolean importData(JComponent c, Transferable t) {
        JList target = null;
        ArrayList alist = null;
        if (!canImport(c, t.getTransferDataFlavors())) {
            return false;
        }
        try {
            target = (JList)c;
            if (hasLocalArrayListFlavor(t.getTransferDataFlavors())) {
                alist = (ArrayList)t.getTransferData(localArrayListFlavor);
            } else if (hasSerialArrayListFlavor(t.getTransferDataFlavors())) {
                alist = (ArrayList)t.getTransferData(serialArrayListFlavor);
            } else {
                return false;
            }
        } catch (UnsupportedFlavorException ufe) {
            System.out.println("importData: unsupported data flavor");
            return false;
        } catch (IOException ioe) {
            System.out.println("importData: I/O exception");
            return false;
        }

        // If you want the dialog to appear only for drags from the
        // left list and drops on the right JList.
//        if(target.getName().equals("right") && target != source) {
//            String message = "Do you want to drop here?";
//            int retVal = JOptionPane.showConfirmDialog(target, message, "Confirm",
//                                                       JOptionPane.YES_NO_OPTION,
//                                                       JOptionPane.QUESTION_MESSAGE);
//            System.out.println("retVal = " + retVal);
//            if(retVal == JOptionPane.NO_OPTION || retVal == JOptionPane.CLOSED_OPTION)
//                return false;
//        }

        //At this point we use the same code to retrieve the data
        //locally or serially.

        //We'll drop at the current selected index.
        int index = target.getSelectedIndex();

        //Prevent the user from dropping data back on itself.
        //For example, if the user is moving items #4,#5,#6 and #7 and
        //attempts to insert the items after item #5, this would
        //be problematic when removing the original items.
        //This is interpreted as dropping the same data on itself
        //and has no effect.
//        if (source.equals(target)) {
//            if (indices != null && index >= indices[0] - 1 &&
//                  index <= indices[indices.length - 1]) {
//                indices = null;
//                return true;
//            }
//        }

        DefaultListModel listModel = (DefaultListModel)target.getModel();
        int max = listModel.getSize();
        //System.out.printf("index = %d  max = %d%n", index, max);
        if (index < 0) {
            index = max;
        } else {
            index++;
            if (index > max) {
                index = max;
            }
        }
        addIndex = index;
        addCount = alist.size();
        for (int i=0; i < alist.size(); i++) {
            listModel.add(index++, alist.get(i));
        }
        return true;
    }

    @Override
    protected void exportDone(JComponent c, Transferable data, int action) {
        if ((action == MOVE) && (indices != null)) {
            DefaultListModel model = (DefaultListModel)source.getModel();

            //If we are moving items around in the same list, we
            //need to adjust the indices accordingly since those
            //after the insertion point have moved.
            if (addCount > 0) {
                for (int i = 0; i < indices.length; i++) {
                    if (indices[i] > addIndex &&
                        indices[i] + addCount < model.getSize()) {
                        indices[i] += addCount;
                    }
                }
            }
            for (int i = indices.length -1; i >= 0; i--)
                model.remove(indices[i]);
        }
        indices = null;
        addIndex = -1;
        addCount = 0;
    }

    private boolean hasLocalArrayListFlavor(DataFlavor[] flavors) {
        if (localArrayListFlavor == null) {
            return false;
        }

        for (int i = 0; i < flavors.length; i++) {
            if (flavors[i].equals(localArrayListFlavor)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSerialArrayListFlavor(DataFlavor[] flavors) {
        if (serialArrayListFlavor == null) {
            return false;
        }

        for (int i = 0; i < flavors.length; i++) {
            if (flavors[i].equals(serialArrayListFlavor)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canImport(JComponent c, DataFlavor[] flavors) {
        if (hasLocalArrayListFlavor(flavors))  { return true; }
        if (hasSerialArrayListFlavor(flavors)) { return true; }
        return false;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        if (c instanceof JList) {
            source = (JList)c;
            indices = source.getSelectedIndices();
            Object[] values = source.getSelectedValues();
            if (values == null || values.length == 0) {
                return null;
            }
            ArrayList<String> alist = new ArrayList<String>(values.length);
            for (int i = 0; i < values.length; i++) {
                Object o = values[i];
                String str = o.toString();
                if (str == null) str = "";
                alist.add(str);
            }
            return new ReportingListTransferable(alist);
        }
        return null;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    public class ReportingListTransferable implements Transferable {
        ArrayList data;

        public ReportingListTransferable(ArrayList alist) {
            data = alist;
        }

        public Object getTransferData(DataFlavor flavor)
                                 throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return data;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] { localArrayListFlavor,
                                      serialArrayListFlavor };
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            if (localArrayListFlavor.equals(flavor)) {
                return true;
            }
            if (serialArrayListFlavor.equals(flavor)) {
                return true;
            }
            return false;
        }
    }
}