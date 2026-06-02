package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{
	// 이름으로 조회
    List<User> findByName(String name);

    // 이름 포함 검색 (LIKE)
    List<User> findByNameContaining(String name);

    // 이름 + 이메일로 조회
    List<User> findByNameAndEmail(String name, String email);

    // JPQL 직접 작성
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> searchByName(@Param("name") String name);
    
    User findByEmail(String email);
}
