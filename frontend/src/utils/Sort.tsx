import React, { useState } from "react";

export const getSort = (value: string) => {
  switch (value) {
    case "Old to new":
      return "createdDateTime,asc";
    case "New to old":
      return "createdDateTime,desc";
    case "Price hight to low":
      return "price,desc";
    case "Price low to High":
      return "price,asc";
    default:
      return "createdDateTime,desc";
  }

}