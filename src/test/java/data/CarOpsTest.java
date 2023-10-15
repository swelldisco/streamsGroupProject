package data;

import challenges.CarOps;
import domain.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static challenges.CarOps.*;
import static challenges.CarOps.averagePriceOfCarsByMake;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CarOpsTest {

    private static List<Car> cars;
    private static Optional<Car> allCars;

    @BeforeAll
    static void setUp() throws IOException {
        // Fetch data before all tests
        cars = FetchData.getCarList();
    }


    //Question-31
    @Test
    void shouldPassTheTestWithActualCount(){
        Map<String, Long> filteredCars = CarOps.countCarsByMake(cars);
        assertEquals(filteredCars.get("Chevrolet"), 40 );
    }
    @Test
    void shouldReturnNullWhenItIsUnknownMake(){
        Map<String, Long> filteredCars = CarOps.countCarsByMake(cars);
        assertNotEquals(filteredCars.get("FalseMakeOfCar"), 10);
    }


    //Question-32
    @Test
    void checkingWithRightPrice(){
        double averagePrice = CarOps.averagePriceOfCars(cars);
        assertEquals(129355.31270000001, averagePrice);
    }
    @Test
    void checkingWithTheWrong(){
        double averagePrice = CarOps.averagePriceOfCars(cars);
        assertNotEquals(1200000000, averagePrice);
    }


    //Question-33
    @Test
    void checkingWithTheRightResult(){
        double sumOfPrice = sumOfPriceOfCars(cars);
        assertEquals(6.467765634999995E7, sumOfPrice);
    }


    //Question-34
    @Test
    void checkingWithTheWrongValue(){
        assertNotEquals(false, carsWithBlueColor(cars));
    }
    @Test
    void checkingWithTheRightValue(){
        assertEquals(true, carsWithBlueColor(cars));
    }


    //Question-35
    @Test
    void checkingWithTheWrongValue35(){
        assertNotEquals(false, isAllCarExpensive(cars));
    }
    @Test
    void checkingWithTheRightValue35(){
        assertEquals(true, isAllCarExpensive(cars));
    }


    //Question-36
    @Test
    void checkingWithTheWrongValue36(){
        assertNotEquals(true, isThisConditionMatch(cars));
    }
    @Test
    void checkingWithTheRightValue36(){
        assertEquals(false, isThisConditionMatch(cars));
    }


    //Question-37
    @Test
    void checkingWithTheRightValue37 (){
        List<Car> skippedCars = skippingTheFirst5Cars(cars, 5);
        //Number of cars left
        assertEquals(495, skippedCars.size());
    }
    @Test
    void checkingWithTheWrongValue37 (){
        List<Car> skippedCars = skippingTheFirst5Cars(cars, 5);
        //Number of cars left
        assertNotEquals(5, skippedCars.size());
    }


    //Question-38
    @Test
    void checkingWithTheRightValue38 (){
        List<Car> limitedCars = skippingTheFirst5Cars(cars, 10);
        assertNotEquals(400, limitedCars.size());
    }
    @Test
    void checkingWithTheWrongValue38 (){
        List<Car> limitedCars = skippingTheFirst5Cars(cars, 10);
        assertEquals(490, limitedCars.size());
    }


    //Question-39
    @Test
    void checkingWithTheRightValue39(){
        List<String> distinctCars = listOfDistinctCarColor(cars);
        assertEquals(19, distinctCars.size());
    }
    @Test
    void checkingWithTheWrongValue39(){
        List<String> distinctCars = listOfDistinctCarColor(cars);
        assertNotEquals(20, distinctCars.size());
    }


    //Question-40
    @Test
    void checkingWithTheRightValue40(){
        List<String> concatenatedCars = concatenatingMakeAndModel(cars);
        assertEquals("Rogue Rogue", concatenatedCars.get(0));
        assertEquals("Land Cruiser Land Cruiser", concatenatedCars.get(1));
    }


    //Question-41
    @Test
    void checkingWithTheRightValue41(){
        Optional<Car> firstCar = findTheFirstCar(cars);
        assertEquals( "Nissan", firstCar.get().getMake());
    }


    //Question-42
    @Test
    void checkingIfTheCarIsPresent(){
        Optional<Car> anyCar = findAnyCar(cars);
        assertTrue(anyCar.isPresent());
    }


    //Question-43
    @Test
    void checkingIfThereIsDuplicates(){

    }


    //Question-44
    @Test
    void partitionedCars(){
        Map<Boolean, List<Car>> limitedCars = highPriceAndLowPrice(cars, 50000);
        assertEquals(2, limitedCars.size());
    }


    //Question-45
    @Test
    void totalPriceOfCarsByMake(){
        Map<String, Double> price = calculateTotalPriceByMake(cars);
        assertEquals(625912.3, price.get("Lexus"));
        assertNotEquals(33400.0, price.get("Chrysler"));
    }


    //Question-46
    @Test
    void joinedCarName(){
        String joinedNameOfCars = joinCarsByNames(cars);
        assertThat(joinedNameOfCars.contentEquals("Nissan Rogue"));
        assertThat(joinedNameOfCars.contentEquals("Toyota LandCruiser"));
        assertThat(joinedNameOfCars.contentEquals("Suzuki Verona"));
        assertThat(joinedNameOfCars.contentEquals("Volkswagen Golf"));

    }


    //Question-47
    @Test
    void listAllTheDetailOfCar(){

    }


    //Question-48
    @Test
    void checkingWithTheRightValue48(){
        Map<String, Double> priceOfCars = averagePriceOfCarsByMake(cars);
        assertEquals(125182.46, priceOfCars.get("Lexus"));
        assertEquals(87808.33111111111, priceOfCars.get("Chrysler"));
        assertEquals(122211.63142857143, priceOfCars.get("Subaru"));
    }


    //Question-49
    @Test
    void concatenatingAllInfo(){

    }


    //Question-50
    @Test
    void checkingWithTheRightValue50(){
        List<Car> newestCars = newestCarFromTheList(cars);
        assertEquals(2013, newestCars.get(0).getYear());
        assertEquals(2013, newestCars.get(1).getYear());
        assertNotEquals(2023, newestCars.get(2).getYear());

    }
}
