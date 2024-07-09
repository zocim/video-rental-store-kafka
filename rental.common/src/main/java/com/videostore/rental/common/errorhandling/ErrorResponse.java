/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.common.errorhandling;

import java.time.LocalDateTime;

/**
 * @author Created by Maneva.
 * @since 6.7.24.
 */

public record ErrorResponse(LocalDateTime timestamp, String message, String details) {
}
