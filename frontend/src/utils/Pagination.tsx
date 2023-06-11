import React, { useState } from "react";

export function usePagination(data: any[], itemsPerPage) {
  const [page, setPage] = useState(1);
  const count = Math.ceil(data.length / itemsPerPage);

  const begin = (page - 1) * itemsPerPage;
  const end = begin + itemsPerPage;

  const paginationData = data.slice(begin, end);

  const onChange = (e, page) => {
    setPage(page);
  };

  return { paginationData, onChange, page, count };
}
