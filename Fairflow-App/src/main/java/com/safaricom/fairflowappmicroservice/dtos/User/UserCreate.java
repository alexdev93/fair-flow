package com.safaricom.fairflowappmicroservice.dtos.User;

import com.safaricom.fairflowappmicroservice.dtos.Agent.AgentRequestDto;
import com.safaricom.fairflowappmicroservice.dtos.Representative.RepresentativeRequestDto;
import lombok.Data;

import java.util.List;

@Data
public class UserCreate {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private List<String> roleNames;

    public UserCreate(AgentRequestDto agentDto){
        email = (agentDto.getEmail());
        password = (agentDto.getPassword());
        firstName = (agentDto.getFirstName());
        lastName = (agentDto.getLastName());
        userName = (agentDto.getPhoneNumber());
        roleNames = (List.of("AGENT"));
    }

    public UserCreate(RepresentativeRequestDto representativeDto){
        email = (representativeDto.getEmail());
        password = (representativeDto.getPassword());
        firstName = (representativeDto.getFirstName());
        lastName = (representativeDto.getLastName());
        userName = (representativeDto.getPhoneNumber());
        roleNames = (List.of("REPRESENTATIVE"));
    }
}
