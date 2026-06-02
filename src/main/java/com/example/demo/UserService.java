package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	// 전체 조회
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	// 단건 조회
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    // 이름으로 조회
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }
    
    // 이름 포함 검색
    public List<User> searchByName(String name) {
        return userRepository.searchByName(name);
    }

    // 저장
    public User save(User user) {
        // 이메일 중복 체크
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("이미 존재하는 이메일입니다");
        }
        return userRepository.save(user);
    }


    // 삭제
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
