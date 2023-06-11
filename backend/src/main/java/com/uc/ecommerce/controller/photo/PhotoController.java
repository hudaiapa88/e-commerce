package com.uc.ecommerce.controller.photo;

import com.uc.ecommerce.core.security.annotation.IsAuthenticated;
import com.uc.ecommerce.core.security.annotation.PermitAll;
import com.uc.ecommerce.service.imp.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.sql.SQLException;

@RestController
@RequestMapping("photo")
@RequiredArgsConstructor
@IsAuthenticated
public class PhotoController {
    private final PhotoService photoService;
    @PostMapping("/product/{productId}")
    public void upload(@PathVariable Long productId,@RequestParam MultipartFile data){
        photoService.upload(productId,data);
    }

    @PermitAll
    @GetMapping(value = "/product/{productId}", produces = MediaType.ALL_VALUE)
    public ResponseEntity getData(@PathVariable Long productId) throws SQLException {
        InputStream inputStream = photoService.getData(productId).getBinaryStream();
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .body(inputStreamResource);
    }
}
