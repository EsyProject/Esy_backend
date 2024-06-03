package apiBoschEsy.apiInSpringBoot.service.utils;

import org.springframework.stereotype.Service;

@Service
public class DashboardMath {

    // Method Average
    public Double getAverageAssessment(Integer sizeList, Double valueAssessment){
        return valueAssessment/sizeList;
    }

}
