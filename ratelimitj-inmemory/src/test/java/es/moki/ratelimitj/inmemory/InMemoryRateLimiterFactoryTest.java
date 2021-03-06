package es.moki.ratelimitj.inmemory;


import com.google.common.collect.ImmutableSet;
import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryRateLimiterFactoryTest {

    private InMemoryRateLimiterFactory factory = new InMemoryRateLimiterFactory();

    @Test
    public void shouldReturnTheSameInstanceForSameRules() {

        RequestLimitRule rule1 = RequestLimitRule.of(1, TimeUnit.MINUTES, 10);
        RequestRateLimiter rateLimiter1 = factory.getInstance(ImmutableSet.of(rule1));

        RequestLimitRule rule2 = RequestLimitRule.of(1, TimeUnit.MINUTES, 10);
        RequestRateLimiter rateLimiter2 = factory.getInstance(ImmutableSet.of(rule2));

        assertThat(rateLimiter1).isSameAs(rateLimiter2);
    }

    @Test
    public void shouldReturnTheSameInstanceForSameSetOfRules() {

        RequestLimitRule rule1a = RequestLimitRule.of(1, TimeUnit.MINUTES, 10);
        RequestLimitRule rule1b = RequestLimitRule.of(1, TimeUnit.HOURS, 100);
        RequestRateLimiter rateLimiter1 = factory.getInstance(ImmutableSet.of(rule1a, rule1b));

        RequestLimitRule rule2a = RequestLimitRule.of(1, TimeUnit.MINUTES, 10);
        RequestLimitRule rule2b = RequestLimitRule.of(1, TimeUnit.HOURS, 100);
        RequestRateLimiter rateLimiter2 = factory.getInstance(ImmutableSet.of(rule2a, rule2b));

        assertThat(rateLimiter1).isSameAs(rateLimiter2);
    }

    @Test
    public void shouldNotReturnTheSameInstanceForSameRules() {

        RequestLimitRule rule1 = RequestLimitRule.of(1, TimeUnit.MINUTES, 22);
        RequestRateLimiter rateLimiter1 = factory.getInstance(ImmutableSet.of(rule1));

        RequestLimitRule rule2 = RequestLimitRule.of(1, TimeUnit.MINUTES, 33);
        RequestRateLimiter rateLimiter2 = factory.getInstance(ImmutableSet.of(rule2));

        assertThat(rateLimiter1).isNotSameAs(rateLimiter2);
    }
}