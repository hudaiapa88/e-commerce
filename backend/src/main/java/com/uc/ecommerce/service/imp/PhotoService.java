package com.uc.ecommerce.service.imp;

import com.uc.ecommerce.model.entity.photo.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

public interface PhotoService {

    Blob getData(Long productId);

    Photo findByProductId(Long productId);

    void upload(Long productId, MultipartFile photo);
}
