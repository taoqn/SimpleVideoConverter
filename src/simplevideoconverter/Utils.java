/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simplevideoconverter;

import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author juanki23
 */
public class Utils {
    public final static String rmvb = "rmvb";
    public final static String avi = "avi";
    public final static String mpg = "mpg";
    public final static String mpeg = "mpeg";
    public final static String vob = "vob";
    public final static String mp4 = "mp4";
    public final static String mkv = "mkv";
    public final static String ogv = "ogv";
    public final static String wmv = "wmv";
    public final static String ogg = "ogg";
    public final static String ogm = "ogm";
    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    
    
}


