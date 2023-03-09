package com.code.glancer.interview.scheduler.service;

import com.code.glancer.interview.scheduler.dto.CalenderDto;
import com.code.glancer.interview.scheduler.dto.EmailDto;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    void sendEmail(EmailDto emailDto);
    void sendCalenderInvite(CalenderDto calenderDto) throws IOException, MessagingException;
}
