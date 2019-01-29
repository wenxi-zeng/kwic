package edu.utdallas.wxz180008;

import edu.utdallas.wxz180008.autoindex.AutoIndexView;
import edu.utdallas.wxz180008.util.ConfigHelper;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Kwic {
    public static void main(String[] args) {
        SimpleDateFormat format =   new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());
        File folder;

        if (ConfigHelper.getInstance().isAutoMode())
            folder = new File(ConfigHelper.getInstance().getDataFolder() + File.separator + dateStr);
        else
            folder = new File(ConfigHelper.getInstance().getDataFolder());

        Logger logger = Logger.getLogger("KwicLogger");
        FileHandler fh;
        try {
            fh = new FileHandler(folder + File.separator + "kwic-" + dateStr + ".log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!folder.exists()) {
            logger.info("Folder " + folder + " not found.");
            return;
        }

        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null || listOfFiles.length == 0) {
            logger.info("No files found under folder " + folder);
            return;
        }

        AutoIndexView view = new AutoIndexView();
        for (File file : listOfFiles) {
            if (file.isFile() && FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("csv")) {
                try {
                    view.processFile(file.getCanonicalPath());
                    logger.info(file.getCanonicalPath() + " processed.");
                    view.reset();
                } catch (IOException e) {
                    logger.info(Arrays.toString(e.getStackTrace()));
                }
            }
        }

        logger.info("Completed.");
    }
}
