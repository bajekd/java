package com.aplikacje_serwerowe;

import com.aplikacje_serwerowe.voting.entities.Poll;
import com.aplikacje_serwerowe.voting.repositories.PollRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AplikacjeSerweroweApplication implements CommandLineRunner {
    
    @Autowired
    PollRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(AplikacjeSerweroweApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        String pollOwner = "Bajek";
        String pollName = "Test_1";
        Map<String, Integer> pollOptions = new HashMap<String, Integer>();
        pollOptions.put("Java", 1);
        pollOptions.put("Ruby", 3);
        pollOptions.put("Python", 15);
        
        Poll poll = new Poll(pollName, pollOwner, pollOptions);
        
        repository.save(poll);
        System.out.println(poll.toString());
    }

}
