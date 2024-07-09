/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.domain.enitties;

import com.videostore.rental.common.enums.FilmType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author Created by Maneva.
 * @since 5.7.24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Film {
    @Id
    private Long id;
    @NonNull
    private String title;
    @NonNull
    @Enumerated(EnumType.STRING)
    private FilmType filmType;
}
