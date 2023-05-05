package com.frog.travelwithme.utils;

import com.frog.travelwithme.domain.buddy.entity.Buddy;
import com.frog.travelwithme.domain.common.DeletionEntity;
import com.frog.travelwithme.domain.feed.controller.dto.FeedDto;
import com.frog.travelwithme.domain.feed.controller.dto.TagDto;
import com.frog.travelwithme.domain.member.controller.dto.MemberDto;
import com.frog.travelwithme.domain.member.controller.dto.MemberDto.EmailVerificationResult;
import com.frog.travelwithme.domain.member.controller.dto.MemberDto.SignUp;
import com.frog.travelwithme.domain.member.entity.Member;
import com.frog.travelwithme.domain.recruitment.controller.dto.RecruitmentDto;
import com.frog.travelwithme.domain.recruitment.entity.Recruitment;
import com.frog.travelwithme.global.enums.EnumCollection;
import com.frog.travelwithme.global.enums.EnumCollection.Gender;
import com.frog.travelwithme.global.enums.EnumCollection.OAuthStatus;
import com.frog.travelwithme.global.security.auth.controller.dto.AuthDto;
import com.frog.travelwithme.global.security.auth.controller.dto.AuthDto.LoginDto;
import com.frog.travelwithme.global.security.auth.userdetails.CustomUserDetails;
import com.frog.travelwithme.global.utils.TimeUtils;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * StubData 설명: 테스트를 위한 Stub data 관리
 * 작성자: 김찬빈
 * 버전 정보: 1.0.0
 * 작성일자: 2023/04/03
 **/
public class StubData {
    public static class MockMember {
        static final Long id = 1L;
        @Getter
        static String email = "e_ma-il@gmail.com";
        static String password = "Password1234!";
        static String nickname = "nickname";
        @Getter
        static String image = "defaultImageUrl";
        static String address = "address";
        static String introduction = "introduction";
        static String nation = "nation";
        static String role = "USER";
        static Gender enumGender = Gender.MALE;
        static String stringGender = enumGender.getDescription();
        static String patchStringGender = Gender.FEMALE.getDescription();
        static LocalDateTime createdAt = LocalDateTime.now();
        static LocalDateTime lastModifiedAt = LocalDateTime.now();
        @Getter
        static String emailKey = "email";
        @Getter
        static String codeKey = "code";
        @Getter
        static String codeValue = "123456";
        @Getter
        static String authCodePrefix = "AuthCode ";

        static String emailOther = "emailOther@gmail.com";
        static String nicknameOther = "nicknameOther";

        public static SignUp getSignUpDto() {
            return SignUp.builder()
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .address(address)
                    .introduction(introduction)
                    .nation(nation)
                    .gender(stringGender)
                    .role(role)
                    .build();
        }

        public static SignUp getFailedSignUpDtoByEmail(String failedEmail) {
            return SignUp.builder()
                    .email(failedEmail)
                    .password(password)
                    .nickname(nickname)
                    .address(address)
                    .introduction(introduction)
                    .gender(stringGender)
                    .nation(nation)
                    .role(role)
                    .build();
        }

        public static SignUp getSignUpDtoByEmailAndNickname(String email, String nickname) {
            return SignUp.builder()
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .address(address)
                    .introduction(introduction)
                    .gender(stringGender)
                    .nation(nation)
                    .role(role)
                    .build();
        }

        public static SignUp getFailedSignUpDtoByPassword(String failedPassword) {
            return SignUp.builder()
                    .email(email)
                    .password(failedPassword)
                    .nickname(nickname)
                    .address(address)
                    .introduction(introduction)
                    .gender(stringGender)
                    .nation(nation)
                    .role(role)
                    .build();
        }

        public static SignUp getFailedSignUpDtoByGender(String failedGender) {
            return SignUp.builder()
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .address(address)
                    .introduction(introduction)
                    .gender(failedGender)
                    .nation(nation)
                    .role(role)
                    .build();
        }

