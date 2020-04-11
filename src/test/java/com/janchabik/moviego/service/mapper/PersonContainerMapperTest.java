package com.janchabik.moviego.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonContainerMapperTest {

    private PersonContainerMapper personContainerMapper;

    @BeforeEach
    public void setUp() {
        personContainerMapper = new PersonContainerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(personContainerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(personContainerMapper.fromId(null)).isNull();
    }
}
