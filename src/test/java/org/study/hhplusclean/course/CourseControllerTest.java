package org.study.hhplusclean.course;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.study.hhplusclean.domain.User;
import org.study.hhplusclean.user.UserRepository;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    static UserRepository userRepository;

    @BeforeAll
    static void set(){
        for(int i =0; i<40 ; i++) {
            userRepository.save(User.builder()
                    .id(Long.valueOf(i))
                    .userName("사용자"+i).build());
        }
    }

    @Test
    @DisplayName("동시에40명이신청한 경우 30명만 성공")
    void applyTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(40);
        AtomicInteger exceptionCount = new AtomicInteger();
        for(int i =0; i<40 ; i++){
            long userId = i+1;
            long courseId = 1;

            Runnable test = () -> {
                try {
                    mockMvc.perform(patch(userId+"/apply/"+courseId))
                            .andDo(print());
                } catch (Exception e) {
                    exceptionCount.getAndIncrement();
                }
            };

            executorService.submit(() -> {
                test.run();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        assertEquals(10,exceptionCount.get());

    }

    @Test
    @DisplayName("동일한 유저정보로 5번 신청한 경우 한번만 성공")
    void sameUserCourseApply() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        AtomicInteger exceptionCount = new AtomicInteger();

        long userId = 1;
        long courseId = 1;
        Runnable test = () -> {
            try {
                mockMvc.perform(patch(userId+"/apply/"+courseId))
                        .andDo(print());
            } catch (Exception e) {
                exceptionCount.getAndIncrement();
            }
        };

        for(int i =0; i<5 ; i++){
            executorService.submit(() -> {
                test.run();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        assertEquals(4,exceptionCount.get());
    }
}
