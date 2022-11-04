package com.realestate.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.realestate.domain.enums.TourRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tour_requests")
public class TourRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM/dd/yyyy HH:mm:ss", timezone = "Turkey")
    @NotNull(message = "Please enter the pick up time of the tour")
    @Column(nullable = false)
    private LocalDateTime tourRequestTime;

    @NotNull(message = "please enter adult")
    @Column(nullable = false)
    private String adult;

    @NotNull(message = "please enter adult")
    @Column(nullable = false)
    private  String child;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    @NotNull(message = "Please enter the TourRequest status")
    private TourRequestStatus status;

}