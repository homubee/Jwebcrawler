import { useState, useEffect, Fragment } from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import { styled } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import MuiDrawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import MuiAppBar, { AppBarProps as MuiAppBarProps } from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import { EditNote, History, InfoOutlined, People } from '@mui/icons-material';
import Footer from './Footer';
import AdminSignInForm from '../views/auth/AdminSignInForm';
import WrongPage from '../views/WrongPage';
import AdminMain from '../views/AdminMain';
import MemberList from '../views/member/MemberList';
import PostList from '../views/post/PostList';
import CrawlLogList from '../views/crawl/CrawlLogList';
import CreatePostForm from '../views/post/CreatePostForm';
import PostView from '../views/post/PostView';
import UpdatePostForm from '../views/post/UpdatePostForm';
import { useSelector } from 'react-redux';
import { RootState } from '../store/store';
import { apiInstance } from '../network/axiosInstance';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { logout } from '../store/authSlice';
import { Button } from '@mui/material';

const mainListItems = (
  <Fragment>
    <ListItemButton href="/admin">
      <ListItemIcon>
        <InfoOutlined />
      </ListItemIcon>
      <ListItemText primary="메인 페이지" />
    </ListItemButton>
    <ListItemButton href="/admin/member">
      <ListItemIcon>
        <People />
      </ListItemIcon>
      <ListItemText primary="회원 목록" />
    </ListItemButton>
    <ListItemButton href="/admin/post">
      <ListItemIcon>
        <EditNote />
      </ListItemIcon>
      <ListItemText primary="게시글 목록" />
    </ListItemButton>
    <ListItemButton href="/admin/crawl">
      <ListItemIcon>
        <History />
      </ListItemIcon>
      <ListItemText primary="크롤링 이력" />
    </ListItemButton>
  </Fragment>
);

const drawerWidth: number = 240;

interface AppBarProps extends MuiAppBarProps {
  open?: boolean;
}

const AppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== 'open',
})<AppBarProps>(({ theme, open }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(['width', 'margin'], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));

const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    '& .MuiDrawer-paper': {
      position: 'relative',
      whiteSpace: 'nowrap',
      width: drawerWidth,
      transition: theme.transitions.create('width', {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
      }),
      boxSizing: 'border-box',
      ...(!open && {
        overflowX: 'hidden',
        transition: theme.transitions.create('width', {
          easing: theme.transitions.easing.sharp,
          duration: theme.transitions.duration.leavingScreen,
        }),
        width: theme.spacing(7),
        [theme.breakpoints.up('sm')]: {
          width: theme.spacing(9),
        },
      }),
    },
  }),
);

function AdminRoutes() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const memberInfo = useSelector((state: RootState) => state.auth);

  const [nickname, setNickname] = useState(memberInfo.nickname);
  const [roleList, setRoleList] = useState(memberInfo.roleList);

  useEffect(() => {
    setNickname(memberInfo.nickname);
    setRoleList(memberInfo.roleList);
  }, [memberInfo]);

  const onClickLogout = async () => {
    await apiInstance.post("/auth/logout");
    dispatch(logout());

    navigate("login");
  }

  const [open, setOpen] = useState(true);
  const toggleDrawer = () => {
    setOpen(!open);
  };

  return (
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar position="absolute" open={open}>
        <Toolbar
          sx={{
            pr: '24px', // keep right padding when drawer closed
          }}
        >
          <IconButton
            edge="start"
            color="inherit"
            aria-label="open drawer"
            onClick={toggleDrawer}
            sx={{
              marginRight: '36px',
              ...(open && { display: 'none' }),
            }}
          >
            <MenuIcon />
          </IconButton>
          <Typography
            component="h1"
            variant="h6"
            color="inherit"
            noWrap
            sx={{ flexGrow: 1 }}
          >
            JWebCrawler 관리자 시스템
          </Typography>
          {roleList.includes("ROLE_ADMIN") && 
          <>
          <Typography variant="h6" color="inherit" sx={{ my: 1, mx: 1.5 }}>
            {nickname} 님
          </Typography>
          <Button variant="contained" color="error" onClick={onClickLogout} sx={{ my: 1, mx: 1.5 }}>
            Logout
          </Button>
          </>
          }
        </Toolbar>
      </AppBar>
      <Drawer variant="permanent" open={open}>
        <Toolbar
          sx={{
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'flex-end',
            px: [1],
          }}
        >
          <IconButton onClick={toggleDrawer}>
            <ChevronLeftIcon />
          </IconButton>
        </Toolbar>
        <Divider />
        <List component="nav">
          {mainListItems}
          <Divider sx={{ my: 1 }} />
        </List>
      </Drawer>
      <Box
        component="main"
        sx={{
          backgroundColor: (theme) =>
            theme.palette.mode === 'light'
              ? theme.palette.grey[100]
              : theme.palette.grey[900],
          flexGrow: 1,
          height: '100vh',
          overflow: 'auto',
        }}
      >
        <Toolbar />
        <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
          <Grid container spacing={3}>
            <Grid item xs={12}>
              <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
              <Routes>
                <Route path="/login" element={<AdminSignInForm/>} />
                {roleList.includes("ROLE_ADMIN") ? 
                <>
                <Route path="/" element={<AdminMain/>} />
                <Route path="/member" element={<MemberList/>} />
                <Route path="/post" element={<PostList/>} />
                <Route path="/post/:postId" element={<PostView/>} />
                <Route path="/post/:postId/edit" element={<UpdatePostForm/>} />
                <Route path="/post/new" element={<CreatePostForm />} />
                <Route path="/crawl" element={<CrawlLogList/>} />
                <Route path = "/*" element={<WrongPage/>} />
                </>
                :
                <Route
                  path = "/*"
                  element={<Navigate replace to="login"/>}
                />
                }
              </Routes>
              </Paper>
            </Grid>
          </Grid>
          <Box sx={{ mt: 4 }}>
            <Footer />
          </Box>
        </Container>
      </Box>
    </Box>
  );
}

export default AdminRoutes;