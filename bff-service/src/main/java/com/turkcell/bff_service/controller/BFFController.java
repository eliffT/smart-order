package com.turkcell.bff_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import org.springframework.boot.autoconfigure.security.oauth2.server.servlet.OAuth2AuthorizationServerProperties.OidcEndpoint;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/auth")
public class BFFController {

    @GetMapping("me")
    public Mono<Map<String, Object>>  get(@AuthenticationPrincipal OidcUser user) {
        return Mono.just(
            Map.of
        )
    }

}
