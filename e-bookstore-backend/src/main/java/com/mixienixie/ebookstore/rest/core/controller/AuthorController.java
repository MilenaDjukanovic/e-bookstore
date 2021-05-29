package com.mixienixie.ebookstore.rest.core.controller;

import com.mixienixie.ebookstore.core.requests.CreateAuthorRequest;
import com.mixienixie.ebookstore.repo.core.entity.AuthorDto;
import com.mixienixie.ebookstore.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("api/public")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("authors")
    public Page<AuthorDto> findAll(Pageable pageable) {
        return this.authorService.findAll(pageable);
    }

    @PostMapping("authors")
    public AuthorDto create(@RequestBody @Valid CreateAuthorRequest createAuthorRequest){
        return this.authorService.create(createAuthorRequest);
    }
}
