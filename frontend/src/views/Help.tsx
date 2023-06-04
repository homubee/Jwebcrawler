import { Box, Link, Typography } from "@mui/material";

const Help = () => {
  return (
    <Box>
      <Box sx={{mb: 1}}>
        <Typography variant="h4">도움말</Typography>
      </Box>
      <Box sx={{mb: 3}}>
        <Typography variant="h6">1) HTTP 설정 (필수)</Typography>
        <Box sx={{mb: 1}}>
          <Typography variant="subtitle1">1. 사이트 설정으로 이동</Typography>
          <Box
            component="img"
            sx={{p:1, border: "2px dashed", maxWidth: 1000}}
            src={`${process.env.PUBLIC_URL}/img/help_http_1.png`}
          />
          <Typography variant="body1">(자물쇠 버튼 클릭 -&gt; 사이트 설정 버튼 클릭)</Typography>
        </Box>
        <Box sx={{mb: 1}}>
          <Typography variant="subtitle1">2. 안전하지 않은 콘텐츠를 '허용'으로 변경</Typography>
          <Box
            component="img"
            sx={{p:1, border: "2px dashed", maxWidth: 1000}}
            src={`${process.env.PUBLIC_URL}/img/help_http_2.png`}
          />
        </Box>
        <Box sx={{mb: 1}}>
          <Typography variant="subtitle1">3. 새로고침하여 설정값 반영</Typography>
          <Box
            component="img"
            sx={{p:1, border: "2px dashed", maxWidth: 1000}}
            src={`${process.env.PUBLIC_URL}/img/help_http_3.png`}
          />
        </Box>
        <Box sx={{mb: 1}}>
          <Typography variant="subtitle1">4. 주의 요함 표시 확인 후 서비스 이용</Typography>
          <Box
            component="img"
            sx={{p:1, border: "2px dashed", maxWidth: 1000}}
            src={`${process.env.PUBLIC_URL}/img/help_http_4.png`}
          />
        </Box>
        <Typography variant="body1">(자물쇠 버튼이 빨간색 주의 표시로 변경됨)</Typography>
      </Box>
      <Box>
        <Typography variant="h6">2) 크롤링 기능 사용 방법</Typography>
        <Box sx={{mb: 1}}>
          <Typography variant="subtitle1">1. URL 입력하기</Typography>
          <Typography variant="body1">- 아래의 URL 부분 붉은 박스에 크롤링하고자 하는 웹문서의 URL을 입력</Typography>
          <Box
            component="img"
            sx={{p:1, border: "2px dashed", maxWidth: 1000}}
            src={`${process.env.PUBLIC_URL}/img/help_crawl_1.png`}
          />
        </Box>
        <Box sx={{mb: 1}}>
          <Typography variant="subtitle1">2. 본문 크롤링 설정</Typography>
          <Typography variant="body1">- 크롤링 유형을 '본문'으로 선택 시 이용 가능</Typography>
          <Typography variant="body1">- 본문 줄바꿈 유형이 p 태그 형식인지, br 태그 형식인지에 따라 선택 가능</Typography>
          <Box
            component="img"
            sx={{p:1, border: "2px dashed", maxWidth: 1000}}
            src={`${process.env.PUBLIC_URL}/img/help_crawl_2.png`}
          />
          <Typography variant="subtitle2">① p 태그 유형 예시</Typography>
          <Box sx={{border: "1px dotted", maxWidth: 1000}}>
            <Typography variant="body2">&lt;div&gt;</Typography>
            <Typography variant="body2">&nbsp;&nbsp;&lt;p&gt;test1&lt;/p&gt;</Typography>
            <Typography variant="body2">&nbsp;&nbsp;&lt;p&gt;test2&lt;/p&gt;</Typography>
            <Typography variant="body2">&lt;/div&gt;</Typography>
          </Box>
          <Typography variant="subtitle2">② br 태그 유형 예시</Typography>
          <Box sx={{border: "1px dotted", maxWidth: 1000}}>
            <Typography variant="body2">&lt;div&gt;</Typography>
            <Typography variant="body2">&nbsp;&nbsp;test1</Typography>
            <Typography variant="body2">&nbsp;&nbsp;&lt;br /&gt;</Typography>
            <Typography variant="body2">&nbsp;&nbsp;test2</Typography>
            <Typography variant="body2">&lt;/div&gt;</Typography>
          </Box>
        </Box>
        <Box sx={{mb: 1}}>
          <Typography variant="subtitle1">3. 목록 크롤링 설정</Typography>
          <Typography variant="body1">- 크롤링 유형을 '목록'으로 선택 시 이용 가능</Typography>
          <Typography variant="body1">- 원하는 목록 항목에 해당하는 리스트 태그(tr, li 등) 관련 정보를 입력</Typography>
          <Typography variant="body1">- 타겟 태그는 최종적으로 크롤링하고자 하는 텍스트가 위치하고 있는 태그를 입력</Typography>
          <Box
            component="img"
            sx={{p:1, border: "2px dashed", maxWidth: 1000}}
            src={`${process.env.PUBLIC_URL}/img/help_crawl_3.png`}
          />
        </Box>
        <Box sx={{mb: 1}}>
          <Typography variant="subtitle1">4. 루트 태그 id로 검색 여부</Typography>
          <Typography variant="body1">- 태그 id로 검색할 시, 루트 태그를 찾을 때, 부가정보 없이 한번에 원하는 태그를 찾을 수 있음</Typography>
          <Typography variant="body1">- 태그 id로 검색 설정 해제 시, 아래와 같이 해당 입력폼이 비활성화</Typography>
          <Typography variant="body1">- 따라서 태그 id로 검색 또는 태그 이름/태그 클래스/태그 속성으로 검색 중 선택하여 진행</Typography>
          <Typography variant="body1">※ 루트 태그는 크롤링하고자 하는 태그 전체를 포함하는 가장 안쪽 태그를 의미함(전체 html 문서에서 검색 시보다 안전한 크롤링을 수행, 중복 방지)</Typography>
          <Box
            component="img"
            sx={{p:1, border: "2px dashed", maxWidth: 1000}}
            src={`${process.env.PUBLIC_URL}/img/help_crawl_4.png`}
          />
        </Box>
        <Box sx={{mb: 1}}>
          <Typography variant="subtitle1">5. 크롤링 예시</Typography>
          <Typography variant="subtitle2">① 본문 크롤링 (네이버 블로그 게시글 본문)</Typography>
          <Box sx={{mb: 1, border: "1px dotted", maxWidth: 1000}}>
            <Typography variant="body2" sx={{wordBreak:"break-all"}}>URL : https://blog.naver.com/PostView.naver?blogId=blogpeople&logNo=150083247322&redirect=Dlog&widgetTypeCall=true&from=section&topReferer=https%3A%2F%2Fsection.blog.naver.com%2FBlogHome.naver%3FdirectoryNo%3D0%26currentPage%3D1%26groupId%3D0&directAccess=false</Typography>
            <Typography variant="body2">rootTag : div</Typography>
            <Typography variant="body2">rootClass : se-main-container</Typography>
            <Typography variant="body2">본문 줄바꿈 유형 : p 태그</Typography>
          </Box>
          <Box
            component="img"
            sx={{p:1, border: "2px dashed", maxWidth: 1000}}
            src={`${process.env.PUBLIC_URL}/img/help_crawl_5.png`}
          />
          <Typography variant="subtitle2">② 목록 크롤링 (네이버 연합뉴스 메인 목록)</Typography>
          <Box sx={{mb: 1, border: "1px dotted", maxWidth: 1000}}>
            <Typography variant="body2" sx={{wordBreak:"break-all"}}>URL : https://news.naver.com/main/list.naver?mode=LPOD&mid=sec&sid1=001&sid2=140&oid=001&isYeonhapFlash=Y</Typography>
            <Typography variant="body2">rootId : main_content</Typography>
            <Typography variant="body2">listTag : li</Typography>
            <Typography variant="body2">listAttr : data-comment</Typography>
            <Typography variant="body2">targetTag : strong</Typography>
          </Box>
          <Box
            component="img"
            sx={{p:1, border: "2px dashed", maxWidth: 1000}}
            src={`${process.env.PUBLIC_URL}/img/help_crawl_6.png`}
          />
        </Box>
      </Box>
    </Box>
  )
};

export default Help;