package mps.commisioner.dao;

import mps.commisioner.dto.ArrestHistoryDto;

import java.util.Optional;

public interface ArrestDao {

    Long submitRequestForArresting(ArrestHistoryDto arrestHistoryDto);

    Optional<ArrestHistoryDto> findArrestHistoryByArrestHistoryId(Long arrestHistoryId);
}
