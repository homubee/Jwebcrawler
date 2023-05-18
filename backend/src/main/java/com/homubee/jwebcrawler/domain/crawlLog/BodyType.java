package com.homubee.jwebcrawler.domain.crawlLog;

/**
 * 게시글 내용 형태 구분 클래스
 * <br>
 * PAGE : {@code <p></p>} 형태로 줄바꿈하는 게시글
 * <br>
 * BREAK : {@code <br>} 형태로 줄바꿈하는 게시글
 */
public enum BodyType {
    PAGE, BREAK
}
