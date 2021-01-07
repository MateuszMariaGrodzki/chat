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
    public void generateSlugWithAllPolishSignsTest(){
        //given
        String inputName = "ąćęłńóśźż";

        //when
        String result = slugService.generateSlugFromName(inputName);

        //then
        Assertions.assertEquals("acelnoszz",result);
    }

    @Test
    @DisplayName("Test generateSlug with spaces")
    public void generateSlugWithSpacesTest(){
        //given
        String inputName = "jan kowalski";

        //when
        String result = slugService.generateSlugFromName(inputName);

        //then
        Assertions.assertEquals("jan-kowalski",result);
    }

    @Test
    @DisplayName("Test generateSlug with Big polish letters and space")
    public void generateSlugWithBigPolishLettersAndSpacesTest(){
        //given
        String inputName = "ĄĆĘ ŁŃÓŚ ŹŻ";

        //when
        String result = slugService.generateSlugFromName(inputName);

        //then
        Assertions.assertEquals("ace-lnos-zz",result);
    }

    @Test
    @DisplayName("Test generateSlug when ending with empty slug")
    public void generateSlugWithSlugEndpingEmptyTest(){
        //given
        String inputName = "@@@";

        //when
        String result = slugService.generateSlugFromName(inputName);

        //then
        Assertions.assertEquals("",result);
    }

    @Test
    @DisplayName("Test generateSlug with big letters and no polish sign and no spaces")
    public void generateSlugWithBigLettersNoSpacesNoPolishSignsTest(){
        //given
        String inputName = "JANKOWALSKI";

        //when
        String result = slugService.generateSlugFromName(inputName);

        //then
        Assertions.assertEquals("jankowalski",result);
    }

    @Test
    @DisplayName("Test generateSlug with forbidden signs")
    public void generateSlugWithForbiddenSignsTest(){
        //given
        String inputString = "J@#%@<.>2kowalSKć'][;ma";

        //when
        String result = slugService.generateSlugFromName(inputString);

        //then
        Assertions.assertEquals("j2kowalskcma",result);
    }
}
