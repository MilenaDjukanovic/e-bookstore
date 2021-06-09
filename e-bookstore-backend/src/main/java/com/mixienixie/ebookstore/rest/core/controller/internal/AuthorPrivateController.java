package com.mixienixie.ebookstore.rest.core.controller.internal;

import com.mixienixie.ebookstore.core.requests.CreateAuthorRequest;
import com.mixienixie.ebookstore.repo.core.entity.AuthorDto;
import com.mixienixie.ebookstore.security.authorizations.IsPublishingHouseRepresentative;
import com.mixienixie.ebookstore.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Private API Endpoint for Author
 * @author ndjordjieski
 */
@RestController @RequestMapping("api/private/author")
@IsPublishingHouseRepresentative()
@AllArgsConstructor
public class AuthorPrivateController{

    /** Author Service */
    private final AuthorService authorService;

    @PostMapping()
    public AuthorDto create(@RequestBody @Valid CreateAuthorRequest createAuthorRequest){
        return this.authorService.create(createAuthorRequest);
    }
}
