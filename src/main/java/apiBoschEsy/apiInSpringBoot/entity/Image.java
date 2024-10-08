package apiBoschEsy.apiInSpringBoot.entity;

import apiBoschEsy.apiInSpringBoot.dto.image.ImageCreateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = true)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = true)
    private Ticket ticket;

    public Image(String imageUrl, Event product) {
        this.imgUrl = imageUrl;
        this.event = product;
    }
    public Image(String imageUrl, Ticket ticket){
        this.imgUrl = imageUrl;
        this.ticket = ticket;
    }
}