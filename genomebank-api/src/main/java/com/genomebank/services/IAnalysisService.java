package com.genomebank.services;

import com.genomebank.dto.response.GeneAnalysisOutDTO;
import com.genomebank.dto.response.SequenceStatsOutDTO;
import java.util.List;

public interface IAnalysisService {

    List<GeneAnalysisOutDTO> getGenesInRange(Long chromosomeId, Long start, Long end);

    SequenceStatsOutDTO getSequenceStats(Long chromosomeId);
}
