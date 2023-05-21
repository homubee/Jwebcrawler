import { Box, Button, TextField, Typography } from '@mui/material';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import { useState, useEffect } from 'react';
import { apiInstance } from '../../network/axiosInstance';
import { Post, PostRequestDTO } from '../../type/apiEntity';
import { useNavigate, useParams } from 'react-router-dom';
import WrongPage from '../WrongPage';

function UpdatePostForm() {
  const navigate = useNavigate();

  const { postId } = useParams();

  const [postRequest, setPostRequest] = useState<PostRequestDTO>({
    memberId: 1,
    title: "",
    content: "",
  });

  useEffect(() => {
    apiInstance.get(`/posts/${postId}`)
    .then((res) => {
      var postRes: Post = res.data
      setPostRequest({
        ...postRequest,
        title: postRes.title,
        content: postRes.content,
      });
    });
  }, [postId]);

  const onChangePostRequest = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value, name } = e.target;
    setPostRequest({
      ...postRequest,
      [name]: value
    });
  }

  const onClickSubmitPost = async () => {
    await apiInstance.put(`/posts/${postId}`, postRequest);
    navigate(-1);
  }

  const onClickDeletePost = async () => {
    await apiInstance.delete(`/posts/${postId}`);
    navigate("/admin/post");
  }

  return (
    postRequest.title ? 
    <Box>
      <Typography component="h1" variant="h5">
        게시글 수정
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
            value={postRequest.title}
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
            value={postRequest.content}
          />
        </Box>
      </Box>
      <Box sx={{display:"flex", justifyContent:"flex-end"}}>
        <Button variant="contained" sx={{mr:1, width:100}} onClick={onClickSubmitPost}>
          수정완료
        </Button>
        <Button variant="contained" sx={{width:100}} color={"error"} onClick={onClickDeletePost}>
          삭제
        </Button>
      </Box>
    </Box>
    : <WrongPage />
  );
}

export default UpdatePostForm;