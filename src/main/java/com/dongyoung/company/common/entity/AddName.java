package com.dongyoung.company.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AddName {

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

}
