package com.janchabik.moviego.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.janchabik.moviego.web.rest.TestUtil;

public class FilmTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Film.class);
        Film film1 = new Film();
        film1.setId(1L);
        Film film2 = new Film();
        film2.setId(film1.getId());
        assertThat(film1).isEqualTo(film2);
        film2.setId(2L);
        assertThat(film1).isNotEqualTo(film2);
        film1.setId(null);
        assertThat(film1).isNotEqualTo(film2);
    }
}
