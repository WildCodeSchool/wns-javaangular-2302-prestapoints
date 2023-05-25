package fr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.repository.EvaluationRepository;

@Service
public class EvaluationService {

    @Autowired
    EvaluationRepository evaluationRepository;

    public void deleteEvaluation(Integer id) {
        evaluationRepository.deleteById(id);
    }
}
