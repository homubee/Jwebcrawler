import { useState, useEffect } from 'react';
import { apiInstance } from '../../network/axiosInstance';
import TableList from '../../components/table/TableList';
import { Member } from '../../type/apiEntity';
import { MemberTableBody } from '../../components/table/TableBody';
import { SearchToQuery } from '../../utils/convertUtils';
import { EntitySearch } from '../../type/common';
import { MemberSearchForm } from '../../components/SearchForm';

const memberHead = [
  "id", 
  "이메일", 
  "닉네임", 
  "성별", 
  "가입목적", 
];

function MemberList() {
  const [memberList, setMemberList] = useState<Member[]>([]);
  const [memberSearch, setMemberSearch] = useState<EntitySearch>({
    email: "",
    nickname: ""
  });

  useEffect(() => {
    // 회원 목록 조회
    apiInstance.get("/members" + "?" + SearchToQuery(memberSearch))
    .then((res) => {
      setMemberList([...res.data.content]);
    });
  }, [memberSearch]);

  return (
    <TableList title={"회원 목록"} searchForm={<MemberSearchForm setSearch={setMemberSearch} />} tableHeads={memberHead} tableBody={<MemberTableBody members={memberList} />} />
  );
}

export default MemberList;