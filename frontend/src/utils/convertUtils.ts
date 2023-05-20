export function ISOtoKST(date: Date) {
  return date.toLocaleString().replace("T", " ").substring(0, 19);
}