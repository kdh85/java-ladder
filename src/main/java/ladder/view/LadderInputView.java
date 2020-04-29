package ladder.view;

import ladder.domain.LadderResult;
import ladder.domain.Persons;
import ladder.view.constant.ConstPerson;
import ladder.view.exception.InvalidLadderHeightException;
import ladder.view.exception.InvalidLadderResultInputException;
import ladder.view.exception.InvalidNamesInputException;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class LadderInputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String PERSON_NAMES_DELIMITER = ",";
    private static final String LADDER_RESULTS_DELIMITER = ",";
    private static final String GET_PERSON_NAMES_NOTICE = "참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)";
    private static final String GET_LADDER_RESULTS_NOTICE = "\n실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)";
    private static final String GET_LADDER_HEIGHT_NOTICE = "\n최대 사다리 높이는 몇 개인가요?";
    private static final String PERSON_NAME_INVALID_FORMAT_MESSAGE = "사람의 이름은 5글자 이하여야 합니다!";
    private static final String LADDER_RESULT_INVALID_COUNT_MESSAGE = "사다리 게임 실행 결과의 갯수는 %d개 입니다!";
    private static final String LADDER_HEIGHT_INVALID_FORMAT_MESSAGE = "사다리 높이는 숫자 값이어야 합니다!";
    private static final String LADDER_HEIGHT_INVALID_VALUE_MESSAGE = "사다리 높이는 0 이상 이어야 합니다!";

    private static final int LADDER_HEIGHT_MIN = 1;

    public static Persons getPersons() {
        System.out.println(GET_PERSON_NAMES_NOTICE);

        String personNames = scanner.nextLine();
        if (isInvalidInput(personNames)) {
            throw new InvalidNamesInputException();
        }

        return getValidPersons(personNames.split(PERSON_NAMES_DELIMITER));
    }

    private static boolean isInvalidInput(String stringValue) {
        return Objects.isNull(stringValue) || stringValue.isEmpty();
    }

    private static Persons getValidPersons(String[] names) {
        boolean isAllValid = Arrays.stream(names)
                .allMatch(LadderInputView::isPersonNameValid);

        if (!isAllValid) {
            throw new InvalidNamesInputException(PERSON_NAME_INVALID_FORMAT_MESSAGE);
        }

        return Persons.getInstance(names);
    }

    private static boolean isPersonNameValid(String name) {
        return name.length() <= ConstPerson.PERSON_NAME_SIZE_MAX;
    }

    public static LadderResult getLadderResults(int countOfLadder) {
        System.out.println(GET_LADDER_RESULTS_NOTICE);

        String ladderResults = scanner.nextLine();
        if (isInvalidInput(ladderResults)) {
            throw new InvalidLadderHeightException();
        }

        return getValidLadderResult(
                ladderResults.split(LADDER_RESULTS_DELIMITER),
                countOfLadder);
    }

    private static LadderResult getValidLadderResult(String[] ladderResults,
                                                     int countOfLadder) {
        if (ladderResults.length != countOfLadder) {
            throw new InvalidLadderResultInputException(
                    String.format(LADDER_RESULT_INVALID_COUNT_MESSAGE, countOfLadder)
            );
        }

        return LadderResult.getInstance(ladderResults);
    }

    public static int getLadderHeight() {
        System.out.println(GET_LADDER_HEIGHT_NOTICE);

        try {
            return getValidHeight(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException exception) {
            throw new InvalidLadderHeightException(LADDER_HEIGHT_INVALID_FORMAT_MESSAGE);
        }
    }

    private static int getValidHeight(int height) {
        if (height < LADDER_HEIGHT_MIN) {
            throw new InvalidLadderHeightException(LADDER_HEIGHT_INVALID_VALUE_MESSAGE);
        }

        return height;
    }

}