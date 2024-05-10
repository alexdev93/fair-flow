package com.safaricom.fairflowtransactionsmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/history")
public class HistoryController {

    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryController(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @GetMapping
    public ResponseEntity<List<History>> getAllHistory() {
        return new ResponseEntity<>(historyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{historyId}")
    public ResponseEntity<History> getHistoryById(Long historyId) {

        return new ResponseEntity<>(historyRepository.findById(historyId).orElse(null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<History> createHistory(@RequestBody History history) {
        return new ResponseEntity<>(historyRepository.save(history), HttpStatus.CREATED);
    }
}
