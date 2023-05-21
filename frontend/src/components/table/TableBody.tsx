import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableRow from '@mui/material/TableRow';
import { Member, Post, CrawlLog } from '../../type/apiEntity';
import { ISOtoKST } from '../../utils/convertUtils';

interface memberTableProps {
  members: Member[];
}

export function MemberTableBody(props: memberTableProps) {
  return (
    <TableBody>
      {props.members.map((member) => 
        <TableRow key={member.id}>
          <TableCell>{member.id}</TableCell>
          <TableCell>{member.email}</TableCell>
          <TableCell>{member.nickname}</TableCell>
          <TableCell>{member.gender}</TableCell>
          <TableCell>{member.purpose}</TableCell>
        </TableRow>
      )}
    </TableBody>
  );
}

interface postTableProps {
  posts: Post[];
}
  
export function PostTableBody(props: postTableProps) {
  return (
    <TableBody>
      {props.posts.map((post) => 
        <TableRow key={post.id}>
          <TableCell>{post.id}</TableCell>
          <TableCell sx={{wordBreak:"break-all"}}>{post.title}</TableCell>
          <TableCell>{post.member.nickname}</TableCell>
          <TableCell>{post.commentList.length}</TableCell>
        </TableRow>
      )}
    </TableBody>
  );
}

interface crawlLogTableProps {
  crawlLogs: CrawlLog[];
}
  
export function CrawlLogTableBody(props: crawlLogTableProps) {
  return (
    <TableBody>
      {props.crawlLogs.map((crawlLog) => 
        <TableRow key={crawlLog.id}>
          <TableCell>{crawlLog.id}</TableCell>
          <TableCell sx={{wordBreak:"break-all"}}>{crawlLog.url}</TableCell>
          <TableCell>{ISOtoKST(crawlLog.createdAt)}</TableCell>
        </TableRow>
      )}
    </TableBody>
  );
}