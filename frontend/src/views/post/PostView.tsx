import { Box, Button, TextField, Typography } from '@mui/material';
import FormLabel from '@mui/material/FormLabel';
import { useEffect, useState } from 'react';
import { apiInstance } from '../../network/axiosInstance';
import { Post } from '../../type/apiEntity';
import { useNavigate, useParams } from 'react-router-dom';
import WrongPage from '../WrongPage';

function PostView() {
  const navigate = useNavigate();

  const { postId } = useParams();

  const [post, setPost] = useState<Post>()

  useEffect(() => {
    apiInstance.get(`/posts/${postId}`)
    .then((res) => {
      var postRes: Post = res.data
      setPost({
        id: postRes.id,
        member: postRes.member,
        commentList: postRes.commentList,
        title: postRes.title,
        content: postRes.content,
        viewCnt: postRes.viewCnt,
      })
    });
  }, [postId]);

  const onClickEditPost = () => {
    navigate("edit");
  }

  return (
    post?.title ? 
    <Box>
      <Typography component="h1" variant="h5">
        {post?.title}
      </Typography>
      <Box sx={{mt:2, mb:2, maxWidth:1200}}>
        <Typography variant="subtitle1">
          작성자 : {post?.member.nickname}
        </Typography>
        <Box sx={{mt:2, mb:2}}>
          <FormLabel id="post-body">내용</FormLabel>
          <TextField
            id="result-bdoy"
            aria-labelledby="post-body"
            multiline
            rows={30}
            variant="outlined"
            fullWidth
            value={post?.content}
          />
        </Box>
      </Box>
      <Box sx={{display:"flex", justifyContent:"flex-end"}}>
        <Button variant="contained" sx={{mr:1, width:100}} onClick={onClickEditPost}>
          수정하기
        </Button>
      </Box>
    </Box>
    : <WrongPage />
  );
}

export default PostView;