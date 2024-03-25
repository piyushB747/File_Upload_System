package net.unopoint.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.unopoint.entity.EntityImageData;
import net.unopoint.repository.RepositoryStorage;

@Service
public class ServiceRepositoryStorage {

	@Autowired
	private RepositoryStorage repositoryStorage;
	
	
	public String uploadImage(MultipartFile multipartFile) throws IOException {
		
		EntityImageData e1=repositoryStorage.save(
			EntityImageData.builder()
				.imageName(multipartFile.getOriginalFilename())
				.imageType(multipartFile.getContentType())
				.imageData(UtilImage.compressImage(multipartFile.getBytes())).build()
				);
		
	    if (e1 != null) {
            return "file uploaded successfully : " + multipartFile.getOriginalFilename();
        }
        return null;
	}
	
	  public byte[] downloadImage(String fileName){
	        Optional<EntityImageData> dbImageData = repositoryStorage.findByImageName(fileName);
	        byte[] images=UtilImage.decompressImage(dbImageData.get().getImageData());
	        return images;
	    }
	
}
