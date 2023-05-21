import { useState } from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import Button from '@mui/material/Button';
import { Grid, TextField } from '@mui/material';
import { EntitySearch } from '../type/common';

interface memberSearchProps {
  setSearch: React.Dispatch<React.SetStateAction<EntitySearch>>
}

export function MemberSearchForm(props: memberSearchProps) {
  const [selectType, setSelectType] = useState("");
  const [searchKeyword, setSearchKeyword] = useState("");

  const onChangeSearch = (e: SelectChangeEvent) => {
    setSelectType(e.target.value);
  };

  const onChangeKeyword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchKeyword(e.target.value);
  }

  const onClickSearch = () => {
    props.setSearch({
      [selectType] : searchKeyword
    });
  }

  return (
    <Box sx={{ mt:1, mb:1 }}>
      <Grid container spacing={3} alignItems="center">
        <Grid item xs={2}>
          <FormControl fullWidth>
            <InputLabel id="searchType">검색조건</InputLabel>
            <Select fullWidth
              labelId="searchType"
              id="searchType"
              value={selectType}
              label="searchType"
              onChange={onChangeSearch}
            >
              <MenuItem value={"email"}>이메일</MenuItem>
              <MenuItem value={"nickname"}>닉네임</MenuItem>
            </Select>
          </FormControl>
        </Grid>
        <Grid item xs={4}>
          <TextField fullWidth
            value={searchKeyword}
            onChange={onChangeKeyword}
          />
        </Grid>
        <Grid item xs={4}>
          
            <Button variant="contained" onClick={onClickSearch}>
              <Box sx={{p: 1}}>
                검색
              </Box>
            </Button>
        </Grid>
      </Grid>
    </Box>
  );
}

interface postSearchProps {
  setSearch: React.Dispatch<React.SetStateAction<EntitySearch>>
}

export function PostSearchForm(props: postSearchProps) {
  const [selectType, setSelectType] = useState("");
  const [searchKeyword, setSearchKeyword] = useState("");

  const onChangeSearch = (e: SelectChangeEvent) => {
    setSelectType(e.target.value);
  };

  const onChangeKeyword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchKeyword(e.target.value);
  }

  const onClickSearch = () => {
    props.setSearch({
      [selectType] : searchKeyword
    });
  }

  return (
    <Box sx={{ mt:1, mb:1 }}>
      <Grid container spacing={3} alignItems="center">
        <Grid item xs={2}>
          <FormControl fullWidth>
            <InputLabel id="searchType">검색조건</InputLabel>
            <Select fullWidth
              labelId="searchType"
              id="searchType"
              value={selectType}
              label="searchType"
              onChange={onChangeSearch}
            >
              <MenuItem value={"title"}>제목</MenuItem>
              <MenuItem value={"content"}>내용</MenuItem>
              <MenuItem value={"email"}>이메일</MenuItem>
              <MenuItem value={"nickname"}>닉네임</MenuItem>
            </Select>
          </FormControl>
        </Grid>
        <Grid item xs={4}>
          <TextField fullWidth
            value={searchKeyword}
            onChange={onChangeKeyword}
          />
        </Grid>
        <Grid item xs={4}>
          
            <Button variant="contained" onClick={onClickSearch}>
              <Box sx={{p: 1}}>
                검색
              </Box>
            </Button>
        </Grid>
      </Grid>
    </Box>
  );
}