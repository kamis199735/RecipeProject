package kamis199735.springframework.recipeproject.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	void saveImageFile(Long recipeId, MultipartFile file);
}
