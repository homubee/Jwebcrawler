import { Route, Routes } from 'react-router-dom';
import UserSignInForm from '../views/auth/UserSignInForm';
import SignUp from '../views/auth/SignUpForm';
import WrongPage from '../views/WrongPage';
import UserMain from '../views/UserMain';
import Header from './Header';
import Footer from './Footer';

const UserRoutes = () => {
  return (
    <div>
      <Header />
      <Routes>
        <Route path="/" element={<UserMain/>} />
        <Route path="/login" element={<UserSignInForm/>} />
        <Route path="/register" element={<SignUp/>} />
        <Route path = "/*" element={<WrongPage/>} />
      </Routes>
      <Footer />
    </div>
  )
};

export default UserRoutes;