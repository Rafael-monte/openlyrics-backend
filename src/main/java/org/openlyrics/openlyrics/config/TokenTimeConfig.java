package org.openlyrics.openlyrics.config;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class TokenTimeConfig {
    @Value("${config.webtoken.timezone}")
    private String timeZoneId;

    private Instant startTime;

    private Instant endTime;

    public TokenTimeConfig() {
        this.startTime = ZonedDateTime.now(ZoneId.of(timeZoneId)).toInstant();
        this.endTime = ZonedDateTime.now(ZoneId.of(timeZoneId)).plusHours(1).toInstant();
    }
}
