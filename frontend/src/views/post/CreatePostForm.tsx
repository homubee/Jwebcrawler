import { Box, Button, TextField, Typography } from '@mui/material';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import { useState } from 'react';
import { apiInstance } from '../../network/axiosInstance';
import { PostRequestDTO } from '../../type/apiEntity';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { RootState } from '../../store/store';

function CreatePostForm() {
  const navigate = useNavigate();

  const memberInfo = useSelector((state: RootState) => state.auth);

  const [memberId, setMemberId] = useState(memberInfo.id);
  const [postRequest, setPostRequest] = useState<PostRequestDTO>({
    memberId: memberId,
    title: "",
    content: "",
  })

  const onChangePostRequest = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value, name } = e.target;
    setPostRequest({
      ...postRequest,
      [name]: value
    });
  }

  const onClickSubmitPost = async () => {
    await apiInstance.post("/posts", postRequest);
    navigate(-1);
  }

  return (
    <Box>
      <Typography component="h1" variant="h5">
        게시글 작성
      </Typography>
      <Box sx={{mt:2, mb:2, maxWidth:1200}}>
        <FormControl fullWidth>
          <FormLabel id="title">제목</FormLabel>
          <TextField
            margin="dense"
            required
            fullWidth
            aria-labelledby="title"
            name="title"
            type="title"
            id="title"
            onChange={onChangePostRequest}
          />
        </FormControl>
        <Box sx={{mt:2, mb:2}}>
          <FormLabel id="post-body">내용</FormLabel>
          <TextField
            id="result-body"
            aria-labelledby="post-body"
            multiline
            rows={30}
            variant="outlined"
            name="content"
            fullWidth
            onChange={onChangePostRequest}
          />
        </Box>
      </Box>
      <Box sx={{display:"flex", justifyContent:"flex-end"}}>
        <Button variant="contained" onClick={onClickSubmitPost}>
          작성완료
        </Button>
      </Box>
    </Box>
  );
}

export default CreatePostForm;