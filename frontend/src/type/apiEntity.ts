export interface Member {
  id?: number;
  roleList: string[];
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
  url: string;
  createdAt: Date;
}

export interface CrawlBaseRequest {
  memberId: number;
  url: string;
  rootId?: string;
  rootTag?: string;
  rootClass?: string;
  rootAttr?: string;
}

export interface CrawlBodyRequestDTO extends CrawlBaseRequest {
  bodyType: string;
}

export interface CrawlListRequestDTO extends CrawlBaseRequest {
  listId?: string;
  listTag?: string;
  listClass?: string;
  listAttr?: string;
  targetTag?: string;
}

export interface MemberLoginRequestDTO {
  email: string;
  password: string;
}

export interface MemberRegisterRequestDTO {
  email: string;
  password: string;
  nickname: string;
  gender: string;
  purpose: string;
}

export interface PostRequestDTO {
  memberId: number;
  title: string;
  content: string;
}

export interface CommentRequestDTO {
  memberId: number;
  postId: number;
  content: string;
}