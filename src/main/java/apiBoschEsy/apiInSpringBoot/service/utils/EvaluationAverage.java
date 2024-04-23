package apiBoschEsy.apiInSpringBoot.service.utils;

import apiBoschEsy.apiInSpringBoot.entity.Assessment;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryAssessment;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class EvaluationAverage {
    // Access database
    @Autowired
    private IRepositoryAssessment repositoryAssessment;
    @Autowired
    private IRepositoryEvent repositoryEvent;

    public double avarageOfAssessmentEvent(Long event_id){
        // Access the event by ID
        var event = repositoryEvent.findById(event_id);
        // Access the assessmentList based in Event (event_id)
        var assessmentsList = event.get().getAssessments();

        List<Double> assessmentsValue = new ArrayList<>();

        // Validation isPresent assessment
        if (assessmentsList.isEmpty()) {
            return 0.0;
        }

        // For each in List
        for (Assessment assessment : assessmentsList){
            double value= assessment.getAssessment();
            assessmentsValue.add(value);
        }

        // Creating an Avarage
        double number = 0.0;
        for (double value : assessmentsValue){
            number += value;
        }
        double average = number / assessmentsValue.size();
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(average));
    }
}
