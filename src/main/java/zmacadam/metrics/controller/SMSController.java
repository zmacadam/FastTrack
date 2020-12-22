package zmacadam.metrics.controller;

import com.twilio.twiml.TwiMLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zmacadam.metrics.service.SMSService;


@RestController
public class SMSController {

    @Autowired
    private SMSService smsService;

    private static Logger logger = LoggerFactory.getLogger(SMSController.class);

    @GetMapping(value = "/text")
    public String receiveToText(@RequestParam("From") String from,
                                        @RequestParam("Body") String body) {
        logger.info(from);
        logger.info(body);
        return smsService.parseText(from, body);
    }
}
