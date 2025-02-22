package repository;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class MsgBox {

    private static final Icon icon = new javax.swing.ImageIcon(MsgBox.class.getResource("/icon/logo-removebg-preview.png"));

    public static void alert(Component parent, String message) {

        //JOptionPane.showMessageDialog(parent, message, "ADAM STORE thông báo", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(parent, message, "G4 Shoes thông báo", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public static boolean confirm(Component perent, String mess) {
        int result = JOptionPane.showConfirmDialog(perent, mess, "G4 Shoes thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        return result == JOptionPane.YES_OPTION;
    }

    public static String promt(Component parent, String mess) {
        return JOptionPane.showInputDialog(parent, mess, "G4 Shoes thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}
