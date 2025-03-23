import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.repin.dto.NewUserDto;
import org.repin.enums.Goals;
import org.repin.service.CaloriesService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CaloriesServiceTest {

    @InjectMocks
    private CaloriesService caloriesService;

    @Test
    void testCountDailyCaloriesLimit_Pokhudeniye() {
        NewUserDto dto = new NewUserDto();
        dto.setHeight(BigDecimal.valueOf(175));
        dto.setWeight(BigDecimal.valueOf(70));
        dto.setAge(22);
        dto.setGoal(Goals.ПОХУДЕНИЕ);

        int dailyCalories = caloriesService.countDailyCaloriesLimit(dto);

        assertEquals(1392, dailyCalories);
    }

    @Test
    void testCountDailyCaloriesLimit_NaborMassy() {
        NewUserDto dto = new NewUserDto();
        dto.setHeight(BigDecimal.valueOf(175));
        dto.setWeight(BigDecimal.valueOf(70));
        dto.setAge(22);
        dto.setGoal(Goals.НАБОР_МАССЫ);

        int dailyCalories = caloriesService.countDailyCaloriesLimit(dto);

        assertEquals(2089, dailyCalories);
    }

    @Test
    void testCountDailyCaloriesLimit_Podderzhanie() {
        NewUserDto dto = new NewUserDto();
        dto.setHeight(BigDecimal.valueOf(175));
        dto.setWeight(BigDecimal.valueOf(70));
        dto.setAge(22);
        dto.setGoal(Goals.ПОДДЕРЖАНИЕ);

        int dailyCalories = caloriesService.countDailyCaloriesLimit(dto);

        assertEquals(1741, dailyCalories);
    }

    @Test
    void testCountDailyCaloriesLimit_CornerCaseMin() {
        NewUserDto dto = new NewUserDto();
        dto.setHeight(BigDecimal.valueOf(150));  //ебольшой рост
        dto.setWeight(BigDecimal.valueOf(50));   //небольшой вес
        dto.setAge(30);
        dto.setGoal(Goals.ПОДДЕРЖАНИЕ);

        int dailyCalories = caloriesService.countDailyCaloriesLimit(dto);

        assertTrue(dailyCalories > 1000);
    }

    @Test
    void testCountDailyCaloriesLimit_CornerCaseMax() {
        NewUserDto dto = new NewUserDto();
        dto.setHeight(BigDecimal.valueOf(250));  //большой рост
        dto.setWeight(BigDecimal.valueOf(300));   //большой вес
        dto.setAge(30);
        dto.setGoal(Goals.ПОДДЕРЖАНИЕ);

        int dailyCalories = caloriesService.countDailyCaloriesLimit(dto);

        assertTrue(dailyCalories > 1000);
    }
}
