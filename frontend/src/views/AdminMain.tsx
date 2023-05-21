import { Box, Link, Typography } from "@mui/material";

const AdminMain = () => {
  return (
    <Box>
      <Box sx={{mb: 1}}>
        <Typography variant="h4">JWebCrawler 관리자 시스템입니다.</Typography>
      </Box>
      <Box sx={{mb: 1}}>
        <Typography variant="h6">기능 소개</Typography>
        <Typography variant="body1">회원, 게시글, 크롤링 이력 등 시스템 요소들의 목록을 조회하고 관리할 수 있습니다.</Typography>
        <Typography variant="body1">DB에 직접 접속하지 않고 데이터를 다루고 살펴볼 수 있습니다.</Typography>
      </Box>
      <Box sx={{mb: 1}}>
        <Typography variant="h6">시스템 유지보수 및 관리</Typography>
        <Typography variant="body1">본 시스템의 백엔드는 Jenkins, 프론트엔드는 Vercel에 의해 CI/CD 플로우가 구축되어 있습니다.</Typography>
        <Typography variant="body1">시스템에 수정이 필요할 경우, 아래의 깃허브 링크에 접속 및 커밋하여 반영합니다.</Typography>
      </Box>
      <Typography variant="body1">깃허브 링크 : <Link href="https://github.com/homubee/Jwebcrawler">https://github.com/homubee/Jwebcrawler</Link></Typography>
    </Box>
  )
};

export default AdminMain;