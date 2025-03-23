import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.repin.dto.report.DailyReportDto;
import org.repin.dto.report.IsDailyLimitKeptDto;
import org.repin.model.MealIntake;
import org.repin.model.User;
import org.repin.repository.MealIntakeDishRepository;
import org.repin.repository.MealIntakeRepository;
import org.repin.repository.UserRepository;
import org.repin.service.CaloriesService;
import org.repin.service.ReportsService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportsServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CaloriesService caloriesService;

    @Mock
    private MealIntakeRepository mealIntakeRepository;

    @Mock
    private MealIntakeDishRepository mealIntakeDishRepository;

    @InjectMocks
    private ReportsService reportsService;


    @Test
    void testGetDailyReport_UserNotFound_ShouldThrowEntityNotFoundException() {
        UUID userId = UUID.randomUUID();
        LocalDate date = LocalDate.now();

        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            reportsService.getDailyReport(userId, date);
        });
    }

    @Test
    void testGetDailyReport_Success() {
        UUID userId = UUID.randomUUID();
        LocalDate date = LocalDate.now();

        User user = new User();
        user.setDailyCalories(2000);
        MealIntake mealIntake = new MealIntake();
        mealIntake.setId(UUID.randomUUID());

        when(userRepository.existsById(userId)).thenReturn(true);
        when(mealIntakeRepository.findAllByUserIdAndDate(userId, date)).thenReturn(Collections.singletonList(mealIntake));
        when(mealIntakeDishRepository.findAllByMealIntakeId(mealIntake.getId())).thenReturn(Collections.emptyList());
        when(caloriesService.calculateTotalCalories(anyList())).thenReturn(1500);

        DailyReportDto report = reportsService.getDailyReport(userId, date);

        assertNotNull(report);
        assertEquals(1500, report.getTotalCalories());
    }
    //лимит не превышен
    @Test
    void testIsDailyLimitKept_LimitKept() {
        UUID userId = UUID.randomUUID();
        LocalDate date = LocalDate.now();

        User user = new User();
        user.setDailyCalories(2000);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.existsById(userId)).thenReturn(true);

        when(mealIntakeRepository.findAllByUserIdAndDate(userId, date)).thenReturn(Collections.emptyList());

        when(caloriesService.calculateTotalCalories(anyList())).thenReturn(1800);


        IsDailyLimitKeptDto result = reportsService.isDailyLimitKept(userId, date);

        assertNotNull(result);
        assertTrue(result.getIsKept());
        assertEquals(200, result.getDifferenceFromLimit());
    }

    //лимит превышен
    @Test
    void testIsDailyLimitKept_LimitNotKept() {
        UUID userId = UUID.randomUUID();
        LocalDate date = LocalDate.now();

        User user = new User();
        user.setDailyCalories(2000);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.existsById(userId)).thenReturn(true);

        when(mealIntakeRepository.findAllByUserIdAndDate(userId, date)).thenReturn(Collections.emptyList());

        when(caloriesService.calculateTotalCalories(anyList())).thenReturn(2200);

        IsDailyLimitKeptDto result = reportsService.isDailyLimitKept(userId, date);


        assertNotNull(result);
        assertFalse(result.getIsKept());
        assertEquals(200, result.getDifferenceFromLimit());
    }



}
