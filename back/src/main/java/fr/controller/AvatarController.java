package fr.controller;

import java.io.IOException;
import java.util.Optional;

import fr.entity.Avatar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.repository.AvatarRepository;

@RestController
@CrossOrigin(origins = "*")
public class AvatarController {
    
    @Autowired
    AvatarRepository avatarRepository; //TODO tout passer par un service

    @PreAuthorize("permitAll()") //TODO changer pour authorize seulement les roles
    @PostMapping("/avatars/upload")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            byte[] avatarData = file.getBytes();
            Avatar avatar = new Avatar();
            avatar.setData(avatarData);
            avatarRepository.save(avatar);

            return ResponseEntity.ok("Avatar uploaded successfully.");//TODO remplacer le message en dur par un Enum
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload avatar.");//TODO remplacer le message en dur par un Enum
        }
    }

    @PreAuthorize("permitAll()")//TODO changer pour authorize seulement les roles
    @GetMapping("/avatars/{id}")
    public ResponseEntity<byte[]> getAvatar(@PathVariable("id") Integer id) {
        Optional<Avatar> avatarOptional = avatarRepository.findById(id);
        if (avatarOptional.isPresent()) {
            Avatar avatar = avatarOptional.get();
            byte[] avatarData = avatar.getData();

            HttpHeaders headers = new HttpHeaders();
            // headers.setContentType(MediaType.IMAGE_PNG); //TODO Remplacer le type MIME par le type d'avatar stocké en BDD sinon faire sans (comme ici) mais on pourrait limiter les risques en filtrant le type

            return new ResponseEntity<>(avatarData, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
