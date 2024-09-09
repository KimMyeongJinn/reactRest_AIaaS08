import React, { useEffect, useState } from "react";
import { SERVER_URL } from "./constants";
import { DataGrid } from "@mui/x-data-grid";

function CarList() {
  const [cars, setCars] = useState([]); // car목록을 서버로부터 가져와서 저장

  // Application이 시작하면 서버에 1번 요청
  useEffect(() => {
    fetch(SERVER_URL + "api/cars")
      .then((response) => response.json())
      .then((data) => setCars(data._embedded.cars))
      .catch((err) => console.log(err));
  }, []);

  // DataGrid의 헤더에서 사용할 명칭 정보
  const columns = [
    // field가 json객체의 필드명칭과 동일해야 한다.
    { field: "brand", headerName: "Brand", width: 200 },
    { field: "model", headerName: "Model", width: 200 },
    { field: "color", headerName: "Color", width: 200 },
    { field: "year", headerName: "Year", width: 150 },
    { field: "price", headerName: "Price", width: 150 },
  ];

  return (
    <div style={{ height: 500, width: "100%" }}>
      <DataGrid
        columns={columns}
        rows={cars}
        getRowId={(row) => row._links.self.href}
      />
    </div>
  );
}

export default CarList;
