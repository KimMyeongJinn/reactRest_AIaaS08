import React, { useEffect, useState } from "react";
import { SERVER_URL } from "./constants";
import { DataGrid } from "@mui/x-data-grid";
import { Snackbar } from "@mui/material";
import AddCar from "./AddCar";
import EditCar from "./EditCar";

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

  // <AddList/>에 전달하여 새로운 car 정보를 입력받아 서버로 전송하여 등록 요청
  const addCar = (car) => {
    fetch(SERVER_URL + "api/cars", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(car), // js객체 -> json문자열 변환
    })
      .then((response) => {
        if (response.ok) fetchCars();
        else alert("Something went wrong");
      })
      .catch((err) => console.error(err));
  };

  // 서버로 수정 요청
  // 특정 항목 수정 시 => PATCH
  // 전체 항목 수정 시 => PUT
  const updateCar = (car, link) => {
    fetch(link, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(car),
    })
      .then((response) => {
        if (response.ok) fetchCars();
        else alert("Something went wrong");
      })
      .catch((err) => console.error(err));
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
      field: "_links.car.href",
      headerName: "",
      sortable: false,
      filterable: false,
      renderCell: (row) => <EditCar data={row} updateCar={updateCar} />,
    },
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
      <AddCar addCar={addCar} />
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
