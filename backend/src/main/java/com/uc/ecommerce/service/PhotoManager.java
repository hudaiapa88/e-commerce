package com.uc.ecommerce.service;

import com.uc.ecommerce.core.exception.FileUploadException;
import com.uc.ecommerce.core.i18n.Translator;
import com.uc.ecommerce.model.entity.photo.Photo;
import com.uc.ecommerce.model.entity.product.Product;
import com.uc.ecommerce.repository.PhotoRepository;
import com.uc.ecommerce.service.imp.PhotoService;
import com.uc.ecommerce.service.imp.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Blob;

@Service
@RequiredArgsConstructor
public class PhotoManager implements PhotoService {
    private final PhotoRepository photoRepository;
    private final ProductService productService;

    @Override
    public Blob getData(Long productId) {
        return findByProductId(productId).getData();
    }
    @Override
    public Photo findByProductId(Long productId) {
      return  photoRepository.findByProduct_Id(productId).orElseGet(()->{
          Photo photo= new Photo();
          try {
              File file = new File(this.getClass().getClassLoader().getResource("static/urun-resmi-yok.jpg").toURI());
              Blob blob = BlobProxy.generateProxy(new FileInputStream(file), file.length());
              photo.setData(blob);

          } catch (URISyntaxException e) {
              e.printStackTrace();
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }
          return photo;
      });
    }

    @Transactional
    @Override
    public void upload(Long productId, MultipartFile data) {
        Product product = productService.findById(productId);
        Photo photo = new Photo();
        photo.setTitle(data.getOriginalFilename());
        try {
            photo.setData(BlobProxy.generateProxy(data.getInputStream(), data.getSize()));
        } catch (IOException e) {
            throw new FileUploadException(Translator.toLocale("photo.FileUploadException"));
        }
        photo.setProduct(product);
        photoRepository.save(photo);
    }
}
