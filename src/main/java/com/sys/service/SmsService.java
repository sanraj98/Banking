package com.sys.service;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService implements IsmsService {

    private final String twilioPhoneNumber = "+12542847924"; // Your Twilio phone number

    @Autowired
    private TwilioRestClient twilioRestClient;
    
    

    public void sendSms(String toPhoneNumber, String message) {
        try {
            MessageCreator creator = Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    message
            );
            creator.create();
        } catch (ApiException e) {
            // Handle exception
        }
    }
}
