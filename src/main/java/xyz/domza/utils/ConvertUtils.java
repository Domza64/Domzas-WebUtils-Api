package xyz.domza.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class ConvertUtils {
    private static final Logger logger = LogManager.getLogger(ConvertUtils.class);

    public static File convertM4AToMP3(Path inputFilePath) {

        // Create outputFilePath
        String outputFilePath = inputFilePath.toString().replace(".m4a", ".mp3");

        try {
            String command = String.format("ffmpeg -i %s -acodec libmp3lame -aq 4 -y %s", inputFilePath, outputFilePath);

            Process process = Runtime.getRuntime().exec(command);

            // Read the output to check for any errors
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }

            // Wait for the process to complete
            File mp3File = null;
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                mp3File = new File(outputFilePath);
            } else {
                logger.error("Error converting " + inputFilePath);
            }

            cleanup(inputFilePath);

            return mp3File;

        } catch (IOException | InterruptedException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    private static void cleanup(Path path) {
        try {
            Files.deleteIfExists(path);
        }
        catch (Exception ignored) {}
    }
}
