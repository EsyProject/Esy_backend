package apiBoschEsy.apiInSpringBoot.service.utils;

import apiBoschEsy.apiInSpringBoot.entity.Assessment;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.EventNotFoundException;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryAssessment;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class HighPointsIntegerHandler {
    @Autowired
    private IRepositoryAssessment repositoryAssessment;
    @Autowired
    private IRepositoryEvent repositoryEvent;
    public Integer returnHigh(@PathVariable Long event_id, String highPoints) throws EventNotFoundException {
        // Access the vent
        var event = repositoryEvent.findById(event_id).orElseThrow(() -> new EventNotFoundException("Event Not found with ID: " + event_id));
        var listAsssessmentBasedInThisEvent = event.getAssessments();

        // Logic
        int count = 0;
        for(Assessment assessment : listAsssessmentBasedInThisEvent){
            if(assessment != null && assessment.getHighlightPoint() != null && highPoints.equals(assessment.getHighlightPoint().getValue())){
                count ++;
            }
        }
        return count;
    }
}
