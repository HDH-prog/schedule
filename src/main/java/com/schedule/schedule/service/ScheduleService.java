package com.schedule.schedule.service;

import com.schedule.common.exception.ScheduleNotFoundException;
import com.schedule.common.exception.UserNotFoundException;
import com.schedule.schedule.dto.*;
import com.schedule.schedule.entity.Schedule;
import com.schedule.schedule.repository.ScheduleRepository;
import com.schedule.user.entity.User;
import com.schedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 생성 -> CreateScheduleRequest, CreateScheduleResponse
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );
        Schedule schedule = new Schedule(user, request.getTitle(), request.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getUser().getId(),
                savedSchedule.getUser().getUsername(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // 전체 조회 -> GetSchedulesResponse
    @Transactional(readOnly = true)
    public List<GetSchedulesResponse> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(schedule -> new GetSchedulesResponse(
                        schedule.getId(),
                        schedule.getUser().getId(),
                        schedule.getUser().getUsername(),
                        schedule.getTitle(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                ))
                .toList();
    }

    // 단건 조회 -> GetScheduleResponse
    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getUser().getId(),
                schedule.getUser().getUsername(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 수정 -> UpdateScheduleRequest, UpdateScheduleResponse
    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );
        schedule.update(request.getTitle(), request.getContent());
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getUser().getId(),
                schedule.getUser().getUsername(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 삭제
    @Transactional
    public void delete(Long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new ScheduleNotFoundException("없는 일정입니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}