        public static LoginDto getLoginSuccessDto() {
            return LoginDto.builder()
                    .email(email)
                    .password(password)
                    .build();
        }

        public static LoginDto getLoginFailDto() {
            return LoginDto.builder()
                    .email("fail@gmail.com")
                    .password(password)
                    .build();
        }

        public static Member getMemberByEmailAndNickname(String email, String nickname) {
            return Member.builder()
                    .id(id)
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .gender(enumGender)
                    .address(address)
                    .introduction(introduction)
                    .nation(nation)
                    .role(role)
                    .build();
        }

        public static Member getMember() {
            return Member.builder()
                    .id(id)
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .gender(enumGender)
                    .address(address)
                    .introduction(introduction)
                    .nation(nation)
                    .role(role)
                    .oauthstatus(OAuthStatus.NORMAL)
                    .build();
        }

        public static MemberDto.Response getResponseDto() {
            return MemberDto.Response.builder()
                    .id(id)
                    .email(email)
                    .nickname(nickname)
                    .address(address)
                    .nation(nation)
                    .introduction(introduction)
                    .gender(stringGender)
                    .image(image)
                    .role(role)
                    .createdAt(createdAt)
                    .lastModifiedAt(lastModifiedAt)
                    .build();
        }

        public static MemberDto.Patch getPatchDto() {
            return MemberDto.Patch.builder()
                    .password("patch" + password)
                    .nickname("patch" + nickname)
                    .address("patch" + address)
                    .nation("patch" + nation)
                    .gender(patchStringGender)
                    .introduction("patch" + introduction)
                    .build();
        }

        public static AuthDto.LoginResponse getLoginResponseDto() {
            return AuthDto.LoginResponse.builder()
                    .id(id)
                    .email(email)
                    .nickname(nickname)
                    .role(role)
                    .build();
        }

        public static CustomUserDetails getUserDetails() {
            return CustomUserDetails.of(email, role);
        }

        public static CustomUserDetails getUserDetailsByEmailAndRole(String email, String role) {
            return CustomUserDetails.of(email, role);
        }

        public static EmailVerificationResult getEmailVerificationResult(boolean authResult) {
            return EmailVerificationResult.from(authResult);
        }
    }

    public static class MockRecruitment {

        // 1번 Mock BuddyRecruitment 정보
        static Long id = 1L;
        static String title = "바하마 배편 동행 구해요";
        static String content = "1인 방예약이 너무비싸 쉐어하실분 구합니다!";
        static String travelNationality = "The Bahamas";
        static String travelStartDate = "2023-01-01";
        static String travelEndDate = "2023-01-03";
        static Long viewCount = 0L;
        static Long commentCount = 0L;

        // 2번 Mock BuddyRecruitment 정보
        static String patchTitle = "페루여행 쿠스코에서 콜릭티보 동행";
        static String patchContent = "콜렉티보 흥정이랑 같이 마추픽추까지 이동하실분 구해요!";
        static String patchTravelNationality = "Peru";
        static String patchTravelStartDate = "2023-01-30";
        static String patchTravelEndDate = "2023-01-31";


        public static Recruitment getRecruitment() {
            return Recruitment.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .travelNationality(travelNationality)
                    .travelStartDate(TimeUtils.stringToLocalDateTime(travelStartDate))
                    .travelEndDate(TimeUtils.stringToLocalDateTime(travelEndDate))
                    .recruitmentStatus(EnumCollection.RecruitmentStatus.IN_PROGRESS)
                    .deletionEntity(new DeletionEntity())
                    .build();
        }

        public static RecruitmentDto.Post getPostRecruitment() {
            return RecruitmentDto.Post.builder()
                    .title(title)
                    .content(content)
                    .travelNationality(travelNationality)
                    .travelStartDate(travelStartDate)
                    .travelEndDate(travelEndDate)
                    .build();
        }

