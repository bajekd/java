/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplikacje_serwerowe.voting.repositories;

import com.aplikacje_serwerowe.voting.entities.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author bajek
 */
public interface PollRepository extends MongoRepository<Poll, String> {   
}
