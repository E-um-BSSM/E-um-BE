package eum.classroom.domain.service;

import eum.classroom.domain.dto.request.AssignmentRequest;
import eum.classroom.domain.dto.response.AssignmentResponse;
import eum.classroom.domain.dto.response.AssignmentSearchResponse;
import eum.classroom.domain.entity.Assignment;
import eum.classroom.domain.repository.AssignmentRepository;
import eum.classroom.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Transactional
    public AssignmentResponse create(Long classId, AssignmentRequest request) {
        Assignment assignment = Assignment.builder()
                .classId(classId)
                .title(request.title())
                .description(request.description())
                .difficulty(request.difficulty())
                .dueDate(request.dueDate())
                .build();
        Assignment saved = assignmentRepository.save(assignment);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<AssignmentSearchResponse> list(Long classId) {
        return assignmentRepository.findByClassId(classId).stream()
                .map(this::toSearchResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public AssignmentResponse update(Long assignmentId, AssignmentRequest request) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new NotFoundException("Assignment not found"));
        assignment.update(request.title(), request.description(), request.difficulty(), request.dueDate());
        return toResponse(assignment);
    }

    @Transactional
    public void delete(Long assignmentId) {
        assignmentRepository.deleteById(assignmentId);
    }

    private AssignmentResponse toResponse(Assignment assignment) {
        return new AssignmentResponse(
                assignment.getId(),
                assignment.getTitle(),
                assignment.getDescription(),
                assignment.getDueDate(),
                assignment.getDifficulty(),
                assignment.getCreatedAt()
        );
    }

    private AssignmentSearchResponse toSearchResponse(Assignment assignment) {
        return new AssignmentSearchResponse(
                assignment.getId(),
                assignment.getTitle(),
                assignment.getDescription(),
                assignment.getDueDate(),
                assignment.getCreatedAt(),
                "open"
        );
    }
}
