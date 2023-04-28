
package com.frog.travelwithme.domain.recruitment.service;


import com.frog.travelwithme.domain.recruitment.mapper.RecruitmentMapper;
import com.frog.travelwithme.domain.recruitment.controller.dto.RecruitmentDto;
import com.frog.travelwithme.domain.recruitment.entity.Recruitment;
import com.frog.travelwithme.domain.recruitment.repository.RecruitmentRepository;
import com.frog.travelwithme.domain.member.entity.Member;
import com.frog.travelwithme.domain.member.service.MemberService;
import com.frog.travelwithme.global.exception.BusinessLogicException;
import com.frog.travelwithme.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.frog.travelwithme.global.enums.EnumCollection.*;

/**
 * 작성자: 이재혁
 * 버전 정보: 1.0.0
 * 작성일자: 2023/04/11
 **/

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    private final MemberService memberService;

    private final RecruitmentMapper recruitmentMapper;

    public RecruitmentDto.PostResponse createRecruitment(RecruitmentDto.Post postDto,
                                                         String email) {

        Member findMember = memberService.findMemberAndCheckMemberExists(email);
        Recruitment mappedRecruitment = recruitmentMapper.toEntity(postDto);
        mappedRecruitment.addMember(findMember);
        Recruitment recruitment = recruitmentRepository.save(mappedRecruitment);
        return recruitmentMapper.toPostResponseRecruitmentDto(recruitment);

    }

    public RecruitmentDto.PatchResponse updateRecruitment(RecruitmentDto.Patch patchDto,
                                                          Long recruitmentId,
                                                          String email) {
        this.checkEqualWriterAndUser(recruitmentId, email);
        Recruitment recruitment = this.findRecruitmentById(recruitmentId);
        recruitment.updateBuddyRecruitment(patchDto);
        return recruitmentMapper.toPatchResponseRecruitmentDto(recruitment);

    }

    public void deleteRecruitment(Long recruitmentId, String email) {
        Recruitment recruitment = this.checkEqualWriterAndUser(recruitmentId, email);
        recruitment.updateDeletionEntity();
    }

//    TODO : 댓글, 매칭관련 작업이 끝나면 조회 반환 작성하기
//    public RecruitmentDto.GetResponse findRecruitment(Long recruitmentId, String email) {
//
//        Recruitment findRecruitment = this.findRecruitmentById(recruitmentId);
//        Boolean recruitmentRequestStatus = this.checkRecruitmentWriterByEmail(findRecruitment, email);
//        // TODO : 버디 매칭상태를 확인해서 모집 요청상태 만들기
//        // TODO : 버디 매칭상태에서 승인완료된 사람들을 참여인원 찾기
//        return null;
//    }

    @Transactional(readOnly = true)
    public Recruitment findRecruitmentById(Long id) {
        Optional<Recruitment> findRecruitment = recruitmentRepository.findById(id);
        return findRecruitment.orElseThrow(() -> {
            log.debug("RecruitmentService.findRecruitmentById exception occur id: {}", id);
            throw new BusinessLogicException(ExceptionCode.RECRUITMENT_NOT_FOUND);
        });
    }

    @Transactional(readOnly = true)
    public Recruitment checkEqualWriterAndUser(Long recruitmentId, String email) {
        Recruitment findRecruitment = this.findRecruitmentById(recruitmentId);
        Member writer = findRecruitment.getMember();
        Member user = memberService.findMemberAndCheckMemberExists(email);
        if(!writer.equals(user)) {
            log.debug("RecruitmentService.checkEqualWriterAndUser exception occur " +
                    "recruitmentsId: {}, email: {}", recruitmentId, email);
            throw new BusinessLogicException(ExceptionCode.RECRUITMENT_WRITER_NOT_MATCH);
        }
        return findRecruitment;
    }

    @Transactional(readOnly = true)
    public void checkExpiredRecruitment(Recruitment recruitment) {
        RecruitmentStatus status = recruitment.getRecruitmentStatus();
        if(status.equals(RecruitmentStatus.END)) {
            log.debug("RecruitmentService.checkExpiredRecruitment exception occur " +
                    "recruitment: {}", recruitment);
            throw new BusinessLogicException(ExceptionCode.RECRUITMENT_EXPIRED);
        }
    }

//    TODO : 댓글, 매칭관련 작업이 끝나면 조회 반환 작성하기
//    private Boolean checkRecruitmentWriterByEmail(Recruitment recruitment, String email) {
//        Member member = recruitment.getMember();
//        if (member.getEmail().equals(email)) {
//            return true;
//        }
//        return false;
//    }
}
