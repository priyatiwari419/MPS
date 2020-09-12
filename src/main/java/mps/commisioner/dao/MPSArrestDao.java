package mps.commisioner.dao;

import lombok.AllArgsConstructor;
import mps.commisioner.dto.ArrestHistoryDto;
import mps.commisioner.entity.ArrestHistoryEntity;
import mps.commisioner.repository.ArrestHistoryRepository;
import mps.members.translator.BaseObjectTranslator;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class MPSArrestDao implements ArrestDao{

    private final ArrestHistoryRepository arrestHistoryRepository;
    private final BaseObjectTranslator baseObjectTranslator;

    @Override
    public Long submitRequestForArresting(ArrestHistoryDto arrestHistoryDto) {
         ArrestHistoryEntity arrestHistoryEntity = arrestHistoryRepository.save(baseObjectTranslator.map(arrestHistoryDto, ArrestHistoryEntity.class));
       return arrestHistoryEntity.getId();
    }

    @Override
    public Optional<ArrestHistoryDto> findArrestHistoryByArrestHistoryId(Long arrestHistoryId) {
        Optional<ArrestHistoryEntity> arrestHistoryEntity = arrestHistoryRepository.findById(arrestHistoryId);
        return arrestHistoryEntity.map(arrestHistoryEntity1 -> {
          return baseObjectTranslator.map(arrestHistoryEntity1,ArrestHistoryDto.class);
        });
    }
}
