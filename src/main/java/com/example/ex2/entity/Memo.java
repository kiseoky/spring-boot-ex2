package com.example.ex2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "memo")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String text;
}
