import { useState } from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import FormControlLabel from '@mui/material/FormControlLabel';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { MemberRegisterRequestDTO } from '../../type/apiEntity';
import { apiInstance } from '../../network/axiosInstance';
import { useNavigate } from 'react-router-dom';

function SignUp() {
  const navigate = useNavigate();
  const [memberRequest, SetMemberRequest] = useState<MemberRegisterRequestDTO>({
    email: "",
    password: "",
    nickname: "",
    gender: "",
    purpose: "",
  })

  const onChangeMemberInput = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value, name } = e.target;
    SetMemberRequest({
      ...memberRequest,
      [name]: value
    });
  }

  const onClickSubmitSignUp = async () => {
    await apiInstance.post("/members", memberRequest);

    navigate("/");
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
          Sign up
        </Typography>
        <Box sx={{ mt: 3 }}>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                onChange={onChangeMemberInput}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                onChange={onChangeMemberInput}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                name="nickname"
                label="Nickname"
                id="nickname"
                onChange={onChangeMemberInput}
              />
            </Grid>
            <Grid item xs={12}>
              <FormControl>
                <FormLabel id="gender">Gender</FormLabel>
                <RadioGroup
                    row
                    aria-labelledby="gender"
                    defaultValue="MALE"
                    name="gender"
                    onChange={onChangeMemberInput}
                >
                  <FormControlLabel value="MALE" control={<Radio />} label="Male" />
                  <FormControlLabel value="FEMALE" control={<Radio />} label="Female" />
                </RadioGroup>
              </FormControl>
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                name="purpose"
                label="Purpose"
                id="purpose"
                onChange={onChangeMemberInput}
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
            onClick={onClickSubmitSignUp}
          >
            Sign Up
          </Button>
          <Grid container justifyContent="flex-end">
            <Grid item>
              <Link href="/login" variant="body2">
                Already have an account? Sign in
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
    </Container>
  );
}

export default SignUp;