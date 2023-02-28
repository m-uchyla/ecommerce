package com.muchyla.ecommerce.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.muchyla.ecommerce.models.TwoFactorCode;
import com.muchyla.ecommerce.models.User;
import com.muchyla.ecommerce.repositories.TwoFactorCodeRepository;

@Service
public class TwoFactorCodeService implements ITwoFactorCodeService {
	
	TwoFactorCodeRepository twoFactorCodeRepository;
	
	public TwoFactorCodeService(TwoFactorCodeRepository twoFactorCodeRepository) {
		this.twoFactorCodeRepository = twoFactorCodeRepository;
	}
	
	public TwoFactorCode getFromOptional(Optional<TwoFactorCode> twoFactorCode) {
		return (twoFactorCode.isPresent()) ? twoFactorCode.get() : null;
	}

	@Override
	public TwoFactorCode generateCode(User user) {
		TwoFactorCode twoFactorCode = getFromOptional(twoFactorCodeRepository.findByOwner(user));
		if(twoFactorCode != null) {
			if(twoFactorCode.getDeadlineDate().before(new Date())) {
				twoFactorCodeRepository.deleteById(twoFactorCode.getId());
			}else if(twoFactorCode.getDeadlineDate().after(new Date())) {
				return twoFactorCode;
			}	
		}
		Long id;
		do {
			id = Math.round(Math.random() * (99999 - 10000 + 1) + 10000);
		}while(twoFactorCodeRepository.findById(id).isPresent());
		return twoFactorCodeRepository.save(new TwoFactorCode(id,user));
	}

	@Override
	public Boolean checkCode(User user, Long code) {
		TwoFactorCode twoFactorCode = getFromOptional(twoFactorCodeRepository.findByOwner(user));
		if(twoFactorCode.getId().equals(code)) {
			if(twoFactorCode.getDeadlineDate().after(new Date())) {
				twoFactorCodeRepository.deleteById(twoFactorCode.getId());
				return true;
			}else {
				twoFactorCodeRepository.deleteById(twoFactorCode.getId());
				return false;
			}
		}else {
			return false;
		}
	}

}
