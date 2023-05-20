import { useState, useEffect } from 'react';
import { apiInstance } from '../../network/axiosInstance';
import TableList from '../../components/table/TableList';
import { CrawlLog } from '../../type/apiEntity';
import { CrawlLogTableBody } from '../../components/table/TableBody';

const crawlLogHead = [
  "id", 
  "URL", 
  "요청일", 
];

function CrawlLogList() {
  const [crawlLogList, setCrawlLogList] = useState<CrawlLog[]>([]);

  useEffect(() => {
    // 멤버 목록 조회
    apiInstance.get("/crawl")
    .then((res) => {
      setCrawlLogList([...res.data]);
    });
  }, []);

  return (
    <TableList title={"크롤링 이력"} tableHeads={crawlLogHead} tableBody={<CrawlLogTableBody crawlLogs={crawlLogList} />} />
  );
}

export default CrawlLogList;