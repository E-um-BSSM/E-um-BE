package eum.classroom.domain.service;

import eum.classroom.domain.dto.request.ClassRequest;
import eum.classroom.domain.dto.request.ClassSearchRequest;
import eum.classroom.domain.dto.response.ClassResponse;
import eum.classroom.domain.entity.ClassRoom;
import eum.classroom.domain.entity.ClassTag;
import eum.classroom.domain.repository.ClassRoomRepository;
import eum.classroom.domain.repository.ClassTagRepository;
import eum.classroom.global.constclass.ClassStatus;
import eum.classroom.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassRoomRepository classRoomRepository;
    private final ClassTagRepository classTagRepository;
    private final Random random = new Random();

    @Transactional
    public ClassResponse create(ClassRequest request, String creatorId) {
        ClassRoom classRoom = ClassRoom.builder()
                .title(request.title())
                .description(request.description())
                .difficultyLevel(request.difficulty() == null ? 0L : request.difficulty())
                .classRoomCode(Math.abs(random.nextLong()))
                .createdBy(creatorId)
                .classStatus(request.status() == null ? ClassStatus.ACTIVE : request.status())
                .build();
        ClassRoom saved = classRoomRepository.save(classRoom);
        saveTags(saved, request.tags());
        return toResponse(saved, request.tags());
    }

    @Transactional(readOnly = true)
    public List<ClassResponse> search(ClassSearchRequest request) {
        // 간단히 전체 목록 반환. 추후 조건 필터 추가.
        return classRoomRepository.findAll().stream()
                .map(cr -> toResponse(cr, classTagRepository.findByClassId(cr.getId())
                        .stream().map(ClassTag::getContent).toList()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClassResponse get(Long classId) {
        ClassRoom classRoom = classRoomRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException("Class not found"));
        List<String> tags = classTagRepository.findByClassId(classId).stream()
                .map(ClassTag::getContent).toList();
        return toResponse(classRoom, tags);
    }

    @Transactional
    public ClassResponse update(Long classId, ClassRequest request, String actorId) {
        ClassRoom classRoom = classRoomRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException("Class not found"));
        if (!classRoom.getCreatedBy().equals(actorId)) {
            throw new BadRequestException("Only mentor can update class");
        }
        classRoom.update(request.title(), request.description(), request.difficulty(), request.status());
        classTagRepository.deleteAll(classTagRepository.findByClassId(classId));
        saveTags(classRoom, request.tags());
        return toResponse(classRoom, request.tags());
    }

    @Transactional
    public void delete(Long classId, String actorId) {
        ClassRoom classRoom = classRoomRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException("Class not found"));
        if (!classRoom.getCreatedBy().equals(actorId)) {
            throw new BadRequestException("Only mentor can delete class");
        }
        classRoomRepository.delete(classRoom);
    }

    private void saveTags(ClassRoom classRoom, List<String> tags) {
        if (tags == null) {
            return;
        }
        tags.forEach(tag -> classTagRepository.save(
                ClassTag.builder().classRoom(classRoom).classId(classRoom.getId()).content(tag).build()
        ));
    }

    private ClassResponse toResponse(ClassRoom classRoom, List<String> tags) {
        return new ClassResponse(
                classRoom.getId(),
                classRoom.getTitle(),
                classRoom.getClassRoomCode(),
                classRoom.getDescription(),
                tags,
                classRoom.getDifficultyLevel(),
                classRoom.getCreatedAt()
        );
    }
}
