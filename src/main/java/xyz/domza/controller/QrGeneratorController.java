package xyz.domza.controller;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/qrGen")
public class QrGeneratorController {
    private static final Logger logger = LogManager.getLogger(QrGeneratorController.class);

    @GetMapping(value = "/getQRCode")
    public ResponseEntity<ByteArrayResource> getQRCode(@RequestParam String text, @RequestParam String format) throws IOException {
        if (format.equals("png")) {
            logger.info("Generating png Qr Code for: " + text);
            byte[] image = generateQRCodeImage(text).to(ImageType.PNG).stream().toByteArray();

            ByteArrayResource resource = new ByteArrayResource(image);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .contentLength(image.length)
                    .body(resource);
        }
        else if (format.equals("svg")) {
            logger.info("Generating svg Qr Code for: " + text);
            File svgFile = generateQRCodeImage(text).svg();
            byte[] file = Files.readAllBytes(svgFile.toPath());
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type","image/svg+xml");

            ByteArrayResource resource = new ByteArrayResource(file);

            return ResponseEntity.ok()
                    .headers(header)
                    .body(resource);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    private static QRCode generateQRCodeImage(String barcodeText) {
        return QRCode.from(barcodeText).withSize(200, 200);
    }
}
