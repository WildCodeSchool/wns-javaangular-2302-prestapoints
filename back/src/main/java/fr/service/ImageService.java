package fr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fr.entity.Image;
import fr.repository.ImageRepository;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class ImageService {
  @Autowired
  private ImageRepository imageRepository;

  @Transactional
  public Image saveImage(MultipartFile picture) throws IOException, SQLException {
    byte[] imageData = picture.getBytes();
Image image = new Image(imageData);

    return imageRepository.save(image);
  }
}