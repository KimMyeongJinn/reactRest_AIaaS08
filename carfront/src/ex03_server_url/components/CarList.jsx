import React, { useEffect, useState } from "react";
import { SERVER_URL } from "./constants";

function CarList() {
  const [cars, setCars] = useState([]); // car목록을 서버로부터 가져와서 저장

  // Application이 시작하면 서버에 1번 요청
  useEffect(() => {
    fetch(SERVER_URL + "api/cars")
      .then((response) => response.json())
      .then((data) => setCars(data._embedded.cars))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div>
      <table>
        <tbody>
          {cars.map((car, index) => {
            return (
              <tr key={index}>
                <td>{car.brand}</td>
                <td>{car.model}</td>
                <td>{car.color}</td>
                <td>{car.year}</td>
                <td>{car.price}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}

export default CarList;
