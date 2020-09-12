package mps.members.translator;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BaseObjectTranslator {

    protected ModelMapper modelMapper;

    public BaseObjectTranslator(@Qualifier("modelMapper") ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T, R> R map(T entity, Class<R> targetType) {
        return modelMapper.map(entity, targetType);
    }

    public <T, R> List<R> sourceListToTargetList(List<T> entities, Class<R> targetType) {
        return entities.stream().map(source -> map(source, targetType)).collect(Collectors.toList());
    }
}