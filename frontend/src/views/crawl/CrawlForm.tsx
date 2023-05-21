import { Box, Button, TextField, Typography } from '@mui/material';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { useState } from 'react';

function CrawlForm() {
  const [crawlType, setCrawlType] = useState("body");

  const onChangeCrawlType = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCrawlType(e.target.value)
  }

  return (
    <Box>
      <Typography component="h1" variant="h5">
        크롤링 하고 싶은 페이지 URL과 그 속성을 작성해주세요.
      </Typography>
      <Box sx={{mt:2, mb:2, maxWidth:1000}}>
        <FormControl fullWidth>
          <FormLabel id="url-path">URL (웹사이트 경로)</FormLabel>
          <TextField
            margin="dense"
            required
            fullWidth
            aria-labelledby="url-path"
            name="url"
            type="url"
            id="url"
          />
          <FormLabel id="crawling-type">크롤링 유형</FormLabel>
        </FormControl>
        <RadioGroup
          row
          aria-labelledby="crawling-type"
          name="crawling-type"
          value={crawlType}
          onChange={onChangeCrawlType}
        >
          <FormControlLabel value="body" control={<Radio />} label="본문" />
          <FormControlLabel value="list" control={<Radio />} label="목록" />
        </RadioGroup>
      </Box>
      <Box sx={{mt:2, mb:2}}>
        <FormControl sx={{flexDirection:"row"}}>
          <Box sx={{flexDirection:"column", mr: 1, maxWidth:250}}>
            <FormLabel id="root-id">루트 태그 id</FormLabel>
            <TextField
              margin="dense"
              required
              fullWidth
              aria-labelledby="root-id"
              name="root-id"
              type="root-id"
              id="root-id"
            />
          </Box>
          <Box sx={{flexDirection:"column", mr: 1, maxWidth:250}}>
            <FormLabel id="root-class">루트 태그 class</FormLabel>
            <TextField
              margin="dense"
              required
              fullWidth
              aria-labelledby="root-class"
              name="root-class"
              type="root-class"
              id="root-class"
            />
          </Box>
          <Box sx={{flexDirection:"column", mr: 1, maxWidth:250}}>
            <FormLabel id="root-attr">루트 태그 attritbute</FormLabel>
            <TextField
              margin="dense"
              required
              fullWidth
              aria-labelledby="root-attr"
              name="root-attr"
              type="root-attr"
              id="root-attr"
            />
          </Box>
        </FormControl>
      </Box>
      <Box sx={{mt:2, mb:2}}>
      {crawlType === "body" ? 
        <FormControl sx={{minWidth: 225}}>
          <FormLabel id="break-type">줄바꿈 유형</FormLabel>
          <Select fullWidth
            labelId="break-type"
            id="break-type"
            label="break-type"
          >
            <MenuItem value={"PAGE"}>p 태그</MenuItem>
            <MenuItem value={"BREAK"}>br 태그</MenuItem>
          </Select>
        </FormControl>
        : 
        <FormControl sx={{flexDirection:"row"}}>
          <Box sx={{flexDirection:"column", mr: 1, maxWidth:250}}>
            <FormLabel id="list-name">리스트 태그 이름</FormLabel>
            <TextField
              margin="dense"
              required
              fullWidth
              aria-labelledby="list-name"
              name="list-name"
              type="list-name"
              id="list-name"
            />
          </Box>
          <Box sx={{flexDirection:"column", mr: 1, maxWidth:250}}>
            <FormLabel id="list-class">리스트 태그 class</FormLabel>
            <TextField
              margin="dense"
              required
              fullWidth
              aria-labelledby="list-class"
              name="list-class"
              type="list-class"
              id="list-class"
            />
          </Box>
          <Box sx={{flexDirection:"column", mr: 1, maxWidth:250}}>
            <FormLabel id="list-attr">리스트 태그 attritbute</FormLabel>
            <TextField
              margin="dense"
              required
              fullWidth
              aria-labelledby="list-attr"
              name="list-attr"
              type="list-attr"
              id="list-attr"
            />
          </Box>
          <Box sx={{flexDirection:"column", mr: 1, maxWidth:250}}>
            <FormLabel id="target-name">타겟 태그 이름</FormLabel>
            <TextField
              margin="dense"
              required
              fullWidth
              aria-labelledby="target-name"
              name="target-name"
              type="target-name"
              id="target-name"
            />
          </Box>
        </FormControl>
      }
      </Box>
      <Box sx={{display:"flex", justifyContent: "center"}}>
        <Button variant="contained" sx={{pl:10, pr:10}}>크롤링 요청</Button>
      </Box>
    </Box>
  );
}

export default CrawlForm;