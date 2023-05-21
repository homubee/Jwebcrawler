export interface EntitySearch {
  [name: string]: string;
}

export interface MemberInfo {
  id: number;
  roleList: string[];
  email: string;
  nickname: string;
  accessToken: string;
}