package cz.tallavla.vouchermaker.service;

import cz.tallavla.vouchermaker.model.service.LoginDTO;
import cz.tallavla.vouchermaker.model.service.RegisterDTO;

public interface AuthService {

	String login(LoginDTO loginDTO);

	String register(RegisterDTO registerDTO);
}