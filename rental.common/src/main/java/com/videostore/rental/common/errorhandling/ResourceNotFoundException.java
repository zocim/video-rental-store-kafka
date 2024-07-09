/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.common.errorhandling;

/**
 * @author Created by Maneva.
 * @since 6.7.24.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
