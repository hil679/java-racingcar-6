package racingcar.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

public class RacingTest {
    private static final int MOVING_FORWARD = 4;
    private static final Integer STOP = 3;

    @Test
    void 자동차_객체_리스트_추가_테스트() {
        Racing racing = new Racing();
        List<String> carNameList = new ArrayList<>(List.of("robin", "hood", "kyle"));
        List<Car> carList = racing.toCarList(carNameList);

        for (int i = 0; i < carList.size(); i++) {
            assertThat(carList.get(i).getName()).isEqualTo(carNameList.get(i));
        }
    }

    @Test
    void 자동차_객체_전진_테스트() {
        Racing racing = new Racing();
        Car car1 = new Car("robin");
        Car car2 = new Car("hood");
        Car car3 = new Car("kyle");

        List<Car> carList = new ArrayList<>(List.of(car1, car2, car3));
        assertRandomNumberInRangeTest(
                () -> {
                    for (int i = 0; i < 3; i++) {
                        racing.initCarsRandomNum(carList);
                        racing.modifyForwardNum(carList);
                    }
                    assertThat(car1.getForwardNum()).isEqualTo(1);
                    assertThat(car2.getForwardNum()).isEqualTo(3);
                    assertThat(car3.getForwardNum()).isEqualTo(1);
                },
                STOP, MOVING_FORWARD, STOP,
                MOVING_FORWARD, MOVING_FORWARD, STOP,
                STOP, MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Test
    void 우승자_찾기_복수_테스트() {
        Racing racing = new Racing();
        Car car1 = new Car("robin");
        Car car2 = new Car("hood");
        Car car3 = new Car("kyle");

        List<Car> carList = new ArrayList<>(List.of(car1, car2, car3));


        assertRandomNumberInRangeTest(
                () -> {
                    for (int i = 0; i < 3; i++) {
                        racing.initCarsRandomNum(carList);
                        racing.modifyForwardNum(carList);
                    }

                    assertThat(racing.findWinners(carList)).containsExactly("hood", "kyle");
                },
                STOP, MOVING_FORWARD, STOP,
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
                STOP, STOP, MOVING_FORWARD
        );
    }

    @Test
    void 단독_우승자_찾기_테스트() {
        Racing racing = new Racing();
        Car car1 = new Car("robin");
        Car car2 = new Car("hood");
        Car car3 = new Car("kyle");

        List<Car> carList = new ArrayList<>(List.of(car1, car2, car3));


        assertRandomNumberInRangeTest(
                () -> {
                    for (int i = 0; i < 3; i++) {
                        racing.initCarsRandomNum(carList);
                        racing.modifyForwardNum(carList);
                    }

                    assertThat(racing.findWinners(carList)).containsExactly("hood");
                },
                STOP, MOVING_FORWARD, STOP,
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
                STOP, MOVING_FORWARD, MOVING_FORWARD
        );
    }
}
