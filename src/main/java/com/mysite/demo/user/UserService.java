package com.mysite.demo.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mysite.demo.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password) {
    	return join("SBB", username, email, password);
	}

	private SiteUser join(String providerTypeCode, String username, String email, String password) {
		if (getUser(username) !=null) {
			throw new RuntimeException("해당 ID는 이미 사용중입니다.");
		}

		if (StringUtils.hasText(password)) password = passwordEncoder.encode(password);
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
       // user.setPassword(passwordEncoder.encode(password));
        user.setProviderTypeCode(providerTypeCode);
		user.setPassword(password);
        this.userRepository.save(user);
        return user;
    }
    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            //throw new DataNotFoundException("siteuser not found");
        	return null;
        }
    }
    
    //소셜로그인
    @Transactional
	public SiteUser whenSocialLogin(String providerTypeCode, String username) {
		SiteUser siteUser = getUser(username);

		if (siteUser != null) return siteUser;

		return join(providerTypeCode, username, "", "");
	}
}