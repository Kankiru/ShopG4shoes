package repository;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class XImage {

    public static Image getAppIcon() {
        URL url = XImage.class.getResource("/icon/logo-removebg-preview.png");
        return new ImageIcon(url).getImage();
    }
    
    public static Image getAppIcon2() {
        URL url = XImage.class.getResource("/icon/logo-removebg-preview.png");
        return new ImageIcon(url).getImage();
    }
}
