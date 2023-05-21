import { Box, Link, Typography } from "@mui/material";

const UserMain = () => {
  return (
    <Box>
      <Box sx={{mb: 1}}>
        <Typography variant="h4">JWebCrawler : Online web crawling service</Typography>
      </Box>
      <Box sx={{mb: 1}}>
        <Typography variant="h6">소개</Typography>
        <Typography variant="body1">온라인 상에서 간편하게 웹 크롤링을 경험해볼 수 있는 서비스입니다.</Typography>
        <Typography variant="body1">평소 막연하게만 생각했던 크롤링 기능을 직접 체험하고 경험해봅시다!</Typography>
      </Box>
      <Box sx={{mb: 1}}>
        <Typography variant="h6">기능사항</Typography>
        <Typography variant="body1">본 서비스에서는 본문 크롤링과 목록 크롤링, 두 가지 형태의 크롤링 기능을 제공하고 있습니다.</Typography>
        <Typography variant="body1">1. 본문 크롤링은 게시글 형태의 웹문서에서 본문 텍스트를 가져오는 기능입니다.</Typography>
        <Typography variant="body1">2. 목록 크롤링은 목록 형태의 웹문서에서 목록 제목, 날짜 등의 텍스트를 가져오는 기능입니다.</Typography>
        <Typography variant="body1">그 외에도 간단하게 게시판, 댓글 기능 등을 제공하고 있으며, 모든 기능은 로그인한 회원에게만 제공됩니다.</Typography>
      </Box>
      <Box sx={{mb: 1}}>
        <Typography variant="h6">사용법</Typography>
        <Typography variant="body1">본 서비스를 이용하기 위해서는 두가지 지식이 필요합니다.</Typography>
        <Typography variant="body1">1. HTTP 연결 설정 (서버의 SSL 인증서 미적용 문제로, 정상적인 서비스 이용을 위해서는 HTTPS 설정을 비활성화해야 합니다.)</Typography>
        <Typography variant="body1">2. 크롤링 기능 사용 방법 (가이드 및 예시 제공)</Typography>
        <Typography variant="body1"><Link href="/help">도움말 페이지</Link>로 이동하여 관련 내용을 살펴보세요!</Typography>
      </Box>
      <Typography variant="body1">※ 본 웹사이트는 개인 프로젝트용으로 만들어진 것이며, 개인정보는 본 웹사이트 내에서만 이용됩니다. 개인정보 관련 문제 또는 크롤링을 불법적으로 이용하는 문제 발생 시 책임소재는 회원 본인에게 있습니다.</Typography>
    </Box>
  )
};

export default UserMain;