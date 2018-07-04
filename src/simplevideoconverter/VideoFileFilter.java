/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simplevideoconverter;

import java.io.File;
import javax.swing.filechooser.*;

/* ImageFilter.java is used by FileChooserDemo2.java. */
public class VideoFileFilter extends FileFilter {

    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.avi) ||
                extension.equals(Utils.rmvb) ||
                extension.equals(Utils.mpeg) ||
                extension.equals(Utils.mpg) ||
                extension.equals(Utils.mp4) ||
                extension.equals(Utils.vob)||
                extension.equals(Utils.wmv)||
                extension.equals(Utils.mkv)||
                extension.equals(Utils.ogv)||
                extension.equals(Utils.ogg)||
                extension.equals(Utils.ogm)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "rmvb, avi, mpg, mpeg, vob, mp4, wmv, mkv, ogv, ogg, ogm";
    }
}
