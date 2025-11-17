package eum.classroom.domain.service;

import eum.classroom.domain.dto.request.ClassScheduleRequest;
import eum.classroom.domain.dto.response.ClassScheduleResponse;
import eum.classroom.domain.entity.Schedule;
import eum.classroom.domain.repository.ScheduleRepository;
import eum.classroom.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional(readOnly = true)
    public List<ClassScheduleResponse> list(Long classId) {
        return scheduleRepository.findByClassId(classId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClassScheduleResponse create(Long classId, ClassScheduleRequest request) {
        Schedule schedule = Schedule.builder()
                .classId(classId)
                .title(request.title())
                .description(request.description())
                .startTime(request.startAt())
                .endTime(request.endAt())
                .location(null)
                .build();
        return toResponse(scheduleRepository.save(schedule));
    }

    @Transactional(readOnly = true)
    public ClassScheduleResponse get(Long classId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("Schedule not found"));
        return toResponse(schedule);
    }

    @Transactional
    public ClassScheduleResponse update(Long classId, Long scheduleId, ClassScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("Schedule not found"));
        schedule.update(request.title(), request.description(), request.startAt(), request.endAt(), null);
        return toResponse(schedule);
    }

    @Transactional
    public void delete(Long classId, Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    private ClassScheduleResponse toResponse(Schedule schedule) {
        return new ClassScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                null,
                null,
                null
        );
    }
}
