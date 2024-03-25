package net.unopoint.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import net.unopoint.entity.EntityImageData;


public interface RepositoryStorage extends JpaRepository<EntityImageData, Long>{

	Optional<EntityImageData> findByImageName(String fileName);
	
}
