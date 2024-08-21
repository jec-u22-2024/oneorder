package com.preflame.oneorder.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.preflame.oneorder.model.REModel;

/**
 * 今の所jarにしたら使えない機能
 * 
 * 検証をしまくるために分離
 */
public class UploadImage {
    /**
     * 画像アップロードの処理
     * @param file
     * @return
     */
    @PostMapping("/merchImage")
    public ResponseEntity<Object> uploadMerch(@RequestPart("images") MultipartFile file) {
        JSONObject json = new JSONObject();
        HttpStatus stat = null;

        // Path dst = Path.of("./src/main/resources/static/upload/img", file.getOriginalFilename());
        // Path dst = Path.of("./resource/upload/img", file.getOriginalFilename());
        Path dst = Path.of("./upload/img", file.getOriginalFilename());
        try {
            // byte[] fileByte = file.getBytes();
            // Files.write(dst, fileByte);
            Files.copy(file.getInputStream(), dst);
            stat = HttpStatus.CREATED;
        } catch(FileAlreadyExistsException e) {
            try {
                Files.delete(dst);
                // byte[] fileByte = file.getBytes();
                // Files.write(dst, fileByte);
                Files.copy(file.getInputStream(), dst);
            } catch(IOException ex) {
                System.err.println("I/O例外");
            }
            stat = HttpStatus.ACCEPTED;
        } catch(Exception e) {
            e.printStackTrace();
            stat = HttpStatus.BAD_REQUEST;
            json.put("error", "Any Error");
        }
        ResponseEntity<Object> res = new ResponseEntity<Object>(json.toMap(), stat);
        return res;
    }

    @PutMapping("/merchImage")
    public ResponseEntity<Object> updateImage(@RequestPart("images") MultipartFile file, @RequestPart("oldImage") String oldPath) {
        // Path dst = Path.of("./src/main/resources/static/upload/img", file.getOriginalFilename());
        Path dst = Path.of("./upload/img", file.getOriginalFilename());
        try {

            // 一応古い画像を削除する処理
            if(!oldPath.equals("assets/img/noimage.png")) {
                // Path old = Path.of("./src/main/resources/static", oldPath);
                Path old = Path.of(".", oldPath);
                File oldImg = new File(old.toString());
                if(oldImg.exists()) {
                    oldImg.delete();
                }
            }


            // byte[] fileByte = file.getBytes();
            // Files.write(dst, fileByte);
            Files.copy(file.getInputStream(), dst);
            
        } catch(FileAlreadyExistsException e) {
            try {
                Files.delete(dst);
                // byte[] fileByte = file.getBytes();
                // Files.write(dst, fileByte);
                Files.copy(file.getInputStream(), dst);
            } catch(IOException ex) {
                System.err.println("I/O例外");
                return new REModel().getModel(HttpStatus.BAD_REQUEST);
            }
        } catch(NoSuchFileException e) {
            System.err.println("no file error");
        } catch(Exception e) {
            System.err.println("any error");
            return new REModel().getModel(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new REModel().getModel();
    }
}
