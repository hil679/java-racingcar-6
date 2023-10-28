package racingcar.domain;

import java.util.ArrayList;
import java.util.List;

public class Racing {
    public List<Car> toCarList(List<String> carNameList){
        List<Car> carList = new ArrayList<>();

        for (String carName : carNameList) {
            carList.add(new Car(carName));
        }

        return carList;
    }

    private boolean isMove(Car car){
        return car.getRandomNum() >= 4;
    }

    public void modifyForwardNum(List<Car> carList){
        for (Car car : carList) {
            if(isMove(car)){
                car.increaseForwardNum();
            }
        }
    }

    public List<String> findWinners(List<Car> carList){
        List<String> winners = new ArrayList<>();
        int maxValue = findMaxValue(carList);

        for(Car car : carList) {
            if(car.getForwardNum() == maxValue){
                winners.add(car.getName());
            }
        }

        return winners;
    }

    private int findMaxValue(List<Car> carList){
        int maxValue = carList.get(0).getForwardNum();

        for (int i = 1; i < carList.size(); i++) {
            if (maxValue < carList.get(i).getForwardNum()) {
                maxValue = carList.get(i).getForwardNum();
            }
        }

        return maxValue;
    }

    public void initCarsRandomNum(List<Car> carList){
        for (Car car : carList) {
            car.initRandomNum();
        }
    }
}
