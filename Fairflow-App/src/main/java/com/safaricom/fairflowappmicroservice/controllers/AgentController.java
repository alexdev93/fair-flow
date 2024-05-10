package com.safaricom.fairflowappmicroservice.controllers;

import com.safaricom.fairflowappmicroservice.dtos.Agent.AgentRequestDto;
import com.safaricom.fairflowappmicroservice.dtos.Agent.AgentResponseDto;
import com.safaricom.fairflowappmicroservice.services.AgentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/agents")
@Validated
public class AgentController {

    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public ResponseEntity<List<AgentResponseDto>> getAgents() {
        return new ResponseEntity<>(
                agentService.
                        listAllAgents().
                        stream().
                        map((agent) -> new AgentResponseDto(agent))
                        .collect(Collectors.toList())

                , HttpStatus.OK);
    }

    @GetMapping("/{agentId}")
    public ResponseEntity<AgentResponseDto> getAgent(@PathVariable Long agentId) {
        return new ResponseEntity<>(
                new AgentResponseDto(agentService.getAgent(agentId)),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AgentResponseDto> saveAgent(@Valid @RequestBody AgentRequestDto agentDto){
        return new ResponseEntity<>(
                new AgentResponseDto(agentService.saveAgent(agentDto)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{agentId}")
    public ResponseEntity<AgentResponseDto> modifyAgent(@PathVariable Long agentId,
                                                        @Valid @RequestBody AgentRequestDto agentDto) {
        return new ResponseEntity<>(
                new AgentResponseDto(agentService.updateAgent(agentId, agentDto)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{agentId}")
    public ResponseEntity<HttpStatus> deleteAgent(@PathVariable Long agentId) {
        agentService.deleteAgent(agentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
