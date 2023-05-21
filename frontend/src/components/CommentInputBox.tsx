import { Box, Button, FormControl, FormLabel, TextField } from '@mui/material';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { apiInstance } from '../network/axiosInstance';
import { CommentRequestDTO } from '../type/apiEntity';
import { useSelector } from 'react-redux';
import { RootState } from '../store/store';

interface CommentInputProps {
  postId: number;
}

function CommentInputBox(props: CommentInputProps) {
  const navigate = useNavigate();

  const memberInfo = useSelector((state: RootState) => state.auth);

  const [memberId, setMemberId] = useState(memberInfo.id);

  const [commentRequest, setCommentRequest] = useState<CommentRequestDTO>({
    memberId: memberId,
    postId: props.postId,
    content: "",
  });

  const onChangeContent = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value, name } = e.target;
    setCommentRequest({
      ...commentRequest,
      [name]: value
    });
  }

  const onClickSubmitComment = async () => {
    await apiInstance.post("/comments", commentRequest);

    navigate(0);
  }

  const onClickResetComment = () => {
    setCommentRequest({
      ...commentRequest,
      content: ""
    })
  }

  return (
    <FormControl fullWidth>
      <FormLabel>댓글 작성하기</FormLabel>
      <TextField
        placeholder="내용을 입력하세요..."
        minRows={3}
        multiline
        name="content"
        sx={{
          minWidth: 300,
        }}
        value={commentRequest.content}
        onChange={onChangeContent}
      />
      <Box
        sx={{
          display: 'flex',
          border: '2px solid',
          borderColor: 'divider',
        }}
      >
        <Button variant="contained" sx={{ ml: 'auto' }} onClick={onClickSubmitComment}>등록</Button>
        <Button variant="contained" color="warning" sx={{ ml: 1 }} onClick={onClickResetComment}>초기화</Button>
      </Box>
    </FormControl>
  );
}

export default CommentInputBox;