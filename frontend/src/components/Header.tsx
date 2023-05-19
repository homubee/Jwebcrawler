import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';

export default function Header() {
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
        <nav>
          <Link
            variant="button"
            color="text.primary"
            href="#"
            sx={{ my: 1, mx: 1.5 }}
          >
            크롤링
          </Link>
          <Link
            variant="button"
            color="text.primary"
            href="#"
            sx={{ my: 1, mx: 1.5 }}
          >
            게시판
          </Link>
          <Link
            variant="button"
            color="text.primary"
            href="#"
            sx={{ my: 1, mx: 1.5 }}
          >
            도움말
          </Link>
        </nav>
        <Button href="/login" variant="outlined" sx={{ my: 1, mx: 1.5 }}>
          Sign In
        </Button>
        <Button href="/register" variant="outlined" sx={{ my: 1, mx: 1.5 }}>
          Sign Up
        </Button>
      </Toolbar>
    </AppBar>
  );
}