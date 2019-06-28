package com.custom.sean.common.config.jpa;

import com.custom.sean.common.domain.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Nonnull;
import java.util.Optional;


/**
 * @author seangogo
 */
public class AuditorAwareImpl implements AuditorAware<String> {
    @Nonnull
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        return Optional.of( authentication.getPrincipal().toString());
    }
}