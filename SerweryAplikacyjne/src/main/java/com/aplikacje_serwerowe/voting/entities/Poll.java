/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplikacje_serwerowe.voting.entities;

import java.util.Map;
import org.springframework.data.annotation.Id;

/**
 *
 * @author bajek
 */

public class Poll {
    @Id
    public String Id;
    
    public String pollName, pollOwner;
    public Map<String, Integer> pollOptions;
    
    public Poll(String pollName, String pollOwner, Map<String, Integer> pollOptions) {
        this.pollName = pollName;
        this.pollOwner = pollOwner;
        this.pollOptions = pollOptions; 
    }
    
    @Override
    public String toString() {
        return "Poll [id=" + Id + ", pollName=" + pollName + ", pollOwner=" + pollOwner +
                ", pollOptons=" + pollOptions + "]";
    }
}
