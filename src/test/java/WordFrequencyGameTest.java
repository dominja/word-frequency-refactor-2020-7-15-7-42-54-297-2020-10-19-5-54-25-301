import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordFrequencyGameTest {

    @Test
    void should_get_the_1_when_input_the() {
        //Given
        validate_Input_words_process_to_expected_word("the", "the 1");
    }

    @Test
    void should_process_two_words() {
        //Given
        validate_Input_words_process_to_expected_word("the is", "the 1\nis 1");
    }

    @Test
    void should_process_two_words_with_special_spaces() {
        //Given
        validate_Input_words_process_to_expected_word("the      is", "the 1\nis 1");
    }

    @Test
    void should_process_two_words_with_special_enter() {
        //Given
        validate_Input_words_process_to_expected_word("the   \n   is", "the 1\nis 1");
    }

    @Test
    void should_process_two_same_words_with_sorted() {
        //Given
        validate_Input_words_process_to_expected_word("the the is", "the 2\nis 1");
    }

    @Test
    void should_process_sorted_with_count_descending() {
        //Given
        validate_Input_words_process_to_expected_word("the is is", "is 2\nthe 1");
    }

    @Test
    void should_return_error_message() {
        //Given
        validate_Input_words_process_to_expected_word(null, "Calculate Error");
    }


    private void validate_Input_words_process_to_expected_word(String inputString, String expectResult) {
        WordFrequencyGame game = new WordFrequencyGame();
        //When
        String result = game.getResult(inputString);
        //Then
        assertEquals(result, expectResult);
    }
}
