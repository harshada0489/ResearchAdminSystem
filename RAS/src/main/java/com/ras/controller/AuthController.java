package com.ras.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ras.model.ERole;
import com.ras.model.LoginHistory;
import com.ras.model.Role;
import com.ras.model.User;
import com.ras.model.payload.request.LoginRequest;
import com.ras.model.payload.request.SignupRequest;
import com.ras.model.payload.response.JwtResponse;
import com.ras.model.payload.response.MessageResponse;
import com.ras.repository.RoleRepository;
import com.ras.repository.UserRepository;
import com.ras.security.jwt.JwtUtils;
import com.ras.service.LoginHistoryService;
import com.ras.service.UserDetailsImpl;
import com.ras.service.mongodbOperations.NextSequenceService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	NextSequenceService nextSequenceService;
	
	@Autowired
	LoginHistoryService loginHistoryService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest , HttpServletRequest request) {
		System.out.println("For sign in authentication: class AuthController and method: authenticateUser()");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		LoginHistory loginHistory = null;
		if(authentication.isAuthenticated()) {
			Integer userId= userDetails.getId();
			String ipAddress = request.getRemoteAddr();
			Date loginTime = new Date();
			loginHistoryService.insertLoginDetails(userId, ipAddress, loginTime);
		
			
//			System.out.println("Inside class SystemFormResource and method getLastLoggedInUser(),  userId=" + userId);
			HashMap<String,Object> hmap = new HashMap <>();
			
			loginHistory = loginHistoryService.getLastLoggedIn(userId);
			if(loginHistory.getLoginTime() != null) {
				hmap.put("lastLogin", loginHistory.getLoginTime());	
			}else {
				hmap.put("lastLogin", "logged in for 1st time");	
			}
			
			
		}
		
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId()+"", 
												 userDetails.getUsername(), 
												 userDetails.getEmail(),
												 loginHistory.getLoginTime(),
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		System.out.println("Inside  registerUser = "+ signUpRequest.toString()  );
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getFirstName(),
							 signUpRequest.getLastName(),
							 signUpRequest.getInstitute(),
							 signUpRequest.getDepartment());

		String strRoles = signUpRequest.getRoles();
		System.out.println("strRoles = "+ strRoles);
		
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
//			strRoles.forEach(role -> {
				switch (strRoles) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "reviewer":
					Role reviewRole = roleRepository.findByName(ERole.ROLE_REVIEWER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(reviewRole);

					break;
				case "researcher":
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);

					break;
				default:
					Role defaultUser = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(defaultUser);
				}
//			});
		}

		user.setRoles(roles);
		
		
		int seq = nextSequenceService.getNextSequenceForuserId("customSequences");
		System.out.println("user Id generated = " + seq);
		user.setId(seq);

		
		
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	  
	
}