package kz.kbtu.kaspishopback.service;

import kz.kbtu.kaspishopback.domain.KsPhoto;
import kz.kbtu.kaspishopback.repo.FileDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private FileDataRepo fileDataRepo;

    private final String FOLDER_PATH = "/Users/bekzat/Desktop/files/";

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        KsPhoto photo = fileDataRepo.save(KsPhoto.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build());

        file.transferTo(new File(filePath));

        if (photo != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<KsPhoto> photo = fileDataRepo.findByName(fileName);
        String filePath = photo.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}
