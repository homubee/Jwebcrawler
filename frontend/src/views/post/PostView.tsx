import { Box, Button, TextField, Typography } from '@mui/material';
import FormLabel from '@mui/material/FormLabel';
import { useEffect, useState } from 'react';
import { apiInstance } from '../../network/axiosInstance';
import { Post } from '../../type/apiEntity';
import { useNavigate, useParams } from 'react-router-dom';
import WrongPage from '../WrongPage';
import CommentInputBox from '../../components/CommentInputBox';
import CommentBox from '../../components/CommentBox';
import { useSelector } from 'react-redux';
import { RootState } from '../../store/store';

function PostView() {
  const { postId } = useParams();

  const navigate = useNavigate();
    
  const memberInfo = useSelector((state: RootState) => state.auth);

  const [memberId, setMemberId] = useState(memberInfo.id);
  const [roleList, setRoleList] = useState(memberInfo.roleList);
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
      { (memberId === post?.member.id || roleList.includes("ROLE_ADMIN")) &&
      <Box sx={{display:"flex", justifyContent:"flex-end"}}>
        <Button variant="contained" sx={{mr:1, width:100}} onClick={onClickEditPost}>
          수정하기
        </Button>
      </Box>
      }
      <Box sx={{mb: 2}}>
        <Typography variant="subtitle1">
          댓글 목록 ({post?.commentList.length})
        </Typography>
        {post?.commentList.length !== 0 ? post?.commentList.map((comment) => 
          <CommentBox key={comment.id} comment={comment} />
        ) :
        <Typography variant="subtitle2">
          아직 댓글이 없습니다.
        </Typography>
        }
      </Box>
      <CommentInputBox postId={post?.id!}/>
    </Box>
    : <WrongPage />
  );
}

export default PostView;