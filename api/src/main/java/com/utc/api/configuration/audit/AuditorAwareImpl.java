package com.utc.api.configuration.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String userId = "SYSTEM";

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        }

        return Optional.of(userId);
    }
}
