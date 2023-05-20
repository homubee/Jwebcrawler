import { Fragment, ReactElement } from 'react';
import Table from '@mui/material/Table';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Typography from '@mui/material/Typography';

interface tableListProps {
  title: string;
  tableHeads: string[];
  tableBody: ReactElement;
}

function TableList(props: tableListProps) {
  return (
    <Fragment>
      <Typography component="h2" variant="h6" color="primary" gutterBottom>
      {props.title}
      </Typography>
      <Table size="small">
        <TableHead>
          <TableRow>
            {props.tableHeads.map((headName: string, index: number) => 
              <TableCell key={index} style={{fontWeight: "bold"}}>{headName}</TableCell>
            )}
          </TableRow>
        </TableHead>
        {props.tableBody && props.tableBody}
      </Table>
    </Fragment>
  );
}

export default TableList;