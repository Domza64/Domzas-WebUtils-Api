package xyz.domza.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.domza.utils.DownloadUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/converter")
public class ConverterController {
    private static final Logger logger = LogManager.getLogger(ConverterController.class);

    @GetMapping("/getVideo")
    public ResponseEntity<Resource> getVideo(@RequestParam String id, @RequestParam String type) {
        Resource resource = DownloadUtils.getVideoOrSong(id, type);

        logger.info("Downloaded: " + resource.getFilename());

        return ResponseEntity.ok()
                .header("X-Filename", resource.getFilename())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
