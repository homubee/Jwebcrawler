import { Box, TextField, Typography } from '@mui/material';
import { useLocation } from 'react-router-dom';

function CrawlResult() {
  const { state } = useLocation();
  return (
    <Box>
      <Typography component="h1" variant="h5">
        크롤링 결과
      </Typography>
      <Box sx={{mt:2, mb:2}}>
        <Typography variant="h6" sx={{wordBreak:"break-all"}}>
          <Box sx={{fontWeight:"bold"}}>
            = URL 경로 = 
          </Box>
          {state.url}
        </Typography>
        <Typography variant="h6" sx={{wordBreak:"break-all"}}>
          <Box sx={{fontWeight:"bold"}}>
					  = 페이지 제목 = 
          </Box>
          {state.result.title}
        </Typography>
        <Box sx={{mt:2, mb:2}}>
          <TextField
            id="result-bdoy"
            label="크롤링 내용"
            multiline
            rows={30}
            variant="outlined"
            value={state.result.body}
            fullWidth
          />
        </Box>
      </Box>
    </Box>
  );
}

export default CrawlResult;