        public static RecruitmentDto.Patch getPatchRecruitment() {
            return RecruitmentDto.Patch.builder()
                    .title(patchTitle)
                    .content(patchContent)
                    .travelNationality(patchTravelNationality)
                    .travelStartDate(patchTravelStartDate)
                    .travelEndDate(patchTravelEndDate)
                    .build();
        }

        public static RecruitmentDto.PostResponse getPostResponseRecruitment() {
            return RecruitmentDto.PostResponse.builder()
                    .title(title)
                    .content(content)
                    .travelNationality(travelNationality)
                    .travelStartDate(TimeUtils.stringToLocalDate(travelStartDate))
                    .travelEndDate(TimeUtils.stringToLocalDate(travelEndDate))
                    .viewCount(viewCount)
                    .commentCount(commentCount)
                    .nickname(MockMember.nickname)
                    .memberImage(MockMember.image)
                    .build();
        }

        public static RecruitmentDto.PatchResponse getPatchResponseRecruitment() {
            return RecruitmentDto.PatchResponse.builder()
                    .title(patchTitle)
                    .content(patchContent)
                    .travelNationality(patchTravelNationality)
                    .travelStartDate(TimeUtils.stringToLocalDate(patchTravelStartDate))
                    .travelEndDate(TimeUtils.stringToLocalDate(patchTravelEndDate))
                    .build();
        }

    }

    public static class MockBuddy {
        public static Buddy getBuddy() {
            return Buddy.builder()
                    .status(EnumCollection.BuddyStatus.WAIT)
                    .build();
        }
    }

    public static class MockFeed {
        static final String contents = "contents";
        static final String location = "location";
        static final String profileImage = "profileImage";
        static final String nickname = "nickname";
        static final boolean isLiked = false;
        @Getter
        static final String tagName = "tagName";
        static final Long commentCount = 100L;
        static final Long likeCount = 100L;
        static final Long tagCount = 100L;
        static final List<String> tags = List.of(tagName + "1", tagName + "2");
        @Getter
        static final int size = 20;
        static boolean isWriter = true;


        public static FeedDto.Post getPostDto() {
            return FeedDto.Post.builder()
                    .contents(contents)
                    .location(location)
                    .tags(tags)
                    .build();
        }

        public static FeedDto.Patch getPatchDto() {
            return FeedDto.Patch.builder()
                    .contents("patch" + contents)
                    .location("patch" + location)
                    .tags(List.of("patch" + tags.get(0)))
                    .build();
        }

        public static FeedDto.Response getResponseDto() {
            return FeedDto.Response.builder()
                    .contents(contents)
                    .location(location)
                    .profileImage(profileImage)
                    .liked(isLiked)
                    .writer(isWriter)
                    .nickname(nickname)
                    .likeCount(likeCount)
                    .commentCount(commentCount)
                    .tags(tags)
                    .build();
        }

        public static List<FeedDto.Response> getResponseDtos() {
            FeedDto.Response responseDto = getResponseDto();

            return List.of(responseDto);
        }

        public static FeedDto.ResponseDetail getResponseDetailDto() {
            return FeedDto.ResponseDetail.builder()
                    .contents(contents)
                    .prfileImage(profileImage)
                    .nickName(nickname)
                    // TODO: comments, tags 객체로 반환
                    .comments(List.of("comment"))
                    .tags(tags)
                    .build();
        }

        public static List<TagDto.Response> getTagResponseDtoList(int dtoCount) {
            List<TagDto.Response> responseList = new ArrayList<>();
            for (int i = 1; i <= dtoCount; i++) {
                responseList.add(getTagResponseDto(i));
            }

            return responseList;
        }

        private static TagDto.Response getTagResponseDto(int addName) {
            return TagDto.Response.builder()
                    .name(tagName + addName)
                    .count(tagCount)
                    .build();
        }
    }
}
