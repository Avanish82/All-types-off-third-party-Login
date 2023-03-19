package com.oauth.implementation.service;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ClientCredentialParameters;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.microsoft.aad.msal4j.IConfidentialClientApplication;
import com.mysql.cj.util.StringUtils;
import com.oauth.implementation.model.SendMailUser;

@Service
public class SendMailService {
	
private JavaMailSender javaMailSender;

@Value("${spring.cloud.azure.active-directory.profile.tenant-id}") 
private String tenantId;
 
@Value("${spring.cloud.azure.active-directory.credential.client-id}") 
private String clientId;

@Value("${spring.cloud.azure.active-directory.credential.client-secret}") 
private String clientSecret;

@Value("${spring.cloud.azure.active-directory.credential.redirect-uri}") 
private String redirectURL;

@Value("${spring.cloud.azure.active-directory.scope}")
private String scope;

private String username="avanishas123_outlook.com#EXT#@avanishas123outlook.onmicrosoft.com";
//"avanishas123@outlook.com";
//# Specifies your App Registration's Application ID:
//spring.cloud.azure.active-directory.credential.client-id=08942e77-7f1c-4ee7-b0c8-33882039dcf1
//# Specifies your App Registration's secret key:
//spring.cloud.azure.active-directory.credential.client-secret=QAY8Q~TNvuHMO0pkS_x1lgrwBD0ilYNjVVG1-chc
//spring.cloud.azure.active-directory.credential.redirect-uri=https://localhost:9002/login/oauth2/code/
//spring.cloud.azure.active-directory.user-group.allowed-group-names=Group1

private IAuthenticationResult getToken() throws MalformedURLException {
    final IConfidentialClientApplication app = ConfidentialClientApplication.builder(
            this.clientId, 
            ClientCredentialFactory.createFromSecret(this.clientSecret))
        .authority(this.tenantId)
        .build();
    final ClientCredentialParameters parameters = ClientCredentialParameters.builder(
            Collections.singleton(this.scope))
        .build();
    return app
        .acquireToken(parameters)
        .join();
}

//  public void send(final MimeMessage message) throws MessagingException {
public String sendMailUser(SendMailUser sendMailUser) throws MessagingException {
   // if (StringUtils.isNoneBlank(this.clientId, this.clientSecret, this.scope, this.authority)) {
	 if(StringUtils.isEmptyOrWhitespaceOnly(this.clientSecret)) {
        try {
            final StringBuilder passwordBuilder = new StringBuilder();
            passwordBuilder.append("user=").append(this.username)
                    .append('\u0001')
                    .append("auth=").append("Bearer ").append(getToken().accessToken())
                    .append('\u0001').append('\u0001');

            final String base64Password = Base64.getEncoder().encodeToString(passwordBuilder.toString().getBytes(StandardCharsets.UTF_8));

          //  ((JavaMailSenderImpl) this.emailSender).setPassword(base64Password);
            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
//            mailMessage.setFrom(sender);
            mailMessage.setFrom("avanishas123@outlook.com");
            mailMessage.setTo(sendMailUser.getRecipient());
            mailMessage.setCc(sendMailUser.getCcList());
            mailMessage.setBcc(sendMailUser.getBccList());
            mailMessage.setText(sendMailUser.getMsgBody());
            mailMessage.setSubject(sendMailUser.getSubject());
            javaMailSender.send(mailMessage);
        } catch (final MalformedURLException e) {
           // throw new MessagingException("Impossible d'initialiser la connexion OAuth2", e);
        }
    }
   // String sendAzure =null;
	//this.emailSender.send(message);
//	emailSender.send(mailMessage);
	return "hi";
}
  
// Method 1
// To send a simple email
//public String sendMailUser(SendMailUser sendMailUser)
//{
//
//    // Try block to check for exceptions
//    try {
//        // Creating a simple mail message
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//        // Setting up necessary details
//       // mailMessage.setFrom(sender);
//        mailMessage.setTo(sendMailUser.getRecipient());
//        mailMessage.setCc(sendMailUser.getCcList());
//       // mailMessage.setBcc(sendMailUser.getBccList());
//        mailMessage.setText(sendMailUser.getMsgBody());
//        mailMessage.setSubject(sendMailUser.getSubject());
//        
//        // Sending the mail
//        javaMailSender.send(mailMessage);
//        sendMailUser.setEmailStatus("Send");
//      //  emailRepo.save(details);
//        return "Mail Sent Successfully...";
//    }
//
//   //  Catch block to handle the exceptions
//    catch (Exception e) {
//    	sendMailUser.setEmailStatus("Failed");
//      //  emailRepo.save(details);
//         
//        return "Error while Sending Mail";
//    }
//  }
}