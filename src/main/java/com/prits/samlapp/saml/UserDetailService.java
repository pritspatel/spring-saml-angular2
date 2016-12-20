package com.prits.samlapp.saml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class UserDetailService implements SAMLUserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailService.class);
	@Override
	public Object loadUserBySAML(SAMLCredential credential) throws UsernameNotFoundException {
		logger.info("LOADING USER BASED ON SAML AUTHENTICATION OBJECT......{}", credential.toString());
		User u = new User();
		u.setUsername(credential.getAttributeAsString("EmailAddress"));
		u.setFirstName(credential.getAttributeAsString("FirstName"));
		u.setLastName(credential.getAttributeAsString("LastName"));
		
		//TODO You can do lookup in db based on some unique identified came from SAML Response and then prepare the user object with its
		//granted authorities so that later on it can be used either by spring security or you to grant certain access to part of your app
		logger.info(u.toString());
		return u;
	}

}
