package mps.commisioner.service;

import mps.commisioner.dto.ArrestHistoryDto;

public interface ArrestService {

    Long submitRequestForArresting(ArrestHistoryDto arrestHistoryDto,String token);

    ArrestHistoryDto findArrestHistoryByArrestHistoryId(Long arrestHistoryId);

}
