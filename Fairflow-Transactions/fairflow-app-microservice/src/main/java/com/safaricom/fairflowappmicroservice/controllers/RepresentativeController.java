package com.safaricom.fairflowappmicroservice.controllers;

import com.safaricom.fairflowappmicroservice.dtos.Payment.PaymentRequestDto;
import com.safaricom.fairflowappmicroservice.dtos.Representative.RepresentativeRequestDto;
import com.safaricom.fairflowappmicroservice.dtos.Representative.RepresentativeResponseDto;
import com.safaricom.fairflowappmicroservice.services.RepresentativeService;
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
@Validated
@RequestMapping("/api/v1/representatives")
public class RepresentativeController {

    private final RepresentativeService representativeService;

    @Autowired
    public RepresentativeController(RepresentativeService representativeService) {
        this.representativeService = representativeService;
    }

    @GetMapping
    public ResponseEntity<List<RepresentativeResponseDto>> getRepresentatives() {
        return new ResponseEntity<>(representativeService.listAllRepresentatives().
                stream().
                map((representative) -> new RepresentativeResponseDto(representative)).
                collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{representativeId}")
    public ResponseEntity<RepresentativeResponseDto> getRepresentative(@PathVariable Long representativeId) {
        return new ResponseEntity<>(
                new RepresentativeResponseDto(representativeService.getRepresentative(representativeId)),
                HttpStatus.OK);
    }

    @PostMapping("/{representativeId}")
    public ResponseEntity<RepresentativeResponseDto> saveRepresentative(@Valid @RequestBody RepresentativeRequestDto representativeDto,
                                                                        @PathVariable Long representativeId) {
        return new ResponseEntity<>(
                new RepresentativeResponseDto(representativeService.saveRepresentative(representativeDto, representativeId)),
                HttpStatus.CREATED);
    }

    @PutMapping("/{representativeId}")
    public ResponseEntity<RepresentativeResponseDto> updateRepresentative(@PathVariable Long representativeId,
                                                                          @Valid @RequestBody RepresentativeRequestDto representativeDto) {
        return new ResponseEntity<>(
                new RepresentativeResponseDto(representativeService.updateRepresentative(representativeId, representativeDto)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{representativeId}")
    public ResponseEntity<Void> deleteRepresentative(@PathVariable Long representativeId) {
        representativeService.deleteRepresentative(representativeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transferPayment(@Valid @RequestBody PaymentRequestDto paymentDto){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
