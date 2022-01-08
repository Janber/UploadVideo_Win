import rpa.video.DoUpload;
import rpa.video.UploadVideo;

import java.awt.*;
import java.io.File;

/**
 * @author k_go
 * @version 1.0
 * @date 2020/05/13 17:08
 */
public class Main {

    public static void main(String[] args) throws Exception {

        File file = new File("Z:\\Downloads\\output");
        UploadVideo.getAllFilesAndUpload(file);
//        File toutiao = new File("Z:\\Downloads\\Bilibili");
//        UploadVideo.getAllFilesAndUploadToutiao(toutiao);
        File toDeleteFile = new File("Z:\\Downloads");
        UploadVideo.getAllFilesAndUploadToDelete(toDeleteFile);
    }

}
