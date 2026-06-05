package com.projetFe.Event.restcontroller;

import com.projetFe.Event.Image;
import com.projetFe.Event.repository.Imagerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/image")
public class Imagecontroller {
    @Autowired
    private Imagerepository imagerepository ;
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadImage(@RequestParam("file") MultipartFile file)throws IOException {
        // Ici, on laisse IOException remonter (pas de try-catch)
        Image img = new Image(file.getBytes());
        imagerepository.save(img) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable ("id") Long id) {
        Optional<Image> optionalImage = imagerepository.findById(id);
        if (optionalImage.isPresent()) {
            byte[] data = optionalImage.get().getFile();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // or determine dynamically
            headers.setContentLength(data.length);
            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
    }


}
