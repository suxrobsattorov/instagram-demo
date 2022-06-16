package com.example.controller;

import com.example.service.AttachService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/attach")
public class AttachController {

    private final AttachService attachService;

    @PostMapping("/upload")
    public ResponseEntity<?> fileUpload(@RequestBody MultipartFile file) {
        return ResponseEntity.status(201).body(attachService.saveFile(file));
    }

    @GetMapping(value = "/get/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("fileName") String fileName) {
        if (fileName != null && fileName.length() > 0) {
            try {
                return this.attachService.loadAttach(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
        return null;
    }

    @GetMapping(value = "/loadGeneral/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] loadGeneral(@PathVariable("fileName") String fileName) {
        if (fileName != null && fileName.length() > 0) {
            try {
                return this.attachService.loadGeneral(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
        return null;
    }

    @GetMapping("/load/{fineName}")
    public ResponseEntity<Resource> loadFile(@PathVariable("fineName") String fileToken) {
        Resource file = attachService.load(fileToken);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @DeleteMapping("/delete/{key}")
    public ResponseEntity<?> deleteFile(@PathVariable("key") String fileToken) {
        return ResponseEntity.ok().body(attachService.delete(fileToken));
    }

}
