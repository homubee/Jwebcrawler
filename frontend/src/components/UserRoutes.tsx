import { Route, Routes } from 'react-router-dom';
import Header from './Header';
import Footer from './Footer';
import UserSignInForm from '../views/auth/UserSignInForm';
import SignUp from '../views/auth/SignUpForm';
import WrongPage from '../views/WrongPage';
import UserMain from '../views/UserMain';
import CrawlForm from '../views/crawl/CrawlForm';
import { Box } from '@mui/material';
import CrawlResult from '../views/crawl/CrawlResult';
import PostList from '../views/post/PostList';
import CreatePostForm from '../views/post/CreatePostForm';
import PostView from '../views/post/PostView';
import UpdatePostForm from '../views/post/UpdatePostForm';
import Help from '../views/Help';

const UserRoutes = () => {
  return (
    <Box sx={{display:"flex", flexDirection:"column", maxWidth:1200, m:"auto"}}>
      <Header />
      <Box sx={{m:3}}>
        <Routes>
          <Route path="/" element={<UserMain/>} />
          <Route path="/login" element={<UserSignInForm/>} />
          <Route path="/register" element={<SignUp/>} />
          <Route path="/help" element={<Help/>} />
          <Route path="/crawl" element={<CrawlForm/>} />
          <Route path="/crawl/result" element={<CrawlResult/>} />
          <Route path="/post" element={<PostList/>} />
          <Route path="/post/:postId" element={<PostView/>} />
          <Route path="/post/:postId/edit" element={<UpdatePostForm/>} />
          <Route path="/post/new" element={<CreatePostForm/>} />
          <Route path = "/*" element={<WrongPage/>} />
        </Routes>
      </Box>
      <Footer />
    </Box>
  )
};

export default UserRoutes;