import { useState, useEffect } from 'react';
import { apiInstance } from '../../network/axiosInstance';
import TableList from '../../components/table/TableList';
import { Post } from '../../type/apiEntity';
import { PostTableBody } from '../../components/table/TableBody';
import { SearchToQuery } from '../../utils/convertUtils';
import { EntitySearch } from '../../type/common';
import { PostSearchForm } from '../../components/SearchForm';

const postHead = [
  "id", 
  "제목", 
  "닉네임", 
  "댓글수", 
  "조회수", 
];

function PostList() {
  const [postList, setPostList] = useState<Post[]>([]);
  const [postSearch, setPostSearch] = useState<EntitySearch>({
    title: "",
    content: "",
    email: "",
    nickname: ""
  });

  useEffect(() => {
    // 게시글 목록 조회
    apiInstance.get("/posts" + "?" + SearchToQuery(postSearch))
    .then((res) => {
      setPostList([...res.data.content]);
    });
  }, [postSearch]);

  return (
    <TableList title={"게시글 목록"} searchForm={<PostSearchForm setSearch={setPostSearch} />} tableHeads={postHead} tableBody={<PostTableBody posts={postList} />} />
  );
}

export default PostList;