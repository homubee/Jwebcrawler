import { EntitySearch } from "../type/common";

export function ISOtoKST(date: Date) {
  return date.toLocaleString().replace("T", " ").substring(0, 19);
}

export function SearchToQuery(search: EntitySearch) {
  var ret = "";
  for (const [key, value] of Object.entries(search)) {
    ret += key + "=" + value + "&";
  }
  return ret.substring(0, ret.length-1);
}