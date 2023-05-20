import { useState, useEffect } from 'react';
import { apiInstance } from '../../network/axiosInstance';
import TableList from '../../components/table/TableList';
import { Member } from '../../type/apiEntity';
import { MemberTableBody } from '../../components/table/TableBody';

const memberHead = [
  "id", 
  "이메일", 
  "닉네임", 
  "성별", 
  "가입목적", 
];

function MemberList() {
  const [memberList, setMemberList] = useState<Member[]>([]);

  useEffect(() => {
    // 멤버 목록 조회
    apiInstance.get("/members")
    .then((res) => {
      setMemberList([...res.data.content]);
    });
  }, []);

  return (
    <TableList title={"회원 목록"} tableHeads={memberHead} tableBody={<MemberTableBody members={memberList} />} />
  );
}

export default MemberList;