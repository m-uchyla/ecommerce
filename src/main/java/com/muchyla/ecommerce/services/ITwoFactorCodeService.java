package com.muchyla.ecommerce.services;

import com.muchyla.ecommerce.models.TwoFactorCode;
import com.muchyla.ecommerce.models.User;

public interface ITwoFactorCodeService {

	TwoFactorCode generateCode(User user);
	Boolean checkCode(User user, Long code);
}
