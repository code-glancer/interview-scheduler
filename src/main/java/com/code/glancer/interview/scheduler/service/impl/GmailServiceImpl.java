package com.code.glancer.interview.scheduler.service.impl;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import biweekly.property.Method;
import biweekly.util.Duration;
import com.code.glancer.interview.scheduler.dto.CalenderDto;
import com.code.glancer.interview.scheduler.dto.EmailDto;
import com.code.glancer.interview.scheduler.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(EmailDto emailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        if (!CollectionUtils.isEmpty(emailDto.getToList())) {
            String[] toList = emailDto.getToList().toArray(new String[0]);
            simpleMailMessage.setTo(toList);
        } else {
            simpleMailMessage.setTo(emailDto.getTo());
        }
        simpleMailMessage.setSubject(emailDto.getSubject());
        simpleMailMessage.setText(emailDto.getMessage());
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendCalenderInvite(CalenderDto calenderDto) throws IOException, MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setRecipients(Message.RecipientType.TO, getToAddress(calenderDto.getAttendees()));
        mimeMessage.setSubject(calenderDto.getSubject());

        MimeMultipart mimeMultipart = new MimeMultipart("mixed");

        mimeMultipart.addBodyPart(getAttachmentMimeBodyPart());
        mimeMultipart.addBodyPart(createCalenderMimeBody(calenderDto));

        mimeMessage.setContent(mimeMultipart);
        javaMailSender.send(mimeMessage);

    }

    private MimeBodyPart getAttachmentMimeBodyPart() throws IOException, MessagingException {
        File file = new File("src/main/resources/skd-profile.pdf");
        MimeBodyPart attachmentMineBodyPart = new MimeBodyPart();
        attachmentMineBodyPart.attachFile(file);
        return attachmentMineBodyPart;
    }

    private Address[] getToAddress(List<Attendee> attendees) {
        return attendees.stream().map(attendee -> {
            Address address = null;
            try {
                address = new InternetAddress(attendee.getEmail());
            } catch (AddressException e) {
                e.printStackTrace();
            }
            return address;
        }).collect(Collectors.toList()).toArray(new InternetAddress[0]);
    }

    private BodyPart createCalenderMimeBody(CalenderDto calenderDto) throws IOException, MessagingException {
        MimeBodyPart calenderBody = new MimeBodyPart();

        final DataSource source = new ByteArrayDataSource(createCal(calenderDto), "text/calender; charset=UTF-8");
        calenderBody.setDataHandler(new DataHandler(source));
        calenderBody.setHeader("Content-Type", "text/calendar; charset=UTF-8; method=REQUEST");

        return calenderBody;
    }

    private String createCal(CalenderDto calenderDto) {
        ICalendar ical = new ICalendar();
        ical.addProperty(new Method(Method.REQUEST));
        ical.setUrl(calenderDto.getMeetingLink());

        VEvent event = new VEvent();
        event.setUrl(calenderDto.getMeetingLink());
        event.setSummary(calenderDto.getSummary());
        event.setDescription(calenderDto.getDescription());
        event.setDateStart(getStartDate(calenderDto.getEventDateTime()));
        event.setDuration(new Duration.Builder()
                .hours(1)
                .build());
        event.setOrganizer(calenderDto.getOrganizer());
        addAttendees(event, calenderDto.getAttendees());
        ical.addEvent(event);
        return Biweekly.write(ical).go();
    }

    private void addAttendees(VEvent event, List<Attendee> attendees) {
        for (Attendee attendee : attendees) {
            event.addAttendee(attendee);
        }
    }

    private Date getStartDate(LocalDateTime eventDateTime) {
        Instant instant = eventDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
}
