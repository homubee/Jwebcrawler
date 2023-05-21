import { Box, Button, Checkbox, FormGroup, TextField, Typography } from '@mui/material';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { useState } from 'react';
import { apiInstance } from '../../network/axiosInstance';
import { CrawlBodyRequestDTO, CrawlListRequestDTO } from '../../type/apiEntity';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { RootState } from '../../store/store';

function CrawlForm() {
  const navigate = useNavigate();

  const memberInfo = useSelector((state: RootState) => state.auth);

  const [memberId, setMemberId] = useState(memberInfo.id);
  const [crawlType, setCrawlType] = useState("body");
  const [findIdType, setFindIdType] = useState(true);
  const [bodyRequest, setBodyRequest] = useState<CrawlBodyRequestDTO>({
    memberId: memberId,
    url: "",
    rootId: "",
    rootTag: "",
    rootClass: "",
    rootAttr: "",
    bodyType: "",
  })
  const [listRequest, setListRequest] = useState<CrawlListRequestDTO>({
    memberId: memberId,
    url: "",
    rootId: "",
    rootTag: "",
    rootClass: "",
    rootAttr: "",
    listId: "",
    listTag: "",
    listClass: "",
    listAttr: "",
    targetTag: "",
  })

  const onChangeCrawlType = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCrawlType(e.target.value)
  }

  const onChangeFindType = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFindIdType(e.target.checked);
    if (e.target.checked) {
      setBodyRequest({
        ...bodyRequest,
        rootTag: "",
        rootClass: "",
        rootAttr: "",
      });
      setListRequest({
        ...listRequest,
        rootTag: "",
        rootClass: "",
        rootAttr: "",
      });
    } else {
      setBodyRequest({
        ...bodyRequest,
        rootId: "",
      });
      setListRequest({
        ...listRequest,
        rootId: "",
      });
    }
  };

  const onChangeCrawlRequest = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value, name } = e.target;
    setBodyRequest({
      ...bodyRequest,
      [name]: value
    });
    setListRequest({
      ...listRequest,
      [name]: value
    });
  }

  const onChangeBodyRequest = (e: SelectChangeEvent) => {
    const { value, name } = e.target;
    setBodyRequest({
      ...bodyRequest,
      [name]: value
    });
  };

  const onChangeListRequest = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value, name } = e.target;
    setListRequest({
      ...listRequest,
      [name]: value
    });
  }

  const onClickSubmitCrawl = async () => {
    if (crawlType === "body") {
      await apiInstance.post("/crawl/body", bodyRequest)
      .then((res) => {
        navigate("result", { state: {
          url: res.data.url,
          result: res.data.result
        }});
      });
    } else if (crawlType === "list") {
      await apiInstance.post("/crawl/list", listRequest)
      .then((res) => {
        navigate("result", { state: {
          url: res.data.url,
          result: res.data.result
        }});
      });
    }
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
            onChange={onChangeCrawlRequest}
          />
        </FormControl>
        <FormLabel id="crawling-type">크롤링 유형</FormLabel>
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
        <FormLabel id="crawling-type">루트 태그 검색</FormLabel>
        <FormGroup>
          <FormControlLabel control={<Checkbox onChange={onChangeFindType} defaultChecked />} label="태그 id로 검색" />
        </FormGroup>
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
              name="rootId"
              type="root-id"
              id="root-id"
              onChange={onChangeCrawlRequest}
              disabled={!findIdType}
              sx={{
                "& .MuiInputBase-input.Mui-disabled": {
                  backgroundColor: "#f1f1f1",
                },
              }}
            />
          </Box>
          <Box sx={{flexDirection:"column", mr: 1, maxWidth:250}}>
            <FormLabel id="root-name">루트 태그 이름</FormLabel>
            <TextField
              margin="dense"
              required
              fullWidth
              aria-labelledby="root-name"
              name="rootTag"
              type="root-name"
              id="root-name"
              onChange={onChangeCrawlRequest}
              disabled={findIdType}
              sx={{
                "& .MuiInputBase-input.Mui-disabled": {
                  backgroundColor: "#f1f1f1",
                },
              }}
            />
          </Box>
          <Box sx={{flexDirection:"column", mr: 1, maxWidth:250}}>
            <FormLabel id="root-class">루트 태그 class</FormLabel>
            <TextField
              margin="dense"
              required
              fullWidth
              aria-labelledby="root-class"
              name="rootClass"
              type="root-class"
              id="root-class"
              onChange={onChangeCrawlRequest}
              disabled={findIdType}
              sx={{
                "& .MuiInputBase-input.Mui-disabled": {
                  backgroundColor: "#f1f1f1",
                },
              }}
            />
          </Box>
          <Box sx={{flexDirection:"column", mr: 1, maxWidth:250}}>
            <FormLabel id="root-attr">루트 태그 attritbute</FormLabel>
            <TextField
              margin="dense"
              required
              fullWidth
              aria-labelledby="root-attr"
              name="rootAttr"
              type="root-attr"
              id="root-attr"
              onChange={onChangeCrawlRequest}
              disabled={findIdType}
              sx={{
                "& .MuiInputBase-input.Mui-disabled": {
                  backgroundColor: "#f1f1f1",
                },
              }}
            />
          </Box>
        </FormControl>
      </Box>
      <Box sx={{mt:2, mb:2}}>
      {crawlType === "body" ? 
        <FormControl sx={{minWidth: 225}}>
          <FormLabel id="body-type">본문 줄바꿈 유형</FormLabel>
          <Select fullWidth
            labelId="body-type"
            name="bodyType"
            id="body-type"
            label="body-type"
            onChange={onChangeBodyRequest}
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
              name="listTag"
              type="list-name"
              id="list-name"
              onChange={onChangeListRequest}
            />
          </Box>
          <Box sx={{flexDirection:"column", mr: 1, maxWidth:250}}>
            <FormLabel id="list-class">리스트 태그 class</FormLabel>
            <TextField
              margin="dense"
              required
              fullWidth
              aria-labelledby="list-class"
              name="listClass"
              type="list-class"
              id="list-class"
              onChange={onChangeListRequest}
            />
          </Box>
          <Box sx={{flexDirection:"column", mr: 1, maxWidth:250}}>
            <FormLabel id="list-attr">리스트 태그 attritbute</FormLabel>
            <TextField
              margin="dense"
              required
              fullWidth
              aria-labelledby="list-attr"
              name="listAttr"
              type="list-attr"
              id="list-attr"
              onChange={onChangeListRequest}
            />
          </Box>
          <Box sx={{flexDirection:"column", mr: 1, maxWidth:250}}>
            <FormLabel id="target-name">타겟 태그 이름</FormLabel>
            <TextField
              margin="dense"
              required
              fullWidth
              aria-labelledby="target-name"
              name="targetTag"
              type="target-name"
              id="target-name"
              onChange={onChangeListRequest}
            />
          </Box>
        </FormControl>
      }
      </Box>
      <Box sx={{display:"flex", justifyContent: "center"}}>
        <Button variant="contained" sx={{pl:10, pr:10}} onClick={onClickSubmitCrawl}>크롤링 요청</Button>
      </Box>
    </Box>
  );
}

export default CrawlForm;