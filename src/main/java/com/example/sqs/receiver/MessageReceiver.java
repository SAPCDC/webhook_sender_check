package com.example.sqs.receiver;
//import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Service
@Slf4j
@RestController

@RequestMapping(value="/api/webhook",method={ RequestMethod.GET })
public class MessageReceiver
{
    @Value("${cloud.aws.end-point.uri}")
    private String endpoint;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;
    
    @PostMapping
	public ResponseEntity<String> print(@RequestBody String requestBody) throws IOException
	{

        Message payload = MessageBuilder.withPayload(requestBody)
               .build();
               queueMessagingTemplate.send(endpoint, payload);

		try (FileWriter fileWriter = new FileWriter("log")) {
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print("Some String");
			printWriter.printf("Product name is %s",requestBody);
			printWriter.close();
		return new ResponseEntity<String>(requestBody,HttpStatus.OK);
	}
}
}


    
    