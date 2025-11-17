package eum.classroom.presentation;

import eum.classroom.domain.dto.request.AssignmentRequest;
import eum.classroom.domain.dto.request.ClassRequest;
import eum.classroom.domain.dto.request.ClassScheduleRequest;
import eum.classroom.domain.dto.request.ClassSearchRequest;
import eum.classroom.domain.dto.request.SubmissionFeedbackRequest;
import eum.classroom.domain.dto.request.SubmissionRequest;
import eum.classroom.domain.dto.response.AssignmentResponse;
import eum.classroom.domain.dto.response.AssignmentSearchResponse;
import eum.classroom.domain.dto.response.ClassResponse;
import eum.classroom.domain.dto.response.ClassScheduleResponse;
import eum.classroom.domain.dto.response.MemberAcceptResponse;
import eum.classroom.domain.dto.response.SubmissionResponse;
import eum.classroom.domain.service.AssignmentService;
import eum.classroom.domain.service.ClassService;
import eum.classroom.domain.service.MemberService;
import eum.classroom.domain.service.ScheduleService;
import eum.classroom.domain.service.SubmissionService;
import eum.classroom.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;
    private final AssignmentService assignmentService;
    private final MemberService memberService;
    private final SubmissionService submissionService;
    private final ScheduleService scheduleService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ClassResponse>> createClass(Authentication auth,
                                                                  @RequestBody ClassRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(classService.create(request, auth.getName())));
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<List<ClassResponse>>> searchClasses(@RequestBody ClassSearchRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(classService.search(request)));
    }

    @GetMapping("/search/{classId}")
    public ResponseEntity<ApiResponse<ClassResponse>> getClass(@PathVariable("classId") Long classId) {
        return ResponseEntity.ok(ApiResponse.ok(classService.get(classId)));
    }

    @PatchMapping("/{classId}")
    public ResponseEntity<ApiResponse<ClassResponse>> updateClass(Authentication auth,
                                                                  @PathVariable("classId") Long classId,
                                                                  @RequestBody ClassRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(classService.update(classId, request, auth.getName())));
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<ApiResponse<Void>> deleteClass(Authentication auth,
                                                         @PathVariable("classId") Long classId) {
        classService.delete(classId, auth.getName());
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @PostMapping("/{classId}/invite")
    public ResponseEntity<ApiResponse<Long>> createInviteCode(Authentication auth,
                                                              @PathVariable("classId") Long classId) {
        Long code = memberService.createInviteCode(classId, auth.getName());
        return ResponseEntity.ok(ApiResponse.ok(code));
    }

    @GetMapping("/{classId}/invite")
    public ResponseEntity<ApiResponse<Long>> getInviteCode(Authentication auth,
                                                           @PathVariable("classId") Long classId) {
        Long code = memberService.getInviteCode(classId, auth.getName());
        return ResponseEntity.ok(ApiResponse.ok(code));
    }

    @PostMapping("/{classId}/join")
    public ResponseEntity<ApiResponse<Void>> joinClass(Authentication auth,
                                                       @PathVariable("classId") Long classId) {
        memberService.join(classId, auth.getName());
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @DeleteMapping("/{classId}/join")
    public ResponseEntity<ApiResponse<Void>> cancelJoin(Authentication auth,
                                                        @PathVariable("classId") Long classId) {
        memberService.cancelJoin(classId, auth.getName());
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @PatchMapping("/{classId}/accept")
    public ResponseEntity<ApiResponse<MemberAcceptResponse>> acceptMember(Authentication auth,
                                                                          @PathVariable("classId") Long classId,
                                                                          @RequestParam("userId") String userId) {
        return ResponseEntity.ok(ApiResponse.ok(memberService.accept(classId, userId, auth.getName())));
    }

    @DeleteMapping("/{classId}/disagre")
    public ResponseEntity<ApiResponse<Void>> disagreeMember(Authentication auth,
                                                            @PathVariable("classId") Long classId,
                                                            @RequestParam("userId") String userId) {
        memberService.disagree(classId, userId, auth.getName());
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @DeleteMapping("/{classId}/members")
    public ResponseEntity<ApiResponse<Void>> kickMember(Authentication auth,
                                                        @PathVariable("classId") Long classId,
                                                        @RequestParam("userId") String userId) {
        memberService.kick(classId, userId, auth.getName());
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @GetMapping("/{classId}/assignments")
    public ResponseEntity<ApiResponse<List<AssignmentSearchResponse>>> listAssignments(@PathVariable("classId") Long classId) {
        return ResponseEntity.ok(ApiResponse.ok(assignmentService.list(classId)));
    }

    @PostMapping("/{classId}/assignments")
    public ResponseEntity<ApiResponse<AssignmentResponse>> createAssignment(@PathVariable("classId") Long classId,
                                                                            @RequestBody AssignmentRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(assignmentService.create(classId, request)));
    }

    @PatchMapping("/assignments/{assignmentId}")
    public ResponseEntity<ApiResponse<AssignmentResponse>> updateAssignment(@PathVariable("assignmentId") Long assignmentId,
                                                                            @RequestBody AssignmentRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(assignmentService.update(assignmentId, request)));
    }

    @DeleteMapping("/assignments/{assignmentId}")
    public ResponseEntity<ApiResponse<Void>> deleteAssignment(@PathVariable("assignmentId") Long assignmentId) {
        assignmentService.delete(assignmentId);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @GetMapping("/assignments/{assignmentId}/submissions/")
    public ResponseEntity<ApiResponse<List<SubmissionResponse>>> assignmentSubmissions(@PathVariable("assignmentId") Long assignmentId) {
        return ResponseEntity.ok(ApiResponse.ok(submissionService.listByAssignment(assignmentId)));
    }

    @PostMapping("/submissions")
    public ResponseEntity<ApiResponse<SubmissionResponse>> submit(Authentication auth,
                                                                  @RequestBody SubmissionRequest request,
                                                                  @RequestParam("assignmentId") Long assignmentId) {
        return ResponseEntity.ok(ApiResponse.ok(submissionService.submit(request, assignmentId, auth.getName())));
    }

    @PatchMapping("/submissions/{submissionId}")
    public ResponseEntity<ApiResponse<Void>> feedback(@PathVariable("submissionId") Long submissionId,
                                                      @RequestBody SubmissionFeedbackRequest request) {
        submissionService.feedback(submissionId, request);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @DeleteMapping("/submissions/{submissionId}")
    public ResponseEntity<ApiResponse<Void>> cancelSubmission(@PathVariable("submissionId") Long submissionId) {
        submissionService.cancel(submissionId);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @GetMapping("/{classId}/schedules")
    public ResponseEntity<ApiResponse<List<ClassScheduleResponse>>> schedules(@PathVariable("classId") Long classId) {
        return ResponseEntity.ok(ApiResponse.ok(scheduleService.list(classId)));
    }

    @PostMapping("/{classId}/schedules")
    public ResponseEntity<ApiResponse<ClassScheduleResponse>> createSchedule(@PathVariable("classId") Long classId,
                                                                             @RequestBody ClassScheduleRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(scheduleService.create(classId, request)));
    }

    @GetMapping("/{classId}/schedules/{scheduleId}")
    public ResponseEntity<ApiResponse<ClassScheduleResponse>> getSchedule(@PathVariable("classId") Long classId,
                                                                          @PathVariable("scheduleId") Long scheduleId) {
        return ResponseEntity.ok(ApiResponse.ok(scheduleService.get(classId, scheduleId)));
    }

    @PutMapping("/{classId}/schedules/{schedulesId}")
    public ResponseEntity<ApiResponse<ClassScheduleResponse>> updateSchedule(@PathVariable("classId") Long classId,
                                                                             @PathVariable("schedulesId") Long schedulesId,
                                                                             @RequestBody ClassScheduleRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(scheduleService.update(classId, schedulesId, request)));
    }

    @DeleteMapping("/{classId}/schedules/{schedulesId}")
    public ResponseEntity<ApiResponse<Void>> deleteSchedule(@PathVariable("classId") Long classId,
                                                            @PathVariable("schedulesId") Long schedulesId) {
        scheduleService.delete(classId, schedulesId);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
