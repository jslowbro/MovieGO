package com.janchabik.moviego.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.janchabik.moviego.web.rest.TestUtil;

public class PersonContainerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonContainerDTO.class);
        PersonContainerDTO personContainerDTO1 = new PersonContainerDTO();
        personContainerDTO1.setId(1L);
        PersonContainerDTO personContainerDTO2 = new PersonContainerDTO();
        assertThat(personContainerDTO1).isNotEqualTo(personContainerDTO2);
        personContainerDTO2.setId(personContainerDTO1.getId());
        assertThat(personContainerDTO1).isEqualTo(personContainerDTO2);
        personContainerDTO2.setId(2L);
        assertThat(personContainerDTO1).isNotEqualTo(personContainerDTO2);
        personContainerDTO1.setId(null);
        assertThat(personContainerDTO1).isNotEqualTo(personContainerDTO2);
    }
}
