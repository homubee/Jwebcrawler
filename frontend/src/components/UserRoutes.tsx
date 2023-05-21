import { Route, Routes } from 'react-router-dom';
import Header from './Header';
import Footer from './Footer';
import UserSignInForm from '../views/auth/UserSignInForm';
import SignUp from '../views/auth/SignUpForm';
import WrongPage from '../views/WrongPage';
import UserMain from '../views/UserMain';
import CrawlForm from '../views/crawl/CrawlForm';
import { Box } from '@mui/material';

const UserRoutes = () => {
  return (
    <div>
      <Header />
      <Box sx={{m:3}}>
        <Routes>
          <Route path="/" element={<UserMain/>} />
          <Route path="/login" element={<UserSignInForm/>} />
          <Route path="/register" element={<SignUp/>} />
          <Route path="/crawl" element={<CrawlForm/>} />
          <Route path = "/*" element={<WrongPage/>} />
        </Routes>
      </Box>
      <Footer />
    </div>
  )
};

export default UserRoutes;