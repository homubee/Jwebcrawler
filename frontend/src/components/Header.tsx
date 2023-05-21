import { useEffect, useState } from 'react';
import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import { useSelector } from 'react-redux';
import { RootState } from '../store/store';
import { apiInstance } from '../network/axiosInstance';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { logout } from '../store/authSlice';
import { Box } from '@mui/material';

function Header() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const memberInfo = useSelector((state: RootState) => state.auth);

  const [nickname, setNickname] = useState(memberInfo.nickname);

  useEffect(() => {
    setNickname(memberInfo.nickname);
  }, [memberInfo]);

  const onClickLogout = async () => {
    await apiInstance.post("/auth/logout");
    dispatch(logout());

    navigate("/");
  }
  
  return ( 
    <AppBar
      position="static"
      color="default"
      elevation={0}
      sx={{ borderBottom: (theme) => `1px solid ${theme.palette.divider}` }}
    >
      <CssBaseline />
      <Toolbar sx={{ flexWrap: 'wrap' }}>
        <Typography variant="h6" color="inherit" noWrap sx={{ flexGrow: 1 }}>
          <Link href="/" style={{ textDecoration: "none" }}>
            JWebCrawler
          </Link>
        </Typography>
        <Box sx={{ display:"flex", justifyContent:"flex-end", alignItems:"center" }}>
          <Link
            variant="button"
            color="text.primary"
            href="/crawl"
            sx={{ my: 1, mx: 1.5 }}
          >
            크롤링
          </Link>
          <Link
            variant="button"
            color="text.primary"
            href="/post"
            sx={{ my: 1, mx: 1.5 }}
          >
            게시판
          </Link>
          <Link
            variant="button"
            color="text.primary"
            href="/help"
            sx={{ my: 1, mx: 1.5 }}
          >
            도움말
          </Link>
          {nickname === "" ? 
          <>
          <Button href="/login" variant="outlined" sx={{ my: 1, mx: 1.5 }}>
            Sign In
          </Button>
          <Button href="/register" variant="outlined" sx={{ my: 1, mx: 1.5 }}>
            Sign Up
          </Button>
          </>
          :
          <>
          <Typography variant="h6" color="inherit" sx={{ my: 1, mx: 1.5 }}>
            {nickname} 님
          </Typography>
          <Button variant="outlined" onClick={onClickLogout} sx={{ my: 1, mx: 1.5 }}>
            Logout
          </Button>
          </>
          }
        </Box>
      </Toolbar>
    </AppBar>
  );
}

export default Header;