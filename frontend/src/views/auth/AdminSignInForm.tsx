import { useState } from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { MemberLoginRequestDTO } from '../../type/apiEntity';
import { apiInstance } from '../../network/axiosInstance';
import { useNavigate } from 'react-router-dom';

function AdminSignInForm() {
  const navigate = useNavigate();

  const [memberRequest, SetMemberRequest] = useState<MemberLoginRequestDTO>({
    email: "",
    password: "",
  })

  const onChangeMemberInput = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value, name } = e.target;
    SetMemberRequest({
      ...memberRequest,
      [name]: value
    });
  }

  const onClickSubmitSignIn = async () => {
    await apiInstance.post("/auth/login", memberRequest)
    .then((res) => {
      ;
    });

    navigate("/admin");
  };

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <Box
        sx={{
          marginTop: 8,
          marginBottom: 8,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Admin Sign in
        </Typography>
        <Box sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email Address"
            name="email"
            autoFocus
            onChange={onChangeMemberInput}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            onChange={onChangeMemberInput}
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
            onClick={onClickSubmitSignIn}
          >
            Sign In
          </Button>
        </Box>
      </Box>
    </Container>
  );
}

export default AdminSignInForm;