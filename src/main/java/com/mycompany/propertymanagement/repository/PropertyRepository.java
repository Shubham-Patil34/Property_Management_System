package com.mycompany.propertymanagement.repository;

import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {
    // Method 1:
    // List<PropertyEntity> findByUserEntityId(Long userId);

    // Method 2:
    // Alternate way using Query
    @Query("Select p from PropertyEntity p where p.userEntity.id = :userId")
    List<PropertyEntity> findByUserEntityId(@Param("userId") Long userId);

}
