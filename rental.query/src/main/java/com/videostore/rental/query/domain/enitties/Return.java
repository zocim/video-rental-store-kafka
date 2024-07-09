/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.domain.enitties;

import com.videostore.cqrs.core.domain.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Created by Maneva.
 * @since 2.7.24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "rental_return")
public class Return extends BaseEntity {
    @Id
    private String returnId;
    private int daysLate;
    private int lateFee;
    @OneToOne
    @JoinColumn(name = "rental_id", nullable = false, referencedColumnName = "id")
    private Rental rental;
}
