package com.janchabik.moviego.config;

import io.github.jhipster.config.JHipsterProperties;
import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.janchabik.moviego.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.janchabik.moviego.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.janchabik.moviego.domain.User.class.getName());
            createCache(cm, com.janchabik.moviego.domain.Authority.class.getName());
            createCache(cm, com.janchabik.moviego.domain.User.class.getName() + ".authorities");
            createCache(cm, com.janchabik.moviego.domain.Person.class.getName());
            createCache(cm, com.janchabik.moviego.domain.PersonContainer.class.getName());
            createCache(cm, com.janchabik.moviego.domain.PersonContainer.class.getName() + ".films");
            createCache(cm, com.janchabik.moviego.domain.Comment.class.getName());
            createCache(cm, com.janchabik.moviego.domain.Review.class.getName());
            createCache(cm, com.janchabik.moviego.domain.Rating.class.getName());
            createCache(cm, com.janchabik.moviego.domain.Film.class.getName());
            createCache(cm, com.janchabik.moviego.domain.Film.class.getName() + ".personcontainers");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }
}
