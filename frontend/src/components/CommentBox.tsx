import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { Comment } from '../type/apiEntity';

interface CommentBoxProps {
  comment: Comment
}

function CommentBox(props: CommentBoxProps) {
  return (
    <Box
      sx={{
        py: 3,
        mt: 1,
        mb: 1,
        border: "3px dashed",
        borderColor: (theme) =>
          theme.palette.mode === 'light'
            ? theme.palette.grey[400]
            : theme.palette.grey[800],
      }}
    >
      <Container>
        <Typography variant="body1">
          작성자 : {props.comment.member.nickname}
        </Typography>
        <Typography variant="body1">
          내용 : {props.comment.content}
        </Typography>
      </Container>
    </Box>
  );
}

export default CommentBox;