package apiBoschEsy.apiInSpringBoot.constants;

public enum HighlightPoint {
    TOPICS_ADDRESSED("Temáticas abordadas"),
    FOOD("Alimentação"),
    PUNCTUALITY("Pontualidade"),
    SOCIAL_INTERACTIONS("Interações sociais");

    // Attributes
    private String value;

    // Creating a constructor for use value (Return other value in FrontEnd)
    HighlightPoint(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
