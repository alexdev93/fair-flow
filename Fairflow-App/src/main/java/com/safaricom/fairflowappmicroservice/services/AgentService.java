package com.safaricom.fairflowappmicroservice.services;

import com.safaricom.fairflowappmicroservice.dtos.Agent.AgentRequestDto;
import com.safaricom.fairflowappmicroservice.exceptions.NotFoundException;
import com.safaricom.fairflowappmicroservice.models.Agent;
import com.safaricom.fairflowappmicroservice.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

    @Autowired
    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent saveAgent(AgentRequestDto agentDto) {
        return agentRepository.save(new Agent(agentDto));
    }

    public Agent getAgent(Long agentId) {
        return agentRepository.findById(agentId).
                orElseThrow(() -> new NotFoundException("Agent not found"));
    }

    public void deleteAgent(Long agentId) {
        agentRepository.deleteById(agentId);
    }

    public Agent updateAgent(Long agentId, AgentRequestDto agentDto) {
        Agent agent = new Agent(agentDto);
        agent.setId(agentId);
        return agentRepository.save(agent);
    }

    public List<Agent> listAllAgents() {
        return agentRepository.findAll();
    }
}
