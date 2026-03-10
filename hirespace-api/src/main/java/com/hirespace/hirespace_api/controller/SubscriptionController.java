package com.hirespace.hirespace_api.controller;

import com.hirespace.hirespace_api.model.Subscription;
import com.hirespace.hirespace_api.repository.SubscriptionRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@CrossOrigin(origins = "*")
public class SubscriptionController {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionController(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @PostMapping
    public Subscription createSubscription(@RequestBody Subscription subscription) {
        // set start date to today if not provided
        if (subscription.getStartDate() == null) {
            subscription.setStartDate(LocalDate.now());
        }
        // optionally calculate endDate based on plan
        if (subscription.getEndDate() == null) {
            if ("annual".equalsIgnoreCase(subscription.getPlan())) {
                subscription.setEndDate(subscription.getStartDate().plusYears(1));
            } else if ("monthly".equalsIgnoreCase(subscription.getPlan())) {
                subscription.setEndDate(subscription.getStartDate().plusMonths(1));
            }
        }
        return subscriptionRepository.save(subscription);
    }

    @GetMapping("/user/{userId}")
    public List<Subscription> getByUser(@PathVariable Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }
}
