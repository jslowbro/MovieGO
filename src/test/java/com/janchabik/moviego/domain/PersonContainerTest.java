package com.janchabik.moviego.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.janchabik.moviego.web.rest.TestUtil;

public class PersonContainerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonContainer.class);
        PersonContainer personContainer1 = new PersonContainer();
        personContainer1.setId(1L);
        PersonContainer personContainer2 = new PersonContainer();
        personContainer2.setId(personContainer1.getId());
        assertThat(personContainer1).isEqualTo(personContainer2);
        personContainer2.setId(2L);
        assertThat(personContainer1).isNotEqualTo(personContainer2);
        personContainer1.setId(null);
        assertThat(personContainer1).isNotEqualTo(personContainer2);
    }
}
