export interface Member {
  id?: number;
  email: string;
  nickname: string;
  gender: string;
  purpose: string;
}

export interface Post {
  id?: number;
  member: Member;
  commentList: Comment[];
  title: string;
  content: string;
  viewCnt: number;
}

export interface Comment {
  id?: number;
  member: Member;
  content: string;
}

export interface CrawlLog {
  id?: number;
  url: String;
  createdAt: Date;
}