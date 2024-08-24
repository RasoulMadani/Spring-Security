package com.security.springSecurity.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
    private boolean expired;
    /**
     * آیا توکن اعتبار دارد ؟ ممکن است ادمین توکن ار غیرفعال کند
     */
    private boolean revoked;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationAt;

    @ManyToOne
    private User user;
}
