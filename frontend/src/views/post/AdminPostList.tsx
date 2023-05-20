import { useState, useEffect } from 'react';
import { apiInstance } from '../../network/axiosInstance';
import TableList from '../../components/table/TableList';
import { Post } from '../../type/apiEntity';
import { PostTableBody } from '../../components/table/TableBody';

const postHead = [
  "id", 
  "제목", 
  "닉네임", 
  "댓글수", 
];

function AdminPostList() {
  const [postList, setPostList] = useState<Post[]>([]);

  useEffect(() => {
    // 멤버 목록 조회
    apiInstance.get("/posts")
    .then((res) => {
      setPostList([...res.data.content]);
    });
  }, []);

  return (
    <TableList title={"게시글 목록"} tableHeads={postHead} tableBody={<PostTableBody posts={postList} />} />
  );
}

export default AdminPostList;