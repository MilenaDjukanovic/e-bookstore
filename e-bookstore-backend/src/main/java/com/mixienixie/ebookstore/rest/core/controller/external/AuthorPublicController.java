package com.mixienixie.ebookstore.rest.core.controller.external;

import com.mixienixie.ebookstore.repo.core.entity.AuthorDto;
import com.mixienixie.ebookstore.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Public API Endpoint for Authors
 * @author ndjordjieski
 */
@RestController @RequestMapping("api/public/authors")
@AllArgsConstructor
public class AuthorPublicController{

    /** Author Service */
    private final AuthorService authorService;

    @GetMapping()
    public Page<AuthorDto> findAll(Pageable pageable) {
        return this.authorService.findAll(pageable);
    }

    @GetMapping("all")
    public Page<AuthorDto> findAllNoLimit(){
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        return this.authorService.findAll(pageable);
    }
}
