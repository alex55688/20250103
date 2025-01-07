package com.tsb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.tsb.entity.Users;
import com.tsb.entity.UserRepository;

@Service
@Component
public class LoginService {
	
	@Autowired
	private UserRepository userRep;
	
	public boolean checkId(String id) {
		Optional<Users> checkAcc = userRep.findById(id);
		if(checkAcc.isPresent()) {
			return true;
		}
		return false;
	}
	
	public boolean validAcc(Users user) {
		Optional<Users> checkAcc = userRep.findById(user.getId());
		boolean validAcc = false;
		if(checkAcc.isPresent() && user.getId().equals(checkAcc.get().getId())
				&& user.getMarkname().equals(checkAcc.get().getMarkname())
				&& user.getPassword().equals(checkAcc.get().getPassword())) {
			validAcc = true;
		}
		return validAcc;
	}
}
