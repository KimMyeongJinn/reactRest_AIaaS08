import React, { useEffect, useState } from "react";
import { SERVER_URL } from "./constants";
import { DataGrid } from "@mui/x-data-grid";
import { Snackbar } from "@mui/material";
import AddCar from "./AddCar";

function CarList() {
  const [cars, setCars] = useState([]); // car목록을 서버로부터 가져와서 저장
  const [open, setOpen] = useState(false); // 알람 메시지 상태

  // Application이 시작하면 서버에 1번 요청
  useEffect(() => {
    fetchCars();
  }, []);

  // 서버에 car목록 요청 함수
  const fetchCars = () => {
    fetch(SERVER_URL + "api/cars")
      .then((response) => response.json())
      .then((data) => setCars(data._embedded.cars))
      .catch((err) => console.log(err));
  };

  // 삭제 후 갱신된 목록을 서버에 다시 요청
  const onDelClick = (url) => {
    if (window.confirm("Are you sure to delete?")) {
      fetch(url, { method: "DELETE" })
        .then((response) => {
          if (response.ok) {
            fetchCars();
            setOpen(true);
          } else {
            alert("Something went wrong");
          }
        })
        .catch((err) => console.error(err));
    }
  };

  // DataGrid의 헤더에서 사용할 명칭 정보
  const columns = [
    // field가 json객체의 필드명칭과 동일해야 한다.
    { field: "brand", headerName: "Brand", width: 200 },
    { field: "model", headerName: "Model", width: 200 },
    { field: "color", headerName: "Color", width: 200 },
    { field: "year", headerName: "Year", width: 150 },
    { field: "price", headerName: "Price", width: 150 },
    {
      field: "_links.self.href",
      headerName: "",
      sortable: false,
      filterable: false,
      renderCell: (row) => {
        // row.id === row._links.self.href
        return <button onClick={() => onDelClick(row.id)}>Delete</button>;
      },
    },
  ];

  return (
    <>
      <AddCar />
      <div style={{ height: 500, width: "100%" }}>
        {/* getRowId() == row.id */}
        <DataGrid
          columns={columns}
          rows={cars}
          getRowId={(row) => row._links.self.href}
        />
        <Snackbar
          anchorOrigin={{ vertical: "top", horizontal: "center" }}
          open={open}
          autoHideDuration={2000}
          onClose={() => setOpen(false)}
          message="Deleted Car"
        />
      </div>
    </>
  );
}

export default CarList;
