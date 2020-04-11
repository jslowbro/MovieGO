package com.janchabik.moviego.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FilmMapperTest {

    private FilmMapper filmMapper;

    @BeforeEach
    public void setUp() {
        filmMapper = new FilmMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(filmMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(filmMapper.fromId(null)).isNull();
    }
}
