package apiBoschEsy.apiInSpringBoot.constants;

import jakarta.persistence.Embeddable;


public enum Place {
    CA590("Ca590"),
    CA560("Ca560"),
    CA530("Ca530"),
    CA540("Ca540"),
    CA536("Ca536"),
    CA550("Ca550"),
    CA551("Ca551"),
    CA129("Ca129"),
    CA390("Ca390"),
    CA600("Ca600"),
    CA400("Ca400"),
    CA401("Ca401"),
    CA350("Ca350"),
    CA370("Ca370"),
    CA204("Ca204"),
    CA200("Ca200"),
    CA360("Ca360"),
    CA340("Ca340"),
    CA205("Ca205"),
    CA220("Ca220"),
    CA320("Ca320"),
    CA300("Ca300"),
    CA325("Ca325"),
    CA169("Ca169"),
    CA165("Ca165"),
    CA341("Ca341"),
    CA126("Ca126"),
    CA147("Ca147"),
    CA148("Ca148"),
    CA149("Ca149"),
    CA140("Ca140"),
    CA160("Ca160"),
    CA170("Ca170"),
    CA150("Ca150"),
    CA151("Ca151"),
    CA100("Ca100"),
    CA180("Ca180"),
    CA183("Ca183");

    // Attributes
    private String value;

    // Creating a constructor
    Place(String value){
        this.value = value;
    }

    // Get the attributes
    public String getValue() {
        return value;
    }
}
