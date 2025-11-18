package eum.classroom.domain.service;

import eum.classroom.domain.dto.request.SubmissionFeedbackRequest;
import eum.classroom.domain.dto.request.SubmissionRequest;
import eum.classroom.domain.dto.response.SubmissionResponse;
import eum.classroom.domain.entity.Submission;
import eum.classroom.domain.repository.SubmissionRepository;
import eum.classroom.global.constclass.SubmissionStatus;
import eum.classroom.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    @Transactional
    public SubmissionResponse submit(SubmissionRequest request, Long assignmentId, String userId) {
        Submission submission = Submission.builder()
                .assignmentId(assignmentId)
                .studentId(userId)
                .content(request.content())
                .fileUrl(request.fileUrl())
                .status(SubmissionStatus.SUBMITTED)
                .build();
        Submission saved = submissionRepository.save(submission);
        return toResponse(saved, userId);
    }

    @Transactional
    public void feedback(Long submissionId, SubmissionFeedbackRequest request) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new NotFoundException("Submission not found"));
        submission.updateFeedback(request.feedback(), request.score() == null ? null : request.score().longValue());
    }

    @Transactional
    public void cancel(Long submissionId) {
        submissionRepository.deleteById(submissionId);
    }

    @Transactional(readOnly = true)
    public List<SubmissionResponse> listByAssignment(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId).stream()
                .map(sub -> toResponse(sub, null))
                .collect(Collectors.toList());
    }

    private SubmissionResponse toResponse(Submission submission, String userId) {
        return new SubmissionResponse(
                submission.getId(),
                submission.getContent(),
                submission.getFileUrl(),
                submission.getSubmittedAt(),
                userId != null ? userId : submission.getStudentId(),
                submission.getGradedAt(),
                submission.getScore() == null ? null : submission.getScore().intValue(),
                submission.getFeedback(),
                submission.getStatus().name()
        );
    }
}
