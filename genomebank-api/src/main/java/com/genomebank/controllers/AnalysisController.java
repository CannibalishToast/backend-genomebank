package com.genomebank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis")
@RequiredArgsConstructor
public class AnalysisController {
    private final AnalysisService analysisService;

    // GET /analysis/genes?chromosomeId=&start=&end=
    @GetMapping("/genes")
    public ResponseEntity<List<GeneAnalysisOutDTO>> getGenesInRange(
            @RequestParam Long chromosomeId,
            @RequestParam Long start,
            @RequestParam Long end
    ) {
        return ResponseEntity.ok(analysisService.getGenesInRange(chromosomeId, start, end));
    }

    // GET /analysis/sequence/stats?chromosomeId=
    @GetMapping("/sequence/stats")
    public ResponseEntity<SequenceStatsOutDTO> getSequenceStats(@RequestParam Long chromosomeId) {
        return ResponseEntity.ok(analysisService.getSequenceStats(chromosomeId));
    }
}
