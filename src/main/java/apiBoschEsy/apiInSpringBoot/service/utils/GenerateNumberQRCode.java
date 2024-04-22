package apiBoschEsy.apiInSpringBoot.service.utils;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GenerateNumberQRCode {
     // Method for generate random number
    public String generateRandomNumbers(int length) {
        Random random = new Random();
        Set<Integer> generatedNumbers = new HashSet<>(); // Empty numbers
        StringBuilder sb = new StringBuilder();

        while (generatedNumbers.size() < length) {
            int randomNumber = random.nextInt(10);

            if (!generatedNumbers.contains(randomNumber)) {
                generatedNumbers.add(randomNumber);
                sb.append(randomNumber);
            }
        }
        return sb.toString();
    }
}

