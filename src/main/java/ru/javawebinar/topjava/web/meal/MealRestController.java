package ru.javawebinar.topjava.web.meal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        log.info("mealGetAll");
        return MealsUtil.getWithExcess(service.getAll(SecurityUtil.getAuthUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY);

    }

    public List<MealTo> getAllFiltered(String startDate, String endDate, String startTime, String endTime) {
        log.info("mealGetAllFiltered");
        LocalDate sStartDate = (startDate.length() == 0) ? LocalDate.MIN : LocalDate.parse(startDate);
        LocalDate sEndDate = (endDate.length() == 0) ? LocalDate.MAX : LocalDate.parse(endDate);
        LocalTime sStartTime = (startTime.length() == 0) ? LocalTime.MIN : LocalTime.parse(startTime);
        LocalTime sEndTime = (endTime.length() == 0) ? LocalTime.MAX : LocalTime.parse(endTime);
        if (sStartDate.isAfter(sEndDate)) {
            sEndDate = LocalDate.MAX;
        }
        if (sStartTime.isAfter(sEndTime)) {
            sEndTime = LocalTime.MAX;
        }

        List<Meal> meals = MealsUtil.filterMealbyDate(service.getAll(SecurityUtil.getAuthUserId()), sStartDate, sEndDate);
        return MealsUtil.getFilteredWithExcess(meals, MealsUtil.DEFAULT_CALORIES_PER_DAY, sStartTime, sEndTime);
    }

    public Meal get(int id) {
        return service.get(SecurityUtil.getAuthUserId(), id);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal,SecurityUtil.getAuthUserId());
    }
    public boolean delete (int id){
        log.info("delete {}", id);
        return service.delete(id,SecurityUtil.getAuthUserId());
    }

    public Meal update (Meal meal, int id){
        log.info("update {}", meal, id);
        assureIdConsistent(meal,id);
        return service.update(meal,SecurityUtil.getAuthUserId());
    }

}