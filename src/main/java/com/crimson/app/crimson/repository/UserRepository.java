package com.crimson.app.crimson.repository;


import com.crimson.app.crimson.dto.UserDto;
import com.crimson.app.crimson.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);
    boolean existsById(Long userId);

    @Query(value = """
            
            Select new com.crimson.app.crimson.dto.UserDto() from User u
            
            """, nativeQuery = true)
    List<UserDto> getAllUsers();

}
