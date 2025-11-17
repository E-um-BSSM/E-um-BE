package eum.classroom.domain.service;

import eum.classroom.domain.dto.response.MemberAcceptResponse;
import eum.classroom.domain.entity.ClassMember;
import eum.classroom.domain.entity.ClassMemberId;
import eum.classroom.domain.entity.ClassRoom;
import eum.classroom.domain.entity.WaitingList;
import eum.classroom.domain.entity.WaitingListId;
import eum.classroom.domain.repository.ClassMemberRepository;
import eum.classroom.domain.repository.ClassRoomRepository;
import eum.classroom.domain.repository.WaitingListRepository;
import eum.classroom.global.exception.BadRequestException;
import eum.classroom.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final ClassRoomRepository classRoomRepository;
    private final ClassMemberRepository classMemberRepository;
    private final WaitingListRepository waitingListRepository;
    private final Random random = new Random();

    @Transactional
    public Long createInviteCode(Long classId, String actorId) {
        ClassRoom classRoom = classRoomRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException("Class not found"));
        assertMentor(classRoom, actorId);
        Long code = Math.abs(random.nextLong());
        classRoom.updateClassCode(code);
        return code;
    }

    @Transactional(readOnly = true)
    public Long getInviteCode(Long classId, String actorId) {
        ClassRoom classRoom = classRoomRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException("Class not found"));
        assertMentor(classRoom, actorId);
        return classRoom.getClassRoomCode();
    }

    @Transactional
    public void join(Long classId, String userId) {
        WaitingListId id = new WaitingListId(classId, userId);
        waitingListRepository.findById(id).ifPresent(wl -> {
            throw new BadRequestException("Already requested");
        });
        waitingListRepository.save(WaitingList.builder().id(id).build());
    }

    @Transactional
    public void cancelJoin(Long classId, String userId) {
        WaitingListId id = new WaitingListId(classId, userId);
        waitingListRepository.deleteById(id);
    }

    @Transactional
    public MemberAcceptResponse accept(Long classId, String userId, String actorId) {
        ClassRoom classRoom = classRoomRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException("Class not found"));
        assertMentor(classRoom, actorId);
        WaitingListId waitId = new WaitingListId(classId, userId);
        WaitingList waiting = waitingListRepository.findById(waitId)
                .orElseThrow(() -> new NotFoundException("Not in waiting list"));
        waitingListRepository.delete(waiting);

        ClassMemberId memberId = new ClassMemberId(classId, userId);
        classMemberRepository.findById(memberId).ifPresent(cm -> {
            throw new BadRequestException("Already member");
        });
        ClassMember member = ClassMember.builder()
                .id(memberId)
                .build();
        ClassMember saved = classMemberRepository.save(member);
        return new MemberAcceptResponse(userId,
                saved.getJoinedAt() == null ? null : saved.getJoinedAt().format(DateTimeFormatter.ISO_DATE_TIME));
    }

    @Transactional
    public void disagree(Long classId, String userId, String actorId) {
        ClassRoom classRoom = classRoomRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException("Class not found"));
        assertMentor(classRoom, actorId);
        WaitingListId waitId = new WaitingListId(classId, userId);
        waitingListRepository.deleteById(waitId);
    }

    @Transactional
    public void kick(Long classId, String userId, String actorId) {
        ClassRoom classRoom = classRoomRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException("Class not found"));
        assertMentor(classRoom, actorId);
        ClassMemberId memberId = new ClassMemberId(classId, userId);
        classMemberRepository.deleteById(memberId);
    }

    private void assertMentor(ClassRoom classRoom, String actorId) {
        if (classRoom.getCreatedBy().equals(actorId)) {
            return;
        }
        classMemberRepository.findById(new ClassMemberId(classRoom.getId(), actorId))
                .filter(cm -> cm.getRole() == eum.classroom.global.constclass.ClassRole.MENTOR)
                .orElseThrow(() -> new BadRequestException("Only mentor can perform this action"));
    }
}
