package com.frog.travelwithme.global.exception;

import lombok.Getter;

/**
 * ErrorCode 설명: 예외 코드 관리
 * 작성자: 김찬빈
 * 버전 정보: 1.0.0
 * 작성일자: 2023/03/29
 **/
public enum ExceptionCode {

    // Member
    MEMBER_ROLE_DOES_NOT_EXISTS(404, "회원의 권한이 존재하지 않습니다."),
    MEMBER_EXISTS(404, "이미 존재하는 회원입니다."),
    AUTH_CODE_IS_NOT_SAME(404, "인증 번호가 일치하지 않습니다."),
    UNABLE_TO_SEND_EMAIL(404, "메일을 전송할 수 없습니다."),
    NO_SUCH_ALGORITHM(400, "인증 번호 생성을 위한 알고리즘을 찾을 수 없습니다."),
    INVALID_GENDER(404, "성별이 올바르지 않습니다. 성별은 남자, 여자만 허용됩니다."),
    FOLLOW_NOT_FOUND(404, "팔로우를 하지 않았습니다."),
    FOLLOW_EXISTS(404, "이미 팔로우 했습니다."),
    TAGGED_MEMBER_NOT_FOUND(404, "태그된 회원을 찾을 수 없습니다."),

    // Recruitment & Buddy
    RECRUITMENT_NOT_FOUND(404, "해당 동행모집글을 찾을 수 없습니다."),
    RECRUITMENT_WRITER_NOT_MATCH(404, "해당 동행모집글을 작성한 유저가 아닙니다."),
    RECRUITMENT_EXPIRED(404, "동행모집이 종료된 게시글 입니다."),
    RECRUITMENT_MATCHING_REQUEST_MEMBER_NOT_FOUND(404, "찾을 수 없는 동행모집글이거나 매칭신청 중인 회원이 없습니다."),
    MATCHING_NOT_FOUND(404, "해당 동행 매칭신청 내역을 찾을 수 없습니다."),
    MATCHING_RECRUITMENT_IS_DIFFERENT(404, "동행매칭 요청의 모집글과 모집글이 상이한 요청을하였습니다."),
    MATCHING_REQUEST_NOT_ALLOWED(404, "동행매칭 요청이 불가능한 상태입니다."),
    MATCHING_CANCEL_NOT_ALLOWED(404, "동행매칭 취소가 불가능한 상태입니다."),
    MATCHING_APPROVE_NOT_ALLOWED(404, "동행매칭 승인이 불가능한 상태입니다."),
    MATCHING_REJECT_NOT_ALLOWED(404, "동행매칭 거절이 불가능한 상태입니다."),
    MEMBER_INTEREST_NOT_FOUND(404, "회원 관심사를 찾을 수 없습니다."),

    // Feed
    FEED_NOT_FOUND(404, "피드가 존재하지 않습니다."),
    FEED_WRITER_NOT_MATCH(404, "해당 피드를 작성한 유저가 아닙니다."),
    ALREADY_LIKED_FEED(409, "이미 좋아요한 피드입니다."),
    UNABLE_TO_CANCEL_LIKE(409, "좋아요를 하지 않았기 때문에 좋아요를 취소할 수 없습니다. "),
    UNABLE_TO_DELETE_FEED_IMAGE(404, "피드 이미지는 최소 한 장 이상 존재해야 합니다."),
    UNABLE_TO_SAVE_FEED(404, "피드를 생성할 수 없습니다."),
    ONLY_ONE_PARAMETER_TO_FEED_SEARCH(404, "피드 검색에는 반드시 tag 또는 nickname 중 하나의 파라미터만 전달되어야 합니다."),

    // Comment
    COMMENT_NOT_FOUND(404, "댓글이 존재하지 않습니다."),
    COMMENT_WRITER_NOT_MATCH(404, "해당 댓글을 작성한 유저가 아닙니다."),
    COMMENT_DO_NOT_NEED_GROUP_ID(404, "댓글에는 GROUP ID 정보가 필요하지 않습니다."),
    COMMENT_REPLY_NEED_GROUP_ID(404, "대댓글에는 GROUP ID 정보가 필수입니다. GROUP ID를 넣어주십시오."),
    COMMENT_REPLY_GROUP_ID_NOT_FOUND(404, "대댓글의 기준이되는 댓글이 존재하지 않습니다."),
    COMMENT_HAS_BEEN_DELETED(404, "삭제된 댓글입니다. 삭제된 댓글은 수정할 수 없습니다."),
    ALREADY_LIKED_COMMENT(404, "이미 좋아요한 댓글입니다."),

    // Security, JWT
    NO_ACCESS_TOKEN(403, "토큰에 권한 정보가 존재하지 않습니다."),
    TOKEN_SIGNATURE_INVALID(400, "토큰 Signature가 올바르지 않습니다."),
    TOKEN_EXPIRED(400, "토큰이 만료되었습니다."),
    TOKEN_UNSUPPORTED(400, "지원하지 않는 형식의 토큰입니다."),
    TOKEN_ILLEGAL_ARGUMENT(400, "올바르지 않은 토큰입니다."),
    MEMBER_NOT_FOUND(404, "회원을 찾을 수 없습니다."),
    HEADER_REFRESH_TOKEN_NOT_EXISTS(404, "헤더에 Refresh token이 존재하지 않습니다."),
    TOKEN_IS_NOT_SAME(404, "토큰이 일치하지 않습니다."),

    // AES
    ENCRYPTION_FAILED(404, "암호화에 실패했습니다."),
    DECRYPTION_FAILED(404, "복호화에 실패했습니다."),
    SECRET_KEY_INVALID(400, "Secret key가 올바르지 않습니다."),
    MEMBER_ROLE_INVALID(400, "회원의 권한 정보가 올바르지 않습니다."),

    // Redis
    NOT_FOUND_AVAILABLE_PORT(404, "사용 가능한 Port를 찾을 수 없습니다. port: 10000 ~ 65535"),
    ERROR_EXECUTING_EMBEDDED_REDIS(404, "EmbeddedRedis 실행 중 에러 발생"),
    REDIS_SERVER_EXCUTABLE_NOT_FOUND(404, "Redis Server Executable not found"),

    // Utils
    STRING_IS_NOT_LOCAL_DATE_FORMAT(404,"문자열 데이터 형식이 LocalDate 형식이 아닙니다."),
    STRING_IS_NOT_LOCAL_DATE_TIME_FORMAT(404,"문자열 데이터 형식이 LocalDateTime 형식이 아닙니다."),
    UNABLE_TO_CONVERT_LIST_TO_STRING(404, "리스트를 문자열로 변환할 수 없습니다."),
    UNABLE_TO_CONVERT_STRING_TO_LIST(404, "문자열을 리스트로 변환할 수 없습니다."),

    // File
    FILE_DOES_NOT_EXIST(404, "파일이 존재하지 않습니다."),
    FAIL_TO_UPLOAD_FILE(404, "파일을 업로드할 수 없습니다."),
    EXTENSION_IS_NOT_VALID(404, "파일의 확장자는 jpeg 또는 png만 업로드 가능합니다."),
    FAILED_TO_DELETE_FILE(404, "파일을 삭제할 수 없습니다.");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
