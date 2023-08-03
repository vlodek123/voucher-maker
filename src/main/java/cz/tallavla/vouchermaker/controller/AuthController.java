package cz.tallavla.vouchermaker.controller;

import cz.tallavla.vouchermaker.mappers.Mappers;
import cz.tallavla.vouchermaker.model.controller.NewLogin;
import cz.tallavla.vouchermaker.model.controller.NewRegister;
import cz.tallavla.vouchermaker.model.service.LoginDTO;
import cz.tallavla.vouchermaker.model.service.RegisterDTO;
import cz.tallavla.vouchermaker.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vouchermaker/auth")
public class AuthController {
	private final ModelMapper modelMapper = new ModelMapper();

	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	// Login REST API
	@PostMapping(value = {"/login","/signin"})
	public ResponseEntity<String> login(@RequestBody NewLogin newLogin) {

		String response = authService.login(modelMapper.map(newLogin, LoginDTO.class));

		return ResponseEntity.ok(response);
	}

	//Register REST API
	@PostMapping(value = {"/register", "/signup"})
	public  ResponseEntity<String> register(@RequestBody NewRegister newRegister) {
		String response = authService.register(modelMapper.map(newRegister, RegisterDTO.class));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
