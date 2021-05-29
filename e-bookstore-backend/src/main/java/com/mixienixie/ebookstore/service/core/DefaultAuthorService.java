package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateAuthorRequest;
import com.mixienixie.ebookstore.repo.core.AuthorRepository;
import com.mixienixie.ebookstore.repo.core.entity.AuthorDto;
import com.mixienixie.ebookstore.repo.core.entity.AuthorEntity;
import com.mixienixie.ebookstore.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 *
 * Default implementation of the Author Service
 * @author mdjukanovic
 */
@Service
@AllArgsConstructor
public class DefaultAuthorService implements AuthorService {

    /** Author Repository */
    private final AuthorRepository authorRepository;

    /** Author View Mapper */
    private final AuthorCreateMapper authorCreateMapper;

    /** Author Create Mapper */
    private final AuthorViewMapper authorViewMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorDto create(CreateAuthorRequest createAuthorRequest) {
        AuthorEntity authorEntity = this.authorCreateMapper.toEntity(createAuthorRequest);

        authorEntity = this.authorRepository.save(authorEntity);

        return this.authorViewMapper.toDto(authorEntity);
    }

    @Override
    public Page<AuthorDto> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable);
        
        return this.authorRepository.findAll(pageable).map(this.authorViewMapper::toDto);
    }
}
