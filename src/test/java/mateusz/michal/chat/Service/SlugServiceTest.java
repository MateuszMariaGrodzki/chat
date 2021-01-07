package mateusz.michal.chat.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class SlugServiceTest {

    @InjectMocks
    SlugService slugService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test generateSlug with all polish signs")
    public void generateSlugWithAllPolishSigns(){
        //given
        String inputName = "ąćęłńóśźż";

        //when
        String result = slugService.generateSlugFromName(inputName);

        //then
        Assertions.assertEquals("acelnoszz",result);
    }

    @Test
    @DisplayName("Test generateSlug with spaces")
    public void generateSlugWithSpaces(){
        //given
        String inputName = "jan kowalski";

        //when
        String result = slugService.generateSlugFromName(inputName);

        //then
        Assertions.assertEquals("jan-kowalski",result);
    }

    @Test
    @DisplayName("Test generateSlug with Big polish letters and space")
    public void generateSlugWithBigPolishLettersAndSpaces(){
        //given
        String inputName = "ĄĆĘ ŁŃÓŚ ŹŻ";

        //when
        String result = slugService.generateSlugFromName(inputName);

        //then
        Assertions.assertEquals("ace-lnos-zz",result);
    }
}
