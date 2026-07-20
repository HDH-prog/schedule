package com.schedule.schedule.service;

import com.schedule.schedule.dto.*;
import com.schedule.schedule.entity.Schedule;
import com.schedule.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 생성 -> CreateScheduleRequest, CreateScheduleResponse
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request.getUsername(), request.getTitle(), request.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getUsername(),
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
                        schedule.getUsername(),
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
                () -> new IllegalStateException("없는 일정입니다.")
        );
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getUsername(),
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
                () -> new IllegalStateException("없는 일정입니다.")
        );
        schedule.update(request.getTitle(), request.getContent());
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getUsername(),
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
            throw new IllegalStateException("없는 일정입니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}