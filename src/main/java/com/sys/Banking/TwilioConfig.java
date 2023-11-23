package com.sys.Banking;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;

@Configuration
public class TwilioConfig {
	@Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Bean
    public TwilioRestClient twilioRestClient() {
    	Twilio.init(accountSid, authToken);
        return new TwilioRestClient.Builder(accountSid, authToken).build();
    }

}
