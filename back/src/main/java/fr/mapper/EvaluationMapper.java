package fr.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.dto.EvaluationDto;
import fr.entity.Evaluation;

@Component
public class EvaluationMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public EvaluationDto convertToDto(Evaluation evaluation) {
        EvaluationDto evaluationDto = modelMapper.map(evaluation, EvaluationDto.class);

        return evaluationDto;
    }

    public Evaluation convertToEntity(EvaluationDto evaluationDto) {
        Evaluation evaluation = modelMapper.map(evaluationDto, Evaluation.class);

        return evaluation;
    }

    public Iterable<EvaluationDto> convertAllToDto(List<Evaluation> evaluations) {
        List<EvaluationDto> evaluationDto = new ArrayList<>();

        for (Evaluation evaluation : evaluations) {
            evaluationDto.add(modelMapper.map(evaluation, EvaluationDto.class));
        }

        return evaluationDto;
    }
}
