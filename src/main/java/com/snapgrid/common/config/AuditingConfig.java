package com.snapgrid.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditingConfig implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {

        /**
         *  TODO BaseEntity를 extends 하려면 작성 해야함
         *  MemberID 가져오는 로직 작성
         */

        return Optional.empty();
    }
}
